/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aplal
 */
public class AddressHistory {
    private int AddressHistoryID;
    private int UserID;
    private String FullName;
    private String Address;
    private String Phone;

    public AddressHistory() {
    }

    public AddressHistory(int AddressHistoryID, int UserID, String FullName, String Address, String Phone) {
        this.AddressHistoryID = AddressHistoryID;
        this.UserID = UserID;
        this.FullName = FullName;
        this.Address = Address;
        this.Phone = Phone;
    }

    public int getAddressHistoryID() {
        return AddressHistoryID;
    }

    public void setAddressHistoryID(int AddressHistoryID) {
        this.AddressHistoryID = AddressHistoryID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @Override
    public String toString() {
        return "AddressHistory{" + "AddressHistoryID=" + AddressHistoryID + ", UserID=" + UserID + ", FullName=" + FullName + ", Address=" + Address + ", Phone=" + Phone + '}';
    }
 
}
