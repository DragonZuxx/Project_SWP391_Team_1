
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
public class CartItem {
    private int CartItemID, CartID, BookID, Quantity;
    private LocalDateTime CreateAt, UpdateAt;

    public CartItem() {
    }

    public CartItem(int CartItemID, int CartID, int BookID, int Quantity, LocalDateTime CreateAt, LocalDateTime UpdateAt) {
        this.CartItemID = CartItemID;
        this.CartID = CartID;
        this.BookID = BookID;
        this.Quantity = Quantity;
        this.CreateAt = CreateAt;
        this.UpdateAt = UpdateAt;
    }

    public int getCartItemID() {
        return CartItemID;
    }

    public void setCartItemID(int CartItemID) {
        this.CartItemID = CartItemID;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public LocalDateTime getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(LocalDateTime CreateAt) {
        this.CreateAt = CreateAt;
    }

    public LocalDateTime getUpdateAt() {
        return UpdateAt;
    }

    public void setUpdateAt(LocalDateTime UpdateAt) {
        this.UpdateAt = UpdateAt;
    }

    @Override
    public String toString() {
        return "CartItem{" + "CartItemID=" + CartItemID + ", CartID=" + CartID + ", BookID=" + BookID + ", Quantity=" + Quantity + ", CreateAt=" + CreateAt + ", UpdateAt=" + UpdateAt + '}';
    }
    
}
