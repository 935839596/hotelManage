package Servlets;

import DAO.StuUtil;
import VO.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by linGo on 2017/4/2.
 */
public class Apply extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String stuID = req.getParameter("stuID");
        String FirstName = req.getParameter("FirstName");
        String LastName = req.getParameter("LastName");
        String ChineseName = req.getParameter("ChineseName");
        String Nationality = req.getParameter("Nationality");
        String sex = req.getParameter("Sex");
        String DOB = req.getParameter("DOB");
        String RegDate = req.getParameter("RegDate");
        String DueDate = req.getParameter("DueDate");
        String Project = req.getParameter("Project");
        String remark = req.getParameter("remark");
        String file = req.getParameter("file");

        Student student = new Student();
        student.setStuID(stuID);
        student.setDueDate(DueDate);
        student.setDOB(DOB);
        student.setSex(sex);
        student.setFiles(file);
        student.setProgram(Project);
        student.setLastName(LastName);
        student.setFirstName(FirstName);
        student.setChineseName(ChineseName);
        student.setRemark(remark);
        student.setNationality(Nationality);
        student.setRegDate(RegDate);

        int flag = 0;
        try {
            StuUtil stuUtil = StuUtil.getStuUtil();
            flag = stuUtil.addStu(student);
        } catch (SQLException e) {
            e.printStackTrace();
            flag=0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            flag=0;
        }

        resp.getWriter().print(flag);
    }
}
