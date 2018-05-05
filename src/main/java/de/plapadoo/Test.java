package de.plapadoo;

import net.winterly.rxjersey.server.rxjava2.RxJerseyServerFeature;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Test {
  public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
    ResourceConfig rc = new ResourceConfig();
    rc.register(RxJerseyServerFeature.class);
    rc.registerResources(Resource.builder(TestEndpoint.class).build());
    final Logger logger = LogManager.getLogManager().getLogger("");
    logger.setLevel(Level.FINE);
    final ConsoleHandler handler = new ConsoleHandler();
    handler.setLevel(Level.ALL);
    logger.addHandler(handler);
    rc.register(LoggingFeature.class);
    final URI http = new URI("http", null, "0.0.0.0", 8080, null, null, null);
    HttpServer server = GrizzlyHttpServerFactory.createHttpServer(http, rc);

    server.start();

    while (server.isStarted())
      Thread.sleep(2000);
  }
}
