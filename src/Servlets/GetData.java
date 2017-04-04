package Servlets;

import DAO.RoomUtil;
import VO.Room;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linGo on 2017/4/1.
 */
public class GetData extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        resp.setContentType("text/json");

        int type =Integer.parseInt(req.getParameter("type")) ;
        String str = "";
        Room room;
        int remain=0;
        RoomUtil roomUtil = null;
        try {
            roomUtil = RoomUtil.getRoomUtil();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Room> list = new ArrayList<>();
        if (type == 0){
            list = roomUtil.getAllRoom();
        }else if (type == 1){
            list = roomUtil.getRoomWithType(1);
        }else if(type==2){
            list = roomUtil.getRoomWithType(2);
        }else{
            try {
                list = roomUtil.getRoomNotFull();
            } catch (SQLException e) {
                e.printStackTrace();
                list =null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                list = null;
            }
        }

        int perPage = 8;
        int totalPage = (int) Math.ceil((double)list.size() / perPage);
         int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        if (currentPage == -10000) currentPage=totalPage;
         if (currentPage>totalPage) currentPage = totalPage;
        if (currentPage<1) currentPage = 1;


        for (int i = (currentPage-1)*perPage ;i < currentPage*perPage ;i++){
            try{
                room = list.get(i);
            }catch (Exception e ){
                break;
            }
            try {
                remain  = roomUtil.remain(room.getRoomID());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            str += "{\"roomID\":" +room.getRoomID()+
                    ",\"type\":" +room.getType()+
                    ",\"size\":" +room.getSize()+
                    ",\"front\":\"" +room.getFront()+"\""+
                    ",\"monthRent\":" +room.getMonthRent()+
                    ",\"comment\":\""+room.getComment()+"\""+
                    ",\"files\":\""+room.getFiles()+"\""+
                    ",\"remain\":"+remain+
                    "},";
        }
        try{
            str = str.substring(0,str.length()-1);
        }catch (Exception e){ str = null;}

//        if (list.size()>=1) {
//            room = list.get(list.size() - 1);
//            try {
//                remain = roomUtil.remain(room.getRoomID());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            str += "{\"roomID\":" + room.getRoomID() +
//                    ",\"type\":" + room.getType() +
//                    ",\"size\":" + room.getSize() +
//                    ",\"front\":\"" + room.getFront() + "\"" +
//                    ",\"monthRent\":" + room.getMonthRent() +
//                    ",\"comment\":\"" + room.getComment() + "\"" +
//                    ",\"files\":\"" + room.getFiles() + "\"" +
//                    ",\"remain\":" + remain +
//                    "}";
//        }
//        resp.getWriter().print("{\"list\":"+1+"}");
        resp.getWriter().print("{\"list\":"+"["+str+"]," +
                "\"currentPage\":" +currentPage+
                "}");
    }

}
