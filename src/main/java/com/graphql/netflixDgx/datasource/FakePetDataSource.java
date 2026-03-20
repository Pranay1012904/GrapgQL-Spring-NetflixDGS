package com.graphql.netflixDgx.datasource;

import com.netflix.dgsRev.generated.types.Cat;
import com.netflix.dgsRev.generated.types.Dog;
import com.netflix.dgsRev.generated.types.Pet;
import com.netflix.dgsRev.generated.types.PetFoodType;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakePetDataSource {

    @Autowired
    private Faker faker;
    public static final List<Pet> PET_LIST = new ArrayList<>();

    @PostConstruct
    public void getPetList() {
        for (int i = 0; i < 10; i++) {
            switch (ThreadLocalRandom.current().nextInt(1, 3)) {
                case 1:
                    var dog = Dog.newBuilder()
                            .name(faker.dog().name())
                            .breed(faker.dog().breed())
                            .foodType(PetFoodType.values()[faker.random().nextInt(PetFoodType.values().length)])
                            .size(faker.dog().size())
                            .coatLength(faker.dog().coatLength())
                            .build();
                    PET_LIST.add(dog);
                    break;
                case 2:
                    var cat = Cat.newBuilder()
                            .breed(faker.cat().breed())
                            .name(faker.cat().name())
                            .foodType(PetFoodType.values()[faker.random().nextInt(PetFoodType.values().length)])
                            .registry(faker.cat().registry())
                            .build();
                    PET_LIST.add(cat);
                    break;
            }
        }
    }
}
