package com.Training;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.event.EventUtil;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to listen to add event for any event
 * 
 * @author Vivek Dhiman
 * 
 */
@Component(metatype = false, immediate = true, label = "Event heading End Date Chnange Listne", description = "This component will listen to change in event end date nod")
@Service(value = { EventListener.class })
public class EventEndDateListener implements EventListener {
	@Reference
	private SlingRepository repository;
	@Reference
	private EventAdmin eventAdmin;
	@Reference
	private Scheduler scheduler;

	private Session session;
	private ObservationManager observationManager;

	// On component activate register event listener for path
	@Activate
	protected void activate(ComponentContext context) {
		try {
			System.out.println("=========This will Execute==============");
			session = repository.loginAdministrative(null);
			// Listen for changes to our orders
			if (repository.getDescriptor(Repository.OPTION_OBSERVATION_SUPPORTED).equals("true")) 
			{
				observationManager = session.getWorkspace().getObservationManager();
				final String[] types = { "nt:unstructured" };
				observationManager.addEventListener(this, Event.NODE_ADDED,"/content/usergenerated/content", true, null, null, false);
				System.out.println(":::::::::Inside activate::::::::");
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// On deactivate method close any open session
	@Deactivate
	protected void deactivate(ComponentContext componentContext) {
		if (observationManager != null) {
			try {
				observationManager.removeEventListener(this);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (session != null) {
			session.logout();
			session = null;
		}
	}

	public void onEvent(EventIterator itr) {
		// Create a new session for this that can be closed in the end
		Session session2 = null;
		try {
			// Create local session
			session2 = repository.loginAdministrative(null);
			 System.out.println(":::::::: Inside onEvent ::::::::::");
			while (itr.hasNext()) {
				Event eachEvent = itr.nextEvent();
				String path;
				path = eachEvent.getPath();
				Node node = session2.getNode(path);
				System.out.println("Node Path ::: " + node.getName()+ " Event path :: " + path);
				if(node.getName().equals("viv"))
				{
					System.out.println("::::Condition Satisfied :::");
					 node.setProperty("cq:attribute", "nodea");
					 node.setProperty("cq:attributeDemo", "true");
		                //setting the property
		                //node.save();  
		                session.save(); 
		                session2.save();
		                for(PropertyIterator propeIterator = node.getProperties() ; propeIterator.hasNext();)  
		                {  
		                     Property prop= propeIterator.nextProperty();  
		                     if(prop.isMultiple()) // This condition checks for properties whose type is String[](String array)  
		                     {  
		                          Property propVal = node.getProperty(prop.getName());     
		                          Value[] values = propVal.getValues();  
		                          for(Value val: values){  
		                          System.out.println("Value itr :: "+val.getString()); // this will output the value in string format  
		                     }  
		                     }else if(!prop.getDefinition().isMultiple()){  
		                         System.out.println("Name :: "+prop.getName());
		                         System.out.println("Value :: "+prop.getValue().getString());
		                     }  
		                }  
					
				}
				
				// Create property for event
				final Map<String, Serializable> props = new HashMap<String, Serializable>();
				// ADD ALL PROPS HERE FOR EVENT
				// Create Event
				final Job eventJob = new Job() {
					public void execute(JobContext context) {
						System.out.println(":::: Inside Execute ::::");
						org.osgi.service.event.Event event = new org.osgi.service.event.Event("Create Node", context.getConfiguration());
						System.out.println("Event Topic" + event.getTopic());
						eventAdmin.postEvent(event);
					}
				};
				// One Job per path
				final long delay = 30 * 1000;
				final Date fireDate = new Date();
				fireDate.setTime(System.currentTimeMillis() + delay);
				
				this.scheduler.fireJobAt(path, eventJob, props, fireDate);

			}
		} catch (RepositoryException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (session2 != null) {
				session2.logout();
				session2 = null;
			}
		}
	}
}
