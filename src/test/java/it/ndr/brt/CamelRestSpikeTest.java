package it.ndr.brt;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;
import org.mockito.Mockito;

public class CamelRestSpikeTest {

    @Test
    public void test() throws Exception {
        CamelContext context = Mockito.mock(CamelContext.class);

        CamelRestSpike spike = new CamelRestSpike(context);
        spike.start();

        Mockito.verify(context).addRoutes(Mockito.isA(RestRouteBuilder.class));
        Mockito.verify(context).start();
    }
}
