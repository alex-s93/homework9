package ua.com.alevel;

import junit.framework.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.*;
import ua.com.alevel.dao.ProfileDao;
import ua.com.alevel.dao.impl.ProfileDaoImpl;
import ua.com.alevel.db.impl.ProfileDBImpl;
import ua.com.alevel.entity.Profile;

import java.util.List;
import java.util.stream.Collectors;

import static ua.com.alevel.util.ProfileTestUtil.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileDaoTest implements AbstractCrudTest<Profile> {

    private static final ProfileDao profileDao = new ProfileDaoImpl();

    @BeforeAll
    public static void init() {
        for (int i = 0; i < COLLECTION_SIZE; i++) {
            String firstName = "testfirstname" + i;
            String lastName = "testlastname" + i;
            String phone = "+380991234567" + i;
            String city = getRandomCity();
            Profile profile = new Profile();
            profile.setFirstName(firstName);
            profile.setLastName(lastName);
            profile.setPhone(phone);
            profile.setCity(city);
            profileDao.create(profile);
        }
        Assert.assertTrue(CollectionUtils.isNotEmpty(profileDao.read()));
    }

    @AfterAll
    public static void destroy() {
        List<Profile> profiles = profileDao.read();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 10);
        List<String> profilesPhoneList = profiles.stream().map(Profile::getPhone).collect(Collectors.toList());
        Assert.assertTrue(CollectionUtils.isNotEmpty(profilesPhoneList));
        for (String phone : profilesPhoneList) {
            ProfileDBImpl.getInstance().delete(phone);
        }
        Assert.assertTrue(CollectionUtils.isEmpty(profileDao.read()));
    }

    @Test
    @Order(1)
    @Override
    public void create() {
        Profile profile = new Profile();
        profile.setPhone(CREATE_PHONE);
        profile.setFirstName("Myfirstname123");
        profile.setFirstName("Mylastname123");
        profile.setCity("Kiev");
        profileDao.create(profile);
        List<Profile> profilesList = profileDao.read();
        Assert.assertEquals(profilesList.size(), 11);
    }

    @Test
    @Order(2)
    @Override
    public void read() {
        List<Profile> profilesList = profileDao.read();
        Assert.assertEquals(profilesList.size(), 10);
    }

    @Test
    @Order(3)
    @Override
    public void update() {
        List<Profile> profiles = profileDao.readByPhone(CREATE_PHONE);
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
        Profile profile = profiles.get(0);
        profile.setPhone(UPDATE_PHONE);
        profileDao.update(profile);
        profiles = profileDao.readByPhone(UPDATE_PHONE);
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
    }

    @Test
    @Order(4)
    @Override
    public void delete() {
        List<String> profilesPhoneList = profileDao.read().stream().map(Profile::getPhone).collect(Collectors.toList());
        Assert.assertEquals(profilesPhoneList.size(), 11);
        for (String phone : profilesPhoneList) {
            if (phone.equals(DELETE_PHONE)) {
                profileDao.delete(phone);
            }
        }
        List<Profile> profilesList = profileDao.read();
        Assert.assertEquals(profilesList.size(), 10);
    }
}
