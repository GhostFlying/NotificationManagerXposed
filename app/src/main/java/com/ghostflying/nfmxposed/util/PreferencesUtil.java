package com.ghostflying.nfmxposed.util;

/**
 * Created by ghostflying on 2/17/15.
 * <br/>
 * This class defined some const value for Settings
 */
public abstract class PreferencesUtil {
    public final static String PREFERENCES_NAME = "Settings";

    public final static String PRIORITY_PREFERENCES_POSTFIX = "/Priority";
    public final static String VISIBILITY_PREFERENCES_POSTFIX = "/Visibility";
    public final static String VIBRATE_PREFERENCES_POSTFIX = "/Vibrate";

    public final static int DEFAULT_PRIORITY = 1984;// not modified
    public final static int DEFAULT_VISIBILITY = 1984;// not modified
    public final static int DEFAULT_VIBRATE = 1984;//not modified

    public final static long[] VIBRATE_SILENT_PATTERN = new long[]{0, 0};

    public final static int VIBRATE_ENABLE = 1;
    public final static int VIBRATE_DISABLE = 2;
    public final static int VIBRATE_SILENT = 3;
}
