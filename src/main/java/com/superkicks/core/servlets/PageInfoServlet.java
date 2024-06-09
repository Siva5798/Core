package com.superkicks.core.servlets;

import java.io.IOException;

import java.util.HashMap;

import java.util.Map;



import javax.servlet.Servlet;

import javax.servlet.ServletException;



import org.apache.sling.api.SlingHttpServletRequest;

import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.resource.Resource;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.apache.sling.servlets.annotations.SlingServletResourceTypes;

import org.apache.sling.api.servlets.HttpConstants;

import org.json.JSONArray;

import org.osgi.service.component.annotations.Component;

import org.osgi.service.component.annotations.Reference;



import com.day.cq.wcm.api.Page;

import com.day.cq.wcm.api.PageManager;

import com.day.cq.wcm.api.PageManagerFactory;



//import net.sf.json.JSONObject;





@Component(service = { Servlet.class })

@SlingServletResourceTypes(resourceTypes=PageInfoServlet.RESOURCE_TYPE,
            methods=HttpConstants.METHOD_GET,
            selectors="siva",
            extensions="json")

public class PageInfoServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;
    protected static final String RESOURCE_TYPE = "superkicks/components/page";
    @Reference
    private PageManagerFactory pageManagerFactory;
    @Override
    protected void doGet(final SlingHttpServletRequest request,

            final SlingHttpServletResponse response) throws ServletException, IOException {
            response.setContentType("json");

        PageManager pm = pageManagerFactory.getPageManager(request.getResourceResolver());

        //Use the PageManager to find the containing page of the resource (component)

        Page curPage = pm.getContainingPage(request.getResource());

       

        //Verify the page exists and it is a site page and not an XF

        if(curPage != null && !curPage.getName().equals("master")) {

            String responseStr = "";

            JSONArray js=new JSONArray();

            Map<String, String> map = new HashMap<String, String>();

            map.put("Title", curPage.getName());

            map.put("Modified By", curPage.getLastModifiedBy());

            map.put("path",curPage.getPath());

            js.put(map);

            responseStr = js.toString();

            response.getWriter().print(responseStr);

            response.getWriter().close();

        }



    }

}