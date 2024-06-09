package com.superkicks.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Siva Schedular", description = "This is my Test Scheduler")

public @interface Osgiconfigurationdemo {

    @AttributeDefinition(name ="Scheduler Name",description = "Name of Scheduler",
    type=AttributeType.STRING)
    public String getSchedulerName() default "Scheduler name";

    @AttributeDefinition(name ="Can Run concurrently",description = "If you want to run the scheduler to run concurrently",
    type=AttributeType.BOOLEAN)
    public boolean getCanRunConcurrent() default true;

    @AttributeDefinition(name ="Cron Expression ",description = "Enter Cron Expression",
    type=AttributeType.STRING)
    public String getCronExpression() default "0 * * ? * *";

    @AttributeDefinition(name="Enable Scheudler",
    type=AttributeType.BOOLEAN,
    description="Scheduler is enabled / disabled")

    public boolean getEnabledScheduler() default false;
}