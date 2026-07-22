package com.leadgen.bot.service;

import com.leadgen.bot.dto.*;
import com.leadgen.bot.exception.BadRequestException;
import com.leadgen.bot.exception.ResourceNotFoundException;
import com.leadgen.bot.model.Contact;
import com.leadgen.bot.model.Message;
import com.leadgen.bot.model.Thread;
import com.leadgen.bot.repository.ContactRepository;
import com.leadgen.bot.repository.MessageRepository;
import com.leadgen.bot.repository.ThreadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InboxService {

    private final ThreadRepository threadRepository;
    private final MessageRepository messageRepository;
    private final ContactRepository contactRepository;

    public InboxService(ThreadRepository threadRepository, MessageRepository messageRepository, ContactRepository contactRepository) {
        this.threadRepository = threadRepository;
        this.messageRepository = messageRepository;
        this.contactRepository = contactRepository;
    }

    @Transactional(readOnly = true)
    public ThreadsResponse getThreads(String status, String tag, String search, Integer limit, Integer offset) {
        int pageNumber = offset / limit;
        Pageable pageable = PageRequest.of(pageNumber, limit, Sort.by(Sort.Direction.DESC, "updatedAt"));

        Page<Thread> threadsPage = threadRepository.findThreadsWithFilters(status, tag, search, pageable);

        List<ThreadDto> threadDtos = threadsPage.getContent().stream()
                .map(this::mapToThreadDto)
                .collect(Collectors.toList());

        return new ThreadsResponse(threadDtos, threadsPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    public MessagesResponse getMessageHistory(UUID threadId, Integer limit, UUID beforeId) {
        if (!threadRepository.existsById(threadId)) {
            throw new ResourceNotFoundException("Thread with UUID " + threadId + " was not found.", "RESOURCE_NOT_FOUND");
        }

        Pageable pageable = PageRequest.of(0, limit);
        Page<Message> messagesPage = messageRepository.findMessagesBefore(threadId, beforeId, pageable);

        List<MessageDto> messageDtos = messagesPage.getContent().stream()
                .map(this::mapToMessageDto)
                .collect(Collectors.toList());

        return new MessagesResponse(messageDtos);
    }

    @Transactional
    public MessageDto sendOperatorMessage(UUID threadId, String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new BadRequestException("Message text cannot be empty.", "BAD_REQUEST");
        }

        Thread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ResourceNotFoundException("Thread with UUID " + threadId + " was not found.", "RESOURCE_NOT_FOUND"));

        // Disable AI auto-reply (AI assistant) and set to human attention
        thread.setStatus("human_attention");
        thread.setUpdatedAt(Instant.now());
        threadRepository.save(thread);

        // Save the message
        Message message = new Message();
        message.setId(UUID.randomUUID());
        message.setThread(thread);
        message.setSenderType("human_operator");
        message.setSenderName("Operator"); // Standard Operator name or context-specific
        message.setText(text);
        message.setTimestamp(Instant.now());

        messageRepository.save(message);

        // In a real application, here we would publish message to TG queue or call a gateway.
        // We log it and guarantee persistence.

        return mapToMessageDto(message);
    }

    @Transactional
    public AiOverrideResponse overrideHandlerMode(UUID threadId, AiOverrideRequest request) {
        String targetMode = request.getHandlerMode();
        if (targetMode == null || (!targetMode.equals("human_attention") && !targetMode.equals("ai_assistant") && !targetMode.equals("pending"))) {
            throw new BadRequestException("Invalid handler mode specified.", "BAD_REQUEST");
        }

        Thread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ResourceNotFoundException("Thread with UUID " + threadId + " was not found.", "RESOURCE_NOT_FOUND"));

        String previousStatus = thread.getStatus();
        thread.setStatus(targetMode);
        thread.setUpdatedAt(Instant.now());
        threadRepository.save(thread);

        return new AiOverrideResponse(
                thread.getId(),
                previousStatus,
                thread.getStatus(),
                DateTimeFormatter.ISO_INSTANT.format(thread.getUpdatedAt())
        );
    }

    private ThreadDto mapToThreadDto(Thread thread) {
        Contact contact = thread.getContact();
        ContactDto contactDto = new ContactDto(
                contact.getId(),
                contact.getName(),
                contact.getTelegramUsername(),
                contact.getPhoneNumber(),
                contact.getAvatarUrl(),
                DateTimeFormatter.ISO_INSTANT.format(contact.getCreatedAt())
        );

        MessageDto lastMessageDto = messageRepository.findLatestMessage(thread)
                .map(this::mapToMessageDto)
                .orElse(null);

        return new ThreadDto(
                thread.getId(),
                contactDto,
                thread.getStatus(),
                thread.getTag(),
                thread.getUnreadCount(),
                lastMessageDto,
                DateTimeFormatter.ISO_INSTANT.format(thread.getCreatedAt()),
                DateTimeFormatter.ISO_INSTANT.format(thread.getUpdatedAt())
        );
    }

    private MessageDto mapToMessageDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getThread().getId(),
                message.getSenderType(),
                message.getSenderName(),
                message.getText(),
                DateTimeFormatter.ISO_INSTANT.format(message.getTimestamp())
        );
    }
}
