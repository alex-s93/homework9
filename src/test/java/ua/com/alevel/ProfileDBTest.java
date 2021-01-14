package ua.com.alevel;

import junit.framework.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.*;
import ua.com.alevel.db.impl.ProfileDBImpl;
import ua.com.alevel.entity.Profile;

import java.util.List;
import java.util.stream.Collectors;

import static ua.com.alevel.util.ProfileTestUtil.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileDBTest implements AbstractCrudTest<Profile> {
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
            ProfileDBImpl.getInstance().create(profile);
        }
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), COLLECTION_SIZE);
    }

    @AfterAll
    public static void destroy() {
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 10);
        List<String> profilesPhoneList = profiles.stream().map(Profile::getPhone).collect(Collectors.toList());
        Assert.assertTrue(CollectionUtils.isNotEmpty(profilesPhoneList));
        for (String phone : profilesPhoneList) {
            ProfileDBImpl.getInstance().delete(phone);
        }
        Assert.assertTrue(CollectionUtils.isEmpty(ProfileDBImpl.getInstance().readAll()));
    }

    @Test
    @Order(2)
    @Override
    public void create() {
        Profile profile = new Profile();
        profile.setPhone(CREATE_PHONE);
        ProfileDBImpl.getInstance().create(profile);
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), COLLECTION_SIZE + 1);
    }

    @Test
    @Order(1)
    @Override
    public void read() {
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), COLLECTION_SIZE);
        profiles = ProfileDBImpl.getInstance().read(FIELD_ID, 1);
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
    }

    @Test
    @Order(3)
    @Override
    public void update() {
        List<Profile> profiles = ProfileDBImpl.getInstance().read(FIELD_PHONE, CREATE_PHONE);
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
        Profile profile = profiles.get(0);
        profile.setPhone(UPDATE_PHONE);
        ProfileDBImpl.getInstance().update(profile);
        profiles = ProfileDBImpl.getInstance().read(FIELD_PHONE, UPDATE_PHONE);
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
    }

    @Test
    @Order(4)
    @Override
    public void delete() {
        List<Profile> profiles = ProfileDBImpl.getInstance().read(FIELD_PHONE, UPDATE_PHONE);
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
        ProfileDBImpl.getInstance().delete(UPDATE_PHONE);
        profiles = ProfileDBImpl.getInstance().read(FIELD_PHONE, UPDATE_PHONE);
        Assert.assertTrue(CollectionUtils.isEmpty(profiles));
    }
}
