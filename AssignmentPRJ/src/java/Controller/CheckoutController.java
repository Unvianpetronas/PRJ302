package Controller;

import DAL.Cart;
import DAL.ShoppingDAO;
import Entity.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("CART");
        ShoppingDAO dao = new ShoppingDAO();
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");

       
        if (user == null) {
            request.setAttribute("ERROR", "You must log in to checkout!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            request.setAttribute("ERROR", "Your cart is empty.");
            request.getRequestDispatcher("shoppingbag.jsp").forward(request, response);
            return;
        }

        try {
            int orderID = dao.createOrdered(user.getUserid(), cart.getItems());

            if (orderID == 0) {
                throw new Exception("Order creation failed.");
            }

            
            session.removeAttribute("CART");
            request.setAttribute("SUCCESS", "Your order has been placed successfully.");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        } catch (Exception e) {
           
            log("CheckoutController: Error during checkout: " + e.getMessage(), e);

            request.setAttribute("ERROR", "An error occurred during checkout. Please try again.");
            request.getRequestDispatcher("shoppingbag.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Checkout Controller";
    }
}
