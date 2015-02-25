package com.ghostflying.nfmxposed.ui.fragment;

import android.app.DialogFragment;
import android.app.Notification;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;

import com.ghostflying.nfmxposed.R;
import com.ghostflying.nfmxposed.adapter.BaseAppListAdapter;
import com.ghostflying.nfmxposed.adapter.VisibilityAdapter;
import com.ghostflying.nfmxposed.loader.VisibilityLoader;
import com.ghostflying.nfmxposed.model.AppInfo;
import com.ghostflying.nfmxposed.util.PreferencesUtil;

import java.util.List;

/**
 * Created by ghostflying on 2/20/15.
 */
public class VisibilityFragment extends BaseManagerFragment {

    public static VisibilityFragment newInstance(){
        return new VisibilityFragment();
    }

    public VisibilityFragment(){

    }

    @Override
    protected BaseAppListAdapter getAdapter() {
        return new VisibilityAdapter(
                mOnItemClickListener,
                getResources().getColor(R.color.modified_status_text_color),
                getResources().getColor(R.color.unmodified_status_text_color)
        );
    }

    @Override
    public Loader<List<AppInfo>> onCreateLoader(int id, Bundle args) {
        return new VisibilityLoader(getActivity());
    }

    private View.OnClickListener mOnItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getClickedItem(v);
            int clickedVisibility = getSelectedVisibility(mClickedItem.getVisibility());
            DialogFragment mDialog = SingleChooseDialogFragment.newInstance(R.string.visibility_dialog_title, R.array.visibility_choose_items, clickedVisibility);
            mDialog.setTargetFragment(VisibilityFragment.this, 0);
            mDialog.show(getFragmentManager(), null);
        }
    };

    private int getSelectedVisibility(int visibility){
        switch (visibility){
            case Notification.VISIBILITY_PRIVATE:
                return 1;
            case Notification.VISIBILITY_PUBLIC:
                return 2;
            case Notification.VISIBILITY_SECRET:
                return 3;
            default:
                return 0;
        }
    }

    @Override
    public void onPositiveButtonClick(int value, int title) {
        int setVisibility;
        switch (value) {
            case 1:
                setVisibility = Notification.VISIBILITY_PRIVATE;
                break;
            case 2:
                setVisibility = Notification.VISIBILITY_PUBLIC;
                break;
            case 3:
                setVisibility = Notification.VISIBILITY_SECRET;
                break;
            default:
                setVisibility = PreferencesUtil.DEFAULT_VISIBILITY;
        }
        mClickedItem.setVisibility(setVisibility);
        mAdapter.notifyItemChanged(mClickedPosition);
    }
}
