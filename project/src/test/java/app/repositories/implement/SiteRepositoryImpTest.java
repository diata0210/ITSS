package app.repositories.implement;

import app.db.DatabaseConnection;
import app.repositories.SiteRepository;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class SiteRepositoryImpTest {

    private SiteRepository siteRepository = new SiteRepositoryImp();

    @Test
    void testUpdateQuantityProductSite_Success() {
        int siteId = 1;
        int productId = 2;
        int quantityToSubtract = 5;

        int initialQuantityBeforeUpdate = siteRepository.getQuantityInSite(siteId, productId);
        int expectedQuantity = initialQuantityBeforeUpdate - quantityToSubtract;
        siteRepository.updateQuantityProductSite(siteId, productId, quantityToSubtract);

        int initialQuantityAfterUpdate = siteRepository.getQuantityInSite(siteId, productId);
        assertEquals(expectedQuantity, initialQuantityAfterUpdate);
    }

    @Test
    void testUpdateQuantityProductSite_NonExistingSite() {
        int siteId = -1;
        int productId = 2;
        int quantityToSubtract = 10;

        Exception exception = assertThrows(RuntimeException.class, () -> {
            siteRepository.updateQuantityProductSite(siteId, productId, quantityToSubtract);
        });

        assertEquals("Non-existing site ID", exception.getMessage());
    }

    @Test
    void testUpdateQuantityProductSite_NonExistingProduct() {
        int siteId = 1;
        int productId = 999;
        int quantityToSubtract = 10;

        siteRepository.updateQuantityProductSite(siteId, productId, quantityToSubtract);

        int quantityAfterUpdate = siteRepository.getQuantityInSite(siteId, productId);
        assertEquals(0, quantityAfterUpdate);
    }

    @Test
    void testUpdateQuantityProductSite_QuantityExceeds() {
        int siteId = 1;
        int productId = 1;
        int quantityToSubtract = 1000;

        int initialQuantityBeforeUpdate = siteRepository.getQuantityInSite(siteId, productId);

        siteRepository.updateQuantityProductSite(siteId, productId, quantityToSubtract);

        int initialQuantityAfterUpdate = siteRepository.getQuantityInSite(siteId, productId);
        assertEquals(initialQuantityBeforeUpdate - quantityToSubtract, initialQuantityAfterUpdate);
    }
}