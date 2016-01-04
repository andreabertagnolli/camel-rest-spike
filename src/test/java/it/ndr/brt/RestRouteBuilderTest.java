package it.ndr.brt;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class RestRouteBuilderTest extends CamelTestSupport {

    public static final String FROM = "direct:start";
    public static final String TO = "mock:result";

    @Produce(uri = FROM)
    protected ProducerTemplate template;

    @EndpointInject(uri = TO)
    protected MockEndpoint resultEndpoint;

    @Test
    public void when_payload_is_valid_returns_ok() throws InterruptedException {
        resultEndpoint.expectedBodiesReceived("OK!");

        template.sendBody("Test");

        resultEndpoint.assertIsSatisfied();
    }

    @Test
    public void when_payload_is_empty_returns_error() throws InterruptedException {
        resultEndpoint.expectedHeaderReceived(Exchange.HTTP_RESPONSE_CODE, "422");

        template.sendBody(null);

        resultEndpoint.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RestRouteBuilder(FROM, TO);
    }
}
