package com.Training;


import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import javax.jcr.Session;
import javax.jcr.Node;
@SlingServlet(methods={"GET"},paths={"/bin/service/testnew"},generateComponent=true)
public class CreateNode extends SlingSafeMethodsServlet{
    @Reference
    private org.apache.sling.jcr.api.SlingRepository repository;

    private Session session;
    @Override
    protected void doGet(SlingHttpServletRequest request ,SlingHttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("inside servlet");
        try
        {
           session=repository.loginAdministrative(null);
           out.println("Session ===== "+session.getRootNode());
            Node root = session.getRootNode();
            Node testNode;

             testNode=root.getNode("content");
             testNode.addNode("vivek_3","nt:unstructured");


            session.save();
            session.logout();
        } catch (Exception e) {
            // TODO: handle exception
            out.println("EXCEPTION in A"+e.getMessage()+"/n"+e.getLocalizedMessage());
            e.printStackTrace();
        }
        }




    }





