package it.ndr.brt;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class RestRouteBuilder extends RouteBuilder {

    private String from;
    private String to;

    public RestRouteBuilder() {
        this("direct:upload", "mock:result");
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
        .choice()
        .when(body().isEqualTo(null))
            .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("422"))
        .otherwise()
            .setBody(simple("OK!"))
        .to(to);
    }

}
