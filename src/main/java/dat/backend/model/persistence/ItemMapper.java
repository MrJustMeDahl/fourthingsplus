package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

}
