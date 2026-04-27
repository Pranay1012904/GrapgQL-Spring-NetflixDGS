package com.graphql.netflixDgx.resolver;

import com.graphql.netflixDgx.datasource.FakeHelloDataSource;
import com.netflix.dgsRev.generated.DgsConstants;
import com.netflix.dgsRev.generated.types.Hello;
import com.netflix.dgsRev.generated.types.HelloInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class FakeHelloMutation {

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.AddHello)
    public int addHello(@InputArgument(name = "helloInput") HelloInput helloInput) {
        var hello = Hello.newBuilder()
                .text(helloInput.getText())
                .randomNumber(helloInput.getNumber())
                .build();
        FakeHelloDataSource.HELLO_LIST.add(hello);
        return FakeHelloDataSource.HELLO_LIST.size();
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.ReplaceHelloInputText)
    public List<Hello> replaceHelloInputText(@InputArgument(name = "helloInput") HelloInput helloInput) {
        FakeHelloDataSource.HELLO_LIST
                .stream()
                .filter(h -> h.getRandomNumber() == helloInput.getNumber())
                .forEach(h -> h.setText(helloInput.getText()));
        return FakeHelloDataSource.HELLO_LIST;
    }

    @DgsData(parentType = DgsConstants.MUTATION_TYPE, field = DgsConstants.MUTATION.DeleteHello)
    public int deleteHello(@InputArgument(name = "number") int number) {
        List<Hello> newHelloList = FakeHelloDataSource.HELLO_LIST
                .stream()
                .filter(h -> h.getRandomNumber() != number).toList();
        return newHelloList.size();
    }


}
