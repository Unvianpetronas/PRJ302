/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author tuanm
 */
public class AddressDTO  extends UserDTO{

    private int addressID;
    private String street;
    private String city;
    private String state;
    private String postalcode;
    private String country;

    public AddressDTO() {
    }

   public AddressDTO(int addressID, String street, String city, String state, String postalcode, String country, int userid, String username, String name, String password, String role, String phone) {
    super(userid, username, name, password, role, phone,addressID);
    this.addressID = addressID;
    this.street = street;
    this.city = city;
    this.state = state;
    this.postalcode = postalcode;
    this.country = country;
}

    public AddressDTO(int addressID, String street, String city, String state, String postalcode, String country) {
        this.addressID = addressID;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalcode = postalcode;
        this.country = country;
    }
    

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AddressDTO{" + "addressID=" + addressID + ", street=" + street + ", city=" + city + ", state=" + state + ", postalcode=" + postalcode + ", country=" + country + '}';
    }
    
    
    
}
