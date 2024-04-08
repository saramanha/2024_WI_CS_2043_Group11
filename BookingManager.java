import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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

    public String retrieveBookingByLastName(String lastName) {
        StringBuilder bookingDetails = new StringBuilder();
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String[] nameParts = parts[1].split(" "); // Split the name into first name and last name
                if (nameParts.length > 1 && nameParts[1].trim().equalsIgnoreCase(lastName.trim())) { // Check the last name
                    found = true;
                    appendBookingDetails(bookingDetails, parts);
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while retrieving booking: " + e.getMessage());
        }
        return found ? bookingDetails.toString() : null;
    }

    private void appendBookingDetails(StringBuilder bookingDetails, String[] bookingParts) {
        bookingDetails.append("Unique ID: ").append(bookingParts[0]).append("\n");
        bookingDetails.append("Name: ").append(bookingParts[1]).append("\n");
        bookingDetails.append("Email: ").append(bookingParts[2]).append("\n");
        bookingDetails.append("Phone: ").append(bookingParts[3]).append("\n");
        bookingDetails.append("Room Number: ").append(bookingParts[4]).append("\n");
        bookingDetails.append("Room Type: ").append(bookingParts[5]).append("\n");
        bookingDetails.append("Number of People: ").append(bookingParts[6]).append("\n");
        bookingDetails.append("Food Accommodation: ").append(bookingParts[7]).append("\n");
        bookingDetails.append("Check-in Date: ").append(bookingParts[8]).append("\n");
        bookingDetails.append("Check-out Date: ").append(bookingParts[9]).append("\n");
        bookingDetails.append("Transportation Included: ").append(bookingParts[10]).append("\n");
        bookingDetails.append("Movies & Extra Channels Included: ").append(bookingParts[11]).append("\n");
        bookingDetails.append("Loyalty Member: ").append(bookingParts[12]).append("\n");
        bookingDetails.append("Paid for Year: ").append(bookingParts[13]).append("\n");
        bookingDetails.append("Total Cost: $").append(bookingParts[14]).append("\n\n");
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
            case NOTHING: // Explicit handling for clarity
                foodPricePerNight = 0;
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
