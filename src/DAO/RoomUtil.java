package DAO;

import VO.Room;
import VO.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linGo on 2017/3/29.
 */
public class RoomUtil {
    private static RoomUtil roomUtil;
    private Connection connection;
    PreparedStatement ps = null ;

    private RoomUtil() throws SQLException, ClassNotFoundException {
        DBcommonUtil dBcommonUtil = DBcommonUtil.getCommonDataBaseUtil();
        this.connection =  dBcommonUtil.getConnection();
    }

    public static RoomUtil getRoomUtil() throws SQLException, ClassNotFoundException {
        if (roomUtil==null)
            roomUtil = new RoomUtil();
        return roomUtil;
    }

    public boolean addRoom(Room room){
        try {
            ps = connection.prepareStatement("INSERT into room(roomID,type,size,front,monthRent,comment,file) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1,room.getRoomID());
            ps.setInt(2,room.getType());
            ps.setInt(3,room.getSize());
            ps.setString(4,room.getFront());
            ps.setInt(5,room.getMonthRent());
            ps.setString(6,room.getComment());
            ps.setString(7,room.getFiles());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Room> getAllRoom(){
        List <Room> list = new ArrayList<>();
        Room room = null;
        try{
            ps = connection.prepareStatement("SELECT roomID FROM room order by roomID DESC ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(getRoomWithRoomID(rs.getInt("roomID")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Room getRoomWithRoomID(int roomID){

        try {
            ps = connection.prepareStatement("SELECT * FROM room WHERE roomID = ?");
            ps.setInt(1,roomID);
            ResultSet rs = ps.executeQuery();
            Room room = new Room();
            while (rs.next()){
                room.setType(rs.getInt("type"));
                room.setRoomID(rs.getInt("roomID"));
                room.setFront(rs.getString("front"));
                room.setSize(rs.getInt("size"));
                room.setFiles(rs.getString("file"));
                room.setComment(rs.getString("comment"));
                room.setMonthRent(rs.getInt("monthRent"));
            }
            return room;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Room> getRoomWithType(int type){
        List<Room> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT roomID FROM room WHERE TYPE = ?");
            ps.setInt(1,type);
            ResultSet rs = ps.executeQuery();
            Room room = null;
            while (rs.next()){
                int roomID = rs.getInt("roomID");
                list.add(getRoomWithRoomID(roomID));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Room> getRoomNotFull() throws SQLException, ClassNotFoundException {
        RoomUtil roomUtil = RoomUtil.getRoomUtil();
        List<Room> list = roomUtil.getAllRoom();
        List<Room>list1 = new ArrayList<>();
        for (int i=0 ;i<list.size();i++){
            if (roomUtil.remain(list.get(i).getRoomID())!=0){
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    public int remain(int roomID) throws SQLException, ClassNotFoundException {
        StuUtil stuUtil = StuUtil.getStuUtil();
        List <Student> list  = stuUtil.getStudentWithRoomID(roomID);
        int i = list.size();
        Room room = getRoomWithRoomID(roomID);
        return room.getType()-i;
    }


    public static void main(String []args) throws SQLException, ClassNotFoundException {
        RoomUtil roomUtil = RoomUtil.getRoomUtil();
        /*Room room = new Room();
        room.setRoomID(880);
        room.setType(2);
        room.setSize(100);
        room.setFront("north");
        room.setStuID(2014150207);
        room.setMonthRent(1000);
        room.setComment("这是个号房间");
        room.setFiles("sdfjskdflasjfla");

        roomUtil.addRoom(room);*/

      //  System.out.println(roomUtil.remain(880));
    }
}
