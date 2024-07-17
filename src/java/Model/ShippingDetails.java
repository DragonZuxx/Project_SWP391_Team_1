/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author DMX THANH SON
 */
public class ShippingDetails {

    private int shippingId;
    private int orderId;
    private String shippingMethod;
    private String shippingCost;
    private Date estimatedDeliveryDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public ShippingDetails() {
    }

    public ShippingDetails(int shippingId, int orderId, String shippingMethod, String shippingCost, Date estimatedDeliveryDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.shippingMethod = shippingMethod;
        this.shippingCost = shippingCost;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ShippingDetails{" + "shippingId=" + shippingId + ", orderId=" + orderId + ", shippingMethod=" + shippingMethod + ", shippingCost=" + shippingCost + ", estimatedDeliveryDate=" + estimatedDeliveryDate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
}
