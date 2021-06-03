package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static final String ACCOUNT_REGEX ="^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";;
    public Validate() {
    }
    public boolean isvalid(String regex) {
        Pattern pattern = Pattern.compile(ACCOUNT_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
