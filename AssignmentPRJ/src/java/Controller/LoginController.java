package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAL.UserDAO;
import Entity.AddressDTO;
import Entity.UserDTO;
import javax.servlet.http.HttpSession;

/**
 * Servlet for handling user login
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String USER_PAGE = "user.jsp";
    private static final String INCORRECT_MESSAGE = "Incorrect username or password";
    private static final String UNSUPPORTED_MESSAGE = "Your role is not supported yet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("Username"); 
            String password = request.getParameter("Password"); 
            HttpSession session = request.getSession();

            if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
                UserDAO dao = new UserDAO();
                UserDTO validUser = dao.checkLogin(username, password); 

                if (validUser != null) {
                    session.setAttribute("LOGIN_USER", validUser);
                    AddressDTO address = dao.getAddress(validUser.getUserid());
                    session.setAttribute("ADDRESS_USER", address);

                    if ("Customer".equalsIgnoreCase(validUser.getRole())) {
                        url = USER_PAGE;
                    } else if ("Admin".equalsIgnoreCase(validUser.getRole())) {
                        url = ADMIN_PAGE;
                    } else {
                        request.setAttribute("MESSAGE", UNSUPPORTED_MESSAGE);
                    }
                } else {
                    request.setAttribute("MESSAGE", INCORRECT_MESSAGE);
                }
            } else {
                request.setAttribute("MESSAGE", "Username and password cannot be empty.");
            }

            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
        return "Login Controller Servlet";
    }
}
