package app.repositories;

import app.models.CreateSiteOrder;
import app.models.SiteOrder;

import java.util.List;

public interface OrderToSiteRepository {
    List<SiteOrder> getAllOrderToSite();
    SiteOrder getById( int id);
    void createSiteOrders(CreateSiteOrder createSiteOrder);
}


