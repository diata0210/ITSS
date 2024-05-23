package app.services;

import java.util.List;

import app.models.SellOrder;
import app.repositories.SellOrderRepository;

public class SellOrderServiceImp {
    private SellOrderRepository sellOrderRepository;

    public void setSellOrderRepository(SellOrderRepository sellOrderRepository){
        this.sellOrderRepository = sellOrderRepository;
    }
    public SellOrder getById(int id){
        return sellOrderRepository.getById(id);
    }

    public List<SellOrder> getAlls(){
        return sellOrderRepository.getAlls();
    }

}

