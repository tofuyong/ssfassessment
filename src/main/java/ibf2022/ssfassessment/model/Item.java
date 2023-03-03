package ibf2022.ssfassessment.model;

import jakarta.validation.constraints.Min;

public class Item {
    
    private String name;

    @Min(value=1, message="You must add at least 1 item")
    private Integer quantity;

    // Getters & Setters
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public Integer getQuantity() {return this.quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}

    // Constructor
    public Item(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

}
