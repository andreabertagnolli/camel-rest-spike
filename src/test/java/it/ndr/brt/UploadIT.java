package it.ndr.brt;

import static javax.ws.rs.client.Entity.entity;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UploadIT {

    private WebTarget target;

    @BeforeClass
    public static void serviceInitialization() {
        new RestApplication().start();
    }

    @Before
    public void setup() {
        target = ClientBuilder.newClient().target("http://localhost:8080/upload");
    }

    @Test
    public void when_body_is_empty_return_422_error() {
        Response response = target.request().post(entity(null, MediaType.TEXT_PLAIN_TYPE));

        assertEquals(422, response.getStatus());
    }

    @Test
    public void when_body_is_an_xml_return_200() {
        Response response = target.request().post(entity("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><test>SOMETHING</test>", MediaType.TEXT_PLAIN_TYPE));

        assertEquals(200, response.getStatus());
    }
}
