package com.leadgen.bot.controller;

import com.leadgen.bot.dto.*;
import com.leadgen.bot.service.InboxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inbox/threads")
public class InboxController {

    private final InboxService inboxService;

    public InboxController(InboxService inboxService) {
        this.inboxService = inboxService;
    }

    @GetMapping
    public ResponseEntity<ThreadsResponse> getThreads(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    ) {
        ThreadsResponse response = inboxService.getThreads(status, tag, search, limit, offset);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{threadId}/messages")
    public ResponseEntity<MessagesResponse> getMessageHistory(
            @PathVariable("threadId") UUID threadId,
            @RequestParam(value = "limit", required = false, defaultValue = "50") Integer limit,
            @RequestParam(value = "before", required = false) UUID before
    ) {
        MessagesResponse response = inboxService.getMessageHistory(threadId, limit, before);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{threadId}/messages")
    public ResponseEntity<MessageDto> sendOperatorMessage(
            @PathVariable("threadId") UUID threadId,
            @RequestBody OperatorMessageRequest request
    ) {
        MessageDto response = inboxService.sendOperatorMessage(threadId, request.getText());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{threadId}/override")
    public ResponseEntity<AiOverrideResponse> overrideHandlerMode(
            @PathVariable("threadId") UUID threadId,
            @RequestBody AiOverrideRequest request
    ) {
        AiOverrideResponse response = inboxService.overrideHandlerMode(threadId, request);
        return ResponseEntity.ok(response);
    }
}
