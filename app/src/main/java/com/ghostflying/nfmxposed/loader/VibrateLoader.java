package com.ghostflying.nfmxposed.loader;

import android.content.Context;

import com.ghostflying.nfmxposed.model.AppInfo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ghostflying on 2/23/15.
 */
public class VibrateLoader extends BaseAppInfoLoader {

    public VibrateLoader(Context context){
        super(context);
    }

    @Override
    protected void sortApps(List<AppInfo> apps) {
        Collections.sort(apps, new Comparator<AppInfo>() {
            @Override
            public int compare(AppInfo lhs, AppInfo rhs) {
                if (lhs.isVibrateRestricted() && !rhs.isVibrateRestricted()){
                    return -1;
                }
                else if (!lhs.isVibrateRestricted() && rhs.isVibrateRestricted()){
                    return 1;
                }
                else {
                    return lhs.getAppName().compareTo(rhs.getAppName());
                }
            }
        });
    }
}
