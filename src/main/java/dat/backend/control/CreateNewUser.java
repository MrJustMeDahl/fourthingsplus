package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateNewUser", value = "/createnewuser")
public class CreateNewUser extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User currentUser = null;
        try{
            currentUser = UserFacade.createUser(username, password, "user",connectionPool);
        } catch(DatabaseException e){
            request.setAttribute("errormessage", e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.getSession().setAttribute("user", currentUser);
        request.setAttribute("itemlist", ItemFacade.getDoingForUser(connectionPool, currentUser));
        request.setAttribute("donelist", ItemFacade.getDoneForUser(connectionPool, currentUser));

        request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
    }
}
