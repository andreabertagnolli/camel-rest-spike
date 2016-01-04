package it.ndr.brt;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UploadRouteIT {

    @BeforeClass
    public static void setup() {
        RestApplication main = new RestApplication();
        main.start();
    }

    @Test
    public void when_body_is_empty_return_422_error() {
        Response response = ClientBuilder.newClient().target("http://localhost:8080/upload").request().post(Entity.entity(null, MediaType.TEXT_PLAIN_TYPE));

        Assert.assertEquals(422, response.getStatus());
    }

    @Test
    public void when_body_is_not_empty_return_200() {
        Response response = ClientBuilder.newClient().target("http://localhost:8080/upload").request().post(Entity.entity("SOMETHING", MediaType.TEXT_PLAIN_TYPE));

        Assert.assertEquals(200, response.getStatus());
    }
}
