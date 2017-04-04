package function;

import DAO.DBcommonUtil;
import DAO.RoomUtil;
import DAO.StuUtil;
import VO.Student;
import org.omg.PortableInterceptor.INACTIVE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static function.helpFunction.getNextmonth;
import static function.helpFunction.getNowDate;

/**
 * Created by linGo on 2017/3/29.
 */
public class Count {
    private static Count count ;
    private Connection connection;
    PreparedStatement ps = null ;

    private Count() throws SQLException,ClassNotFoundException{
        DBcommonUtil dBcommonUtil = DBcommonUtil.getCommonDataBaseUtil();
        this.connection =  dBcommonUtil.getConnection();
    }
    public static Count getCount() throws SQLException, ClassNotFoundException {
        if (count == null){
            count = new Count();
        }
        return count;
    }

    public List<Student> living() throws SQLException, ClassNotFoundException {
        List <Student> list = new ArrayList<>();
        StuUtil stuUtil = StuUtil.getStuUtil();
        try{
            String now = getNowDate();
            ps = connection.prepareStatement("select stuID from stu_room WHERE ?<galeDay");
            ps.setString(1,now);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String i = rs.getString("stuID");
                Student student = stuUtil.getStudentWithStuID(i);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public HashMap<String ,Integer> getCountry() throws SQLException, ClassNotFoundException {
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        String nationality = null;
        List<Student> list = living();
        for (int i =0 ;i<list.size();i++){
            String str = list.get(i).getNationality();
            int num = 0;
            if (map.get(str)==null){
                map.put(str,1);
            }else {
                num = map.get(str);
                map.put(str, ++num);
            }
        }
        return map;
    }

    public List<Student> application() throws SQLException, ClassNotFoundException {
        StuUtil stuUtil = StuUtil.getStuUtil();
        List<Student> list = new ArrayList<>();

        String now = getNowDate();
        ps = connection.prepareStatement("SELECT stuID FROM student WHERE flag=0 and ?<dueDate");
        ps.setString(1,now);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String i = rs.getString("stuID");
            Student student = stuUtil.getStudentWithStuID(i);
            list.add(student);
        }
        return list ;
    }

    public int incomePerMonth(String date) throws SQLException, ClassNotFoundException {
        String []month = date.split("-");
        String thisMonth = month[0]+"-"+month[1];
        String nextMonth = getNextmonth(thisMonth);
        return helpGetIncome(thisMonth,nextMonth);
    }

    public int incomePerYear(String date) throws SQLException, ClassNotFoundException {
        String []day=date.split("-");
        String cur = day[0];
        String next = Integer.toString(Integer.parseInt(day[0])+1);
        return helpGetIncome(cur,next);
    }

    public int helpGetIncome(String cur,String next) throws SQLException, ClassNotFoundException {
        RoomUtil roomUtil = RoomUtil.getRoomUtil();
        int rent = 0;
        try {
            ps = connection.prepareStatement("SELECT roomID FROM stu_room WHERE arrivalDate<? and arrivalDate > ?");
            ps.setString(1,next);
            ps.setString(2,cur);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int roomID = rs.getInt("roomID");
                rent += roomUtil.getRoomWithRoomID(roomID).getMonthRent();
            }
            return rent;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String []args) throws SQLException, ClassNotFoundException {
        Count count = Count.getCount();
        List<Student> list = count.living();
    //    System.out.println(list.size());

       /* HashMap<String,Integer>map = count.getCountry();
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            System.out.print(entry.getKey()+":");
            System.out.println(entry.getValue());
        }*/

//       List<Student> lists = count.application();
//        System.out.println(lists.size());


//        count.incomePerMonth(getNowDate());
        System.out.println(count.incomePerMonth(getNowDate()));
        System.out.println(count.incomePerYear(getNowDate()));
    }
}
