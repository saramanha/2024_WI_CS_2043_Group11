import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingManager {
    private static final String FILENAME = "bookings.txt";
    private RoomInventoryManager roomInventoryManager;

    public void addBooking(String name, String email, String phone, int roomNumber, RoomType roomType, int numPeople, FoodAccommodation foodAccommodation, String checkInDate, String checkOutDate, boolean transportation, boolean moviesAndChannels, boolean loyaltyMember, boolean paidForYear, RoomInventoryManager roomInventoryManager) {
        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate checkOut = LocalDate.parse(checkOutDate);
        this.roomInventoryManager = roomInventoryManager;
        long numOfNights = ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalCost = calculateTotalCost(roomType, foodAccommodation, numOfNights, moviesAndChannels, transportation, loyaltyMember, paidForYear);
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))) {
            writer.println(String.join(",", String.valueOf(nextUniqueId()), name, email, phone, String.valueOf(roomNumber), roomType.toString(), String.valueOf(numPeople), foodAccommodation.toString(), checkInDate, checkOutDate, String.valueOf(transportation), String.valueOf(moviesAndChannels), String.valueOf(loyaltyMember), String.valueOf(paidForYear), String.valueOf(totalCost)));
            System.out.println("Booking added successfully.");
            roomInventoryManager.decrementRoomAvailability(roomType);
        } catch (IOException e) {
            System.err.println("Error occurred while adding booking: " + e.getMessage());
        }
    }

    private double calculateTotalCost(RoomType roomType, FoodAccommodation foodAccommodation, long numOfNights, boolean moviesAndChannels, boolean transportation, boolean loyaltyMember, boolean paidForYear) {
        double costPerNight = switch (roomType) {
            case SINGLE_ROOM -> 50.0;
            case REGULAR_SUITE -> 75.0;
            case CONNECTING_ROOM -> 100.0;
            case DELUXE_SUITE -> 150.0;
            case VIP_SUITE -> 200.0;
        };
        double foodCostPerDay = switch (foodAccommodation) {
            case BREAKFAST -> 10.0;
            case BREAKFAST_AND_LUNCH -> 25.0;
            case BREAKFAST_LUNCH_DINNER_BAR -> 50.0;
        };
        double transportCost = transportation ? 20.0 : 0.0;
        double moviesCost = moviesAndChannels ? 15.0 : 0.0;
        double discount = loyaltyMember ? 0.1 : 0.0; 
        double totalCost = (costPerNight + foodCostPerDay) * numOfNights + transportCost + moviesCost;
        if (paidForYear) {
            totalCost -= 100; 
        }
        totalCost -= totalCost * discount; 
        return totalCost;
    }

    private int nextUniqueId() {
        int maxId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                int id = Integer.parseInt(line.split(",")[0]);
                if (id > maxId) {
                    maxId = id;
                }
            }
        } 
        catch (IOException e) {
            System.err.println("Error occurred while generating unique ID: " + e.getMessage());
        }
        return maxId + 1;
    }

    public void retrieveBookingByLastName(String lastName) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String[] nameParts = parts[1].split(" "); 
                if (nameParts.length > 1 && nameParts[1].trim().equalsIgnoreCase(lastName.trim())) { 
                    found = true;
                    printBookingDetails(parts);
                }
            }
            if (!found) {
                System.out.println("Booking with last name '" + lastName + "' not found.");
            }
        } catch (IOException e) {
            System.err.println("Error occurred while retrieving booking: " + e.getMessage());
        }
    }

    private void printBookingDetails(String[] bookingParts) {
        System.out.println("Unique ID: " + bookingParts[0]);
        System.out.println("Name: " + bookingParts[1]);
        System.out.println("Email: " + bookingParts[2]);
        System.out.println("Phone: " + bookingParts[3]);
        System.out.println("Room Number: " + bookingParts[4]);
        System.out.println("Room Type: " + bookingParts[5]);
        System.out.println("Number of People: " + bookingParts[6]);
        System.out.println("Food Accommodation: " + bookingParts[7]);
        System.out.println("Check-in Date: " + bookingParts[8]);
        System.out.println("Check-out Date: " + bookingParts[9]);
        System.out.println("Transportation Included: " + bookingParts[10]);
        System.out.println("Movies & Extra Channels Included: " + bookingParts[11]);
        System.out.println("Loyalty Member: " + bookingParts[12]);
        System.out.println("Paid for Year: " + bookingParts[13]);
        System.out.println("Total Cost: $" + bookingParts[14]);
    }

    public void modifyBooking(int bookingId, Scanner scanner) {
        List<String> bookings = new ArrayList<>();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int currentId = Integer.parseInt(line.split(",")[0]);
                if (currentId == bookingId) {
                    found = true;
                    String[] parts = line.split(",");
                    String name = parts[1]; 
                    String email = parts[2];
                    
                    System.out.println("Modifying booking for " + name + " (" + email + "). Enter new details:");

                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();

                    System.out.print("Room Number: ");
                    int roomNumber = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter room type (1 - Single Room, 2 - Regular Suite, 3 - Connecting Room, 4 - Deluxe Suite, 5 - VIP Suite):");
                    RoomType roomType = RoomType.values()[Integer.parseInt(scanner.nextLine()) - 1];

                    System.out.print("Number of People: ");
                    int numPeople = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter food accommodation (1 - Breakfast, 2 - Breakfast & Lunch, 3 - Breakfast, Lunch, Dinner & Bar):");
                    FoodAccommodation foodAccommodation = FoodAccommodation.values()[Integer.parseInt(scanner.nextLine()) - 1];

                    System.out.print("Check-In Date (YYYY-MM-DD): ");
                    String checkInDate = scanner.nextLine();

                    System.out.print("Check-Out Date (YYYY-MM-DD): ");
                    String checkOutDate = scanner.nextLine();

                    System.out.print("Transportation needed (true/false): ");
                    boolean transportation = Boolean.parseBoolean(scanner.nextLine());

                    System.out.print("Movies and Channels access (true/false): ");
                    boolean moviesAndChannels = Boolean.parseBoolean(scanner.nextLine());

                    System.out.print("Loyalty Member (true/false): ");
                    boolean loyaltyMember = Boolean.parseBoolean(scanner.nextLine());

                    System.out.print("Paid for Year (true/false): ");
                    boolean paidForYear = Boolean.parseBoolean(scanner.nextLine());

                    LocalDate checkIn = LocalDate.parse(checkInDate);
                    LocalDate checkOut = LocalDate.parse(checkOutDate);
                    long numOfNights = ChronoUnit.DAYS.between(checkIn, checkOut);
                    double totalCost = calculateTotalCost(roomType, foodAccommodation, numOfNights, moviesAndChannels, transportation, loyaltyMember, paidForYear);
                    
                    String modifiedLine = bookingId + "," + name + "," + email + "," + phone + "," + roomNumber + "," + roomType + "," + numPeople + "," + foodAccommodation + "," + checkInDate + "," + checkOutDate + "," + transportation + "," + moviesAndChannels + "," + loyaltyMember + "," + paidForYear + "," + totalCost;
                    
                    bookings.add(modifiedLine);
                } 
                else {
                    bookings.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading bookings file: " + e.getMessage());
            return;
        }
        if (found) {
            try (PrintWriter writer = new PrintWriter(FILENAME)) {
                for (String bookingLine : bookings) {
                    writer.println(bookingLine);
                }
            } 
            catch (IOException e) {
                System.err.println("Error writing to bookings file: " + e.getMessage());
            }
            System.out.println("Booking modified successfully.");
        } 
        else {
            System.out.println("Booking ID not found.");
        }
    }
    public void removeBooking(int uniqueId) {
        List<String> lines = new ArrayList<>();
        boolean found = false;
        RoomType roomTypeToRemove = null; 

        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int currentId = Integer.parseInt(parts[0]);
                if (currentId == uniqueId) {
                    found = true;
                    roomTypeToRemove = RoomType.valueOf(parts[5]);
                } else {
                    lines.add(line);
                }
            }
        } 
        catch (IOException e) {
            System.err.println("Error occurred while trying to remove booking: " + e.getMessage());
        }

        if (found && roomTypeToRemove != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, false))) {
                for (String remainingLine : lines) {
                    writer.println(remainingLine);
                }
            } catch (IOException e) {
                System.err.println("Error occurred while writing updated bookings back to file: " + e.getMessage());
            }
            roomInventoryManager.incrementRoomAvailability(roomTypeToRemove);
            System.out.println("Successfully removed booking ID " + uniqueId + " and incremented availability for " + roomTypeToRemove);
        } 
        else if (!found) {
            System.out.println("No booking found with ID " + uniqueId);
        }
    }

}