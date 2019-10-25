package chattlesnake;

import java.time.LocalDate;

public class User {

    private int ID;
    private String name;
    private LocalDate create_date;

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
    User(int ID, String name, LocalDate create_date){
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

    public LocalDate getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }
}
