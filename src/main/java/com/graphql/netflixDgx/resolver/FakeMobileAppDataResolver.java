package com.graphql.netflixDgx.resolver;

import com.graphql.netflixDgx.datasource.FakeMobileAppDataSource;
import com.netflix.dgsRev.generated.DgsConstants;
import com.netflix.dgsRev.generated.types.MobileApp;
import com.netflix.dgsRev.generated.types.MobileAppFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.Optional;

@DgsComponent
public class FakeMobileAppDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE,field = DgsConstants.QUERY.MobileApps)
    public List<MobileApp> getMobileApps(@InputArgument(name = "mobileAppsFilter", collectionType = MobileAppFilter.class) Optional<MobileAppFilter> filter){
        return FakeMobileAppDataSource.MOBILE_APP_LIST;
    }
}
 //---------------------------------------TODO:---------create a function for filtering---------------------