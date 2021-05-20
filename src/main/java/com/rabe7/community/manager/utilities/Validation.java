package com.rabe7.community.manager.utilities;

import android.text.TextUtils;

public class Validation {

    public static Boolean isEmail(CharSequence str) { return str != null && android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches(); }
    public static Boolean isPhone(CharSequence str) { return str != null && android.util.Patterns.PHONE.matcher(str).matches(); }
    public static boolean isPassword(final String password) { return password.length() > Constants.PASSWORD_LENGTH;  }
    public static boolean isPasswordMatch(final String password , final String confirmPassword){return password.equals(confirmPassword);}
    public static boolean isNullOrEmpty(String string){ return TextUtils.isEmpty(string); }

}
