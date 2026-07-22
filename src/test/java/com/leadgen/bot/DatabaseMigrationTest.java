package com.leadgen.bot;

import com.leadgen.bot.model.Proxy;
import com.leadgen.bot.model.TgAccount;
import com.leadgen.bot.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LeadGenApplication.class)
@Transactional
public class DatabaseMigrationTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testSchemaAndEntityMappings() {
        // 1. Create and persist a Proxy
        Proxy proxy = new Proxy();
        proxy.setHost("127.0.0.1");
        proxy.setPort(1080);
        proxy.setProtocol("SOCKS5");
        proxy.setUsername("proxyuser");
        proxy.setPassword("proxypass");

        entityManager.persist(proxy);
        assertNotNull(proxy.getId());

        // 2. Create and persist a User linking to the Proxy
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");
        user.setPasswordHash("hashed_password_123");
        user.setDefaultProxy(proxy);

        entityManager.persist(user);
        assertNotNull(user.getId());

        // 3. Create and persist a TgAccount linking to the User and Proxy
        TgAccount tgAccount = new TgAccount();
        tgAccount.setUser(user);
        tgAccount.setProxy(proxy);
        tgAccount.setPhoneNumber("+79991234567");
        tgAccount.setUsername("johndoe_tg");
        tgAccount.setSessionFormat("SESSION");
        tgAccount.setSessionData("some_session_token_data");
        tgAccount.setStatus("ACTIVE");
        tgAccount.setDailyDialogueLimit(20);
        tgAccount.setCurrentDailyDialoguesCount(5);
        tgAccount.setLastSyncAt(Instant.now());

        entityManager.persist(tgAccount);
        assertNotNull(tgAccount.getId());

        // Clear persistence context to force loading from DB
        entityManager.flush();
        entityManager.clear();

        // 4. Retrieve and verify mappings
        TgAccount retrievedAccount = entityManager.find(TgAccount.class, tgAccount.getId());
        assertNotNull(retrievedAccount);
        assertEquals("+79991234567", retrievedAccount.getPhoneNumber());
        assertEquals("johndoe_tg", retrievedAccount.getUsername());
        assertEquals("ACTIVE", retrievedAccount.getStatus());
        assertEquals(20, retrievedAccount.getDailyDialogueLimit());
        assertEquals(5, retrievedAccount.getCurrentDailyDialoguesCount());
        assertNotNull(retrievedAccount.getLastSyncAt());

        // Check foreign keys / relationships
        assertNotNull(retrievedAccount.getUser());
        assertEquals("john_doe", retrievedAccount.getUser().getUsername());
        assertEquals("john@example.com", retrievedAccount.getUser().getEmail());

        assertNotNull(retrievedAccount.getProxy());
        assertEquals("127.0.0.1", retrievedAccount.getProxy().getHost());
        assertEquals(1080, retrievedAccount.getProxy().getPort());
        assertEquals("SOCKS5", retrievedAccount.getProxy().getProtocol());

        // Check User's default proxy mapping
        User retrievedUser = entityManager.find(User.class, user.getId());
        assertNotNull(retrievedUser);
        assertNotNull(retrievedUser.getDefaultProxy());
        assertEquals("127.0.0.1", retrievedUser.getDefaultProxy().getHost());
    }
}
