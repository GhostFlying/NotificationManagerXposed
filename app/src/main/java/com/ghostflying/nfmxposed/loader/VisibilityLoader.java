package com.ghostflying.nfmxposed.loader;

import android.content.Context;

import com.ghostflying.nfmxposed.model.AppInfo;

/**
 * Created by ghostflying on 2/20/15.
 */
public class VisibilityLoader extends BaseAppInfoLoader {

    public VisibilityLoader(Context context){
        super(context);
    }

    @Override
    protected boolean isRestricted(AppInfo info) {
        return info.isVisibilityRestricted();
    }

}
