package it.ndr.brt;

import org.apache.camel.builder.RouteBuilder;

public class RestRouteBuilder extends RouteBuilder {

    private String from;
    private String to;

    public RestRouteBuilder() {
        this("direct:upload", "mock:update");
    }

    public RestRouteBuilder(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration().component("restlet")
                .host("localhost").port(8080);

        rest().post("/upload").to(from);

        from(from)
        .setBody(simple("OK!"))
        .to(to);
    }

}
