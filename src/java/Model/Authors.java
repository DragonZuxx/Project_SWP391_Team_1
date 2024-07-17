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
public class Authors {

    private int AuthorID;
    private String Name;
    private String Biography;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

    public Authors() {
    }

    public Authors(int AuthorID, String Name, String Biography, LocalDateTime CreatedAt, LocalDateTime UpdatedAt) {
        this.AuthorID = AuthorID;
        this.Name = Name;
        this.Biography = Biography;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(int AuthorID) {
        this.AuthorID = AuthorID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBiography() {
        return Biography;
    }

    public void setBiography(String Biography) {
        this.Biography = Biography;
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
        return "Authors{" + "AuthorID=" + AuthorID + ", Name=" + Name + ", Biography=" + Biography + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + '}';
    } 
}
