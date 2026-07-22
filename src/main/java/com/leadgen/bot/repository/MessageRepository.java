package com.leadgen.bot.repository;

import com.leadgen.bot.model.Message;
import com.leadgen.bot.model.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    // Find the last message of a thread
    @Query("SELECT m FROM Message m WHERE m.thread = :thread ORDER BY m.timestamp DESC, m.id DESC")
    Page<Message> findLatestMessage(@Param("thread") Thread thread, Pageable pageable);

    default Optional<Message> findLatestMessage(Thread thread) {
        Page<Message> page = findLatestMessage(thread, org.springframework.data.domain.PageRequest.of(0, 1));
        return page.getContent().stream().findFirst();
    }

    // Find messages before a specific message ID cursor (UUID based) or simply with pagination
    @Query("SELECT m FROM Message m WHERE m.thread.id = :threadId " +
           "AND (:beforeId IS NULL OR m.timestamp < (SELECT cursorMsg.timestamp FROM Message cursorMsg WHERE cursorMsg.id = :beforeId)) " +
           "ORDER BY m.timestamp DESC, m.id DESC")
    Page<Message> findMessagesBefore(
            @Param("threadId") UUID threadId,
            @Param("beforeId") UUID beforeId,
            Pageable pageable
    );
}
