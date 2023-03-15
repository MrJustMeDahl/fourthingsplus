package dat.backend.model.entities;

import java.sql.Timestamp;

public class Item {

    private int itemID;
    private String name;
    private boolean done;
    private String username;
    private Timestamp created;

    public Item(int itemID, String name, boolean done, String username, Timestamp created) {
        this.itemID = itemID;
        this.name = name;
        this.done = done;
        this.username = username;
        this.created = created;
    }

    public int getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getCreated() {
        return created;
    }
}
