package com.Training;

import javax.jcr.Node;
import javax.jcr.Session;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.event.EventUtil;
import org.apache.sling.event.JobProcessor;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yogeshupadhyay
 *
 */

@Component(metatype = false, immediate = true, label = "SOME LABEL",
description="This component will change event heading end date property at specific time")
@Service(value = {JobProcessor.class,EventHandler.class })
@Property(name="event.topics", value="Create Node")
@SuppressWarnings("deprecation")
public class EventEndDateProcessor implements EventHandler,JobProcessor{
/** Default log. */
   // protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Reference
   private SlingRepository repository;
   
   public void handleEvent(Event event) {
	   System.out.println(":::::::: Inside handleEvent ::::::::::");
    if (EventUtil.isLocal(event)) {
    EventUtil.processJob(event, this);
    }
}

public boolean process(Event event){
	Session session=null;
		try {
		System.out.println("In process method");
		session = repository.loginAdministrative(null);
		System.out.println(":::::::: Inside Process ::::::::::");
		if(session.hasPendingChanges()) {
		session.save();
			}
		
	}

catch (Exception e) {
	System.out.println(e);
}finally{
	if(session!=null){
				session.logout();
				session=null;
				}
		}
return true;
}

}