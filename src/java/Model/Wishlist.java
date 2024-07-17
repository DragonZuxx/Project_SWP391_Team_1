/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Aplal
 */
public class Wishlist {
    private int WishlistID;
    private int UserID;
    private int BookID;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

    public Wishlist() {
    }

    public int getWishlistID() {
        return WishlistID;
    }

    public void setWishlistID(int WishlistID) {
        this.WishlistID = WishlistID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
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
        return "Wishlist{" + "WishlistID=" + WishlistID + ", UserID=" + UserID + ", BookID=" + BookID + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + '}';
    }
    
    
}
