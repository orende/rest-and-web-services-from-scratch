package se.citerus.resources;

import org.apache.commons.lang3.Validate;
import se.citerus.api.Key;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/keys")
@Produces(MediaType.APPLICATION_JSON)
public class KeyResource {

    private final List<Key> keys;
    private final AtomicInteger counter;

    public KeyResource() {
        this.keys = Collections.synchronizedList(new ArrayList<Key>());
        this.counter = new AtomicInteger(1);
    }

    @POST
    public Response createKey(@QueryParam("name") String name, @QueryParam("cutting") String cutting) {
        Validate.notEmpty(name);
        Validate.notEmpty(cutting);
        Key key = new Key(counter.getAndIncrement(), name, cutting);
        keys.add(key);
        return Response.created(URI.create("/api/keys/" + key.id)).build();
    }

    @GET
    @Path("/{id}")
    public Response getKey(@PathParam("id") Integer id) {
        Optional<Key> keyOptional = keys.stream().filter(k -> k.id == id).findFirst();
        return keyOptional.isPresent() ? Response.ok(keyOptional.get()).build() : Response.status(404).build();
    }

    @GET
    public Response getKeys() {
        return Response.ok(keys).build();
    }
}
