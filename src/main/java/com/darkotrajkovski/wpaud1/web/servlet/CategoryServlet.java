package com.darkotrajkovski.wpaud1.web.servlet;

import com.darkotrajkovski.wpaud1.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="category-servlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");
        PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h3>User Info</h3>");
        pw.println("IP Address: " + ipAddress + "<br>");
        pw.println("Client Agent: " + clientAgent);
        pw.println("<h3>Category List</h3>");
        pw.println("<ul>");
        categoryService.listCategories().forEach(c -> pw.format("<li>%s (%s)</li>", c.getName(), c.getDescription()));
        pw.println("</ul>");
        pw.println("<h3>Add Category</h3>");
        pw.println("<form method='POST' action='/servlet/category'>" +
                "<label for='name'>Name:</label>" +
                "<input id='name' type='text' name='name'/>" +
                "<label for='description'>Description:</label>" +
                "<input id='desc' type='text' name='desc'/>" +
                "<input type='submit' value='Submit'/>" +
                "</form>");
        pw.println("</body>");
        pw.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        String categoryDescription = req.getParameter("desc");
        categoryService.create(categoryName, categoryDescription);
        resp.sendRedirect("/servlet/category");
    }
}
