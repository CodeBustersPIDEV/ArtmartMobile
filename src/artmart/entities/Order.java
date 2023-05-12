package artmart.entities;

import com.codename1.payment.Product;

public class Order {
    
    private int orderId;
    private int quantity;
    private String shippingaddress;
    private String orderdate;
    private double totalcost;
    private int userid;
    private int productid;
    private int shippingmethod;
    private int paymentmethod;

    public Order() {
    }

    public Order(int orderId, int quantity, String shippingaddress, String orderdate, double totalcost, int userid, int productid, int shippingmethod, int paymentmethod) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.shippingaddress = shippingaddress;
        this.orderdate = orderdate;
        this.totalcost = totalcost;
        this.userid = userid;
        this.productid = productid;
        this.shippingmethod = shippingmethod;
        this.paymentmethod = paymentmethod;
    }

    public Order(int quantity, String shippingaddress, String orderdate, double totalcost, int userid, int productid, int shippingmethod, int paymentmethod) {
        this.quantity = quantity;
        this.shippingaddress = shippingaddress;
        this.orderdate = orderdate;
        this.totalcost = totalcost;
        this.userid = userid;
        this.productid = productid;
        this.shippingmethod = shippingmethod;
        this.paymentmethod = paymentmethod;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShippingaddress() {
        return shippingaddress;
    }

    public void setShippingaddress(String shippingaddress) {
        this.shippingaddress = shippingaddress;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getShippingmethod() {
        return shippingmethod;
    }

    public void setShippingmethod(int shippingmethod) {
        this.shippingmethod = shippingmethod;
    }

    public int getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(int paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", quantity=" + quantity + ", shippingaddress=" + shippingaddress + ", orderdate=" + orderdate + ", totalcost=" + totalcost + ", userid=" + userid + ", productid=" + productid + ", shippingmethod=" + shippingmethod + ", paymentmethod=" + paymentmethod + '}';
    }
}