/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author Aplal
 */
public class Books {

    private int BookID;
    private String Title;
    private String Publisher;
    private String PublicationDate;
    private String ISBN;
    private String Price;
    private int Stock;
    private int SoldQuantity;
    private String Description;
    private String CoverImage;
    private Boolean IsAvailable;
    private Boolean IsBanned;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

    public Books() {
    }

    public Books(int BookID, String Title, String Publisher, String PublicationDate, String ISBN, String Price, int Stock, int SoldQuantity, String Description, String CoverImage, Boolean IsAvailable, Boolean IsBanned, LocalDateTime CreatedAt, LocalDateTime UpdatedAt) {
        this.BookID = BookID;
        this.Title = Title;
        this.Publisher = Publisher;
        this.PublicationDate = PublicationDate;
        this.ISBN = ISBN;
        this.Price = Price;
        this.Stock = Stock;
        this.SoldQuantity = SoldQuantity;
        this.Description = Description;
        this.CoverImage = CoverImage;
        this.IsAvailable = IsAvailable;
        this.IsBanned = IsBanned;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public String getPublicationDate() {
        return PublicationDate;
    }

    public void setPublicationDate(String PublicationDate) {
        this.PublicationDate = PublicationDate;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public int getSoldQuantity() {
        return SoldQuantity;
    }

    public void setSoldQuantity(int SoldQuantity) {
        this.SoldQuantity = SoldQuantity;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getCoverImage() {
        return CoverImage;
    }

    public void setCoverImage(String CoverImage) {
        this.CoverImage = CoverImage;
    }

    public Boolean getIsAvailable() {
        return IsAvailable;
    }

    public void setIsAvailable(Boolean IsAvailable) {
        this.IsAvailable = IsAvailable;
    }

    public Boolean getIsBanned() {
        return IsBanned;
    }

    public void setIsBanned(Boolean IsBanned) {
        this.IsBanned = IsBanned;
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
        return "Books{" + "BookID=" + BookID + ", Title=" + Title + ", Publisher=" + Publisher + ", PublicationDate=" + PublicationDate + ", ISBN=" + ISBN + ", Price=" + Price + ", Stock=" + Stock + ", SoldQuantity=" + SoldQuantity + ", Description=" + Description + ", CoverImage=" + CoverImage + ", IsAvailable=" + IsAvailable + ", IsBanned=" + IsBanned + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + '}';
    }

    
    
}
