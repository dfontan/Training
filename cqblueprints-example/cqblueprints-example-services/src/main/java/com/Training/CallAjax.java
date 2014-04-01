package com.Training;


import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.felix.scr.annotations.Component;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.sling.jcr.api.SlingRepository;
@SlingServlet(methods={"GET"},paths={"/bin/service/ajax"},generateComponent=false)
@Component(enabled=true ,immediate =true)
public class CallAjax extends SlingSafeMethodsServlet{



    protected void doGet(SlingHttpServletRequest req ,SlingHttpServletResponse res)
        {
            try{
                res.getWriter().println("Hello world ajax call");
            }catch(Exception e)
            {
                e.getMessage();
            }
        }


    }





