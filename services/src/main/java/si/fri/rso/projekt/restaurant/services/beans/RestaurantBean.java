package si.fri.rso.projekt.restaurant.services.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
//import si.fri.rso.projekt.buyers.models.Buyer;
import si.fri.rso.projekt.restaurant.services.configuration.AppProperties;
import si.fri.rso.projekt.restaurant.models.MongoRestaurant;
import si.fri.rso.projekt.restaurant.models.Restaurant;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class RestaurantBean {

    private Logger log = Logger.getLogger(RestaurantBean.class.getName());

    private Client httpClient;

    private ObjectMapper objectMapper;

    @Inject
    private AppProperties appProperties;

    @Inject
    @DiscoverService("rso-restaurant")
    private Optional<String> url;

    @Inject
    @DiscoverService("rso-buyer")
    private Optional<String> containerUrl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        objectMapper = new ObjectMapper();
    }

    public String readConfig() {
        if (appProperties.isExternalServicesEnabled())
            return "ext service enabled!";
        return "ext service disabled";
    }


    public void setConfig(boolean config) {
        appProperties.setExternalServicesEnabled(config);
    }

    public List<Restaurant> getRestaurants() {
        MongoRestaurant mb = new MongoRestaurant();

        log.logp(Level.INFO, "RestaurantBean", "getRestaurants()", "getRestaurant call");
        return mb.getAllRestaurant();
    }

    public Restaurant getRestaurants(Integer restaurantID) {
        MongoRestaurant mb = new MongoRestaurant();

        Restaurant restaurant= mb.getRestaurant(restaurantID);

        if(restaurantID == null) {
            return null;
        }

        return restaurant;
    }
}
