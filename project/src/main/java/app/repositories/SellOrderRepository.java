package app.repositories;

import app.models.SellOrder;
import java.util.List;

public interface SellOrderRepository {
    List<SellOrder> getAlls();
    SellOrder getById(int id);
}
