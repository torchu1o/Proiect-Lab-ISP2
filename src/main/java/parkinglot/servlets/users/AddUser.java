package parkinglot.servlets.users;

import parkinglot.ejb.UserBean;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AddUser", value = "/AddUser")
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"})) // ✅ Adăugat
public class AddUser extends HttpServlet {

    @Inject
    private UserBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Trimitem grupurile întotdeauna, ca să fie vizibile în JSP
        request.setAttribute("userGroups",
                new String[] {"READ_CARS", "WRITE_CARS", "READ_USERS", "WRITE_USERS"});

        request.getRequestDispatcher("/WEB-INF/pages/users/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Preluăm rolurile exact cum au fost bifate în formular
        String[] userGroupsArray = request.getParameterValues("user_groups");
        List<String> groupsToAssign = (userGroupsArray == null)
                ? new ArrayList<>()
                : Arrays.asList(userGroupsArray);

        usersBean.createUser(username, email, password, groupsToAssign);

        // După înregistrare, redirecționează la lista de useri
        response.sendRedirect(request.getContextPath() + "/Users"); // ✅ Corectat
    }
}