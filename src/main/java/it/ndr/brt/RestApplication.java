package it.ndr.brt;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class RestApplication {

    private final CamelContext context;

    public RestApplication() {
        this.context = new DefaultCamelContext();
    }

    public RestApplication(CamelContext context) {
        this.context = context;
    }

    public void start() {
        try {
            context.addRoutes(new RestRouteBuilder());
            context.addRoutes(new UploadRouteBuilder());
            context.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
