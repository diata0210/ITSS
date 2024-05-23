package app.services;

import app.models.SiteOrder;
import app.models.WHCheckTable;
import app.repositories.WarehouseRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class WarehouseServiceImp {
    private WarehouseRepository warehouseRespository;

    public WarehouseRepository getWarehouseRespository() {
        return this.warehouseRespository;
    }
    public void setWarehouseRespository(WarehouseRepository warehouseRespository ){
        this.warehouseRespository= warehouseRespository;
    }
    public List<WHCheckTable> getSiteOrderDetail(int orderSiteID) {
        return this.warehouseRespository.getSiteOrderDetail(orderSiteID);
    }
    public List<SiteOrder> getSiteOrders(){
        return  this.warehouseRespository.getSiteOrders();
    }
    public List<SiteOrder> getSiteOrdersChecked(){
        return  this.warehouseRespository.getSiteOrdersChecked();
    }
    public SiteOrder getById(int id){
        return this.warehouseRespository.getById(id);
    }
    public SiteOrder getByIdChecked(int id){
        return this.warehouseRespository.getByIdChecked(id);
    }

    public void updateActualQuantity(int siteOrderID, int productID, int checked){
        warehouseRespository.updateActualQuantity(siteOrderID,productID,checked);
    }
    public void updateSiteOrder(int siteOrderID, LocalDateTime arrivalDate, BigDecimal actualValue, int orderStatus){
        warehouseRespository.updateSiteOrder(siteOrderID,arrivalDate,actualValue,orderStatus);
    }


//    public static List<SiteOrder> getAllSiteOrders() {
//        return this.warehouseRespository.selectSiteOrder();
//    }

}
