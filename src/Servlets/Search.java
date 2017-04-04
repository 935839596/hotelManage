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
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        resp.setContentType("text/json");

        int roomNum= Integer.parseInt(req.getParameter("roomNum"));
        RoomUtil roomUtil = null;
        Room room = new Room();
        int remain=0;
        try {
            roomUtil = RoomUtil.getRoomUtil();
            room = roomUtil.getRoomWithRoomID(roomNum);
            remain  = roomUtil.remain(room.getRoomID());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String str = "";
        str += "{\"roomID\":" +room.getRoomID()+
                ",\"type\":" +room.getType()+
                ",\"size\":" +room.getSize()+
                ",\"front\":\"" +room.getFront()+"\""+
                ",\"monthRent\":" +room.getMonthRent()+
                ",\"comment\":\""+room.getComment()+"\""+
                ",\"files\":\""+room.getFiles()+"\""+
                ",\"remain\":"+remain+
                "}";

        resp.getWriter().print("{\"list\":"+str+"}");
    }
}