package mao.shu.util;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 该类进行时间与字符串的转换,将时间转换为这样的格式: yyyy-MM-dd HH:mm:ss
 */
public class DateUtil {
    private static final String pattern = "yyyy-MM-dd HH:mm:ss";
    public static String getFormatDateTime(Date date){
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String getFormatDateTime(){
        return new SimpleDateFormat(pattern).format(new Date());
    }
}
