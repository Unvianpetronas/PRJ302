package Controller;

import DAL.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean check = false;

        try {
            String userID_raw = request.getParameter("Userid").trim();
            String name = request.getParameter("name").trim();
            String phone = request.getParameter("Phone").trim();
            String addressID_raw = request.getParameter("addressID").trim();
            String street = request.getParameter("street").trim();
            String city = request.getParameter("city").trim();
            String state = request.getParameter("state").trim();
            String postalCode = request.getParameter("postalcode").trim();
            String country = request.getParameter("country").trim();
            int userid = Integer.parseInt(userID_raw);
            int addressID = Integer.parseInt(addressID_raw);

            UserDAO dao = new UserDAO();
            check = dao.updateUser(userid, name, phone, addressID, street, city, postalCode,country,state);

            if (check) {
                request.setAttribute("MESS", "Update successful!");
            } else {
                request.setAttribute("MESS", "Update failed!");
            }
        } catch (Exception e) {
            log("Error in UpdateController: " + e.toString());
            request.setAttribute("MESS", "An error occurred during the update!");
        } finally {
            request.getRequestDispatcher("Update.jsp").forward(request, response);
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
        return "Handles user updates";
    }
}
