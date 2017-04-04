package Servlets;

import function.Count;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by linGo on 2017/4/2.
 */
public class Rent extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        int  type = Integer.parseInt(req.getParameter("type"));
        String date = req.getParameter("date");
        int money=0;
        try {
            Count count = Count.getCount();
            if (type==1){
                 money = count.incomePerMonth(date);
            }else {
                 money = count.incomePerYear(date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        resp.getWriter().print(money);
    }
}
