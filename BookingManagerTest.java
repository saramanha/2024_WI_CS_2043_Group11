import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingManagerTest {
    private static final String FILENAME = "test_bookings.txt";
    private BookingManager bookingManager;

    @BeforeEach
    public void setUp() {
        bookingManager = new BookingManager();
    }

    @AfterEach
    public void tearDown() {
        
        try (PrintWriter writer = new PrintWriter(FILENAME)) {
            writer.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddBooking() {
        bookingManager.addBooking("John Doe", "john@abc.com", "5267456852", 101, RoomType.SINGLE_ROOM,
                1, FoodAccommodation.BREAKFAST, "2024-04-01", "2024-04-03", false, true, false, false);
        
        
        List<String> lines = readLinesFromFile();
        assertTrue(lines.size() > 0); 
    }

    @Test
    public void testRemoveBooking() {
        bookingManager.addBooking("Jane Doe", "jane@abc.com", "987654321", 102, RoomType.REGULAR_SUITE,
                2, FoodAccommodation.BREAKFAST_AND_LUNCH, "2024-04-05", "2024-04-08", true, false, false, false);
        
        bookingManager.removeBooking(1);
        
        
        List<String> lines = readLinesFromFile();
        assertEquals(0, lines.size()); 
    

    @Test
    public void testRetrieveBookingByLastName() {
        bookingManager.addBooking("John Doe", "john@abc.com", "526746852", 101, RoomType.SINGLE_ROOM,
                1, FoodAccommodation.BREAKFAST, "2024-04-01", "2024-04-03", false, true, false, false);

        bookingManager.addBooking("Jane Doe", "jane@abc.com", "987654321", 102, RoomType.REGULAR_SUITE,
                2, FoodAccommodation.BREAKFAST_AND_LUNCH, "2024-04-05", "2024-04-08", true, false, false, false);
        
        String bookingDetails = bookingManager.retrieveBookingByLastName("Doe");
        
      
        assertTrue(bookingDetails.contains("John Doe"));
        assertTrue(bookingDetails.contains("Jane Doe"));
    }

    @Test
    public void testAppendBookingDetails() {
        StringBuilder bookingDetails = new StringBuilder();
        String[] bookingParts = {"1", "John Doe", "john@abc.com", "526746852", "101", "SINGLE_ROOM", "1", 
                                 "BREAKFAST", "2024-04-01", "2024-04-03", "false", "true", "false", "false", "200.0"};
        
        bookingManager.appendBookingDetails(bookingDetails, bookingParts);
        
        
        assertTrue(bookingDetails.toString().contains("John Doe"));
        assertTrue(bookingDetails.toString().contains("SINGLE_ROOM"));
        assertTrue(bookingDetails.toString().contains("200.0"));
    }

    private List<String> readLinesFromFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
