package chattlesnake;

import java.time.LocalDateTime;

public class Group {

    private String name;
    private LocalDateTime create_date;
    private boolean passwd_required;

    Group(){

    }

    Group(String name, LocalDateTime create_date, boolean passwd_required){
        this.name = name;
        this.create_date = create_date;
        this.passwd_required = passwd_required;
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

    public boolean isPasswd_required() {
        return passwd_required;
    }

    public void setPasswd_required(boolean passwd_required) {
        this.passwd_required = passwd_required;
    }
}
