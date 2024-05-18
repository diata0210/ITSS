package app.services;

import app.annotation.Component;
import app.annotation.Inject;
import app.models.SiteOrder;
import app.repositories.OrderSiteRepository;

import java.util.List;

@Component
public class OrderSiteServiceImp {
    @Inject
    private OrderSiteRepository orderSiteRepository;

    public List<SiteOrder> getAllSiteOrders() {
        return orderSiteRepository.getAlls();
    }
}
