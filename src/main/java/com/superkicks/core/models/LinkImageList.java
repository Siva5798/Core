package com.superkicks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;



@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkImageList {

    
    @ValueMapValue
    private String topic;

    @ValueMapValue
    private String pathLink;

    @ValueMapValue
    private String imgg;
    @ValueMapValue
    private String shoePrice;
    public String getTopic() {
        return topic;
    }

    public String getPathLink() {
        return pathLink;
    }
    public String getImgg(){
        return imgg;

    }
    
public  String getShoePrice(){
    return shoePrice;
}
}