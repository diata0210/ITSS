package app.services;

import app.models.ProductSite;
import app.models.Site;
import app.models.VehicleSite;
import app.repositories.SiteRepository;

import java.util.List;

public class SiteServiceImp {
    private SiteRepository siteRepository;

    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<Site> getAllSites(){
        return siteRepository.getAllSites();
    }

    public Site getById(int id){
        return siteRepository.getById(id);
    }

    public List<ProductSite> getAllProductInSite(int siteID){
        return siteRepository.getAllProductInSite(siteID);
    };

    public int getQuantityInSite(int siteId, int productId){
        return siteRepository.getQuantityInSite(siteId, productId);
    }

    public List<VehicleSite> getAllVehicleSites(int siteId){
        return siteRepository.getAllVehicleInSite(siteId);
    }
    public void updateQuantityInProductSite(int siteId, int productId, int newQuantity){
        siteRepository.updateQuantityProductSite(siteId, productId, newQuantity);
    }

}
