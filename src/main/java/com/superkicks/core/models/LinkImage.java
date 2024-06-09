package com.superkicks.core.models;



import java.util.*;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkImage {

    @Inject
    private List<LinkImageList> allLinks;
   
    public List<LinkImageList> getAllLinks() {

        return new ArrayList<>(allLinks);

    }
    public boolean getEmpty(){
        return allLinks.isEmpty();
    }

}