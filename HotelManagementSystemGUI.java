import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelManagementSystemGUI extends JFrame implements ActionListener {
    private BookingManager bookingManager;

    private JTextField nameField, emailField, phoneField, roomNumberField, checkInDateField, checkOutDateField;
    private JComboBox<RoomType> roomTypeComboBox;
    private JComboBox<FoodAccommodation> foodAccommodationComboBox;
    private JCheckBox moviesAndChannelsCheckBox, transportationCheckBox, loyaltyMemberCheckBox, paidForYearCheckBox;
    private JTextArea outputArea;

    public HotelManagementSystemGUI() {
        bookingManager = new BookingManager();

        setTitle("Hotel Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 10, 10));

        mainPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        mainPanel.add(nameField);

        mainPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        mainPanel.add(emailField);

        mainPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        mainPanel.add(phoneField);

        mainPanel.add(new JLabel("Room Number:"));
        roomNumberField = new JTextField();
        mainPanel.add(roomNumberField);

        mainPanel.add(new JLabel("Room Type:"));
        roomTypeComboBox = new JComboBox<>(RoomType.values());
        mainPanel.add(roomTypeComboBox);

        mainPanel.add(new JLabel("Food Accommodation:"));
        foodAccommodationComboBox = new JComboBox<>(FoodAccommodation.values());
        mainPanel.add(foodAccommodationComboBox);

        mainPanel.add(new JLabel("Movies & Extra Channels:"));
        moviesAndChannelsCheckBox = new JCheckBox();
        mainPanel.add(moviesAndChannelsCheckBox);

        mainPanel.add(new JLabel("Transportation:"));
        transportationCheckBox = new JCheckBox();
        mainPanel.add(transportationCheckBox);

        mainPanel.add(new JLabel("Loyalty Member:"));
        loyaltyMemberCheckBox = new JCheckBox();
        mainPanel.add(loyaltyMemberCheckBox);

        mainPanel.add(new JLabel("Paid for Year:"));
        paidForYearCheckBox = new JCheckBox();
        mainPanel.add(paidForYearCheckBox);

        mainPanel.add(new JLabel("Check-in Date (YYYY-MM-DD):"));
        checkInDateField = new JTextField();
        mainPanel.add(checkInDateField);

        mainPanel.add(new JLabel("Check-out Date (YYYY-MM-DD):"));
        checkOutDateField = new JTextField();
        mainPanel.add(checkOutDateField);

        JButton addBookingButton = new JButton("Add Booking");
        addBookingButton.addActionListener(this);
        mainPanel.add(addBookingButton);

        JButton removeBookingButton = new JButton("Remove Booking");
        removeBookingButton.addActionListener(this);
        mainPanel.add(removeBookingButton);

        JButton modifyBookingButton = new JButton("Modify Booking");
        modifyBookingButton.addActionListener(this);
        mainPanel.add(modifyBookingButton);

        JButton retrieveBookingButton = new JButton("Retrieve Booking");
        retrieveBookingButton.addActionListener(this);
        mainPanel.add(retrieveBookingButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane);

        getContentPane().add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Booking")) {
            addBooking();
        } else if (e.getActionCommand().equals("Remove Booking")) {
            removeBooking();
        } else if (e.getActionCommand().equals("Modify Booking")) {
            modifyBooking();
        } else if (e.getActionCommand().equals("Retrieve Booking")) {
            retrieveBooking();
        }
    }

    private void addBooking() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        int roomNumber = Integer.parseInt(roomNumberField.getText());
        RoomType roomType = (RoomType) roomTypeComboBox.getSelectedItem();
        int numPeople = 1; // Assuming default
        FoodAccommodation foodAccommodation = (FoodAccommodation) foodAccommodationComboBox.getSelectedItem();
        boolean moviesAndChannels = moviesAndChannelsCheckBox.isSelected();
        boolean transportation = transportationCheckBox.isSelected();
        boolean loyaltyMember = loyaltyMemberCheckBox.isSelected();
        boolean paidForYear = paidForYearCheckBox.isSelected();
        String checkInDate = checkInDateField.getText();
        String checkOutDate = checkOutDateField.getText();

        bookingManager.addBooking(name, email, phone, roomNumber, roomType, numPeople, foodAccommodation, checkInDate, checkOutDate, transportation, moviesAndChannels, loyaltyMember, paidForYear);

        // Update output area with booking details
        outputArea.append("Booking added successfully.\n");
        outputArea.append("Name: " + name + "\n");
        outputArea.append("Email: " + email + "\n");
        outputArea.append("Phone: " + phone + "\n");
        outputArea.append("Room Number: " + roomNumber + "\n");
        outputArea.append("Room Type: " + roomType + "\n");
        outputArea.append("Food Accommodation: " + foodAccommodation + "\n");
        outputArea.append("Check-in Date: " + checkInDate + "\n");
        outputArea.append("Check-out Date: " + checkOutDate + "\n");
        outputArea.append("Transportation Included: " + transportation + "\n");
        outputArea.append("Movies & Extra Channels Included: " + moviesAndChannels + "\n");
        outputArea.append("Loyalty Member: " + loyaltyMember + "\n");
        outputArea.append("Paid for Year: " + paidForYear + "\n");
        outputArea.append("\n");
    }

    private void removeBooking() {
        try {
            int uniqueId = Integer.parseInt(JOptionPane.showInputDialog("Enter the unique ID of the booking you want to remove:"));
            bookingManager.removeBooking(uniqueId);
            outputArea.append("Booking with ID " + uniqueId + " removed successfully.\n\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid unique ID.");
        }
    }

    private void modifyBooking() {
        try {
            int uniqueId = Integer.parseInt(JOptionPane.showInputDialog("Enter the unique ID of the booking you want to modify:"));
            bookingManager.removeBooking(uniqueId);
            addBooking();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid unique ID.");
        }
    }

    private void retrieveBooking() {
        String lastName = JOptionPane.showInputDialog("Enter the last name to retrieve bookings:");
        bookingManager.retrieveBookingByLastName(lastName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelManagementSystemGUI gui = new HotelManagementSystemGUI();
            gui.setVisible(true);
        });
    }
}