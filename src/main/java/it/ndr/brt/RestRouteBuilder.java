package it.ndr.brt;

import org.apache.camel.builder.RouteBuilder;

public class RestRouteBuilder extends RouteBuilder {

    private String from;
    private String to;

    public RestRouteBuilder() {
        this("rest:something", "result:something");
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
