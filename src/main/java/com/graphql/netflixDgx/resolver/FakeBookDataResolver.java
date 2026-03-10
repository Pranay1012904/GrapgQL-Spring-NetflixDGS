package com.graphql.netflixDgx.resolver;

import com.graphql.netflixDgx.datasource.FakeBookDataSource;
import com.netflix.dgsRev.generated.DgsConstants;
import com.netflix.dgsRev.generated.types.Book;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@DgsComponent
public class FakeBookDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field ="books")
    public List<Book> getBookList(@InputArgument(name = "author") String authorName) {
        if (StringUtils.isBlank(authorName)) {
            return FakeBookDataSource.BOOK_LIST;
        }
        return FakeBookDataSource.BOOK_LIST.stream().filter((b) -> checkAuthor(b, authorName)).toList();
    }

    private boolean checkAuthor(Book b, String author) {
        return b.getAuthor().getName().equalsIgnoreCase(author);
    }
}
