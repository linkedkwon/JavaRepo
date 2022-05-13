package version.java8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimePackageCase {

    public void useCase(){
        //case 1 : LocalDate
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate.toString());

        //case 2 : LocalTime
        LocalTime targetTime = LocalTime.of(5, 20);
        System.out.println(Integer.toString(targetTime.getHour()) + ":" +
                Integer.toString(targetTime.getMinute()));

        //case 3 : LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toString());

        //case 4 : ZonedDateTime
        ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime seoulDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(utcDateTime.toString());
        System.out.println(seoulDateTime.toString());
    }

    public void useArithmeticCase(){
        /** case1 - plus, minus */
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime hourAgo = now.minusHours(1);
        LocalDateTime aboutHour = now.plusHours(1);
        System.out.println("1 hour ago : " + hourAgo + "\n" +
                "about an hour :" + aboutHour);

        /** case2 - compare two instance */
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(1994, 10, 10,
                11, 59);

        nowDateTime.isBefore(target); //false
        nowDateTime.isEqual(target); //false
        nowDateTime.isAfter(target); //true

        /**case3 - difference between A and B */
        LocalTime startTime = LocalTime.now();
        for(int i = 0; i < 1000; i++){
            //...생ㄹ갸
        }
        LocalTime endTime = LocalTime.now();
        startTime.until(endTime, ChronoUnit.HOURS);
    }


    public String getLocalDateNow(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d a h:m");
        return now.format(formatter);
    }


    void test(){
        useCase();
        getLocalDateNow();
        useArithmeticCase();
    }
}
