package io.edrb.transferservice.base;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.rules.ExternalResource;


public class JettyServerResource extends ExternalResource {

    private Server jettyServer = new Server(8080);

    protected void before() throws Throwable {

        System.out.println("Start jettyServer");

        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        ctx.setContextPath("/transfer-service");

        jettyServer.setHandler(ctx);

        ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/api/*");
        servletHolder.setInitOrder(1);
        //servletHolder.setInitParameter("jersey.config.server.provider.packages","io.edrb.transferservice.resource");
        servletHolder.setInitParameter("javax.ws.rs.Application","io.edrb.transferservice.config.TransferServiceConfig");

        jettyServer.start();
        jettyServer.join();

        System.out.println("JettyServer started");
    }

    protected void after() {
        if (this.jettyServer.isRunning()) {
            try {
                System.out.println("Stop jettyServer");
                jettyServer.stop();
                System.out.println("JettyServer stopped");
            } catch (Exception e) {
                System.out.println("Exception while stopping JettyServer");
            }
        }
    }
}
