package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Iehor Funtusov, created 28/12/2020 - 11:15 AM
 */

@Getter
@Setter
public abstract class AbstractEntity {

    private Integer id;
    private Date created;

    public AbstractEntity() {
        this.created = new Date();
    }
}
