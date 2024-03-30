import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

enum RoomType {
    SINGLE_ROOM,
    REGULAR_SUITE,
    CONNECTING_ROOM,
    DELUXE_SUITE,
    VIP_SUITE
}

enum FoodAccommodation {
    BREAKFAST,
    BREAKFAST_AND_LUNCH,
    BREAKFAST_LUNCH_DINNER_BAR
} 

public class RoomInventoryManager {
    private Map<RoomType, Integer> totalRoomsByType = new HashMap<>();
    private Map<RoomType, Integer> availableRoomsByType = new HashMap<>();

    public void initializeRoomInventory(Scanner scanner) {
        for (RoomType type : RoomType.values()) {
            System.out.print("Enter total number of " + type.name() + " rooms: ");
            int count = scanner.nextInt();
            totalRoomsByType.put(type, count);
            availableRoomsByType.put(type, count);
        }
        scanner.nextLine(); 
        System.out.println("Room inventory initialized successfully.");
    }

    public boolean bookRoom(RoomType roomType) {
        Integer available = availableRoomsByType.get(roomType);
        if (available == null || available <= 0) {
            System.out.println("No available rooms of type: " + roomType);
            return false;
        }
        availableRoomsByType.put(roomType, available - 1);
        System.out.println("Room booked successfully.");
        return true;
    }

    public void releaseRoom(RoomType roomType) {
        Integer available = availableRoomsByType.get(roomType);
        if (available == null || available >= totalRoomsByType.get(roomType)) {
            System.out.println("Maximum room capacity reached, cannot release more rooms of type: " + roomType);
            return;
        }
        availableRoomsByType.put(roomType, available + 1);
        System.out.println("Room released successfully.");
    }
    public void decrementRoomAvailability(RoomType roomType) {
        availableRoomsByType.computeIfPresent(roomType, (key, val) -> val > 0 ? val - 1 : 0);
    }

    public void incrementRoomAvailability(RoomType roomType) {
        availableRoomsByType.computeIfPresent(roomType, (key, val) -> val + 1);
    }

    public void displayAvailableRooms() {
        availableRoomsByType.forEach((type, count) -> System.out.println(type.name() + ": " + count + " available"));
    }
}
