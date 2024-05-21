package app.services;

import app.models.CreateSiteOrder;
import app.models.SiteOrder;
import app.repositories.OrderToSiteRepository;

import java.util.List;

public class OrderToSiteServiceImp {

    private OrderToSiteRepository orderToSiteRepository;

    public void setOrderSiteRepository(OrderToSiteRepository orderToSiteRepository) {
        this.orderToSiteRepository = orderToSiteRepository;
    }

    public List<SiteOrder> getAllOrderToSite() {
        return this.orderToSiteRepository.getAllOrderToSite();
    }

    public SiteOrder getSiteOrderById(int id){
        return this.orderToSiteRepository.getById(id);
    }

    public void createOrder(CreateSiteOrder createSiteOrder){
        orderToSiteRepository.createSiteOrders(createSiteOrder);
    }
}