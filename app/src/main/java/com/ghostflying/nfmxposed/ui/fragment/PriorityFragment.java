package com.ghostflying.nfmxposed.ui.fragment;

import android.app.DialogFragment;
import android.app.Notification;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;

import com.ghostflying.nfmxposed.R;
import com.ghostflying.nfmxposed.adapter.BaseAppListAdapter;
import com.ghostflying.nfmxposed.adapter.PriorityAdapter;
import com.ghostflying.nfmxposed.loader.PriorityLoader;
import com.ghostflying.nfmxposed.model.AppInfo;
import com.ghostflying.nfmxposed.util.PreferencesUtil;

import java.util.List;

/**
 * Created by ghostflying on 2/20/15.
 */
public class PriorityFragment extends BaseManagerFragment {

    public static PriorityFragment newInstance(){
        return new PriorityFragment();
    }

    public PriorityFragment(){

    }

    @Override
    protected BaseAppListAdapter getAdapter() {
        return new PriorityAdapter(
                mOnItemClickListener,
                getResources().getColor(R.color.modified_status_text_color),
                getResources().getColor(R.color.unmodified_status_text_color)
        );
    }

    @Override
    public Loader<List<AppInfo>> onCreateLoader(int id, Bundle args) {
        return new PriorityLoader(getActivity());
    }

    private View.OnClickListener mOnItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getClickedItem(v);
            int clickedPriority = getSelectedPriority(mClickedItem.getPriority());
            DialogFragment mDialog = SingleChooseDialogFragment.newInstance(R.string.priority_dialog_title, R.array.priority_choose_items, clickedPriority);
            mDialog.setTargetFragment(PriorityFragment.this, 0);
            mDialog.show(getFragmentManager(), null);
        }
    };

    private int getSelectedPriority(int priority){
        switch (priority){
            case Notification.PRIORITY_MAX:
                return 1;
            case Notification.PRIORITY_HIGH:
                return 2;
            case Notification.PRIORITY_DEFAULT:
                return 3;
            case Notification.PRIORITY_LOW:
                return 4;
            case Notification.PRIORITY_MIN:
                return 5;
            default:
                return 0;
        }
    }

    @Override
    public void onPositiveButtonClick(int value, int title) {
        int setPriority;
        switch (value) {
            case 1:
                setPriority = Notification.PRIORITY_MAX;
                break;
            case 2:
                setPriority = Notification.PRIORITY_HIGH;
                break;
            case 3:
                setPriority = Notification.PRIORITY_DEFAULT;
                break;
            case 4:
                setPriority = Notification.PRIORITY_LOW;
                break;
            case 5:
                setPriority = Notification.PRIORITY_MIN;
                break;
            default:
                setPriority = PreferencesUtil.DEFAULT_PRIORITY;
        }
        mClickedItem.setPriority(setPriority);
        mAdapter.notifyItemChanged(mClickedPosition);
    }
}
