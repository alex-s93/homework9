package ua.com.alevel.dao;

import ua.com.alevel.entity.AbstractEntity;

/**
 * @author Iehor Funtusov, created 28/12/2020 - 11:53 AM
 */

public interface AbstractDao<E extends AbstractEntity> {

    void create(E e);
    void update(E e);
}
