package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditTask", value = "/edittask")
public class EditTask extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User currentUser = (User) request.getSession().getAttribute("user");
        request.setAttribute("currentTask", request.getParameter("itemid"));
        request.setAttribute("itemlist", ItemFacade.getDoingForUser(connectionPool, currentUser));
        request.setAttribute("donelist", ItemFacade.getDoneForUser(connectionPool, currentUser));

        request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User currentUser = (User) request.getSession().getAttribute("user");
        String itemName = request.getParameter("editedTask");
        int itemID = Integer.parseInt(request.getParameter("itemid"));
        try {
            ItemFacade.updateItemName(connectionPool, itemID, itemName);
        } catch (DatabaseException e){
            request.setAttribute("errormessage", e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("itemlist", ItemFacade.getDoingForUser(connectionPool, currentUser));
        request.setAttribute("donelist", ItemFacade.getDoneForUser(connectionPool, currentUser));

        request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
    }
}
