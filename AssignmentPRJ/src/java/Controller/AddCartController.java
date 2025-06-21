/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAL.Cart;
import DAL.ShoppingDAO;
import Entity.ProductDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tuanm
 */
public class AddCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String productIDParam = request.getParameter("productID");
        String quantityParam = request.getParameter("quantity");
        request.setAttribute("QUANTITY", quantityParam);

        try {
            
            if (productIDParam == null || productIDParam.isEmpty()
                    || quantityParam == null || quantityParam.isEmpty()) {
                request.setAttribute("ERROR", "Missing product or quantity");
                request.getRequestDispatcher("shopping.jsp").forward(request, response);
                return;
            }

          
            int productID = Integer.parseInt(productIDParam);
            int quantity = Integer.parseInt(quantityParam);

            if (quantity <= 0) {
                request.setAttribute("ERROR", "Quantity must be at least 1");
                request.getRequestDispatcher("shopping.jsp").forward(request, response);
                return;
            }

            ShoppingDAO dao = new ShoppingDAO();
            ProductDTO product = dao.getProductByID(productID);

            if (product == null) {
                request.setAttribute("ERROR", "Product not found");
                request.getRequestDispatcher("shopping.jsp").forward(request, response);
                return;
            }

            if (product.getQuantity() < quantity) {
                request.setAttribute("ERROR", "Not enough stock. Available: " + product.getQuantity());
                request.getRequestDispatcher("shopping.jsp").forward(request, response);
                return;
            }

         
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }

            cart.add(product, quantity);
            session.setAttribute("CART", cart);
            request.setAttribute("SUCCESS", "Added to cart: " + product.getName()+" quantity: "+quantity );

        } catch (NumberFormatException e) {
            request.setAttribute("ERROR", "Invalid number format: " + e.getMessage());
            request.getRequestDispatcher("shopping.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            log("Error in AddCartController: " + e.toString());
            request.setAttribute("ERROR", "Server error");
            request.getRequestDispatcher("shopping.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("shopping.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
