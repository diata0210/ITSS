package app.repositories;

import app.models.SiteOrder;
import app.models.WHCheckTable;
import app.models.WHCheckedTable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface WarehouseRepository {
    void checkAuthUser();
    List<SiteOrder> getSiteOrders();
    List<SiteOrder> getSiteOrdersChecked();
    List<WHCheckTable> getSiteOrderDetail(int SiteOrderID);
    List<WHCheckedTable> getSiteOrderDetailChecked(int SiteOrderID);
    SiteOrder getById(int id);
    SiteOrder getByIdChecked(int id);
    void updateActualQuantity(int siteOrderID, int productID, int checked);
    void updateSiteOrder(int siteOrderID, LocalDateTime arrivalDate, BigDecimal actualValue, int orderStatus);

}
