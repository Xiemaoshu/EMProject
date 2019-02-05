package mao.shu.util;

public class EncryptUtil {
    public static final String salt = "bWxkbmphdmE=";
    public static String getPassword(String password){
        return new MD5Code().getMD5ofStr(password + "({" + salt + "})");
    }

}
