import java.util.ArrayList;
import java.util.List;

public class Accommodation {
    private RoomType roomType;
    private List<String> services;

    public Accommodation(RoomType roomType) {
        this.roomType = roomType;
        this.services = new ArrayList<>();
    }

    public void addService(String service) {
        services.add(service);
    }

    public void removeService(String service) {
        services.remove(service);
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public List<String> getServices() {
        return services;
    }
    public float calculateTotalCost() {
        float basePrice = roomType.getPrice();
        float totalCost = basePrice;
        for (String service : services) {
            totalCost += getServiceCost(service);
        }

        return totalCost;
    }
    private float getServiceCost(String service) {
        float cost = 0.0f;
        switch (service.toLowerCase()) {
            case "transportation":
                cost += 40.0f; 
                break;
            case "breakfast":
                cost += 20.0f; 
                break;
            case "lunch":
                cost += 30.0f; 
                break;
            case "dinner":
                cost += 40.0f; 
                break;
            case "Drinks":
                cost += 5.0f;
                break;
            case "movies":
                cost += 2.0f; 
                break;
            case "extra channels":
                cost += 10.0f; 
                break;
            default:
                // Handle unknown services
                break;
        }
        return cost;
    }
}
