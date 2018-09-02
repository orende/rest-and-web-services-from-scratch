package se.citerus.resources;

import org.junit.Test;
import se.citerus.api.Saying;

import java.util.Optional;

import static org.junit.Assert.*;

public class HelloWorldResourceTest {

    @Test
    public void shouldReturnDefaultNameIfOmitted() throws Exception {
        HelloWorldResource resource = new HelloWorldResource("Hello %s", "default");

        Saying response = resource.sayHello(Optional.empty());

        assertEquals("Hello default", response.getContent());
    }

    @Test
    public void shouldReturnSubmittedNameIfSet() throws Exception {
        HelloWorldResource resource = new HelloWorldResource("Hello %s", "default");

        Saying response = resource.sayHello(Optional.of("world"));

        assertEquals("Hello world", response.getContent());
    }
}