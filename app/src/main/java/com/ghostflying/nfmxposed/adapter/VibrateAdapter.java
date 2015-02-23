package com.ghostflying.nfmxposed.adapter;

import android.view.View;
import android.widget.TextView;

import com.ghostflying.nfmxposed.R;
import com.ghostflying.nfmxposed.model.AppInfo;
import com.ghostflying.nfmxposed.util.PreferencesUtil;

/**
 * Created by ghostflying on 2/23/15.
 */
public class VibrateAdapter extends BaseAppListAdapter {

    public VibrateAdapter(View.OnClickListener itemClickListener, int modifiedTextColor, int unmodifiedTextColor){
        super(itemClickListener, modifiedTextColor, unmodifiedTextColor);
    }

    @Override
    protected void setStatusText(AppInfo info, TextView status) {
        switch (info.getVibrate()) {
            case PreferencesUtil.VIBRATE_ENABLE:
                status.setText(R.string.status_vibrate_enable);
                status.setTextColor(mModifiedTextColor);
                break;
            case PreferencesUtil.VIBRATE_DISABLE:
                status.setText(R.string.status_vibrate_disable);
                status.setTextColor(mModifiedTextColor);
                break;
            case PreferencesUtil.VIBRATE_SILENT:
                status.setText(R.string.status_vibrate_silent);
                status.setTextColor(mModifiedTextColor);
                break;
            default:
                status.setText(R.string.status_not_modified);
                status.setTextColor(mUnmodifiedTextColor);
        }
    }
}
