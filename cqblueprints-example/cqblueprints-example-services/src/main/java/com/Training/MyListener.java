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
 * @author yogeshupadhyay
 * 
 */
@Component(metatype = false, immediate = true, label = "Event heading End Date Chnange Listner", description = "This component will listen to change in event end date node")
@Service(value = { EventListener.class })
public class MyListener implements EventListener {
	@Reference
	private SlingRepository repository;
	@Reference
	private EventAdmin eventAdmin;
	@Reference
	private Scheduler scheduler;

	private Session session;
	private ObservationManager observationManager;

	// private Logger log = LoggerFactory.getLogger(this.getClass());

	// On component activate register event listener for path
	@Activate
	protected void activate(ComponentContext context) {
		try {
			session = repository.loginAdministrative(null);
			// Listen for changes to our orders
			System.out.println("::: Inside Activate :::");
			if (repository.getDescriptor(
					Repository.OPTION_OBSERVATION_SUPPORTED).equals("true")) {
				observationManager = session.getWorkspace()
						.getObservationManager();
				final String[] types = { "nt:unstructured" };
				observationManager.addEventListener(this, Event.NODE_ADDED,
						"/", true, null, types, false);
		//		System.out.println("::: Inside Event :::");
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

	/**
	 * on Event check if node qulifies and meet all criteria Then add that node
	 * to scheduler with end time as end date
	 */

	public void onEvent(EventIterator itr) {
		// Create a new session for this that can be closed in the end
		Session session2 = null;
		try {
			// Create local session
			session2 = repository.loginAdministrative(null);
			System.out.println("::: Inside Onevent ::: ");
			while (itr.hasNext()) {
				Event eachEvent = itr.nextEvent();
				String path;
				path = eachEvent.getPath();
				Node node = session2.getNode(path);
				if (true) {

					// Create property for event
					final Map<String, Serializable> props = new HashMap<String, Serializable>();
					// ADD ALL PROPS HERE FOR EVENT
					// Create Event
					final Job eventJob = new Job() {
						public void execute(JobContext context) {
							org.osgi.service.event.Event event = new org.osgi.service.event.Event(
									"", context.getConfiguration());
							eventAdmin.postEvent(event);
						}
					};
					final long delay = 30 * 1000;
					final Date fireDate = new Date();
					fireDate.setTime(System.currentTimeMillis() + delay);
					this.scheduler.fireJobAt(path, eventJob, props, fireDate);
				}
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