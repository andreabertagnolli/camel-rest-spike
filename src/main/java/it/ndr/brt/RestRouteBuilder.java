package it.ndr.brt;

import org.apache.camel.builder.RouteBuilder;

public class RestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("restlet")
                .host("localhost").port(8080);

        rest().post("/upload").to(UploadRouteBuilder.ENDPOINT);
    }

}
