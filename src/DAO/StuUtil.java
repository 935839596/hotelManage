package DAO;

import VO.Student;
import function.helpFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static function.helpFunction.getNowDate;
import static function.helpFunction.getPayNum;

/**
 * Created by linGo on 2017/3/29.
 */
public class StuUtil {
    private static StuUtil stuUtil;
    private Connection connection;
    PreparedStatement ps = null ;

    private StuUtil() throws SQLException, ClassNotFoundException {
        DBcommonUtil dBcommonUtil = DBcommonUtil.getCommonDataBaseUtil();
        this.connection =  dBcommonUtil.getConnection();
    }

    public static StuUtil getStuUtil() throws SQLException, ClassNotFoundException {
        if (stuUtil==null)
           stuUtil = new StuUtil();
        return stuUtil;
    }

    public int addStu(Student student){
        if (student == null)
            return  0;
        try {
            ps = connection.prepareStatement("select flag from student where stuID=? and ?<dueDate");
            ps.setString(1,student.getStuID());
            ps.setString(2,getNowDate());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int i = rs.getInt(rs.findColumn("flag"));
                if (i==1){
                    return -1;
                }else{
                    return -2;
                }
            }


            ps = connection.prepareStatement("insert into student(stuID,firstName,lastName,ChineseName,nationality,Sex,DOB,regDate,dueDate,program,files,remark) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ");
            ps.setString(1,student.getStuID());
            ps.setString(2,student.getFirstName());
            ps.setString(3,student.getLastName());
            ps.setString(4,student.getChineseName());
            ps.setString(5,student.getNationality());
            ps.setString(6,student.getSex());
            ps.setString(7,student.getDOB());
            ps.setString(8,student.getRegDate());
            ps.setString(9,student.getDueDate());
            ps.setString(10,student.getProgram());
            ps.setString(11,student.getFiles());
            ps.setString(12,student.getRemark());
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean deal(String stuID,int flag){
        try {
            ps = connection.prepareStatement("UPDATE student set flag = ? WHERE stuID=?");
            ps.setInt(1,flag);
            ps.setString(2,stuID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int settle(String stuID,int roomID){
        try {
            ps = connection.prepareStatement("select flag from student where stuID =? and ?<dueDate");
            ps.setString(1,stuID);
            ps.setString(2,getNowDate());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int i = rs.getInt(rs.findColumn("flag"));
                if (i==1){
                    return -1;
                }
            }

            String dateNowStr = getNowDate();
            String nationality = getStudentWithStuID(stuID).getNationality();
            String payNum = getPayNum(stuID);
            ps = connection.prepareStatement("insert into stu_room(stuID,roomID,payNum,arrivalDate,galeDay,nationality) VALUES (?,?,?,?,?,?) ");
            ps.setString(1,stuID);
            ps.setInt(2,roomID);
            ps.setString(3,payNum);
            ps.setString(4,dateNowStr);
            ps.setString(5, helpFunction.getNextMonth());
            ps.setString(6,nationality);
            ps.executeUpdate();
            deal(stuID,1);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public Student getStudentWithStuID(String stuID) throws SQLException {
        Student stu = new Student();
        ps = connection.prepareStatement("SELECT * FROM student WHERE stuID=?");
        ps.setString(1,stuID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            stu.setStuID(rs.getString("stuID"));
            stu.setFirstName(rs.getString("firstName"));
            stu.setLastName(rs.getString("lastName"));
            stu.setChineseName(rs.getString("ChineseName"));
            stu.setNationality(rs.getString("nationality"));
            stu.setDueDate(rs.getString("dueDate"));
            stu.setRegDate(rs.getString("regDate"));
            stu.setDOB(rs.getString("DOB"));
            stu.setProgram(rs.getString("program"));
           stu.setFlag(rs.getInt("flag"));
            stu.setSex(rs.getString("sex"));
            stu.setFiles(rs.getString("files"));
            stu.setRemark(rs.getString("remark"));
        }
        return stu;
    }

    public List<Student> getStudentWithRoomID(int roomID) throws SQLException {
        List<Student> list = new ArrayList<>();
        String now = getNowDate();
        ps = connection.prepareStatement("select stuID,arrivalDate,galeDay from stu_room WHERE roomId=? and ?<galeDay");
        ps.setInt(1,roomID);
        ps.setString(2,now);
        ResultSet rs = ps.executeQuery();
        Student student = null;
        while (rs.next()){
            String stuID = rs.getString("stuID");
            student = getStudentWithStuID(stuID);
            student.setArrivalDate(rs.getString("arrivalDate"));
            student.setGaleDate(rs.getString("galeDay"));
            list.add(student);
        }
        return list;
    }

    public static void main(String []args) throws SQLException, ClassNotFoundException {
        StuUtil stuUtil = StuUtil.getStuUtil();
      /*  Student student=new Student();
        student.setStuID(2015515886);
        student.setFirstName("Steve");
        student.setLastName("Jobs");
        student.setChineseName("乔布斯");
        student.setDOB("1996-08-16");
        student.setSex("male");
        student.setDueDate("2017-4-5");
        student.setFiles("slkdfjksdfjl");
        student.setNationality("Chinese");
        student.setProgram("hotelManage");
        student.setRegDate("2014-10-12");

        stuUtil.addStu(student);*/

//        Student stu = stuUtil.getStudentWithStuID(2015515886);
//       System.out.println(stu.getFirstName());

        //stuUtil.settle(2015515886,880);

       /* List<Student> list = new ArrayList<>();
        list = stuUtil.getStudentWithRoomID(880);
        for (int i = 0; i<list.size();i++){
            System.out.print(list.get(i).getChineseName()+" "+list.get(i).getStuID());
        }*/



    }
}
