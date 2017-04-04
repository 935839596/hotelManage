package Servlets;

import DAO.RoomUtil;
import DAO.StuUtil;
import VO.Room;
import VO.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by linGo on 2017/4/2.
 */
public class GetRoomInfo extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            this.doPost(req, resp);
    }

    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json");

        int roomID =Integer.parseInt(req.getParameter("roomID")) ;
        RoomUtil roomUtil = null;
        StuUtil stuUtil = null;
        List<Student> list =null;
        int remain = 0;
        try {
            stuUtil = StuUtil.getStuUtil();
             roomUtil = RoomUtil.getRoomUtil();
            list = stuUtil.getStudentWithRoomID(roomID);
            remain = roomUtil.remain(roomID);
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Room room = roomUtil.getRoomWithRoomID(roomID);

        String roomStr = "{" +
                "\"roomID\":" +room.getRoomID()+
                ",\"type\":" +room.getType()+
                ",\"front\":"+"\""+room.getFront()+"\""+
                ",\"pay\":"+room.getMonthRent()+
                ",\"remain\":"+remain+
                ",\"file\":"+ "\""+room.getFiles()+"\""+
                ",\"remark\":"+"\""+room.getComment()+"\""+
                "}";
        String stuStr = "";
        for (int i = 0 ; i <list.size() ; i++){
            stuStr += "{" +
                    "\"FirstName\":" +"\""+list.get(i).getFirstName()+"\""+
                    ",\"LastName\":" +"\""+list.get(i).getLastName()+"\""+
                    ",\"stuID\":\"" +list.get(i).getStuID()+
                    "\",\"ChineseName\":" +"\""+list.get(i).getChineseName()+"\""+
                    ",\"arrivalDate\":" +"\""+list.get(i).getArrivalDate()+"\""+
                    ",\"nationality\":" +"\""+list.get(i).getNationality()+"\""+
                    ",\"galeDate\":" +"\""+list.get(i).getGaleDate()+"\"" +
                    ",\"file\":"+"\""+list.get(i).getFiles()+"\"" +
                    "},";
        }
        try{
            stuStr  = stuStr.substring(0,stuStr.length()-1);
        }catch (Exception e){stuStr=null;}
        String str = "{\"feedback\":"+"[{\"room\":"+roomStr+"},{\"students\":["+stuStr+"]}]}";
        if (stuStr==null)
            str = "{\"feedback\":"+"[{\"room\":"+roomStr+"},{\"students\":"+null+"}]}";

        resp.getWriter().print(str);
    }
}
