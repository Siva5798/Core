package com.superkicks.core.schedulers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component(service = Runnable.class, immediate = true)
@Designate(ocd = Osgiconfigurationdemo.class)

public class SchedulerDemo implements Runnable {

    private static final Logger Log = LoggerFactory.getLogger(SchedulerDemo.class);

    @Reference
    Scheduler scheduler;

    @Activate
    public void init(Osgiconfigurationdemo odgi) {
        addScheduler(odgi);
    }

    public void addScheduler(Osgiconfigurationdemo dem) {
        Log.info("Scheduled method is called");
        if(dem.getEnabledScheduler())
        {
            //dem.getCronExpression();
            //scheduler.EXPR(dem.getCronExpression());
            // assign it to an local variable thats the dynamic way 

            ScheduleOptions so = scheduler.EXPR( dem.getCronExpression());
            so.canRunConcurrently(dem.getCanRunConcurrent());
            Map<String, Serializable> ha = new HashMap<>();
            ha.put("URL :", "www.google.com");
            so.config(ha);

            //one imp thing in scheduler after defining the methods we have a scheduler option
            //schedule to be metioned with this keyword with the object 

           // scheduler.schedule(this,so);
            scheduler.unschedule("Siva Schedular");


        }
    }

    @Deactivate
    public void deactivate(Osgiconfigurationdemo deact) {
        deactivateScheduler(deact);
    }

    public void deactivateScheduler(Osgiconfigurationdemo deac) {
        Log.info("SCheduler is deactivated");
        scheduler.unschedule(deac.getSchedulerName());
    }

    @Override
    public void run() {
        Log.info("Scheduler has been executed");

    }

}
