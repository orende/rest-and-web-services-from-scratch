package se.citerus;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import se.citerus.resources.HelloWorldResource;

public class RestExampleApplication extends Application<RestExampleConfiguration> {

    public static void main(final String[] args) throws Exception {
        new RestExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "RestExample";
    }

    @Override
    public void initialize(final Bootstrap<RestExampleConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final RestExampleConfiguration configuration,
                    final Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);
    }
}
