import java.util.Scanner;

public class HotelManagementSystem {
    public static void main(String[] args) {
        BookingManager bookingManager = new BookingManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Choose an operation:");
            System.out.println("1. Add a booking");
            System.out.println("2. Modify a booking");
            System.out.println("3. Remove a booking");
            System.out.println("4. Retrieve a booking by last name");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    private static void addBooking(Scanner scanner, BookingManager bookingManager) {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter phone:");
        String phone = scanner.nextLine();
        System.out.println("Enter room number:");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter room type (1 - Single Room, 2 - Regular Suite, 3 - Connecting Room, 4 - Deluxe Suite, 5 - VIP Suite):");
        int roomTypeChoice = scanner.nextInt();
        RoomType roomType = RoomType.values()[roomTypeChoice - 1];
        scanner.nextLine(); // Consume newline
        System.out.println("Enter number of people:");
        int numPeople = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter food accommodation (1 - Breakfast, 2 - Breakfast & Lunch, 3 - Breakfast, Lunch, Dinner & Bar):");
        int foodAccommodationChoice = scanner.nextInt();
        FoodAccommodation foodAccommodation = FoodAccommodation.values()[foodAccommodationChoice - 1];
        scanner.nextLine(); // Consume newline
        System.out.println("Do you want movies & extra channels? (Y/N): ");
        String moviesAndChannelsChoice = scanner.nextLine();
        boolean moviesAndChannels = moviesAndChannelsChoice.equalsIgnoreCase("Y");
        System.out.println("Do you need transportation? (Y/N): ");
        String transportationChoice = scanner.nextLine();
        boolean transportation = transportationChoice.equalsIgnoreCase("Y");
        System.out.println("Is the customer a loyalty member? (Y/N):");
        String loyaltyMemberChoice = scanner.nextLine();
        boolean loyaltyMember = loyaltyMemberChoice.equalsIgnoreCase("Y");

        // Ask if the customer has paid for the year
        System.out.println("Has the customer paid for this year? (Y/N):");
        String paidForYearChoice = scanner.nextLine();
        boolean paidForYear = paidForYearChoice.equalsIgnoreCase("Y");

        System.out.println("Enter check-in date (YYYY-MM-DD):");
        String checkInDate = scanner.nextLine();
        System.out.println("Enter check-out date (YYYY-MM-DD):");
        String checkOutDate = scanner.nextLine();

        bookingManager.addBooking(name, email, phone, roomNumber, roomType, numPeople, foodAccommodation, checkInDate, checkOutDate, transportation, moviesAndChannels, loyaltyMember, paidForYear);
    }

    private static void modifyBooking(Scanner scanner, BookingManager bookingManager) {
        System.out.println("Enter the unique ID of the booking you want to modify:");
        int uniqueId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        // Assuming you want to modify all details
        addBooking(scanner, bookingManager);
    }

    private static void removeBooking(Scanner scanner, BookingManager bookingManager) {
        System.out.println("Enter the unique ID of the booking you want to remove:");
        int uniqueId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        bookingManager.removeBooking(uniqueId);
    }

    private static void retrieveBookingByLastName(Scanner scanner, BookingManager bookingManager) {
        System.out.println("Enter the last name to retrieve bookings:");
        String lastName = scanner.nextLine();
        bookingManager.retrieveBookingByLastName(lastName);
    }
}
