package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static final String ACCOUNT_REGEX ="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";;
    private static final String DATE_REGEX = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    private static final String EMAIL_REGEX="^(.+)@(.+)$";
    private static final String PHONE_REGEX= "^\\d{10}$";
    public Validate() {
    }
    public boolean isvalidaccount(String regex) {
        Pattern pattern = Pattern.compile(ACCOUNT_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
    public boolean isvaliddate(String regex) {
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
    public boolean isvalidemail(String regex) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
    public boolean isvalidphone(String regex) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

}
