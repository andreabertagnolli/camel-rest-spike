package it.ndr.brt;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RouteTest {

    @Test
    public void test() {
        CamelRestSpike main = new CamelRestSpike();
        main.start();

        Response response = ClientBuilder.newClient().target("http://localhost:8080/upload").request().post(Entity.entity("entity", MediaType.TEXT_PLAIN_TYPE));

        Assert.assertEquals(200, response.getStatus());
    }
}
