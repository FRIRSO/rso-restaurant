package si.fri.rso.projekt.restaurant.models;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class MongoRestaurant {


    private final String DBUser       = "root";
    private final String DBPassword   = "13tpxnxJTwUScc3V";
    private final String DBName       = "db-restaurant";
    private final String DBCollection = "restaurants";

    private MongoClient connectDB() {
        MongoClientURI uri = new MongoClientURI("mongodb://"+ DBUser +":"+ DBPassword +"@gsascluster-shard-00-00-ocnkx.azure.mongodb.net:27017," +
                "gsascluster-shard-00-01-ocnkx.azure.mongodb.net:27017,gsascluster-shard-00-02-ocnkx.azure.mongodb.net:27017/test?" +
                "ssl=true&replicaSet=gsasCluster-shard-0&authSource=admin&retryWrites=true");

        return new MongoClient(uri);
    }

    public List<Restaurant> getAllRestaurant() {
        MongoClient client = connectDB();

        MongoDatabase db = client.getDatabase(DBName);

        MongoCollection<Document> queueCollection = db.getCollection(DBCollection);

        List<Restaurant> results = new ArrayList<>();

        for(Document curr : queueCollection.find()) {

            Restaurant restaurant = new Restaurant(curr.getInteger("restaurantID"),
                                    curr.getInteger("menuID"),
                                    curr.getString("name"));

            results.add(restaurant);
        }

        return results;
    }

    public Restaurant getRestaurant(Integer restaurantID) {
        MongoClient client = connectDB();
        MongoDatabase db = client.getDatabase(DBName);
        MongoCollection<Document> bc = db.getCollection(DBCollection);

        Bson filter = Filters.eq("restaurantID", restaurantID);

        Document result = bc.find(filter).first();

        if(result == null) {
            return null;
        }


        return new Restaurant(result.getInteger("restaurantID"),
                result.getInteger("menuID"),
                result.getString("name"));
    }
}
