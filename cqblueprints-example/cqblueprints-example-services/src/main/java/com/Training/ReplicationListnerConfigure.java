package com.Training;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;

@Component(metatype = true, immediate = true, label = "Replication Listner", description = "Listen to events")  
 @Service(value = ReplicationListnerConfigure.class )  
 public class ReplicationListnerConfigure implements EventListener{  
 @Reference  
 private SlingRepository slingRepo;  
 @Reference  
 private ResourceResolverFactory resourceResolverFactory;  
 private Session session;  
 private ResourceResolver resourceResolver;  
 public void activate(ComponentContext componentContext){  
      try{  
           resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);  
           session = slingRepo.loginAdministrative(null);  
           ObservationManager obsManager = session.getWorkspace().getObservationManager();  
           obsManager.addEventListener(this, Event.NODE_ADDED, "/content/usergenerated/content/", true, null, null, false);  
//listening to events
      }catch(Exception e){  
           e.printStackTrace();  
      }  
 }  
 @Override  
 public void onEvent(EventIterator events) {  
      while(events.hasNext()){  
           Event myEvent = events.nextEvent();  
           try {  
                Resource resource = resourceResolver.resolve(myEvent.getPath());  
                Node node = resource.adaptTo(Node.class);  
                node.setProperty("cq:distribute", true);
                node.setProperty("cq:attribute", "nodea");
                //setting the property
                //node.save();  
                session.save(); 
                for(PropertyIterator propeIterator = node.getProperties() ; propeIterator.hasNext();)  
                {  
                     Property prop= propeIterator.nextProperty();  
                     if(prop.isMultiple()) // This condition checks for properties whose type is String[](String array)  
                     {  
                          Property propVal = node.getProperty(prop.getName());     
                          Value[] values = propVal.getValues();  
                          for(Value val: values){  
                          System.out.println(val.getString()); // this will output the value in string format  
                     }  
                     }else if(!prop.getDefinition().isMultiple()){  
                         System.out.println(prop.getName());
                         System.out.println(prop.getValue());
                     }  
                }  
           } catch (RepositoryException e) {  
                e.printStackTrace();  
           }  
      }  
 }  
      public void deactivate(ComponentContext componentContext){  

           if(resourceResolver!=null){  
                resourceResolver.close();  
           }  
      }  
 }  