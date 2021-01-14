package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile extends AbstractEntity {

    private String city;
    private String firstName;
    private String lastName;
    private String phone;

    public Profile() {
        super();
    }
}
