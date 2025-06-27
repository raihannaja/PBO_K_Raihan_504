package model;

public final class AppInfo {
    public static final String APP_NAME = "Brain Apllication";
    public static final String VERSION = "1.0";
    public static final String DEVELOPER = "Brain Dev Team | Kelompok 5 - Informatika 2K";

    // Versi standar
    public static String getFullInfo() {
        return APP_NAME + " - v" + VERSION;
    }

    // Versi overload dengan parameter
    public static String getFullInfo(boolean withDeveloper) {
        if (withDeveloper) {
            return "  Dev : " + DEVELOPER;
        } else {
            return getFullInfo();
        }
    }
}
