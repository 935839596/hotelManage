package Servlets;

import DAO.StuUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by linGo on 2017/4/2.
 */
public class SettleDown extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        int roomID = Integer.parseInt(req.getParameter("roomID"));
        String stuID = req.getParameter("stuID").trim();
        int flag = 0;

        try {
            StuUtil stuUtil = StuUtil.getStuUtil();
            flag = stuUtil.settle(stuID,roomID);

        } catch (SQLException e) {
            e.printStackTrace();
            flag = 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            flag = 0;
        }

        resp.getWriter().print(flag);

    }
}
