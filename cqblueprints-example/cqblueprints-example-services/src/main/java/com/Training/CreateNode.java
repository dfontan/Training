package com.Training;


import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Node;
@SlingServlet(methods={"GET"},paths={"/bin/service/create"},generateComponent=true)
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
        String requestParam1=request.getParameter("node");
        System.out.println("Param1 ::::: "+requestParam1);
        try
        {
           session=repository.loginAdministrative(null);
           out.println("Session ===== "+session.getRootNode());
            Node root = session.getRootNode();
            Node testNode;

             testNode=root.getNode("content");
             NodeIterator nodeIterator=testNode.getNodes();
             while(nodeIterator.hasNext())
             {
            	 Node temp=(Node) nodeIterator.next();
            	 System.out.println("Node name ::::: "+temp.getName());
            	 if(temp.getName().equals(requestParam1))
            	 {
            		 out.println("Node Already exists");
            		 break;
            	 }
             }
             testNode.addNode(requestParam1,"nt:unstructured");
             
           session.save();
            session.logout();
        } catch (Exception e) {
            // TODO: handle exception
            out.println("EXCEPTION in A"+e.getMessage()+"/n"+e.getLocalizedMessage());
            e.printStackTrace();
        }
        }




    }





