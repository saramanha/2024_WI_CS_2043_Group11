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
    private static final double MOVIE_PRICE = 2;
    private static final double EXTRA_CHANNELS_PRICE = 10;
    private static final double TRANSPORTATION_PRICE = 40;
    private static final double LOYALTY_DISCOUNT = 0.10;
    private static final double UNPAID_YEAR_FEE = 250;

    public void addBooking(String name, String email, String phone, int roomNumber, RoomType roomType,
                           int numPeople, FoodAccommodation foodAccommodation, String checkInDate, String checkOutDate,
                           boolean transportation, boolean moviesAndChannels, boolean loyaltyMember, boolean paidForYear) {
        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate checkOut = LocalDate.parse(checkOutDate);
        long numOfNights = ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalCost = calculateTotalCost(roomType, foodAccommodation, numOfNights, moviesAndChannels, transportation, loyaltyMember, paidForYear);

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true))) {
            writer.println(String.join(",", String.valueOf(nextUniqueId()), name, email, phone, String.valueOf(roomNumber),
                    roomType.toString(), String.valueOf(numPeople), foodAccommodation.toString(), checkInDate, checkOutDate,
                    String.valueOf(transportation), String.valueOf(moviesAndChannels), String.valueOf(loyaltyMember), String.valueOf(paidForYear), String.valueOf(totalCost)));
        } catch (IOException e) {
            System.err.println("Error occurred while adding booking: " + e.getMessage());
        }

        System.out.println("Booking added successfully.");
        System.out.println("Total cost: $" + totalCost);
    }

    public void removeBooking(int uniqueId) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) != uniqueId) {
                    lines.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while removing booking: " + e.getMessage());
        }

        if (found) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
                for (String line : lines) {
                    writer.println(line);
                }
            } catch (IOException e) {
                System.err.println("Error occurred while updating bookings: " + e.getMessage());
            }
            System.out.println("Booking with ID " + uniqueId + " removed successfully.");
        } else {
            System.out.println("Booking with ID " + uniqueId + " not found.");
        }
    }

    public void retrieveBookingByLastName(String lastName) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String[] nameParts = parts[1].split(" "); // Split the name into first name and last name
                if (nameParts.length > 1 && nameParts[1].trim().equalsIgnoreCase(lastName.trim())) { // Check the last name
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

    private double calculateTotalCost(RoomType roomType, FoodAccommodation foodAccommodation, long numOfNights,
                                      boolean moviesAndChannels, boolean transportation, boolean loyaltyMember, boolean paidForYear) {
        double roomPricePerNight;
        switch (roomType) {
            case SINGLE_ROOM:
                roomPricePerNight = 100;
                break;
            case REGULAR_SUITE:
                roomPricePerNight = 110;
                break;
            case CONNECTING_ROOM:
                roomPricePerNight = 160;
                break;
            case DELUXE_SUITE:
                roomPricePerNight = 150;
                break;
            case VIP_SUITE:
                roomPricePerNight = 350;
                break;
            default:
                roomPricePerNight = 0;
                break;
        }

        double foodPricePerNight;
        switch (foodAccommodation) {
            case BREAKFAST:
                foodPricePerNight = 20;
                break;
            case BREAKFAST_AND_LUNCH:
                foodPricePerNight = 50;
                break;
            case BREAKFAST_LUNCH_DINNER_BAR:
                foodPricePerNight = 70;
                break;
            default:
                foodPricePerNight = 0;
                break;
        }

        double additionalAccommodationCost = 0;
        if (moviesAndChannels) {
            additionalAccommodationCost += MOVIE_PRICE;
            additionalAccommodationCost += EXTRA_CHANNELS_PRICE;
        }
        if (transportation) {
            additionalAccommodationCost += TRANSPORTATION_PRICE;
        }

        double totalCost = ((roomPricePerNight + foodPricePerNight) * numOfNights) + additionalAccommodationCost;

        if (loyaltyMember && paidForYear) {
            totalCost -= totalCost * LOYALTY_DISCOUNT;
        }

        if (!paidForYear) {
            totalCost += UNPAID_YEAR_FEE;
        }

        return totalCost;
    }

    private int nextUniqueId() {
        int maxId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                if (id > maxId) {
                    maxId = id;
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while generating unique ID: " + e.getMessage());
        }
        return maxId + 1;
    }
}
