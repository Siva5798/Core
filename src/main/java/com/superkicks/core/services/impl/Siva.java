package com.superkicks.core.services.impl;

import com.superkicks.core.services.MySimpleService;

import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Component(service = MySimpleService.class,immediate = true, name = "siva")
@ServiceRanking(1002)
public class Siva implements MySimpleService{

    @Override
    public String demoString(String str) {
        
        return StringUtils.lowerCase(str) +"  Siva";
    }
    @Activate
    protected void activate(BundleContext bc) {
        
        //LOGGER.info("My simple service Second activated");
    }
}
