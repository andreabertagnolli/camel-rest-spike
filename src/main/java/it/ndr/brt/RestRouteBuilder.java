package it.ndr.brt;

import org.apache.camel.builder.RouteBuilder;

public class RestRouteBuilder extends RouteBuilder {

    private String from;
    private String to;

    public RestRouteBuilder() {
        this("restlet:http://localhost:8080/upload?restletMethod=post", "mock:update");
    }

    public RestRouteBuilder(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void configure() throws Exception {
        from(from)
        .setBody(simple("OK!"))
        .to(to);
    }

}
