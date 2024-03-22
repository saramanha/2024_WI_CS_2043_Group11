public class Room {
  private int roomNumber;
  private String type;
  private boolean availability;
  private float price;

  // Constructor
  public Room(int roomNumber, String type, boolean availability, float price) {
      this.roomNumber = roomNumber;
      this.type = type;
      this.availability = availability;
      this.price = price;
  }

  // Getters and setters
  public int getRoomNumber() {
      return roomNumber;
  }

  public void setRoomNumber(int roomNumber) {
      this.roomNumber = roomNumber;
  }

  public String getType() {
      return type;
  }

  public void setType(String type) {
      this.type = type;
  }

  public boolean isAvailability() {
      return availability;
  }

  public void setAvailability(boolean availability) {
      this.availability = availability;
  }

  public float getPrice() {
      return price;
  }

  public void setPrice(float price) {
      this.price = price;
  }

  // Other methods
  public boolean checkAvailability() {
      return availability;
  }

  public float getPrice(String roomType) {
      // Implementation to get price based on room type
      // You may implement logic to return price based on the type provided
      return price;
  }
}
