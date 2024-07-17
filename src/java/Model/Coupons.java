
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Aplal
 */
public class Coupons {

    private int CouponID;
    private String Code;
    private String Description;
    private BigDecimal DiscountPercentage;
    private Date ExpirationDate;
    private BigDecimal MinimumOrderAmount;
    private int RemainingQuantity;
    private int UsedQuantity;
    private boolean isExpired;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

    public Coupons() {
    }

    public Coupons(int CouponID, String Code, String Description, BigDecimal DiscountPercentage, Date ExpirationDate, BigDecimal MinimumOrderAmount, int RemainingQuantity, int UsedQuantity, boolean isExpired, LocalDateTime CreatedAt, LocalDateTime UpdatedAt) {
        this.CouponID = CouponID;
        this.Code = Code;
        this.Description = Description;
        this.DiscountPercentage = DiscountPercentage;
        this.ExpirationDate = ExpirationDate;
        this.MinimumOrderAmount = MinimumOrderAmount;
        this.RemainingQuantity = RemainingQuantity;
        this.UsedQuantity = UsedQuantity;
        this.isExpired = isExpired;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }

    public int getCouponID() {
        return CouponID;
    }

    public void setCouponID(int CouponID) {
        this.CouponID = CouponID;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public BigDecimal getDiscountPercentage() {
        return DiscountPercentage;
    }

    public void setDiscountPercentage(BigDecimal DiscountPercentage) {
        this.DiscountPercentage = DiscountPercentage;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Date ExpirationDate) {
        this.ExpirationDate = ExpirationDate;
    }

    public BigDecimal getMinimumOrderAmount() {
        return MinimumOrderAmount;
    }

    public void setMinimumOrderAmount(BigDecimal MinimumOrderAmount) {
        this.MinimumOrderAmount = MinimumOrderAmount;
    }

    public int getRemainingQuantity() {
        return RemainingQuantity;
    }

    public void setRemainingQuantity(int RemainingQuantity) {
        this.RemainingQuantity = RemainingQuantity;
    }

    public int getUsedQuantity() {
        return UsedQuantity;
    }

    public void setUsedQuantity(int UsedQuantity) {
        this.UsedQuantity = UsedQuantity;
    }

    public boolean isIsExpired() {
        return isExpired;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDateTime CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(LocalDateTime UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    @Override
    public String toString() {
        return "Coupons{" + "CouponID=" + CouponID + ", Code=" + Code + ", Description=" + Description + ", DiscountPercentage=" + DiscountPercentage + ", ExpirationDate=" + ExpirationDate + ", MinimumOrderAmount=" + MinimumOrderAmount + ", RemainingQuantity=" + RemainingQuantity + ", UsedQuantity=" + UsedQuantity + ", isExpired=" + isExpired + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + '}';
    }

    

    
}
