package com.leadgen.bot.repository;

import com.leadgen.bot.model.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, UUID> {

    @Query("SELECT t FROM Thread t JOIN FETCH t.contact c " +
           "WHERE (:status IS NULL OR t.status = :status) " +
           "AND (:tag IS NULL OR t.tag = :tag) " +
           "AND (:search IS NULL OR " +
           "     LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "     LOWER(c.telegramUsername) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "     LOWER(c.phoneNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "     EXISTS (SELECT m FROM Message m WHERE m.thread = t AND LOWER(m.text) LIKE LOWER(CONCAT('%', :search, '%'))))")
    Page<Thread> findThreadsWithFilters(
            @Param("status") String status,
            @Param("tag") String tag,
            @Param("search") String search,
            Pageable pageable
    );
}
