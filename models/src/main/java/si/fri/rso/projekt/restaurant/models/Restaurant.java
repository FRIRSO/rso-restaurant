package si.fri.rso.projekt.restaurant.models;

public class Restaurant {

    private int restaurantID;
    private int menuID;
    private String name;

    public Restaurant(int restaurantID, int menuID, String name) {
        this.restaurantID = restaurantID;
        this.menuID = menuID;
        this.name = name;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
