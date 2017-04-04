package function;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by linGo on 2017/3/29.
 */
public class helpFunction {
    public static String getNowDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }

    public static String getNextMonth() {
        /*Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;*/

        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        Date day =  c.getTime();
        String str=  new SimpleDateFormat("yyyy-MM-dd").format(day);
        return str;
    }
    public static String getNextmonth(String thisMonth){
        Date t;
        String nmonth=new String();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");

        try{
            t=sdf.parse(thisMonth);
            Date nextmonth=new Date((t.getYear()+(t.getMonth()+1)/12),(t.getMonth()+1)%12,t.getDate());
            nmonth = sdf.format(nextmonth.getTime());
        }catch(Exception e ){

            e.printStackTrace();
        }
        return nmonth;
    }
    public  static String getPayNum(String stuID){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(date);
        String payNum = stuID+dateNowStr;
        return payNum;
    }

    public static void main(String []args){
       // System.out.println(getPayNum(201551515));
    }

}
