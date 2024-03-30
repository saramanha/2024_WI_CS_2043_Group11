import java.util.Scanner;

public class HotelManagementSystem {
    private static RoomInventoryManager roomInventoryManager = new RoomInventoryManager();
    private static BookingManager bookingManager = new BookingManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        roomInventoryManager.initializeRoomInventory(scanner);
        boolean running = true;
        while (running) {
            System.out.println("Choose an operation:");
            System.out.println("1. Add a booking");
            System.out.println("2. Modify a booking");
            System.out.println("3. Remove a booking");
            System.out.println("4. Retrieve a booking by last name");
            System.out.println("5. Display available rooms");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addBooking(scanner, bookingManager);
                    break;
                case 2:
                    modifyBooking(scanner, bookingManager);
                    break;
                case 3:
                    removeBooking(scanner, bookingManager);
                    break;
                case 4:
                    retrieveBookingByLastName(scanner, bookingManager);
                    break;
                case 5:
                    roomInventoryManager.displayAvailableRooms();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }

    private static void addBooking(Scanner scanner, BookingManager bookingManager) {
        System.out.println("Enter booking details:");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Room Number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter room type (1 - Single Room, 2 - Regular Suite, 3 - Connecting Room, 4 - Deluxe Suite, 5 - VIP Suite):");
        int roomTypeIndex = scanner.nextInt();
        RoomType roomType = RoomType.values()[roomTypeIndex - 1];
        scanner.nextLine(); 

        System.out.print("Number of People: ");
        int numPeople = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter food accommodation (1 - Breakfast, 2 - Breakfast & Lunch, 3 - Breakfast, Lunch, Dinner & Bar):");
        int foodAccommodationIndex = scanner.nextInt();
        FoodAccommodation foodAccommodation = FoodAccommodation.values()[foodAccommodationIndex - 1];
        scanner.nextLine(); 

        System.out.print("Check-In Date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();

        System.out.print("Check-Out Date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        System.out.print("Transportation needed (true/false): ");
        boolean transportation = scanner.nextBoolean();

        System.out.print("Movies and Channels access (true/false): ");
        boolean moviesAndChannels = scanner.nextBoolean();

        System.out.print("Loyalty Member (true/false): ");
        boolean loyaltyMember = scanner.nextBoolean();

        System.out.print("Paid for Year (true/false): ");
        boolean paidForYear = scanner.nextBoolean();
        scanner.nextLine(); 

        bookingManager.addBooking(name, email, phone, roomNumber, roomType, numPeople, foodAccommodation, checkInDate, checkOutDate, transportation, moviesAndChannels, loyaltyMember, paidForYear, roomInventoryManager);
    }

    private static void removeBooking(Scanner scanner, BookingManager bookingManager) {
        System.out.println("Enter booking ID to remove:");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); 

        bookingManager.removeBooking(bookingId);
        System.out.println("Booking removed successfully for ID: " + bookingId);
    }

    private static void modifyBooking(Scanner scanner, BookingManager bookingManager) {
        System.out.println("Enter the unique ID of the booking you want to modify:");
        int uniqueId = scanner.nextInt();
        scanner.nextLine(); 
        bookingManager.modifyBooking(uniqueId, scanner);
    }

    private static void retrieveBookingByLastName(Scanner scanner, BookingManager bookingManager) {
        System.out.println("Enter last name for booking retrieval:");
        String lastName = scanner.nextLine();

        bookingManager.retrieveBookingByLastName(lastName);
    }
    
}
