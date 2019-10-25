package chattlesnake;

import java.util.LinkedList;

public class RelationsManager {

    LinkedList<User> knownUsers;

    public void addKnownUser(User user) {
        knownUsers.add(user);
    }


    /**
     * Searches the linked list for the correct User ID and returns the name of the user
     * @param user_id
     * @return
     */
    public String getUsername(int user_id) {
        for (User user: knownUsers) {
            if ( user.getID() == user_id)
                return user.getName();
        }

        // Should never be returned
        return "user not found";
    }


    /**
     * Searches the linked list for the correct User ID and returns the user
     * @param user_id
     * @return
     */
    public User getUser(int user_id) {
        for (User user: knownUsers) {
            if ( user.getID() == user_id)
                return user;
        }

        // Should never be returned
        return new User();

    }

}
