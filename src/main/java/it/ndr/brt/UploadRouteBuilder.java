package it.ndr.brt;

import static it.ndr.brt.Predicates.isAValidXML;
import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;

import org.apache.camel.builder.RouteBuilder;

public class UploadRouteBuilder extends RouteBuilder {

    public static final String ENDPOINT = "direct:upload";

    private String from;
    private String to;
	private String database;

    public UploadRouteBuilder() {
        this(ENDPOINT, "mock:result", "mock:db");
    }

    public UploadRouteBuilder(String from, String to, String database) {
        this.from = from;
        this.to = to;
		this.database = database;
    }

    @Override
    public void configure() throws Exception {
        from(from)
        .choice()
            .when(isAValidXML())
                .setBody(simple("OK!"))
                .setHeader(HTTP_RESPONSE_CODE, simple("200"))
                .to(database)
            .otherwise()
                .setHeader(HTTP_RESPONSE_CODE, simple("422"))
        .end()
        .to(to);
    }

}
