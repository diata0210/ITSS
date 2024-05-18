package app.repositories;

import app.models.SiteOrder;

import java.util.List;

public interface OrderSiteRepository {
    public List<SiteOrder> getAlls();
    public SiteOrder getById();
    public SiteOrder getByStatus(int id);
}


