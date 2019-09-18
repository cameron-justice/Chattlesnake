package chattlesnake;

import java.time.LocalDateTime;

public class User {

    private int ID;
    private String name;
    private LocalDateTime create_date;

    /**
     * Default constructor
     */
    User(){

    }

    /**
     * Full constructor
     * @param ID ID of the user
     * @param name name of the user
     * @param create_date DateTime the user joined Chattlesnake
     */
    User(int ID, String name, LocalDateTime create_date){
        this.ID = ID;
        this.name = name;
        this.create_date = create_date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }
}
