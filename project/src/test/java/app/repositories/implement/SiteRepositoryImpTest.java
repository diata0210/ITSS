package app.repositories.implement;

import app.repositories.SiteRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SiteRepositoryImpTest {

    private SiteRepository siteRepository = new SiteRepositoryImp();

    //    Với các điều kiện để update thành công
    @Test
    void testUpdateQuantityProductSite_Success() {
        int siteId = 1;
        int productId = 1;
        int quantityToSubtract = 5;

        // Giá trị ban đầu
        int initialQuantityBeforeUpdate = siteRepository.getQuantityInSite(siteId, productId);
        // Giá trị mong muốn
        int expectedQuantity = initialQuantityBeforeUpdate - quantityToSubtract;
        siteRepository.updateQuantityProductSite(siteId, productId, quantityToSubtract);

        // Giá trị thực tế
        int initialQuantityAfterUpdate = siteRepository.getQuantityInSite(siteId, productId);
        assertEquals(initialQuantityBeforeUpdate, initialQuantityAfterUpdate + quantityToSubtract);
        assertEquals(expectedQuantity, initialQuantityAfterUpdate);
    }
    //    Với các điều kiện để update thất bại
    void testUpdateQuantityProductSite_NonExistingSite() {
        // Arrange
        int siteId = -1;
        int productId = 1;
        int quantityToSubtract = 10;

        // Act & Assert
        try {
            siteRepository.updateQuantityProductSite(siteId, productId, quantityToSubtract);
            // If no exception is thrown, fail the test
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            // Assert
            assertEquals("Non-existing site ID", e.getMessage());
        }
    }
}
