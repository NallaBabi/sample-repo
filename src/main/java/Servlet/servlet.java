package Servlet;

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
@WebServlet("/register")
public class servlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/order","root","root");
			String sql="insert into orders(orderid,ordername,orderpayment,ordertotal) values (?,?,?,?)";
			PreparedStatement pmst=conn.prepareStatement(sql);
			int orderid=Integer.parseInt(req.getParameter("orderid"));
			String ordername=req.getParameter("ordername");
			String orderpayment=req.getParameter("orderpayment");
			int ordertotal=Integer.parseInt(req.getParameter("ordertotal"));
			pmst.setInt(1, orderid);
			pmst.setString(2,ordername);
			pmst.setString(3,orderpayment);
			pmst.setInt(4,ordertotal);
			
			
			int i=pmst.executeUpdate();
			PrintWriter pw=resp.getWriter();
			if(i>0) {
				pw.println("success");
				
			}
			else {
				pw.println("error");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
