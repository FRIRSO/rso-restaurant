package si.fri.rso.projekt.restaurant.api.v1.resources;


import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.projekt.restaurant.models.Restaurant;
import si.fri.rso.projekt.restaurant.services.beans.RestaurantBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("restaurants")
@Log
public class RestaurantApi {

    @Inject
    private RestaurantBean restaurantBean;


    @GET
    @Path("service")
    public Response service() {
        return Response.status(Response.Status.OK).entity(restaurantBean.readConfig()).build();
    }

    @GET
    @Path("disable")
    public Response test4() {
        restaurantBean.setConfig(false);
        String response = "OK";
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("enable")
    public Response test5() {
        restaurantBean.setConfig(true);
        String response = "OK";
        return Response.status(Response.Status.OK).entity(response).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurants() {
        return Response.ok(restaurantBean.getRestaurants()).build();
    }

    @GET
    @Path("/{restaurantID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurantByID(@PathParam("restaurantID") Integer restaurantID) {
        Restaurant queue = restaurantBean.getRestaurants(restaurantID);

        if(queue != null) {
            return Response.ok(queue).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
