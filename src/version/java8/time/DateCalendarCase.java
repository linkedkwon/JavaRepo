package version.java8.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCalendarCase {

    public String getDateByDate(int option){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return option == 1 ? date.toString() : df.format(date);
    }


    public String getDateByCalendar(int option){
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        if(option == 1)
            return new SimpleDateFormat("yyyy-MM-dd").format(date);

        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DAY_OF_MONTH);
        String dateInfo = Integer.toString(y) + "-" + Integer.toString(m) +
                "-" + Integer.toString(d);

        return dateInfo;
    }


    public void causeTwoClass(){
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.NOVEMBER, 1);  //연산 과정을 이해하기 힘든 코드
        cal.set(2022, 5, 13); //2022-06-13, 0부터 시작
        cal.get(Calendar.DAY_OF_WEEK); //1
        Date date = cal.getTime();
        System.out.println(date.getDay()); //0, 서로 일관성없는 상수 필드
    }


    void test(){
        getDateByCalendar(1);
        getDateByDate(1);
        causeTwoClass();
    }

}
