package it.ndr.brt;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class UploadRouteBuilder extends RouteBuilder {

    public static final String ENDPOINT = "direct:upload";

    private String from;
    private String to;

    public UploadRouteBuilder() {
        this(ENDPOINT, "mock:result");
    }

    public UploadRouteBuilder(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void configure() throws Exception {
        from(from)
        .choice()
        .when(body().isEqualTo(null))
            .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("422"))
        .otherwise()
            .setBody(simple("OK!"))
        .to(to);
    }

}
