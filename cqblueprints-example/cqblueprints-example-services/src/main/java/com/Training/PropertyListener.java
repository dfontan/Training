package com.Training;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
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
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
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
@Component(metatype = false, immediate = true, label = "Event heading End Date Chnange Listner", description = "This component will listen to change in event end date node")
@Service(value = { EventListener.class })
public class PropertyListener implements EventListener {
	@Reference
	private SlingRepository repository;
	@Reference
	private EventAdmin eventAdmin;
	@Reference
	private Scheduler scheduler;

	private Session session;
	private ObservationManager observationManager;

	 @Reference  
	 private ResourceResolverFactory resourceResolverFactory;
	 private ResourceResolver resourceResolver;  
	// On component activate register event listener for path
	@Activate
	protected void activate(ComponentContext context) {  
	      try{  
	           resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);  
	           session = repository.loginAdministrative(null);  
	           ObservationManager obsManager = session.getWorkspace().getObservationManager();  
	           obsManager.addEventListener(this, Event.NODE_ADDED, "/content/usergenerated/content/", true, null, null, false);  
	//listening to events
	      }catch(Exception e){  
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

	public void onEvent(EventIterator events) {  
	      while(events.hasNext()){  
	           Event myEvent = events.nextEvent();  
	           try {  
	                Resource resource = resourceResolver.resolve(myEvent.getPath());  
	                Node node = resource.adaptTo(Node.class);  
	                node.setProperty("cq:distribute", true);  //setting the property
	                //node.save();  
	                session.save();  
	           } catch (RepositoryException e) {  
	                e.printStackTrace();  
	           }  
	      }  
	 }
}
