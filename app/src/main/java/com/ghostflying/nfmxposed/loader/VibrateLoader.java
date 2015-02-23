package com.ghostflying.nfmxposed.loader;

import android.content.Context;

import com.ghostflying.nfmxposed.model.AppInfo;

/**
 * Created by ghostflying on 2/23/15.
 */
public class VibrateLoader extends BaseAppInfoLoader {

    public VibrateLoader(Context context){
        super(context);
    }

    @Override
    protected boolean isRestricted(AppInfo info) {
        return info.isVibrateRestricted();
    }
}
