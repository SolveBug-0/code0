package data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号验证
 */
public class Policy {

    public static boolean dispose(String str){
        if(str.length()<6)
            return false;
        else
            return true;
    }

    public static boolean dispose2(String phone) {
        String regex = "^((13[0-9])|(16[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9])|(19[0,5-9]))\\d{8}$";
        if(phone.length() != 11){
            return false;
        }else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                return true;
            } else {
                return false;
            }
        }
    }
}
