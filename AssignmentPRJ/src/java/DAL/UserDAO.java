package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entity.*;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO extends DBConnect {

    public UserDTO checkLogin(String username, String password) throws SQLException {
        String sql = "SELECT [UserID]\n"
                + "      ,[Username]\n"
                + "      ,[Name]\n"
                + "      ,[Role]\n"
                + "      ,[Phone]\n"
                + "      ,[AddressID]\n"
                + "      ,[Password]\n"
                + "  FROM [dbo].[USERS]\n"
                + "  WHERE [Username] = ? AND [Password] = ? ";
        UserDTO userValid = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            if (c != null) {
                stm = c.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    userValid = new UserDTO(rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("Name"),
                            rs.getString("Password"),
                            rs.getString("Role"),
                            rs.getString("Phone"),
                            rs.getInt("AddressID"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while checking login: " + e.getMessage());
        }
        return userValid;
    }

    public boolean Checkdoublicate(String username) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        String sql = "SELECT \n"
                + "      [Username]\n"
                + "      \n"
                + "  FROM [dbo].[USERS]\n"
                + "  WHERE [Username] = ? ";
        try {
            if (c != null) {
                stm = c.prepareStatement(sql);
                stm.setString(1, username);

            }
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }

        } catch (Exception e) {
            e.toString();
        }
        return check;
    }

    public AddressDTO getAddress(int userID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        AddressDTO address = null;
        String sql = "SELECT [AddressID]\n"
                + "      ,[Street]\n"
                + "      ,[City]\n"
                + "      ,[State]\n"
                + "      ,[PostalCode]\n"
                + "      ,[Country]\n"
                + "  FROM [dbo].[ADDRESS]\n"
                + "  WHERE [AddressID] = (SELECT [AddressID] FROM [dbo].[USERS] WHERE [UserID] = ? )";
        try {
            if (c != null) {
                stm = c.prepareStatement(sql);
                stm.setInt(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    address = new AddressDTO(rs.getInt("AddressID"), rs.getString("Street"), rs.getString("City"), rs.getString("State"), rs.getString("PostalCode"), rs.getString("Country"));
                }
            }

        } catch (Exception e) {
            System.out.println("Error at get address");
        }
        return address;

    }

    public ArrayList getListUser(String search) {
        ArrayList<AddressDTO> list = new ArrayList<>();
        String sql = "SELECT a.[AddressID],\n"
                + "       b.[UserID],\n"
                + "       b.[Username],\n"
                + "       b.[Name],\n"
                + "       b.[Password],\n"
                + "       b.[Role],\n"
                + "       b.[Phone],\n"
                + "       a.[Street],\n"
                + "       a.[City],\n"
                + "       a.[State],\n"
                + "       a.[PostalCode],\n"
                + "       a.[Country]\n"
                + "FROM [dbo].[USERS] b\n"
                + "JOIN [dbo].[ADDRESS] a ON b.[AddressID] = a.[AddressID]\n"
                + "WHERE b.[Name] LIKE ? ";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            if (c != null) {
                stm = c.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();

                while (rs.next()) {
                    AddressDTO a = new AddressDTO(
                            rs.getInt("AddressID"),
                            rs.getString("Street"),
                            rs.getString("City"),
                            rs.getString("State"),
                            rs.getString("PostalCode"),
                            rs.getString("Country"),
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("Name"),
                            rs.getString("Password"),
                            rs.getString("Role"),
                            rs.getString("Phone")
                    );
                    list.add(a);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public ArrayList getListUser2condition(String search, String role) {
        ArrayList<AddressDTO> list = new ArrayList<>();
        String sql = "SELECT a.[AddressID],\n"
                + "       b.[UserID],\n"
                + "       b.[Username],\n"
                + "       b.[Name],\n"
                + "       b.[Password],\n"
                + "       b.[Role],\n"
                + "       b.[Phone],\n"
                + "       a.[Street],\n"
                + "       a.[City],\n"
                + "       a.[State],\n"
                + "       a.[PostalCode],\n"
                + "       a.[Country]\n"
                + "FROM [dbo].[USERS] b\n"
                + "JOIN [dbo].[ADDRESS] a ON b.[AddressID] = a.[AddressID]\n"
                + "WHERE b.[Name] LIKE ? AND b.[Role] = ?";
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            if (c != null) {
                stm = c.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                stm.setString(2, role);
                rs = stm.executeQuery();

                while (rs.next()) {
                    AddressDTO a = new AddressDTO(
                            rs.getInt("AddressID"),
                            rs.getString("Street"),
                            rs.getString("City"),
                            rs.getString("State"),
                            rs.getString("PostalCode"),
                            rs.getString("Country"),
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("Name"),
                            rs.getString("Password"),
                            rs.getString("Role"),
                            rs.getString("Phone")
                    );
                    list.add(a);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public boolean deleteUser(int userid) {
        String deleteOrderDetailSQL = "DELETE FROM ORDER_DETAIL WHERE OrderID IN (SELECT OrderID FROM ORDERED WHERE UserID = ?)";
        String deleteCheckoutSQL = "DELETE FROM CHECKOUT WHERE OrderID IN (SELECT OrderID FROM ORDERED WHERE UserID = ?)";
        String deleteOrderedSQL = "DELETE FROM ORDERED WHERE UserID = ?";
        String deleteUserSQL = "DELETE FROM USERS WHERE UserID = ?";
        String deleteAddressSQL = "DELETE FROM ADDRESS WHERE AddressID = ?";

        boolean check = false;
        PreparedStatement stmOrderDetail = null;
        PreparedStatement stmCheckout = null;
        PreparedStatement stmOrdered = null;
        PreparedStatement stmUser = null;
        PreparedStatement stmAddress = null;
        PreparedStatement stmGetAddress = null;
        ResultSet rs = null;

        try {
            if (c != null) {
                c.setAutoCommit(false);

                int addressID = -1;
                stmGetAddress = c.prepareStatement("SELECT AddressID FROM USERS WHERE UserID = ?");
                stmGetAddress.setInt(1, userid);
                rs = stmGetAddress.executeQuery();
                if (rs.next()) {
                    addressID = rs.getInt("AddressID");
                }

                stmOrderDetail = c.prepareStatement(deleteOrderDetailSQL);
                stmOrderDetail.setInt(1, userid);
                stmOrderDetail.executeUpdate();

                stmCheckout = c.prepareStatement(deleteCheckoutSQL);
                stmCheckout.setInt(1, userid);
                stmCheckout.executeUpdate();

                stmOrdered = c.prepareStatement(deleteOrderedSQL);
                stmOrdered.setInt(1, userid);
                stmOrdered.executeUpdate();

                stmUser = c.prepareStatement(deleteUserSQL);
                stmUser.setInt(1, userid);
                int userRowsDeleted = stmUser.executeUpdate();

                if (userRowsDeleted > 0 && addressID > 0) {
                    stmAddress = c.prepareStatement(deleteAddressSQL);
                    stmAddress.setInt(1, addressID);
                    int addressRowsDeleted = stmAddress.executeUpdate();

                    check = (addressRowsDeleted > 0 || userRowsDeleted > 0);
                } else if (userRowsDeleted > 0) {

                    check = true;
                }

                c.commit();

            }
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        
        return check;
    }

    public boolean checkUserExists(int userID) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) AS count FROM USERS WHERE UserID = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            if (c != null) {
                stm = c.prepareStatement(sql);
                stm.setInt(1, userID);
                rs = stm.executeQuery();

                if (rs.next()) {
                    exists = (rs.getInt("count") > 0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exists;
    }

    public boolean updateUser(int userID, String name, String phone, int addressID, String street, String city, String postalcode, String country, String state) {
        String sql = "UPDATE [dbo].[USERS] SET [Name] = ?, [Phone] = ? WHERE [AddressID] = ? AND [UserID] = ?";
        String sqladdress = "UPDATE [dbo].[ADDRESS]"
                + "   SET [Street] = ? "
                + "      ,[City] = ? "
                + "      ,[State] = ? "
                + "      ,[PostalCode] = ? "
                + "      ,[Country] = ? "
                + " WHERE [AddressID] = ?";
        PreparedStatement stm = null;
        PreparedStatement stmaddress = null;
        boolean check = false;

        try {
            if (c != null) { // Assuming 'c' is your database connection
                stm = c.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, phone);
                stm.setInt(3, addressID);
                stm.setInt(4, userID);
                stmaddress = c.prepareStatement(sqladdress);
                stmaddress.setString(1, street);
                stmaddress.setString(2, city);
                stmaddress.setString(3, state);
                stmaddress.setString(4, postalcode);
                stmaddress.setString(5, country);
                stmaddress.setInt(6, addressID);

                int rowUpdate = stm.executeUpdate();
                check = rowUpdate > 0; 
                if (check) {
                    stmaddress.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    public int createAddress(String street, String city, String state, String postalcode, String country) {
        String sql = "INSERT INTO [dbo].[ADDRESS]\n"
                + "           ([Street]\n"
                + "           ,[City]\n"
                + "           ,[State]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country])\n"
                + "     VALUES\n"
                + "            ( ?"
                + "           , ? "
                + "           , ? "
                + "           , ? "
                + "           , ? )";
        int addressId = -1;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            if (c != null) {
                stm = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, street);
                stm.setString(2, city);
                stm.setString(3, state);
                stm.setString(4, postalcode);
                stm.setString(5, country);

            }
            int rowsUpdate = stm.executeUpdate();
            if (rowsUpdate > 0) {
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    addressId = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressId;
    }

    public boolean CreateUser(String username, String name, String role, String phone, int addressId, String password) {
        String sql = "INSERT INTO [dbo].[USERS]\n"
                + "           ([Username]\n"
                + "           ,[Name]\n"
                + "           ,[Role]\n"
                + "           ,[Phone]\n"
                + "           ,[AddressID]\n"
                + "           ,[Password])\n"
                + "     VALUES\n"
                + "           ( ?"
                + "           ,?"
                + "           ,?"
                + "           ,?"
                + "           ,?"
                + "           ,?)";
        PreparedStatement stm = null;
        boolean check = false;
        try {
            if (c != null) {
                stm = c.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, name);
                stm.setString(3, role);
                stm.setString(4, phone);
                stm.setInt(5, addressId);
                stm.setString(6, password);
            }
            int rowsupdate = stm.executeUpdate();
            if (rowsupdate > 0) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check;

    }


    /*public boolean updateUser(String userID, String fullName, String roleID) {
        String sql = "UPDATE [dbo].[tblUsers] SET [fullName] = ?, [roleID] = ? WHERE [userID] = ?";
        PreparedStatement stm = null;
        boolean check = false;
        try {
            // Assuming 'c' is your database connection object
            if (c != null) {
                stm = c.prepareStatement(sql);
                // Setting the parameters without % for a direct update
                stm.setString(1, fullName);
                stm.setString(2, roleID);
                stm.setString(3, userID);

                // Execute the update
                int rowUpdate = stm.executeUpdate();
                if (rowUpdate > 0) {
                    check = true;  // Update was successful
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    public boolean CreateUser(String Id, String name, String role, String pass, String comfirm) {
        String sql = "INSERT INTO [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = c.prepareStatement(sql);
            stm.setString(1, Id);
            stm.setString(2, name);
            stm.setString(3, pass);
            stm.setString(4, role);
            stm.setString(5, comfirm);
            int rowsInserted = stm.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUserIdExists(String userId) {
        String sql = "SELECT COUNT(*) FROM [dbo].[tblUsers] WHERE [userID] = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            if (c != null) {
                stm = c.prepareStatement(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();

                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Returns true if a user with this ID already exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false; // Returns false if no user with this ID exists or an error occurred
    }

    public UserError CreateUserV2(UserDTO user) throws SQLException {
        String sql = "INSERT INTO [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status]) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        UserError error = new UserError();
        try {
            stm = c.prepareStatement(sql);
            stm.setString(1, user.getUserID());
            stm.setString(2, user.getFullName());
            stm.setString(3, user.getPassword());
            stm.setString(4, user.getRoleID());
            stm.setString(5, user.getStatus());
            int rowsInserted = stm.executeUpdate();
            if (rowsInserted > 0) {
                return null;
            } else {
                error.setError("Failed to create user");
                return error;
            }

        } catch (SQLException e) {
            
            switch (e.getErrorCode()) {
                case 2627: 
                case 2601: // Duplicate key error
                    error.setUserIDError("User ID already exists");
                    break;
                
                case 547: // Foreign key constraint
                    error.setRoleIDError("Invalid Role ID");
                    break;
                
                case 8152: // String or binary data would be truncated
                    if (e.getMessage().contains("userID")) {
                        error.setUserIDError("User ID is too long");
                    } else if (e.getMessage().contains("fullName")) {
                        error.setFullNameError("Full Name is too long");
                    } else if (e.getMessage().contains("password")) {
                        error.setPassConfirmError("Password is too long");
                    }
                    break;
                case 102:
                    error.setError("Umkown Message");
                
                default:
                    
                    error.setError("Database error: " + e.getMessage());
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return error;
    }*/
}
