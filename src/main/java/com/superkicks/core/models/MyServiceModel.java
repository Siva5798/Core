package com.superkicks.core.models;

import javax.inject.Inject;

import com.superkicks.core.services.MySimpleService;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Filter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Model(adaptables = Resource.class)
public class MyServiceModel {

    @Inject
    private String msg;

    @OSGiService
    private MySimpleService mySimpleService;

   // MySimpleService mySimpleService = new MyServiceModel();

    public String getFilterAnswer() {

        return mySimpleService.demoString(msg);

    }

}
