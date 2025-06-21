package Entity;

public class UserDTO {
    private int userid;        // UserID as INT
    private String username;   // Added Username from DB
    private String name;
    private String password;   
    private String role;       
    private String phone;
    private int addressID;     // Added AddressID from DB

    public UserDTO() {
    }

    public UserDTO(int userid, String username, String name, String password, String role, String phone, int addressID) {
        this.userid = userid;
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.addressID = addressID;
    }
    

 

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", addressID=" + addressID +
                '}';
    }
}
