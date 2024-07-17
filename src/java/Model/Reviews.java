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
public class Reviews {

    private int ReviewID;
    private int BookID;
    private int UserID;
    private String Comment;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

    public Reviews() {
    }

    public Reviews(int ReviewID, int BookID, int UserID, String Comment, LocalDateTime CreatedAt, LocalDateTime UpdatedAt) {
        this.ReviewID = ReviewID;
        this.BookID = BookID;
        this.UserID = UserID;
        this.Comment = Comment;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }

    public int getReviewID() {
        return ReviewID;
    }

    public void setReviewID(int ReviewID) {
        this.ReviewID = ReviewID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
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
        return "Reviews{" + "ReviewID=" + ReviewID + ", BookID=" + BookID + ", UserID=" + UserID + ", Comment=" + Comment + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + '}';
    }

    
}
