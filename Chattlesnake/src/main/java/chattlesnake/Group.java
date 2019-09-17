package chattlesnake;

import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 * Java Object representation of a chat Group
 */
public class Group {

    private int ID;
    private String name;
    private LocalDateTime create_date;
    private LinkedList<User> members; // Holds all members of the group

    /**
     * Default constructor
     */
    Group(){
        members = new LinkedList<User>();
    }

    /**
     * Partial constructor
     * @param ID ID of the group
     * @param name Name of the group
     * @param create_date DateTime the group was created
     */
    Group(int ID, String name, LocalDateTime create_date){
        this.ID = ID;
        this.name = name;
        this.create_date = create_date;

        members = new LinkedList<User>();
    }

    /**
     * Full constructor
     * @param name Name of the group
     * @param create_date DateTime the group was created
     * @param members List of members in the group
     */
    Group(String name, LocalDateTime create_date, LinkedList<User> members){
        this.name = name;
        this.create_date = create_date;

        this.members = members;
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

    /**
     * Adds a member to the group
     * @param member User object to add
     */
    public void addMember(User member){
        members.add(member);
    }

    /**
     * Retrieve the list of members
     * @return The list of members
     */
    public LinkedList<User> getMembers(){
        return members;
    }

    /**
     * Set the list of members
     * @param members new list of members
     */
    public void setMembers(LinkedList<User> members){
        this.members = members;
    }

    /**
     * Removes a member from the group
     * @param member_id ID of the member to remove
     */
    public void removeMember(int member_id){
        members.removeIf(user -> user.getID() == member_id);
    }

    /**
     * Gets the member with the matching ID
     * @param member_id the ID to look for
     * @return member with matching ID; null if member does not exist in group
     */
    public User getMember(int member_id){
        for(int i = 0; i < members.size(); i++){
            if(members.get(i).getID() == member_id)
                return members.get(i);
        }

        return null;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
