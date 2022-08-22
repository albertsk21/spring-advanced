package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Result {

    public static String timeConversion(String s) {

        String time = String.valueOf(s.charAt(s.length() -2)) + String.valueOf(s.charAt(s.length() - 1));
        s =  s.replace("PM","");
        s =  s.replace("AM","");


        String[] dateTime = s.split(":");

        int hours = Integer.parseInt(dateTime[0]);
        int minutes = Integer.parseInt(dateTime[1]);
        int seconds = Integer.parseInt(dateTime[2]);

        if(time.equals("PM")){

            boolean needToBeConverted = hours + 12 <= 24 && hours != 12;

            if (needToBeConverted ) {
                hours += 12;
            }
        }else{
            boolean isConverted = String.valueOf(hours).length() == 1;

            if(!isConverted){
                hours -= 12;
                if(hours > 24){
                    hours -= 12;
                }
            }



        }


        String hoursConversion = String.valueOf(hours).length() == 1 ? "0" +  hours : String.valueOf(hours);
        String minutesConversion = String.valueOf(minutes).length() == 1 ? "0" +  minutes : String.valueOf(minutes);
        String secondConversion = String.valueOf(seconds).length() == 1 ? "0" +  seconds : String.valueOf(seconds);

        return hoursConversion + ":" + minutesConversion + ":" + secondConversion;
    }




}
