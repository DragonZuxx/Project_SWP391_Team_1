/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class OrderDetail {
    private int iddetail, orderid,bookid,quantity;
    private String unitPrice,createdat,updatedat;

    public OrderDetail() {
    }

    public OrderDetail(int iddetail, int orderid, int bookid, int quantity, String unitPrice, String createdat, String updatedat) {
        this.iddetail = iddetail;
        this.orderid = orderid;
        this.bookid = bookid;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.createdat = createdat;
        this.updatedat = updatedat;
    }

    public int getIddetail() {
        return iddetail;
    }

    public void setIddetail(int iddetail) {
        this.iddetail = iddetail;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }
    
    
}
