package com.graphql.netflixDgx.datasource;

import com.netflix.dgsRev.generated.DgsConstants;
import com.netflix.dgsRev.generated.types.Hello;
import com.netflix.dgsRev.generated.types.SmartSearchResult;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DgsComponent
public class FakeSmartSearchDataResolver {
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.SmartSearch)
    public List<SmartSearchResult> getSmartSearchResult(@InputArgument(name = "keyword") Optional<String> key) {
        List<SmartSearchResult> list = new ArrayList<>();
        if (key.isEmpty()) {
            list.addAll(FakeHelloDataSource.HELLO_LIST);
            list.addAll(FakeBookDataSource.BOOK_LIST);
        } else {
            FakeHelloDataSource.HELLO_LIST
                    .stream()
                    .filter(h -> StringUtils.containsIgnoreCase(h.getText(), key.get()))
                    .forEach(list::add);
            FakeBookDataSource.BOOK_LIST
                    .stream()
                    .filter(b -> StringUtils.containsIgnoreCase(b.getTitle(), key.get()))
                    .forEach(list::add);
        }
        return list;
    }
}
