package it.ndr.brt;

import org.apache.camel.CamelContext;
import org.junit.Test;
import org.mockito.Mockito;

public class RestApplicationTest {

    @Test
    public void test() throws Exception {
        CamelContext context = Mockito.mock(CamelContext.class);

        RestApplication spike = new RestApplication(context);
        spike.start();

        Mockito.verify(context).addRoutes(Mockito.isA(RestRouteBuilder.class));
        Mockito.verify(context).start();
    }
}
