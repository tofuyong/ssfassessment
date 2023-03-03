package ibf2022.ssfassessment.model;

public class Invoice {
    private String invoiceId;
    private String name;
    private String address;
    private Float total;
    
    // Getters & Setters
    public String getInvoiceId() {return this.invoiceId;}
    public void setInvoiceId(String invoiceId) {this.invoiceId = invoiceId;}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return this.address;}
    public void setAddress(String address) {this.address = address;}

    public Float getTotal() {return this.total;}
    public void setTotal(Float total) {this.total = total;}

}
