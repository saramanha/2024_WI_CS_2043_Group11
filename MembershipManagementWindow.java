import javax.swing.*;
import java.awt.*;

public class MembershipManagementWindow extends JFrame {
    private JTextField uniqueIdField, nameField, emailField;
    private JCheckBox hasPaidCheckBox;

    public MembershipManagementWindow() {
        setTitle("Membership Management");
        setSize(500, 500);
        setLayout(new GridLayout(7, 2)); // Updated grid layout for an additional button

        add(new JLabel("Unique ID:"));
        uniqueIdField = new JTextField();
        add(uniqueIdField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Has Paid for Year:"));
        hasPaidCheckBox = new JCheckBox();
        add(hasPaidCheckBox);

        JButton addButton = new JButton("Add Member");
        addButton.addActionListener(e -> addMember());
        add(addButton);

        JButton changeStatusButton = new JButton("Change Payment Status");
        changeStatusButton.addActionListener(e -> changePaymentStatus());
        add(changeStatusButton);

        JButton removeButton = new JButton("Remove Member");
        removeButton.addActionListener(e -> removeMember());
        add(removeButton);

        JButton checkButton = new JButton("Check Status");
        checkButton.addActionListener(e -> checkStatus());
        add(checkButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addMember() {
        if (Member.addMember(uniqueIdField.getText(), nameField.getText(), emailField.getText(), hasPaidCheckBox.isSelected())) {
            JOptionPane.showMessageDialog(this, "Member added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add member.");
        }
    }

    private void changePaymentStatus() {
      if (Member.changePaymentStatus(uniqueIdField.getText())) {
          JOptionPane.showMessageDialog(this, "Payment status changed successfully.");
          // Optionally, refresh or update the GUI to reflect the new status
      } else {
          JOptionPane.showMessageDialog(this, "Failed to change payment status or member not found.");
      }
  }
  

    private void removeMember() {
        if (Member.removeMember(uniqueIdField.getText())) {
            JOptionPane.showMessageDialog(this, "Member removed successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to remove member or member not found.");
        }
    }
    private void checkStatus() {
        String status = Member.getMemberDetailsByName(nameField.getText());
        if (status != null) {
            String[] details = status.split(",");
            boolean hasPaid = Boolean.parseBoolean(details[3]);
            String paymentStatusMessage = hasPaid ? "has paid for the year." : "has not paid for the year.";
            
            JOptionPane.showMessageDialog(this, "Member found: " + details[1] + " (" + details[2] + ") " + paymentStatusMessage, "Member Status", JOptionPane.INFORMATION_MESSAGE);
            hasPaidCheckBox.setSelected(hasPaid);
        } else {
            JOptionPane.showMessageDialog(this, "Member not found.", "Member Status", JOptionPane.ERROR_MESSAGE);
        }
    }
    
  
  
}
