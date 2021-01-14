package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Iehor Funtusov, created 28/12/2020 - 11:49 AM
 */

@Getter
@Setter
public class User extends AbstractEntity {

    private String name;
    private String email;
    private Integer age;

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "created='" + getCreated() + '\'' +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
