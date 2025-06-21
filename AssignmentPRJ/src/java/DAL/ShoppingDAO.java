package DAL;

import Entity.ProductDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ShoppingDAO extends DBConnect {

    public ArrayList<ProductDTO> getAllProduct() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT [ProductID], [Name], [Description], [Price], [Quantity] FROM [DBShopping].[dbo].[PRODUCT]";

        try {
            stm = c.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                ProductDTO productDTO = new ProductDTO(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity")
                );
                list.add(productDTO);
            }
        } catch (SQLException e) {
            System.err.println("Error getting products: " + e.getMessage());
        } 
        return list;
    }

    public ProductDTO getProductByID(int productID) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM PRODUCT WHERE ProductID = ?";

        try {
            stm = c.prepareStatement(sql);
            stm.setInt(1, productID);
            rs = stm.executeQuery();

            if (rs.next()) {
                return new ProductDTO(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting product by ID: " + e.getMessage());
        } 
        return null;
    }

    public int createOrdered(int userID, Map<Integer, ProductDTO> cart) throws Exception {
        PreparedStatement psOrder = null;
        PreparedStatement psOrderDetail = null;
        PreparedStatement psUpdateStock = null;
        ResultSet rs = null;
        int orderId = -1;
        boolean originalAutoCommit = false;
        if (c == null) {
            throw new Exception("Database connection is null.");
        }
        String insertOrderSQL = "INSERT INTO ORDERED (UserID, Date, Total) VALUES (?, GETDATE(), ?)";
        String insertOrderDetailSQL = "INSERT INTO ORDER_DETAIL (OrderID, ProductID, Quantity, Price) VALUES (?, ?, ?, ?)";
        String updateStockSQL = "UPDATE PRODUCT SET Quantity = Quantity - ? WHERE ProductID = ? AND Quantity >= ?";

        try {

            originalAutoCommit = c.getAutoCommit();
            c.setAutoCommit(false);

            double totalAmount = 0;
            for (ProductDTO product : cart.values()) {
                if (product == null) {
                    throw new Exception("Invalid product in cart.");
                }
                totalAmount += product.getPrice() * product.getQuantity();
            }
            psOrder = c.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            psOrder.setInt(1, userID);
            psOrder.setDouble(2, totalAmount);
            psOrder.executeUpdate();
            rs = psOrder.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            } else {
                throw new Exception("Failed to retrieve OrderID from database.");
            }

            psOrderDetail = c.prepareStatement(insertOrderDetailSQL);
            psUpdateStock = c.prepareStatement(updateStockSQL);
            for (ProductDTO product : cart.values()) {
                ProductDTO dbProduct = getProductByID(product.getProductID());
                if (dbProduct == null || dbProduct.getQuantity() < product.getQuantity()) {
                    throw new Exception("Product " + product.getName() + " is out of stock.");
                }

                psOrderDetail.setInt(1, orderId);
                psOrderDetail.setInt(2, product.getProductID());
                psOrderDetail.setInt(3, product.getQuantity());
                psOrderDetail.setDouble(4, product.getPrice());
                psOrderDetail.executeUpdate();

                psUpdateStock.setInt(1, product.getQuantity());
                psUpdateStock.setInt(2, product.getProductID());
                psUpdateStock.setInt(3, product.getQuantity());
                int rowsAffected = psUpdateStock.executeUpdate();

                if (rowsAffected == 0) {
                    throw new Exception("Stock update failed for product " + product.getName());
                }
            }

        } catch (Exception e) {
          
            System.err.println("Error rolling back transaction: " + e.getMessage());

        } 
        return orderId;
    }

 
  
}
