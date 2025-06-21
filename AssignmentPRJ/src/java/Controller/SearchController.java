package Controller;
import DAL.UserDAO;
import Entity.AddressDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {
    private static final String INCORRECT_MESSAGE = "NOT FOUND";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String search = request.getParameter("Search");
            String role = request.getParameter("Fillter");
            
         
            if (search == null) {
                search = "";
            }
            if (role == null) {
                role = "All";
            }
            
            ArrayList<AddressDTO> list = new ArrayList<>();
            request.setAttribute("SEARCH", search);
            UserDAO dao = new UserDAO();
            
            if (role.equals("All")){
                list = dao.getListUser(search);
            } else {
                list = dao.getListUser2condition(search, role);
            }
            
            
            request.setAttribute("SEARCHLIST", list);
            
            
            if (list == null || list.isEmpty()) {
                request.setAttribute("MESS", INCORRECT_MESSAGE);
            }
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
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