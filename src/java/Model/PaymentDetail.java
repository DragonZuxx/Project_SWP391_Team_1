/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Aplal
 */
public class PaymentDetail {
    private int paymentID;
    private int orderID;
    private String paymentMethod;
    private Date paymentDate;
    private String paymentStatus;
    private double amountPaid;
    private Date createdAt;
    private Date updatedAt;

    public PaymentDetail() {
    }

    public PaymentDetail(int paymentID, int orderID, String paymentMethod, Date paymentDate, String paymentStatus, double amountPaid, Date createdAt, Date updatedAt) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.amountPaid = amountPaid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PaymentDetail{" + "paymentID=" + paymentID + ", orderID=" + orderID + ", paymentMethod=" + paymentMethod + ", paymentDate=" + paymentDate + ", paymentStatus=" + paymentStatus + ", amountPaid=" + amountPaid + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
}
