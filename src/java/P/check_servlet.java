/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;

public class check_servlet extends HttpServlet {    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String username=request.getParameter("uname");
        String password=request.getParameter("pwd");
        try 
        {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/electures","root","");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select password from student where username='"+username+"'");
        if(rs.next())
        {
            if(rs.getString("password").equals(password))
            {
                RequestDispatcher rd=request.getRequestDispatcher("dashboard.jsp");
                rd.forward(request, response);
            }
            else
            {
                out.println("<center><h4>Wrong password!</h4></center>");
                RequestDispatcher rd=request.getRequestDispatcher("login.html");
                rd.include(request, response);
            }
        }
        else
        {
            out.println("<center><h4>Wrong username!</h4></center>");
            RequestDispatcher rd=request.getRequestDispatcher("login.html");
            rd.include(request, response);
        }
        }
        catch (SQLException e) 
        {
            out.println("SQL exception occured:"+e);
        }    
        catch(ClassNotFoundException e)
        {
            out.println("ClassNotFoundException:"+e);
        }
        catch(Exception e)
        {
            out.println("Exception occured:"+e);
        }        
    } 
}
