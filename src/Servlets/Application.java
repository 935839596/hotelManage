package Servlets;

import VO.Student;
import function.Count;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linGo on 2017/4/2.
 */
public class Application extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json");

        String feedback="";
        List<Student> list = new ArrayList<>();
        try {
            Count count = Count.getCount();
            list = count.application();
            for (int i =0 ;i<list.size();i++){
                feedback += "{" +
                        "\"FirstName\":" +"\""+list.get(i).getFirstName()+"\""+
                        ",\"LastName\":" +"\""+list.get(i).getLastName()+"\""+
                        ",\"stuID\":\"" +list.get(i).getStuID()+
                        "\",\"ChineseName\":" +"\""+list.get(i).getChineseName()+"\""+
                        ",\"sex\":" +"\""+list.get(i).getSex()+"\""+
                        ",\"nationality\":" +"\""+list.get(i).getNationality()+"\""+
                        ",\"dueDate\":" +"\""+list.get(i).getDueDate()+"\""+
                        ",\"project\":" +"\""+list.get(i).getProgram()+"\""+
                        ",\"DOB\":" +"\""+list.get(i).getDOB()+"\""+
                        ",\"regDate\":" +"\""+list.get(i).getRegDate()+"\""+
                        ",\"file\":" +"\""+list.get(i).getFiles()+"\""+
                        "},";
            }
            try{
                feedback = feedback.substring(0,feedback.length()-1);
            }catch (Exception e){feedback = null;}


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (feedback==null)
            resp.getWriter().print("{\"feedback\":"+null+"}");
        else {
            resp.getWriter().print("{\"feedback\":["+feedback+"]}");
        }


    }
}