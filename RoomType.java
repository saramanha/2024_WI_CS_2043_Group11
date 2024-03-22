public enum RoomType {
  SINGLE_ROOM("Single Room (1 Queen Bed)", 100.0f),
  REGULAR_SUITE("Regular Suite (2 Queen Beds, small kitchen, small living area)", 110.0f),
  CONNECTING_ROOM("Connecting Room (Two connected suite rooms)", 160.0f),
  DELUXE_SUITE("Deluxe Suite (1 King Bed, kitchen, living area, bath & shower, balcony)", 150.0f),
  VIP_SUITE("VIP Suite (2 Rooms, 1 king bed in each room, TV in each room + access to all channels, living room, kitchen, bath & shower, balcony, transportation, Breakfast + Lunch + Dinner & Bar)", 350.0f);

  private final String description;
  private final float price;

  RoomType(String description, float price) {
      this.description = description;
      this.price = price;
  }

  public String getDescription() {
      return description;
  }

  public float getPrice() {
      return price;
  }
}
