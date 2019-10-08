package xyz.diaodeyipi.orderfood.entity;

/**
 * @author yangsijie
 */
public class Order {
    private int id;
    private int deskId;
    private double totalPrice;

    public Order() {
    }

    public Order(int id, int deskId, double totalPrice) {
        this.id = id;
        this.deskId = deskId;
        this.totalPrice = totalPrice;
    }

    public Order(int deskId, double totalPrice) {
        this.deskId = deskId;
        this.totalPrice = totalPrice;
    }

    public Order(int deskId) {
        this.deskId = deskId;
        this.totalPrice = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeskId() {
        return deskId;
    }

    public void setDeskId(int deskId) {
        this.deskId = deskId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
