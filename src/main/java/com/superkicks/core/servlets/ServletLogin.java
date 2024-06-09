package com.superkicks.core.servlets;
import com.day.cq.commons.jcr.JcrConstants;

import org.apache.sling.api.SlingHttpServletRequest;

import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.resource.Resource;

import org.apache.sling.api.servlets.HttpConstants;

import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONArray;
import org.osgi.service.component.annotations.Component;

import org.osgi.service.component.propertytypes.ServiceDescription;



import javax.servlet.Servlet;

import javax.servlet.ServletException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



/**

 * Servlet that writes some sample content into the response. It is mounted for

 * all resources of a specific Sling resource type. The

 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are

 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.

 */

@Component(service = { Servlet.class })

@SlingServletResourceTypes(

        resourceTypes="superkicks/components/page",

        methods=HttpConstants.METHOD_GET,

        selectors="txt")

@ServiceDescription("Simple Demo Servlet")

public class ServletLogin extends SlingSafeMethodsServlet {

    @Override

    protected void doGet(final SlingHttpServletRequest req,

            final SlingHttpServletResponse resp) throws ServletException, IOException {

		String fn = req.getParameter("Fname");
		String ln = req.getParameter("Lname");
		String mail = req.getParameter("Email");
		String no = req.getParameter("mnum");
        JSONArray js=new JSONArray();
		Map<String,String> map=new HashMap<String,String>();
		map.put("first-name",fn);
		map.put("last-name",ln);
		map.put("email",mail);
		map.put("Phone-number",no);
		js.put(map);

        resp.setContentType("text/plain");

        resp.getWriter().write("form details = " +js);
		
    }

}