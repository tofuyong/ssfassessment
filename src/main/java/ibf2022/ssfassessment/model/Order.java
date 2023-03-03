package ibf2022.ssfassessment.model;


import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {

    private Item item;
    private String cname = "Mermaid";
    private String address = "10 Ocean Avenue";

    // Getters & Setters
    public Item getItem() {return this.item;}
    public void setItem(Item item) {this.item = item;}
    
    public String getCname() {return this.cname;}
    public void setCname(String cname) {this.cname = cname;}

    public String getAddress() {return this.address;}
    public void setAddress(String address) {this.address = address;}

    // Create Quotation from json
    public static Order create(JsonObject json){
        Order quotation = new Order();
        quotation.setCname(json.getString("cname"));
        quotation.setAddress(json.getString("address"));
        return quotation;
    }

    // Create json from Quotation
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("name", cname)
            .add("address", address)
            .build();
    }

}
