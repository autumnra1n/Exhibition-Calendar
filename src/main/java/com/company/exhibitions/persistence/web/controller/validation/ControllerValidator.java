package com.company.exhibitions.web.controller.validation;

import com.company.exhibitions.utils.Mapper;

import java.sql.Date;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerValidator {

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,32}$";
    private static final String PASSWORD_PATTERN = "^[a-z0-9_-]{3,64}$";
    private static final String EMAIL_PATTERN = "^(\\S+)@(\\S+)\\.(\\S+)$";

    private boolean validateIdType(Map <String, String> parameters){
        try{
            Integer.parseInt(parameters.get("id"));
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private boolean validateIdLength(Map <String, String> parameters){
        return !Objects.isNull(parameters.get("id")) && Integer.valueOf(parameters.get("id"))<Integer.MAX_VALUE;
    }

    private boolean validateThemeLength(Map <String, String> parameters){
        return !Objects.isNull(parameters.get("theme")) && parameters.get("theme").length()<100;
    }

    private boolean validateDate(Map <String, String> parameters){
        try{
            Date.valueOf(parameters.get("date"));
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private boolean validateDescription(Map <String, String> parameters){
        return !Objects.isNull(parameters.get("description")) && parameters.get("description").length()<1000;
    }

    private boolean validateRolename(Map <String, String> parameters){
        return !Objects.isNull(parameters.get("name")) && parameters.get("name").length()<32;
    }

    private boolean validateShowroomLocation(Map <String, String> parameters){
        return !Objects.isNull(parameters.get("location")) && parameters.get("location").length()<50;
    }

    private boolean validateShowroomName(Map <String, String> parameters){
        return !Objects.isNull(parameters.get("name")) && parameters.get("name").length()<50;
    }

    public boolean validateTicketDescription(Map <String, String> parameters){
        return !Objects.isNull(parameters.get("description")) && parameters.get("description").length()<50;
    }

    public boolean validateTicketValue(Map <String, String> parameters){
        int i;
        try {
            i = Integer.valueOf(parameters.get("value"));
        } catch (Exception e){
            return false;
        }
        return i>0&&i<Integer.MAX_VALUE;
    }

    public boolean validateTicketAmount(Map <String, String> parameters){
        int i;
        try {
            i = Integer.valueOf(parameters.get("amount"));
        } catch (Exception e){
            return false;
        }
        return i>0&&i<Integer.MAX_VALUE;
    }

    public boolean validateLogin(Map <String, String> parameters){
        String login = parameters.get("login");
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public boolean validatePassword(Map <String, String> parameters){
        String password = parameters.get("password");
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean validateEmail(Map <String, String> parameters){
        String email = parameters.get("email");
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return email.length()<64&&matcher.matches();
    }

    public boolean validateFirstName(Map <String, String> parameters){
        return !Objects.isNull(parameters.get("firstName")) && parameters.get("firstName").length()<32;
    }

    public boolean validateLastName(Map <String, String> parameters){
        return !Objects.isNull(parameters.get("lastName")) && parameters.get("lastName").length()<32;
    }
}
