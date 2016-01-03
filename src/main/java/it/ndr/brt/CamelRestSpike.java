package it.ndr.brt;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelRestSpike {

    private final CamelContext context;

    public CamelRestSpike() {
        this.context = new DefaultCamelContext();
    }

    public CamelRestSpike(CamelContext context) {
        this.context = context;
    }

    public void start() {
        try {
            context.addRoutes(new RestRouteBuilder());
            context.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
