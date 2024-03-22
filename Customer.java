public class Customer {
    private int customerId = 1000;

    private String email;

    private String name;
    
    private boolean loyaltyStatus;
    
    public Customer(String emailIn, String nameIn, boolean loyaltyStatusIn) {
        emailIn = email;
        nameIn = name;
        loyaltyStatusIn = loyaltyStatus;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return name;
        
    }
    
    public boolean getLoyaltyStatus() {
        return loyaltyStatus;
    }
    
}