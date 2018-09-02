package se.citerus.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldResourceIntegrationTest {

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new HelloWorldResource("Hello %s", "default"))
            .build();

    @Test
    public void shouldReturnDefaultNameAnd200ForValidRequest() {
        Response response = resources
                .target("/greetings/hello")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(String.class)).isEqualTo("{\"id\":1,\"content\":\"Hello default\"}");
    }

    @Test
    public void shouldReturn404ForRequestToUnknownPath() {
        Response response = resources
                .target("/greetings/goodbye")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(404);
    }
}
