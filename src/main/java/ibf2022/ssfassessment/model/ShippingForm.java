package ibf2022.ssfassessment.model;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

public class ShippingForm {
    
    @NotNull(message="Name cannot be null")
    @Size(min=2, message= "Name must be at least 2 characters long") 
    private String cname;

    @NotNull(message="Address cannot be null")
    @NotEmpty(message="Address cannot be empty")
    private String address;

    private List<Item> cartItems = new LinkedList<Item>();

    // Getters & Setters
    public String getCname() {return this.cname;}
    public void setCname(String cname) {this.cname = cname;}
    
    public String getAddress() {return this.address;}
    public void setAddress(String address) {this.address = address;}

    public List<Item> getCartItems() {return this.cartItems;}
    public void setCartItems(List<Item> cartItems) {this.cartItems = cartItems;}

    // Method to add Item to cart
    public void addCartItems(Item item) {
        this.cartItems.add(item);
    }
}
