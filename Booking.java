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

public class Booking {
    private int uniqueId;
    private String name;
    private String email;
    private String phone;
    private int roomNumber;
    private RoomType roomType;
    private int numPeople;
    private FoodAccommodation foodAccommodation;
    private String checkInDate;
    private String checkOutDate;
    private double totalCost;
    private boolean transportation;
    private boolean moviesAndChannels;
    private boolean loyaltyMember;
    private boolean paidForYear;

    public Booking(int uniqueId, String name, String email, String phone, int roomNumber, RoomType roomType,
                   int numPeople, FoodAccommodation foodAccommodation, String checkInDate, String checkOutDate,
                   double totalCost, boolean transportation, boolean moviesAndChannels,
                   boolean loyaltyMember, boolean paidForYear) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.numPeople = numPeople;
        this.foodAccommodation = foodAccommodation;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
        this.transportation = transportation;
        this.moviesAndChannels = moviesAndChannels;
        this.loyaltyMember = loyaltyMember;
        this.paidForYear = paidForYear;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public FoodAccommodation getFoodAccommodation() {
        return foodAccommodation;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isTransportation() {
        return transportation;
    }

    public boolean isMoviesAndChannels() {
        return moviesAndChannels;
    }

    public boolean isLoyaltyMember() {
        return loyaltyMember;
    }

    public boolean isPaidForYear() {
        return paidForYear;
    }
}
