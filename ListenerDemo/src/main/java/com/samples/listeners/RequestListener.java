package com.samples.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener {

   
    public void requestDestroyed(ServletRequestEvent sre)  { 
    	System.out.println("Request Destroyed");
    }

    public void requestInitialized(ServletRequestEvent sre)  { 
    System.out.println("Request created");
    }
	
}
