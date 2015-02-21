package com.ghostflying.nfmxposed;

import android.app.Notification;

import com.ghostflying.nfmxposed.util.PreferencesUtil;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
/**
 * Created by ghostflying on 2/16/15.
 */
public class XposedMain implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XSharedPreferences pre = new XSharedPreferences(BuildConfig.APPLICATION_ID, PreferencesUtil.PREFERENCES_NAME);

        String priorityPreName = loadPackageParam.packageName + PreferencesUtil.PRIORITY_PREFERENCES_POSTFIX;
        String visibilityPreName = loadPackageParam.packageName + PreferencesUtil.VISIBILITY_PREFERENCES_POSTFIX;

        if (pre.contains(priorityPreName) || pre.contains(visibilityPreName)){
            final int setPriority = pre.getInt(priorityPreName, Notification.PRIORITY_DEFAULT);
            final int setVisibility = pre.getInt(visibilityPreName, Notification.VISIBILITY_PRIVATE);
            try{
                findAndHookMethod("android.app.NotificationManager", loadPackageParam.classLoader, "notify", String.class, int.class, Notification.class , new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        ((Notification)param.args[2]).priority = setPriority;
                        ((Notification)param.args[2]).visibility = setVisibility;
                    }
                });
            }
            catch (Throwable t){
                XposedBridge.log(t);
            }
        }
    }
}
