package com.graphql.netflixDgx.datasource;

import com.netflix.dgsRev.generated.types.Address;
import com.netflix.dgsRev.generated.types.Author;
import com.netflix.dgsRev.generated.types.Book;
import com.netflix.dgsRev.generated.types.ReleaseHistory;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeBookDataSource {

    @Autowired
    private Faker faker;

    public static final List<Book> BOOK_LIST = new ArrayList<>();

    @PostConstruct
    public void generateBookList() {
        for (int i = 0; i < 10; i++) {
            List<Address> addressList = new ArrayList<>();
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(2, 4); j++) {
                var address = Address.newBuilder()
                        .city(faker.address().city())
                        .street(faker.address().streetAddress())
                        .zipCode(faker.address().zipCode())
                        .country(faker.country().name())
                        .build();
                addressList.add(address);
            }
            var release = ReleaseHistory.newBuilder()
                    .printedEdition(faker.bool().bool())
                    .releasedCountry(faker.country().name())
                    .year(ThreadLocalRandom.current().nextInt(1975, 2027))
                    .build();
            var author = Author.newBuilder()
                    .name(faker.book().author())
                    .originCountry(faker.country().name())
                    .addresses(addressList)
                    .build();
            var book = Book.newBuilder()
                    .author(author)
                    .publisher(faker.book().publisher())
                    .released(release)
                    .title(faker.book().title())
                    .build();
            BOOK_LIST.add(book);
        }
    }
}
