import java.util.Date;

public class Bookings {
    private int bookingID;
    private int customerID;
    private int roomNumber;
    private Date checkInDate;
    private Date checkOutDate;
    private float totalCost;

    public Bookings(int bookingID, int customerID, int roomNumber, Date checkInDate, Date checkOutDate, float totalCost) {
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
    }
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public void modifyBooking(int bookingID, Bookings newDetails) {
        // Implementation to modify a booking
    }

    public float calculateCost(Room roomDetails, Date checkInDate, Date checkOutDate) {
        long diff = checkOutDate.getTime() - checkInDate.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return days * roomDetails.getPrice();
    }

    public void registerLoyaltyMember(Customer customer) {
        // Implementation to register a customer as a loyalty member
    }
}
