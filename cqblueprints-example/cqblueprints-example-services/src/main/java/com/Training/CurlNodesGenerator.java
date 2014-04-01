package com.Training;


import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import java.io.IOException;
import java.io.PrintWriter;


import java.util.*;

import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Node;
@SlingServlet(methods={"GET"},paths={"/bin/service/Nodegen"},generateComponent=true)
public class CurlNodesGenerator extends SlingSafeMethodsServlet{
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
           List list = new ArrayList();
           //String cmd="curl -u admin:admin -F _charset_=utf-8 -F:nameHint=node -Fjcr:primaryType=nt:unstructured -Fsling:resourceType=cqblueprints-example/components/ArticleImageC -FnodeReferenceName=ref-name1 -Fjcr:createdBy=vivek -F”jcr:lastModifiedBy=vivek http://localhost:4502/content/Training1/en/Page1";
           //String cmd="curl -u admin:admin -F jcr:primaryType=sling:Folder http://localhost:4502/content/dam/myFolder1";
           String cmd1="curl -u admin:admin -F _charset_=utf-8  -F jcr:primaryType=nt:unstructured -F sling:resourceType=cqblueprints-example/components/ArticleImageC -F nodeReferenceName=abcRef -F jcr:createdBy=vivek -F jcr:lastModifiedBy=vivek http://localhost:4502/content/Training1/en/Page1";
           Runtime.getRuntime().exec(cmd1);
         
           session.save();
           session.logout();
        } catch (Exception e) {
        
        	session.logout();
            out.println("EXCEPTION ::: "+e.getMessage()+"/n");
            e.printStackTrace();
        }
        }




    }





