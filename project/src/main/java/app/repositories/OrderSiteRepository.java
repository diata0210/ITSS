package app.repositories;

import app.models.SiteOrder;

import java.util.List;

public interface OrderSiteRepository {
    List<SiteOrder> getAlls();
    SiteOrder getById( int id);
    SiteOrder getByStatus(int id);
}


