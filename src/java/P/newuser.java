package P;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maan Mandaliya
 */
public class newuser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String username=request.getParameter("uname");
        String name=request.getParameter("name");
        String mobileno=request.getParameter("mo");
        String email=request.getParameter("eid");
        String qualification=request.getParameter("qual");
        String gender=request.getParameter("gender");
        String password=request.getParameter("pwd");
        try 
        {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/electures","root","");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select username from student where username='"+username+"'");
        if(rs.next())
        {
            out.println("username already exist");
            RequestDispatcher rd=request.getRequestDispatcher("signup.html");
            rd.include(request, response);
        }
        PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?,?,?,?,?)");
        ps.setString(1,username);
        ps.setString(2,name);
        ps.setString(3,mobileno);
        ps.setString(4,email);
        ps.setString(5,qualification);
        ps.setString(6,gender);
        ps.setString(7,password);
        int x=ps.executeUpdate();
        if(x==1)
        {
            out.print("<center><h4>You are registered</h4><center>");
            RequestDispatcher rd=request.getRequestDispatcher("login.html");
            rd.include(request, response);
        }
        else
        {
           out.print("Error in register your data");
           RequestDispatcher rd=request.getRequestDispatcher("signup.html");
           rd.include(request, response);
        }
        } 
        catch (SQLException e) 
        {
            out.print("</br>SQL exception occured:"+e);
        }    
        catch(ClassNotFoundException e)
        {
            out.print("</br>ClassNotFoundException:"+e);
        }
        catch(Exception e)
        {
            out.print("</br>Exception occured:"+e);
        }
    }
}
