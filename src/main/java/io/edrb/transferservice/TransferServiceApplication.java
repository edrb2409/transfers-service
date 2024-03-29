package io.edrb.transferservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TransferServiceApplication {

    private static final Logger LOGGER = Logger.getLogger("TransferServiceApplication");

    public static void main(String[] args) {
        Server server = new Server(8080);

        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        ctx.setContextPath("/transfer-service");

        server.setHandler(ctx);

        ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/api/*");
        servletHolder.setInitOrder(1);
        //servletHolder.setInitParameter("jersey.config.server.provider.packages","io.edrb.transferservice.resource");
        servletHolder.setInitParameter("javax.ws.rs.Application","io.edrb.transferservice.config.TransferServiceConfig");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        } finally {
            server.destroy();
        }

    }

}
