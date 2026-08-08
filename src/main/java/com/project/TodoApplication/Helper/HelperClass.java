package com.project.TodoApplication.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HelperClass {
    //LocalDateTime ->Date Converter
    public static Date parseDate(LocalDateTime localDateTime){
        if(localDateTime==null){
            return null;
        }
        //        System.out.println(ZoneId.systemDefault());
        Date date= Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(date);
        return date;
    }

}
