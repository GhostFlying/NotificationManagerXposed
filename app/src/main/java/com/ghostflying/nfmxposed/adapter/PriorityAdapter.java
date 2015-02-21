package com.ghostflying.nfmxposed.adapter;

import android.app.Notification;
import android.view.View;
import android.widget.TextView;

import com.ghostflying.nfmxposed.R;
import com.ghostflying.nfmxposed.model.AppInfo;

/**
 * Created by ghostflying on 2/20/15.
 */
public class PriorityAdapter extends BaseAppListAdapter {

    public PriorityAdapter(View.OnClickListener itemClickListener, int modifiedTextColor, int unmodifiedTextColor){
        super(itemClickListener, modifiedTextColor, unmodifiedTextColor);
    }

    @Override
    protected void setStatusText(AppInfo info, TextView status) {
        switch (info.getPriority()){
            case Notification.PRIORITY_DEFAULT:
                status.setText(R.string.status_priority_default);
                status.setTextColor(mModifiedTextColor);
                break;
            case Notification.PRIORITY_HIGH:
                status.setText(R.string.status_priority_high);
                status.setTextColor(mModifiedTextColor);
                break;
            case Notification.PRIORITY_LOW:
                status.setText(R.string.status_priority_low);
                status.setTextColor(mModifiedTextColor);
                break;
            case Notification.PRIORITY_MAX:
                status.setText(R.string.status_priority_max);
                status.setTextColor(mModifiedTextColor);
                break;
            case Notification.PRIORITY_MIN:
                status.setText(R.string.status_priority_min);
                status.setTextColor(mModifiedTextColor);
                break;
            default:
                status.setText(R.string.status_not_modified);
                status.setTextColor(mUnmodifiedTextColor);
        }
    }
}
