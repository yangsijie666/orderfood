package xyz.diaodeyipi.orderfood.entity;

/**
 * @author yangsijie
 */
public class OrderDetail {
    private int id;
    private int orderId;
    private int menuId;
    private int num;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, int menuId, int num) {
        this.id = id;
        this.orderId = orderId;
        this.menuId = menuId;
        this.num = num;
    }

    public OrderDetail(int orderId, int menuId, int num) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
