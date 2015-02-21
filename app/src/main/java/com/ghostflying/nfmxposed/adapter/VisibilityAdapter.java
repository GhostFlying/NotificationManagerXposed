package com.ghostflying.nfmxposed.adapter;

import android.app.Notification;
import android.view.View;
import android.widget.TextView;

import com.ghostflying.nfmxposed.R;
import com.ghostflying.nfmxposed.model.AppInfo;

/**
 * Created by ghostflying on 2/20/15.
 */
public class VisibilityAdapter extends BaseAppListAdapter {

    public VisibilityAdapter(View.OnClickListener itemOnClickListener, int modifiedTextColor, int unmodifiedTextColor){
        super(itemOnClickListener, modifiedTextColor, unmodifiedTextColor);
    }

    @Override
    protected void setStatusText(AppInfo info, TextView status) {
        switch (info.getVisibility()){
            case Notification.VISIBILITY_PRIVATE:
                status.setText(R.string.status_visibility_private);
                status.setTextColor(mModifiedTextColor);
                break;
            case Notification.VISIBILITY_PUBLIC:
                status.setText(R.string.status_visibility_public);
                status.setTextColor(mModifiedTextColor);
                break;
            case Notification.VISIBILITY_SECRET:
                status.setText(R.string.status_visibility_secret);
                status.setTextColor(mModifiedTextColor);
                break;
            default:
                status.setText(R.string.status_not_modified);
                status.setTextColor(mUnmodifiedTextColor);
        }
    }
}
