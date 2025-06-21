/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAL.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tuanm
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean check = false;
        boolean checkusername = false;

        try {
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            String phone = request.getParameter("phone");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String postalcode = request.getParameter("postalcode");
            String country = request.getParameter("country");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confrimpassword");

            if (password.equals(confirmPassword)) {
                UserDAO dao = new UserDAO();
                if (!dao.Checkdoublicate(username)) {
                    int addressId = dao.createAddress(street, city, state, postalcode, country);
                    if (addressId != -1) {
                        check = dao.CreateUser(username, name, role, phone, addressId, password);
                    }
                    if (check) {
                        request.setAttribute("MESS", "Create success! Please go back to Login.");
                    } else {
                        request.setAttribute("MESS", "Error creating user.");
                    }
                } else {
                    request.setAttribute("MESS", "Username is duplicate!.");
                }
            }else{
                 request.setAttribute("MESS", "Passwords do not match! Please try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("MESS", "An error occurred while processing your request.");
        } finally {
            request.getRequestDispatcher("CreateUser.jsp").forward(request, response);
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
        return "Handles user creation";
    }
}
