package com.graphql.netflixDgx.resolver;

import com.graphql.netflixDgx.datasource.FakePetDataSource;
import com.netflix.dgsRev.generated.DgsConstants;
import com.netflix.dgsRev.generated.types.Pet;
import com.netflix.dgsRev.generated.types.PetType;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.Optional;

@DgsComponent
public class FakePetDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE,field = DgsConstants.QUERY.Pets)
    public List<Pet> getPets(@InputArgument(name = "petFilter") Optional<String> petType){
        if(petType.isEmpty()){
            return FakePetDataSource.PET_LIST;
        }
       return FakePetDataSource.PET_LIST.stream().filter(pet->filterPet(pet,petType.get())).toList();

    }

    private boolean filterPet(Pet p,String f){
        if(p.getClass().toString().equalsIgnoreCase(f) ){
            return true;
        }else{
            return false;
        }
    }
}
