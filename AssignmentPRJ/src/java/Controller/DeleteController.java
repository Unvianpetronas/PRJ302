package Controller;
import DAL.UserDAO;
import Entity.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DeleteController", urlPatterns = {"/DeleteController"})
public class DeleteController extends HttpServlet {
    private final String SUCCESS = "Delete successful";
    private final String ERROR = "Failed to delete";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String search = "";
        String role = "All";
        
        try {
            HttpSession session = request.getSession();
            UserDTO ktr = (UserDTO) session.getAttribute("LOGIN_USER");
            int userID = Integer.parseInt(request.getParameter("userID"));
            search = request.getParameter("search");
            role = request.getParameter("Fillter");
            request.setAttribute("SEARCH", search);
            
                if (userID == ktr.getUserid()) {
                    request.setAttribute("DELETE", "You cannot delete yourself!");
                } else {
                    UserDAO dao = new UserDAO();
                    boolean checkDelete = dao.deleteUser(userID);

                    if (checkDelete) {
                        request.setAttribute("DELETE", SUCCESS);
                    } else {
                        boolean userExists = dao.checkUserExists(userID);
                        if (!userExists) {
                            request.setAttribute("DELETE", SUCCESS);
                        } else {
                            request.setAttribute("DELETE", ERROR);
                        }
                    }
                }
        } catch (Exception e) {
            log("Error in DeleteController: " + e.toString());
            request.setAttribute("DELETE", "An error occurred during deletion");
            e.printStackTrace();
        } finally {
            
            request.getRequestDispatcher("admin.jsp").forward(request, response);
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
        return "Short description";
    }
}
