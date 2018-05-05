package de.plapadoo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.reactivex.Maybe;

@Path("/foo")
public class TestEndpoint {
  @GET
  public Maybe<Response> get() {
    return Maybe.just(Response.status(Response.Status.BAD_REQUEST).build());
  }
}
