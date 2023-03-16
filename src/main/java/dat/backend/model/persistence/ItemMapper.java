package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class ItemMapper {

    static List<Item> getAllItems(ConnectionPool connectionPool){
        String sql = "SELECT * FROM fourthingsplus.item";
        List<Item> itemList = new ArrayList<>();

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("item_id");
                    String name = rs.getString("name");
                    boolean done = rs.getBoolean("done");
                    String username = rs.getString("username");
                    Timestamp created = rs.getTimestamp("created");
                    itemList.add(new Item(id, name, done, username, created));
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return itemList;
    }

    public static void addItem(String newItem, String username, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "insert into fourthingsplus.item (name, username) values (?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, newItem);
                ps.setString(2, username);
                ps.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert item into database");
        }
    }

    static List<Item> getDoingForUser(ConnectionPool connectionPool, User user){
        String sql = "SELECT * FROM fourthingsplus.item WHERE username = ? && done = 0";
        List<Item> itemList = new ArrayList<>();

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, user.getUsername());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("item_id");
                    String name = rs.getString("name");
                    boolean done = rs.getBoolean("done");
                    String username = rs.getString("username");
                    Timestamp created = rs.getTimestamp("created");
                    itemList.add(new Item(id, name, done, username, created));
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return itemList;
    }

    static List<Item> getDoneForUser(ConnectionPool connectionPool, User user){
        String sql = "SELECT * FROM fourthingsplus.item WHERE username = ? && done = 1";
        List<Item> itemList = new ArrayList<>();

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, user.getUsername());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("item_id");
                    String name = rs.getString("name");
                    boolean done = rs.getBoolean("done");
                    String username = rs.getString("username");
                    Timestamp created = rs.getTimestamp("created");
                    itemList.add(new Item(id, name, done, username, created));
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return itemList;
    }

    public static void doingToDone(ConnectionPool connectionPool, int itemID) throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "UPDATE fourthingsplus.item SET done = 1 WHERE (item_id = ?);";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, itemID);
                ps.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not move task from doing to done");
        }
    }

    public static void doneToDoing(ConnectionPool connectionPool, int itemID) throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "UPDATE fourthingsplus.item SET done = 0 WHERE (item_id = ?);";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, itemID);
                ps.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not move task from done to doing");
        }
    }

    public static void updateItemName(ConnectionPool connectionPool, int itemID, String itemName) throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "UPDATE fourthingsplus.item SET name = ? WHERE item_id = ?;";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, itemName);
                ps.setInt(2, itemID);
                ps.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not move task from done to doing");
        }
    }
}
