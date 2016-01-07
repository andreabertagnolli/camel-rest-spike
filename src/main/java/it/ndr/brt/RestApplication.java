package it.ndr.brt;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

public class RestApplication {

    private final CamelContext context;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.enableHangupSupport();
        main.addRouteBuilder(new RestRouteBuilder());
        main.addRouteBuilder(new UploadRouteBuilder());
        main.run();
    }

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
