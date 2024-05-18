package app.services;

import app.models.SiteOrder;
import app.repositories.OrderSiteRepository;

import java.util.List;

public class OrderSiteServiceImp {

    private OrderSiteRepository orderSiteRepository;

    public OrderSiteRepository getOrderSiteRepository() {
        return orderSiteRepository;
    }

    public void setOrderSiteRepository(OrderSiteRepository orderSiteRepository) {
        this.orderSiteRepository = orderSiteRepository;
    }

    public List<SiteOrder> getAllSiteOrders() {
        return this.orderSiteRepository.getAlls();
    }

    public SiteOrder getSiteOrderById(int id){
        return this.orderSiteRepository.getById(id);
    }

}