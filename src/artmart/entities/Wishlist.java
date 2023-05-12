package artmart.entities;

public class Wishlist {

    private int wishlistId;
    private int userid;
    private int productid;
    private String date;
    private int quantity;
    private int price;

    public Wishlist() {
    }

    public Wishlist(int wishlistId, Integer userid, Integer productid, String date, Integer quantity, int price) {
        this.wishlistId = wishlistId;
        this.userid = userid;
        this.productid = productid;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    public Wishlist(Integer userid, Integer productid, String date, Integer quantity, int price) {
        this.userid = userid;
        this.productid = productid;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Wishlist{" + "wishlistId=" + wishlistId + ", userid=" + userid + ", productid=" + productid + ", date=" + date + ", quantity=" + quantity + ", price=" + price + '}';
    }
}
