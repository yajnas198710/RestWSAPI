package com.reno;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
 
 
public class RestServer {
    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
       
        Server jettyServer = new Server(8282);
        jettyServer.setHandler(context);
 
        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
 
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                AccountManager.class.getCanonicalName());
 
        try {
            jettyServer.start();
            jettyServer.join();
            testService();
        } finally {
            jettyServer.destroy();
        }
    }

	private static void testService() {
		 
		
	}
}