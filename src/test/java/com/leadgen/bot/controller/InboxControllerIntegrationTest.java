package com.leadgen.bot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leadgen.bot.dto.AiOverrideRequest;
import com.leadgen.bot.dto.OperatorMessageRequest;
import com.leadgen.bot.model.Contact;
import com.leadgen.bot.model.Message;
import com.leadgen.bot.model.Thread;
import com.leadgen.bot.repository.ContactRepository;
import com.leadgen.bot.repository.MessageRepository;
import com.leadgen.bot.repository.ThreadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class InboxControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Contact contact1;
    private Contact contact2;
    private Thread thread1;
    private Thread thread2;

    @BeforeEach
    public void setup() {
        // Clear database before tests to prevent interference (if any remains)
        messageRepository.deleteAll();
        threadRepository.deleteAll();
        contactRepository.deleteAll();

        // Save contacts
        contact1 = new Contact(UUID.randomUUID(), "Marcus Sterling", "marcus_sterling_exec", "+15550199281", "https://example.com/avatar1.png");
        contact2 = new Contact(UUID.randomUUID(), "Alice Vance", "alice_v", "+15550199282", "https://example.com/avatar2.png");
        contactRepository.save(contact1);
        contactRepository.save(contact2);

        // Save threads
        thread1 = new Thread(UUID.randomUUID(), contact1, "ai_assistant", "subscription_issue", 2);
        thread1.setCreatedAt(Instant.now().minusSeconds(3600));
        thread1.setUpdatedAt(Instant.now().minusSeconds(1800));

        thread2 = new Thread(UUID.randomUUID(), contact2, "human_attention", "technical_support", 0);
        thread2.setCreatedAt(Instant.now().minusSeconds(7200));
        thread2.setUpdatedAt(Instant.now().minusSeconds(3600));

        threadRepository.save(thread1);
        threadRepository.save(thread2);

        // Save messages for thread1
        Message m1 = new Message(UUID.randomUUID(), thread1, "contact", "Marcus Sterling", "This is unacceptable, I need a refund immediately.", Instant.now().minusSeconds(2000));
        Message m2 = new Message(UUID.randomUUID(), thread1, "ai_assistant", "Olivia AI", "Hello Marcus, I understand. Let me help you with your subscription issue.", Instant.now().minusSeconds(1900));
        messageRepository.save(m1);
        messageRepository.save(m2);

        // Save messages for thread2
        Message m3 = new Message(UUID.randomUUID(), thread2, "contact", "Alice Vance", "My system is completely offline.", Instant.now().minusSeconds(3800));
        messageRepository.save(m3);
    }

    @Test
    public void testGetThreadsNoFilters() throws Exception {
        mockMvc.perform(get("/api/v1/inbox/threads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount", is(2)))
                .andExpect(jsonPath("$.threads", hasSize(2)))
                .andExpect(jsonPath("$.threads[0].contact.name", anyOf(is("Marcus Sterling"), is("Alice Vance"))));
    }

    @Test
    public void testGetThreadsFilterByStatus() throws Exception {
        mockMvc.perform(get("/api/v1/inbox/threads").param("status", "ai_assistant"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount", is(1)))
                .andExpect(jsonPath("$.threads[0].contact.name", is("Marcus Sterling")));
    }

    @Test
    public void testGetThreadsFilterByTag() throws Exception {
        mockMvc.perform(get("/api/v1/inbox/threads").param("tag", "technical_support"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount", is(1)))
                .andExpect(jsonPath("$.threads[0].contact.name", is("Alice Vance")));
    }

    @Test
    public void testGetThreadsFilterBySearchOnContactName() throws Exception {
        mockMvc.perform(get("/api/v1/inbox/threads").param("search", "Alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount", is(1)))
                .andExpect(jsonPath("$.threads[0].contact.name", is("Alice Vance")));
    }

    @Test
    public void testGetThreadsFilterBySearchOnMessageText() throws Exception {
        mockMvc.perform(get("/api/v1/inbox/threads").param("search", "refund"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount", is(1)))
                .andExpect(jsonPath("$.threads[0].contact.name", is("Marcus Sterling")));
    }

    @Test
    public void testGetMessageHistory() throws Exception {
        mockMvc.perform(get("/api/v1/inbox/threads/" + thread1.getId() + "/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messages", hasSize(2)))
                .andExpect(jsonPath("$.messages[0].text", is("Hello Marcus, I understand. Let me help you with your subscription issue."))); // Ordered DESC by default
    }

    @Test
    public void testSendOperatorMessageDeactivatesAi() throws Exception {
        OperatorMessageRequest request = new OperatorMessageRequest("Don't worry Marcus, I am here to help you manually now.");

        mockMvc.perform(post("/api/v1/inbox/threads/" + thread1.getId() + "/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.senderType", is("human_operator")))
                .andExpect(jsonPath("$.text", is("Don't worry Marcus, I am here to help you manually now.")));

        // Verify thread status was changed to human_attention
        Thread updatedThread = threadRepository.findById(thread1.getId()).orElseThrow();
        assertEquals("human_attention", updatedThread.getStatus());
    }

    @Test
    public void testSendOperatorMessageEmptyTextReturnsBadRequest() throws Exception {
        OperatorMessageRequest request = new OperatorMessageRequest("");

        mockMvc.perform(post("/api/v1/inbox/threads/" + thread1.getId() + "/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is("BAD_REQUEST")));
    }

    @Test
    public void testOverrideHandlerModeToAiAssistant() throws Exception {
        // thread1 is currently 'ai_assistant', let's override to 'human_attention'
        AiOverrideRequest request = new AiOverrideRequest("human_attention", "Operator taking over manually");

        mockMvc.perform(post("/api/v1/inbox/threads/" + thread1.getId() + "/override")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.previousStatus", is("ai_assistant")))
                .andExpect(jsonPath("$.currentStatus", is("human_attention")));

        // Verify database
        Thread updatedThread = threadRepository.findById(thread1.getId()).orElseThrow();
        assertEquals("human_attention", updatedThread.getStatus());
    }

    @Test
    public void testThreadNotFoundReturnsErrorResponse() throws Exception {
        UUID randomId = UUID.randomUUID();
        mockMvc.perform(get("/api/v1/inbox/threads/" + randomId + "/messages"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode", is("RESOURCE_NOT_FOUND")))
                .andExpect(jsonPath("$.message", containsString(randomId.toString())));
    }
}
