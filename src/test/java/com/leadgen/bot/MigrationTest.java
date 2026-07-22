package com.leadgen.bot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LeadGenApplication.class)
@ActiveProfiles("test")
public class MigrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoadsAndFlywayMigrationsApplied() {
        // Check dialogs table existence and columns
        List<Map<String, Object>> columnsDialogs = jdbcTemplate.queryForList(
                "SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'DIALOGS'"
        );
        assertFalse(columnsDialogs.isEmpty(), "Table DIALOGS should exist");

        boolean hasTelegramAccountId = false;
        boolean hasTelegramChatId = false;
        boolean hasStatus = false;
        for (Map<String, Object> col : columnsDialogs) {
            String colName = ((String) col.get("COLUMN_NAME")).toUpperCase();
            if (colName.equals("TELEGRAM_ACCOUNT_ID")) {
                hasTelegramAccountId = true;
            } else if (colName.equals("TELEGRAM_CHAT_ID")) {
                hasTelegramChatId = true;
            } else if (colName.equals("STATUS")) {
                hasStatus = true;
            }
        }
        assertTrue(hasTelegramAccountId, "DIALOGS should have telegram_account_id");
        assertTrue(hasTelegramChatId, "DIALOGS should have telegram_chat_id");
        assertTrue(hasStatus, "DIALOGS should have status");

        // Check messages table existence and foreign keys
        List<Map<String, Object>> columnsMessages = jdbcTemplate.queryForList(
                "SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'MESSAGES'"
        );
        assertFalse(columnsMessages.isEmpty(), "Table MESSAGES should exist");

        boolean hasDialogId = false;
        boolean hasSenderType = false;
        boolean hasText = false;
        for (Map<String, Object> col : columnsMessages) {
            String colName = ((String) col.get("COLUMN_NAME")).toUpperCase();
            if (colName.equals("DIALOG_ID")) {
                hasDialogId = true;
            } else if (colName.equals("SENDER_TYPE")) {
                hasSenderType = true;
            } else if (colName.equals("TEXT")) {
                hasText = true;
            }
        }
        assertTrue(hasDialogId, "MESSAGES should have dialog_id");
        assertTrue(hasSenderType, "MESSAGES should have sender_type");
        assertTrue(hasText, "MESSAGES should have text");

        // Let's test standard inserts & relation checks
        jdbcTemplate.execute("INSERT INTO dialogs (telegram_account_id, telegram_chat_id, status) VALUES (101, 202, 'AI_ACTIVE')");
        Long dialogId = jdbcTemplate.queryForObject("SELECT id FROM dialogs WHERE telegram_account_id = 101", Long.class);
        assertNotNull(dialogId);

        jdbcTemplate.execute("INSERT INTO messages (dialog_id, sender_type, text) VALUES (" + dialogId + ", 'AI', 'Hello Lead!')");
        String text = jdbcTemplate.queryForObject("SELECT text FROM messages WHERE dialog_id = " + dialogId, String.class);
        assertEquals("Hello Lead!", text);

        // Test constraint: invalid status
        assertThrows(Exception.class, () -> {
            jdbcTemplate.execute("INSERT INTO dialogs (telegram_account_id, telegram_chat_id, status) VALUES (101, 203, 'INVALID_STATUS')");
        }, "CHECK constraint on dialog status should prevent invalid values");

        // Test constraint: invalid sender_type
        assertThrows(Exception.class, () -> {
            jdbcTemplate.execute("INSERT INTO messages (dialog_id, sender_type, text) VALUES (" + dialogId + ", 'INVALID_SENDER', 'Oups')");
        }, "CHECK constraint on message sender_type should prevent invalid values");
    }
}
