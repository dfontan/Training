package com.Training.impl;


import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

import com.Training.*;

@Component(label = "Hellow world", description = "Hellow world Service", metatype = true, immediate = true, enabled = true)
@Service(HelloWorld.class)
public class HelloWorldImpl implements HelloWorld
{

    public String getName()
    {
        return "Called by Service Intelligrape";
    }

}