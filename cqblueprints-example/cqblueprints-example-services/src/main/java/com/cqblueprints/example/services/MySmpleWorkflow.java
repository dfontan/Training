package com.cqblueprints.example.services;

/**
 * Created with IntelliJ IDEA.
 * User: intelligrape
 * Date: 3/25/14
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.ResultPage;
import com.day.cq.search.result.SearchResult;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.framework.Constants;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import java.lang.System;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Sample workflow process that sets an <code>approve</code> property to the payload based on the process argument value.
 */
@Component
@Service
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "Fetch Dependencies of node."),
        @Property(name = Constants.SERVICE_VENDOR, value = "Adobe"),
        @Property(name = "process.label", value = "Fetch Nodes")})
public class MySmpleWorkflow implements WorkflowProcess {

    private static final String TYPE_JCR_PATH = "JCR_PATH";

    

    private Session sess;
    String resourceType="";
    @Reference 
    SlingRepository repository;
    
    @Reference
    QueryBuilder queryBuilder;
    public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
        WorkflowData workflowData = item.getWorkflowData();
        System.out.println("*****************************************************************************************");
        String pathFromArgument = args.get("PROCESS_ARGS", String.class);
        System.out.println("pathFromArgument  "+pathFromArgument);
       // if (workflowData.getPayloadType().equals(TYPE_JCR_PATH)) {
         //   String path = workflowData.getPayload().toString() + "/jcr:content";
            try {
         //      
            	Session sess = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
            	Map map = new HashMap();
            	map.put("path", "/content");
            	map.put("type","cq:page");
            	map.put("property", "sling:resourceType");
            	map.put("property.value", pathFromArgument); //cqblueprints-example/components/examplepage           	
            	com.day.cq.search.Query query = queryBuilder.createQuery(PredicateGroup.create(map), sess);           
            	SearchResult result = query.getResult();
            	List<Hit> l=result.getHits();
            	System.out.println("No of pages "+l.size());
            	
            	for(Hit hit : l)
            	{
            		System.out.println("title---"+hit.getNode().getParent());
            		

            	}
            	
            	
            } catch (Exception e) {
               System.out.println(e);
            }
        

    }

    private boolean readArgument(MetaDataMap args) {
        String argument = args.get("PROCESS_ARGS", "false");
        return argument.equalsIgnoreCase("true");
    }
}