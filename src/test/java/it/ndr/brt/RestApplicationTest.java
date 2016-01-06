package it.ndr.brt;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

import org.apache.camel.CamelContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestApplicationTest {

	@Mock private CamelContext context;
	
    @Test
    public void application_building() throws Exception {
        new RestApplication(context).start();

        verify(context).addRoutes(isA(RestRouteBuilder.class));
        verify(context).addRoutes(isA(UploadRouteBuilder.class));
        verify(context).start();
    }
}
