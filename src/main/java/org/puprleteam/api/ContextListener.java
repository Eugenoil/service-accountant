package org.puprleteam.api;


import org.puprleteam.interfaces.TrackingService;
import org.puprleteam.service.TrackingServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Загружается до создания всех сервлетов.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private TrackingService trackingService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        trackingService = new TrackingServiceImpl();

        servletContext.setAttribute("trackingService", trackingService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

