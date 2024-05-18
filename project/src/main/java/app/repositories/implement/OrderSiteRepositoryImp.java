package app.repositories.implement;

import app.models.SiteOrder;
import app.repositories.OrderSiteRepository;

import java.util.List;

public class OrderSiteRepositoryImp implements OrderSiteRepository {
    @Override
    public List<SiteOrder> getAlls() {
        return List.of();
    }

    @Override
    public SiteOrder getById() {
        return null;
    }

    @Override
    public SiteOrder getByStatus(int id) {
        return null;
    }
}
