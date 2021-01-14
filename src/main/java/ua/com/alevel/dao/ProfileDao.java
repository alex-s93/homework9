package ua.com.alevel.dao;

import ua.com.alevel.entity.Profile;

import java.util.List;

public interface ProfileDao extends AbstractDao<Profile>{

    List<Profile> readByPhone(String phone);

    List<Profile> read();

    List<Profile> read(String phone);

    void delete(String phone);
}
