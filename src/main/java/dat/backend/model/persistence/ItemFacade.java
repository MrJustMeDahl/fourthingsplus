package dat.backend.model.persistence;

import dat.backend.model.entities.Item;

import java.util.List;

public class ItemFacade {

    public static List<Item> getAllItems(ConnectionPool connectionPool){
        return ItemMapper.getAllItems(connectionPool);
    }

}
