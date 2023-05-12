package artmart.entities;

public class PaymentOption {

    private int paymentoptionId;
    private String name;
    private String availablecountries;

    public PaymentOption() {
    }

    public PaymentOption(int paymentoptionId, String name, String availablecountries) {
        this.paymentoptionId = paymentoptionId;
        this.name = name;
        this.availablecountries = availablecountries;
    }

    public PaymentOption(String name, String availablecountries) {
        this.name = name;
        this.availablecountries = availablecountries;
    }

    public int getPaymentoptionId() {
        return paymentoptionId;
    }

    public void setPaymentoptionId(int paymentoptionId) {
        this.paymentoptionId = paymentoptionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailablecountries() {
        return availablecountries;
    }

    public void setAvailablecountries(String availablecountries) {
        this.availablecountries = availablecountries;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
