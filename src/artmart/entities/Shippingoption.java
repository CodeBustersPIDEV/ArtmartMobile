package artmart.entities;

public class Shippingoption {

    private int shippingoptionId ;
    private String name ;
    private String carrier ;
    private String shippingspeed ;
    private String shippingfee ;
    private String availableregions ;

    public Shippingoption(int shippingoptionId, String name, String carrier, String shippingspeed, String shippingfee, String availableregions) {
        this.shippingoptionId = shippingoptionId;
        this.name = name;
        this.carrier = carrier;
        this.shippingspeed = shippingspeed;
        this.shippingfee = shippingfee;
        this.availableregions = availableregions;
    }

    public Shippingoption() {
    }

    public Shippingoption(String name, String carrier, String shippingspeed, String shippingfee, String availableregions) {
        this.name = name;
        this.carrier = carrier;
        this.shippingspeed = shippingspeed;
        this.shippingfee = shippingfee;
        this.availableregions = availableregions;
    }

    public int getShippingoptionId() {
        return shippingoptionId;
    }

    public void setShippingoptionId(int shippingoptionId) {
        this.shippingoptionId = shippingoptionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getShippingspeed() {
        return shippingspeed;
    }

    public void setShippingspeed(String shippingspeed) {
        this.shippingspeed = shippingspeed;
    }

    public String getShippingfee() {
        return shippingfee;
    }

    public void setShippingfee(String shippingfee) {
        this.shippingfee = shippingfee;
    }

    public String getAvailableregions() {
        return availableregions;
    }

    public void setAvailableregions(String availableregions) {
        this.availableregions = availableregions;
    }

    @Override
    public String toString() {
        return name;
    }

}
