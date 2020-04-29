/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.util.Calendar;

public final class UserFormValidation {

    public static boolean CheckUserName(String username){
        String pattern = "^[aA-zZ]\\w{8,30}$";
        /* Pattern Definition
            Strictly alphanumeric, should start with an alphabet
            Min 8 and Max 30 Characters
         */
        return username.matches(pattern);
    }

    public static boolean CheckPassword(String password){
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}$";
        /* Pattern Definition
            (?=.*[0-9]) a digit must occur at least once
            (?=.*[a-z]) a lower case letter must occur at least once
            (?=.*[A-Z]) an upper case letter must occur at least once
            (?=.*[@#$%^&+=]) a special character must occur at least once
            (?=\\S+$) no whitespace allowed in the entire string
            .{8,30} at least 8 but max 30 characters
         */
        return password.matches(pattern);
    }

    public static boolean CheckEmail(String email){
        String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        /* Pattern Definition
            All uppercase, alphanumeric email id,
            alphanumeric domain name, only alpha/dot parent domain
         */
        return email.matches(pattern);
    }

    public static boolean CheckInteger(String id){
        boolean status = false;

        try {
            int userid = Integer.parseInt(id);
            if (userid > 0){
                status = true;
            }
        } catch (Exception e) {
            status = false;
        } finally{
            return status;
        }
    }

    public static boolean CheckYear(int year){
        boolean status = false;
        int curyear = Calendar.getInstance().get(Calendar.YEAR);

        try {
            if (year > (curyear-4) && year <= curyear){
                status = true;
            }
        } catch (Exception e) {
            status = false;
        } finally{
            return status;
        }
    }

    public static boolean CheckId(String id){
        boolean status = false;
        int idlength = 0;
        
        if (id.isEmpty()){
            status = false;
        } else {
            idlength = id.length();
        
            if (idlength > 11){
                status = false;
            } else {
                status = true;
            }
        }         
        return status;
    }
}
