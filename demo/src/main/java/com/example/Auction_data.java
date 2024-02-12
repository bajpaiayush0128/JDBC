package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Auction_data")
public class Auction_data extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {

            String n = request.getParameter("userName");

            String p = request.getParameter("productName");

            int e = Integer.parseInt(request.getParameter("bidValue"));
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");

                PreparedStatement ps = con.prepareStatement("insert into auction_data values(?,?,?)");

                ps.setString(1, n);

                ps.setString(2, p);

                ps.setInt(3, e);

                int i = ps.executeUpdate();

                if (i > 0) {
                    out.print("You are successfully registered...");
                }

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Auction_data</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet Auction_data at " + request.getContextPath() +
                        "</h1>");
                out.println("</body>");
                out.println("</html>");
                con.close();
                out.close();

            } catch (Exception e2) {
                System.out.println(e2);
            }

        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
