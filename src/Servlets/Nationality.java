package Servlets;

import function.Count;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by linGo on 2017/4/2.
 */
public class Nationality extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        resp.setContentType("text/json");



        HashMap<String,Integer> map=new HashMap<>();


        String feedback ="";
        int currentPage=0;
        int num = 0;
        try {
            Count count = Count.getCount();
            map = count.getCountry();

            int perPage=10;
            int totalPage = (int) Math.ceil((double)map.size()/perPage);
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
            if (currentPage == -10000) currentPage=totalPage;
            if (currentPage>totalPage) currentPage = totalPage;
            if (currentPage<1) currentPage = 1;


                Iterator iter = map.entrySet().iterator();
            int cur;
            for ( cur =0;cur<(currentPage-1)*(perPage); cur++){
                iter.next();
            }

            while (iter.hasNext()) {
                cur++;
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                feedback += "{\""+key+"\":" +val+"},";
                num += (int)val;
                if (cur>=perPage*currentPage) break;
            }
            try{
                feedback = feedback.substring(0,feedback.length()-1);
            }catch (Exception e){
                feedback = "";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        feedback = "{\"array\":["+feedback+"]," +
                "\"totalNum\":" + num+
                ",\"currentPage\":"+currentPage+
                "}";
        resp.getWriter().print(feedback);


    }
}
