package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class ItemFacade {

    public static List<Item> getAllItems(ConnectionPool connectionPool){
        return ItemMapper.getAllItems(connectionPool);
    }

    public static void addItem(String newItem, String username, ConnectionPool connectionPool) throws DatabaseException {
        ItemMapper.addItem(newItem, username, connectionPool);
    }

    public static List<Item> getDoingForUser(ConnectionPool connectionPool, User user){
         return ItemMapper.getDoingForUser(connectionPool, user);
    }

    public static List<Item> getDoneForUser(ConnectionPool connectionPool, User user){
        return ItemMapper.getDoneForUser(connectionPool, user);
    }

    public static void doingToDone(ConnectionPool connectionPool, int itemID) throws DatabaseException{
        ItemMapper.doingToDone(connectionPool, itemID);
    }

    public static void doneToDoing(ConnectionPool connectionPool, int itemID) throws DatabaseException{
        ItemMapper.doneToDoing(connectionPool, itemID);
    }
}
