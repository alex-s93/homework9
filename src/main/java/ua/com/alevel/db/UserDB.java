package ua.com.alevel.db;

import ua.com.alevel.entity.User;

/**
 * @author Iehor Funtusov, created 28/12/2020 - 11:57 AM
 */
public interface UserDB extends AbstractDB<User> {
    void delete(Integer phone);
}
