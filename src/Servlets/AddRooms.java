package Servlets;

import DAO.RoomUtil;
import VO.Room;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by linGo on 2017/4/2.
 */
public class AddRooms extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        Room room = new Room();
        room.setComment(req.getParameter("remark"));
        room.setMonthRent(Integer.parseInt(req.getParameter("monthRent")));
        room.setType(Integer.parseInt(req.getParameter("type")));
        room.setFiles(req.getParameter("file"));
        room.setSize(Integer.parseInt(req.getParameter("size")));
        room.setFront(req.getParameter("front"));
        room.setRoomID(Integer.parseInt(req.getParameter("roomNum")));

        boolean flag = false;
        try {
            RoomUtil roomUtil = RoomUtil.getRoomUtil();
            flag = roomUtil.addRoom(room);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.getWriter().print(flag);
    }
}
