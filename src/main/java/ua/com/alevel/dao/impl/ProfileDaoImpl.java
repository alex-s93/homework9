package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.ProfileDao;
import ua.com.alevel.db.impl.ProfileDBImpl;
import ua.com.alevel.entity.Profile;

import java.util.List;

public class ProfileDaoImpl implements ProfileDao {

    ProfileDBImpl db = ProfileDBImpl.getInstance();

    @Override
    public List<Profile> readByPhone(String phone) {
        return db.read("phone", phone);
    }

    @Override
    public void create(Profile profile) {
        db.create(profile);
    }

    @Override
    public List<Profile> read() {
        return db.readAll();
    }

    @Override
    public List<Profile> read(String phone) {
        return db.read("phone", phone);
    }

    @Override
    public void update(Profile profile) {
        db.update(profile);
    }

    @Override
    public void delete(String phone) {
        db.delete(phone);
    }
}
