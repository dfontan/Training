package com.intelligrape.services;

import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.framework.Constants;

import com.day.cq.search.QueryBuilder;
import com.day.cq.workflow.PayloadMap;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;

@Component(label = "Component log Path", description = "Log Path of Component",immediate = true, metatype = true,enabled=true)
@Service
@Properties({
    @Property(name = Constants.SERVICE_DESCRIPTION, value = "Component Log path"),
    @Property(name = Constants.SERVICE_VENDOR, value = "Adobeone"),
    @Property(name = "process.label", value = "Log Component Path")})
public class LogComponentPath implements WorkflowProcess {

    private static final String TYPE_JCR_PATH = "JCR_PATH";

    private Session sess;
    String resourceType="";
    
    @Reference 
    SlingRepository repository;
    
    @Reference
    QueryBuilder queryBuilder;
    
    public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
        WorkflowData workflowData = item.getWorkflowData();
        System.out.println("************************* WF initiated ****************************************************************");
      
            try {
              
            	Session sess = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
                System.out.println("Payload ::: "+item.getWorkflowData().getPayload());
               
            	
            } catch (Exception e) {
               System.out.println(e);
            }
        
    }

   
}