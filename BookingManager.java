import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class BookingManager {
    private ArrayList<Booking> bookings = new ArrayList<>();
    private int nextUniqueId = 1;
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
        Booking booking = new Booking(nextUniqueId++, name, email, phone, roomNumber, roomType, numPeople,
                foodAccommodation, checkInDate, checkOutDate, totalCost, transportation, moviesAndChannels, loyaltyMember, paidForYear);
        bookings.add(booking);
        System.out.println("Booking added successfully. Unique ID: " + (nextUniqueId - 1));
        System.out.println("Total cost: $" + totalCost);
    }

    public void removeBooking(int uniqueId) {
        for (Booking booking : bookings) {
            if (booking.getUniqueId() == uniqueId) {
                bookings.remove(booking);
                System.out.println("Booking with ID " + uniqueId + " removed successfully.");
                return;
            }
        }
        System.out.println("Booking with ID " + uniqueId + " not found.");
    }

    public void retrieveBookingByLastName(String lastName) {
        for (Booking booking : bookings) {
            if (booking.getName().toLowerCase().endsWith(lastName.toLowerCase())) {
                System.out.println("Booking found:");
                printBookingDetails(booking);
                return;
            }
        }
        System.out.println("Booking with last name '" + lastName + "' not found.");
    }

    private double calculateTotalCost(RoomType roomType, FoodAccommodation foodAccommodation , long numOfNights,
    boolean moviesAndChannels, boolean transportation, boolean loyaltyMember, boolean paidForYear) {
double roomPricePerNight = 0;
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
}

double foodPricePerNight = 0;
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

// Apply loyalty discount if applicable and paid for the year
if (loyaltyMember && paidForYear) {
totalCost -= totalCost * LOYALTY_DISCOUNT;
}

// Add unpaid year fee if applicable and not paid for the year
if (!paidForYear) {
totalCost += UNPAID_YEAR_FEE;
}

return totalCost;
}

private void printBookingDetails(Booking booking) {
System.out.println("Unique ID: " + booking.getUniqueId());
System.out.println("Name: " + booking.getName());
System.out.println("Email: " + booking.getEmail());
System.out.println("Phone: " + booking.getPhone());
System.out.println("Room Number: " + booking.getRoomNumber());
System.out.println("Room Type: " + booking.getRoomType());
System.out.println("Number of People: " + booking.getNumPeople());
System.out.println("Food Accommodation: " + booking.getFoodAccommodation());
System.out.println("Check-in Date: " + booking.getCheckInDate());
System.out.println("Check-out Date: " + booking.getCheckOutDate());
System.out.println("Transportation Included: " + (booking.isTransportation() ? "Yes" : "No"));
System.out.println("Movies & Extra Channels Included: " + (booking.isMoviesAndChannels() ? "Yes" : "No"));
System.out.println("Loyalty Member: " + (booking.isLoyaltyMember() ? "Yes" : "No"));
System.out.println("Paid for Year: " + (booking.isPaidForYear() ? "Yes" : "No"));
System.out.println("Total Cost: $" + booking.getTotalCost());
}
}

