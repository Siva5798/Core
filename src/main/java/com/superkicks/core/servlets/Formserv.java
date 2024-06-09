package com.superkicks.core.servlets;



import java.io.IOException;

import java.io.PrintWriter;


import javax.jcr.Node;

import javax.jcr.Session;

import javax.servlet.Servlet;


import org.apache.sling.api.SlingHttpServletRequest;

import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.resource.Resource;

import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.api.servlets.HttpConstants;

import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.osgi.framework.Constants;

import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class,

        property = {

                Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",

                "sling.servlet.methods=" + HttpConstants.METHOD_POST,

                "sling.servlet.paths=" + "/bin/training/contactusservlet",

                "sling.servlet.extensions=" + "html"

        })

public class Formserv extends SlingAllMethodsServlet {

    public static int count = 0;

 @Override

 protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

 String[] selector = request.getRequestPathInfo().getSelectors();

 String path = "/content/superkicks/us/en/homenew/new-arrivals/reebok/jcr:content/root/container/loginform_up";

 ResourceResolver rr = request.getResourceResolver();

 Resource r = rr.getResource(path);

 Node n = r.adaptTo(Node.class);

 if (selector[0].equals("add")) {

 try {

 count++;



 String fn = request.getParameter("firstname");

 String ln = request.getParameter("lastname");

 String tel = request.getParameter("tel");

 String email = request.getParameter("mail");



 Session s = rr.adaptTo(Session.class);



 Node m = n.addNode("form" + count);



 m.setProperty("firstname", fn);

 m.setProperty("lastname", ln);

 m.setProperty("email", email);

 m.setProperty("tel", tel);

 s.save();

 response.setContentType("text/html");

 PrintWriter out = response.getWriter();

 out.println("your details is added succesfully");

 out.println("<a href='/content/superkicks/us/en/homenew/new-arrivals/reebok.html'>go back</a>");

 } catch (Exception e) {

 e.getMessage();

 }

} else if (selector[0].equals("list")) {

 response.setContentType("text/html");

 PrintWriter out;

 try {
  out = response.getWriter();
  out.println("your list of user input");

  out.println("<table>");

  out.println("<tr> <th>First Name</th><th>Last Name</th><th>Email</th><th>phone<th></tr>");

 for (int i = 0; i < 5; i++) {

 out.println("<tr> <td>First Name</td><td>Last Name</td><td>Email</td><td>phone<th></tr>");

 }

 out.println("</table>");

 out.println("<a href='/content/parkerpen/us/en/contact-us.html'>go back</a>");

 } catch (IOException e) {



 e.printStackTrace();

 }



 }
 }


    @Override

  protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {



  }

}