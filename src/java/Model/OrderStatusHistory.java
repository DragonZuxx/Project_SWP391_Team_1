/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class OrderStatusHistory {
    private int id,orderid;
    private String status,changeDate,createdat,updatesat;

    public OrderStatusHistory() {
    }

    public OrderStatusHistory(int id, int orderid, String status, String changeDate, String createdat, String updatesat) {
        this.id = id;
        this.orderid = orderid;
        this.status = status;
        this.changeDate = changeDate;
        this.createdat = createdat;
        this.updatesat = updatesat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getUpdatesat() {
        return updatesat;
    }

    public void setUpdatesat(String updatesat) {
        this.updatesat = updatesat;
    }
    
}
