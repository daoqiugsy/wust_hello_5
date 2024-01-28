import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class A {
    public static void main(String[] args) throws ParseException {
        Date d=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //date表示日期
        String date=formatter.format(d);
        String rq = date;
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(rq);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        cal.add(Calendar.DATE,-25);
        //w表示星期几
        System.out.println(formatter.format(cal.getTime()));

    }
}
