package app.repositories;

import app.models.SiteOrder;

import java.util.List;

public interface OrderSiteRepository {
    List<SiteOrder> getAlls();
    SiteOrder getById();
    SiteOrder getByStatus(int id);
}


