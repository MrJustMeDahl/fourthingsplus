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

@WebServlet(name = "AddItem", value = "/additem")
public class AddItem extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String newItem = request.getParameter("newItem");
        User currentUser = (User) request.getSession().getAttribute("user");

        try {
            ItemFacade.addItem(newItem, currentUser.getUsername(), connectionPool);
            List<Item> allItems = ItemFacade.getDoingForUser(connectionPool, currentUser);
            List<Item> doneItems = ItemFacade.getDoneForUser(connectionPool, currentUser);
            request.setAttribute("itemlist", allItems);
            request.setAttribute("donelist", doneItems);
        } catch(DatabaseException e){
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
    }
}
