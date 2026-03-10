package com.graphql.netflixDgx.datasource;


import com.netflix.dgsRev.generated.types.Hello;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FakeHelloDataSource {

    @Autowired
    private Faker faker;

    public static final List<Hello> HELLO_LIST = new ArrayList<>();

    @PostConstruct
    public void generateHelloDataSource() {
        for (int i = 0; i < 10; i++) {
            var hello = Hello.newBuilder()
                    .text(faker.text().toString())
                    .randomNumber(faker.random().nextInt(1, 20))
                    .build();
            HELLO_LIST.add(hello);
        }
    }

}
