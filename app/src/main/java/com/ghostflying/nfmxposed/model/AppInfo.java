package com.ghostflying.nfmxposed.model;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.ghostflying.nfmxposed.util.PreferencesUtil;

/**
 * Created by ghostflying on 2/17/15.
 */
public class AppInfo {
    private final static String DEFAULT_VERSION_NAME = "Version not found";

    private String mAppName;
    private String mPackageName;
    private Drawable mIcon;
    private SharedPreferences mPreferences;


    public AppInfo(ApplicationInfo info, PackageManager pm, SharedPreferences preferences){
        mAppName = info.loadLabel(pm).toString();
        mPackageName = info.packageName;
        mIcon = info.loadIcon(pm);
        mPreferences = preferences;
    }

    public String getAppName(){
        return mAppName;
    }

    public Drawable getIcon(){
        return mIcon;
    }

    public int getPriority(){
        return mPreferences.getInt(mPackageName + PreferencesUtil.PRIORITY_PREFERENCES_POSTFIX, PreferencesUtil.DEFAULT_PRIORITY);
    }

    public void setPriority(int priority) {
        if (priority == PreferencesUtil.DEFAULT_PRIORITY){
            mPreferences.edit().remove(mPackageName + PreferencesUtil.PRIORITY_PREFERENCES_POSTFIX).apply();
        }
        else {
            mPreferences.edit().putInt(mPackageName + PreferencesUtil.PRIORITY_PREFERENCES_POSTFIX, priority).apply();
        }
    }

    public int getVisibility(){
        return mPreferences.getInt(mPackageName + PreferencesUtil.VISIBILITY_PREFERENCES_POSTFIX, PreferencesUtil.DEFAULT_VISIBILITY);
    }

    public void setVisibility(int visibility){
        if (visibility == PreferencesUtil.DEFAULT_VISIBILITY){
            mPreferences.edit().remove(mPackageName + PreferencesUtil.VISIBILITY_PREFERENCES_POSTFIX).apply();
        }
        else {
            mPreferences.edit().putInt(mPackageName + PreferencesUtil.VISIBILITY_PREFERENCES_POSTFIX, visibility).apply();
        }
    }

    public boolean isRestricted(){
        return mPreferences.contains(mPackageName + PreferencesUtil.PRIORITY_PREFERENCES_POSTFIX)
                || mPreferences.contains(mPackageName + PreferencesUtil.VISIBILITY_PREFERENCES_POSTFIX);
    }

    public boolean isPriorityRestricted(){
        return mPreferences.contains(mPackageName + PreferencesUtil.PRIORITY_PREFERENCES_POSTFIX);
    }

    public boolean isVisibilityRestricted(){
        return mPreferences.contains(mPackageName + PreferencesUtil.VISIBILITY_PREFERENCES_POSTFIX);
    }
}
