package com.graphql.netflixDgx.datasource;

import com.netflix.dgsRev.generated.types.Address;
import com.netflix.dgsRev.generated.types.Author;
import com.netflix.dgsRev.generated.types.MobileApp;
import com.netflix.dgsRev.generated.types.ReleaseHistory;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeMobileAppDataSource {
    @Autowired
    private Faker faker;
    public static final List<MobileApp> MOBILE_APP_LIST = new ArrayList<>();

    @PostConstruct
    public void generateFakeMobileAppsList() throws MalformedURLException {

        for (int i = 0; i < 10; i++) {
            List<Address> addressList = new ArrayList<>();
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(2, 4); j++) {

                var address = Address.newBuilder().city(faker.address().city()).street(faker.address().streetAddress()).zipCode(faker.address().zipCode()).country(faker.country().name()).build();
                addressList.add(address);
            }
            var release = ReleaseHistory.newBuilder().releasedCountry(faker.country().name()).year(ThreadLocalRandom.current().nextInt(1975, 2027)).build();
            var author = Author.newBuilder().name(faker.app().author()).originCountry(faker.country().name()).addresses(addressList).build();
            var mobileApp = MobileApp.newBuilder()
                    .appId(UUID.randomUUID().toString())
                    .author(author)
                    .name(faker.app().name())
                    .version(faker.app().version())
                    .platform(getRandomPlatform())
                    .downloaded(ThreadLocalRandom.current().nextInt(10,999))
                    .releaseDate(LocalDate.now())
                    .homepage(new URL("http://"+faker.internet().url()))
                    .build();
            MOBILE_APP_LIST.add(mobileApp);
        }

    }

    private List<String> getRandomPlatform() {
        switch (ThreadLocalRandom.current().nextInt(1, 4)) {
            case 1:
                return List.of("iOS", "Linux");
            //return Arrays.asList(p1);
            case 2:
                return List.of("Windows", "Android");

            default:
                return List.of("Android", "Linux");
        }

    }
}
