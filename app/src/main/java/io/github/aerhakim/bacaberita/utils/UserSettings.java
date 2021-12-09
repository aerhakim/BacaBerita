package io.github.aerhakim.bacaberita.utils;


import android.app.Application;

public class UserSettings extends Application {
   public static final String PREFERENCES = "preferences";
   public static final String CUSTOM_THEME = "customTheme";
   public static final String LIGHT_THEME = "lightTheme";
   public static final String DARK_THEME = "darkTHEME";

   private String customTheme;

    public static String getPREFERENCES() {
        return PREFERENCES;
    }

    public static String getCustomTheme() {
        return CUSTOM_THEME;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }

    public static String getLightTheme() {
        return LIGHT_THEME;
    }

    public static String getDarkTheme() {
        return DARK_THEME;
    }
}
