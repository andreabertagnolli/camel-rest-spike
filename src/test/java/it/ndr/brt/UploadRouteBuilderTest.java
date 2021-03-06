package it.ndr.brt;

import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class UploadRouteBuilderTest extends CamelTestSupport {

    public static final String FROM = "direct:start";
    public static final String TO = "mock:result";
    public static final String DATABASE = "mock:db";

    @Produce(uri = FROM)
    protected ProducerTemplate template;

    @EndpointInject(uri = TO)
    protected MockEndpoint resultEndpoint;

    @EndpointInject(uri = DATABASE)
    protected MockEndpoint databaseEndpoint;

    @Test
    public void when_payload_is_valid_xml_returns_ok_and_write_to_db() throws InterruptedException {
        resultEndpoint.expectedHeaderReceived(HTTP_RESPONSE_CODE, "200");
        resultEndpoint.expectedBodiesReceived("OK!");
        databaseEndpoint.expectedMessageCount(1);

        template.sendBody("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><test>ok</test>");

        databaseEndpoint.assertIsSatisfied();
        resultEndpoint.assertIsSatisfied();
    }

    @Test
    public void when_payload_is_not_a_valid_xml_returns_bad_request() throws InterruptedException {
        resultEndpoint.expectedHeaderReceived(HTTP_RESPONSE_CODE, "422");

        template.sendBody("<test>ok!</test");

        resultEndpoint.assertIsSatisfied();
    }

    @Test
    public void when_payload_is_empty_returns_error() throws InterruptedException {
        resultEndpoint.expectedHeaderReceived(HTTP_RESPONSE_CODE, "422");

        template.sendBody(null);

        resultEndpoint.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new UploadRouteBuilder(FROM, TO, DATABASE);
    }
}
