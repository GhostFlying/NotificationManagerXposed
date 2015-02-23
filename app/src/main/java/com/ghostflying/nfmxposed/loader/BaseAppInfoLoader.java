package com.ghostflying.nfmxposed.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.ghostflying.nfmxposed.model.AppInfo;
import com.ghostflying.nfmxposed.util.PreferencesUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ghostflying on 2/20/15.
 */
public abstract class BaseAppInfoLoader extends AsyncTaskLoader<List<AppInfo>> {
    protected SharedPreferences mPreferences;
    private List<AppInfo> mApps;

    public BaseAppInfoLoader(Context context){
        super(context);
        mPreferences = context.getSharedPreferences(PreferencesUtil.PREFERENCES_NAME, Context.MODE_WORLD_READABLE);
    }

    @Override
    public List<AppInfo> loadInBackground() {
        List<AppInfo> apps = new ArrayList<>();
        PackageManager manager = getContext().getPackageManager();
        List<ApplicationInfo> installedApps = manager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<ApplicationInfo> launchApps = checkForLaunchIntent(installedApps, manager);
        for (ApplicationInfo each : launchApps){
            apps.add(new AppInfo(each, manager, mPreferences));
        }
        Collections.sort(apps, new Comparator<AppInfo>() {
            @Override
            public int compare(AppInfo lhs, AppInfo rhs) {
                if (isRestricted(lhs) && !isRestricted(rhs)){
                    return -1;
                }
                else if (!isRestricted(lhs) && isRestricted(rhs)){
                    return 1;
                }
                else {
                    return lhs.getAppName().compareTo(rhs.getAppName());
                }
            }
        });
        return apps;
    }

    protected abstract boolean isRestricted(AppInfo info);

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list, PackageManager packageManager) {
        ArrayList<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    appList.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return appList;
    }

    @Override
    public void deliverResult(List<AppInfo> apps){
        if (isReset()){
            apps = null;
        }

        mApps = apps;

        if (isStarted()){
            super.deliverResult(apps);
        }
    }

    @Override
    protected void onStartLoading(){
        if (mApps != null){
            deliverResult(mApps);
        }

        if (mApps == null || takeContentChanged()){
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading(){
        cancelLoad();
    }

    @Override
    protected void onReset(){
        super.onReset();
        onStopLoading();
        if (mApps != null)
            mApps = null;
    }
}
