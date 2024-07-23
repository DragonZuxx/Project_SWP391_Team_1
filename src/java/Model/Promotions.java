package Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Promotions {
     private int PromotionID;
    private String Title;
    private String Description;
    private Date StartDate;
    private Date EndDate;
    private BigDecimal DiscountPercentage;
    private boolean isActive;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

    public Promotions() {
    }

    public Promotions(int PromotionID, String Title, String Description, Date StartDate, Date EndDate, BigDecimal DiscountPercentage, boolean isActive, LocalDateTime CreatedAt, LocalDateTime UpdatedAt) {
        this.PromotionID = PromotionID;
        this.Title = Title;
        this.Description = Description;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.DiscountPercentage = DiscountPercentage;
        this.isActive = isActive;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }

    public int getPromotionID() {
        return PromotionID;
    }

    public void setPromotionID(int PromotionID) {
        this.PromotionID = PromotionID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    public BigDecimal getDiscountPercentage() {
        return DiscountPercentage;
    }

    public void setDiscountPercentage(BigDecimal DiscountPercentage) {
        this.DiscountPercentage = DiscountPercentage;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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
        return "Promotions{" + "PromotionID=" + PromotionID + ", Title=" + Title + ", Description=" + Description + ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", DiscountPercentage=" + DiscountPercentage + ", isActive=" + isActive + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + '}';
    }


}
