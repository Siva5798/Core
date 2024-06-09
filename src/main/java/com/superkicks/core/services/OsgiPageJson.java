package com.superkicks.core.services;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.Session;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Model(
        
        adaptables = { SlingHttpServletRequest.class },
       
        resourceType = "superkicks/components/page", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name = "jackson", extensions = "json", options = {
        
        @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true"),
        @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "false")
})

public class OsgiPageJson {

    @Self
    private SlingHttpServletRequest request;

    @Self
    private Resource resource;

    @ValueMapValue
    @Named("jcr:title")
    @Required
    private String title;

    @ValueMapValue
    @Optional
    private String pageTitle;

    
    @ValueMapValue
    @Optional
    private String navTitle;

    @ValueMapValue
    @Named("jcr:description")
    @Default(values = "No description provided")
    private String description;

    @ValueMapValue
    @Named("jcr:created")
    private Calendar createdAt;

    @ValueMapValue
    @Default(booleanValues = false)
    boolean navRoot;

    @OSGiService
    @Required
    private QueryBuilder queryBuilder;

    @SlingObject
    @Required
    private ResourceResolver resourceResolver;

    private long size;
    @ScriptVariable(name = "currentPage")
    private Page page;

    @PostConstruct
    private void init() {

        final Map<String, String> map = new HashMap<String, String>();

        map.put("path", page.getPath());
        map.put("type", "cq:Page");

        Query query = queryBuilder.createQuery(PredicateGroup.create(map), resourceResolver.adaptTo(Session.class));
        final SearchResult result = query.getResult();
        this.size = result.getHits().size();
    }

    public String getTitle() {
        return StringUtils.defaultIfEmpty(pageTitle, title);
    }

    public String getDescription(int truncateAt) {
        if (this.description.length() > truncateAt) {
            return StringUtils.substring(this.description, truncateAt) + "...";
        } else {
            return this.description;
        }
    }

    public String getDescription() {
        return this.getDescription(100);
    }

    public long getSize() {
        return this.size;
    }

    @JsonIgnore
    public Calendar getCreatedAt() {
        return createdAt;
    }

    public String getPath() {
        return page.getPath();
    }

    public String getHelloWorld() {
        return "Hello World";   
    }

    public String getCakeDetails() {
        return "abc";
    }

    @JsonProperty(value = "superkicks")
    public String goodbyeWorld() {
        return "shoes";
    }
}