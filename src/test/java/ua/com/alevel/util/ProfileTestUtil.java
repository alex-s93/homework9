package ua.com.alevel.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@UtilityClass
public class ProfileTestUtil {
    public final String CREATE_PHONE = "+3809912345698";
    public final String UPDATE_PHONE = "+3809912345699";
    public final String DELETE_PHONE = "+3809912345675";
    public final String FIELD_PHONE = "phone";
    public final String FIELD_ID = "id";
    public final int COLLECTION_SIZE = 10;

    private final List<String> CITIES = new ArrayList<String>() {{
        add("Kharkiv");
        add("Kiev");
        add("Lviv");
        add("Dnipropetrovsk");
        add("Odessa");
        add("Kherson");
        add("Kriviy Rig");
    }};

    public String getRandomCity() {
        Random rand = new Random();
        return CITIES.get(rand.nextInt(CITIES.size()));
    }


}
