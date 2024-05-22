package app.repositories.implement;

import app.repositories.SiteRepository;
import app.repositories.WarehouseRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WarehouseRepositoryImpTest {

    private WarehouseRepository warehouseRepository = new WarehouseRepositoryImp();

    //    Với các điều kiện để update thành công
    @Test
    void testUpdateActualQuantity_Success() {
        int siteOrderId = 12;
        int productId = 7;
        int checked =5;


        // Giá trị ban đầu
        int initialActualQuantityBeforeUpdate = warehouseRepository.getSiteOrderDetail(12).get(0).getChecked();
        // Giá trị mong muốn
        int expectedQuantity = checked;
        warehouseRepository.updateActualQuantity(siteOrderId,productId,checked);

        // Giá trị thực tế
        int initialQuantityAfterUpdate = warehouseRepository.getSiteOrderDetail(12).get(0).getChecked();
//        assertEquals(initialActualQuantityBeforeUpdate, initialQuantityAfterUpdate);
        assertEquals(expectedQuantity, initialQuantityAfterUpdate);
    }
    //    Với các điều kiện để update thất bại
    void testUpdateActualQuantity_Fail() {
        // Arrange
        int orderSiteId = 12;
        int productId = 7;
        int checked =-1;
        int quantityToSubtract = 5;

        // Act & Assert
        try {
            warehouseRepository.updateActualQuantity(orderSiteId,productId,checked);
            // If no exception is thrown, fail the test
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            // Assert
            assertEquals("Non-existing site ID", e.getMessage());
        }
    }
}
