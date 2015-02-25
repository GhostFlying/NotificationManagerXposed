package com.ghostflying.nfmxposed.ui.fragment;

import android.app.DialogFragment;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;

import com.ghostflying.nfmxposed.R;
import com.ghostflying.nfmxposed.adapter.BaseAppListAdapter;
import com.ghostflying.nfmxposed.adapter.VibrateAdapter;
import com.ghostflying.nfmxposed.loader.VibrateLoader;
import com.ghostflying.nfmxposed.model.AppInfo;
import com.ghostflying.nfmxposed.util.PreferencesUtil;

import java.util.List;

/**
 * Created by ghostflying on 2/23/15.
 */
public class VibrateFragment extends BaseManagerFragment {

    public static VibrateFragment newInstance(){
        return new VibrateFragment();
    }

    public VibrateFragment(){

    }

    @Override
    protected BaseAppListAdapter getAdapter() {
        return new VibrateAdapter(
                mOnItemClickListener,
                getResources().getColor(R.color.modified_status_text_color),
                getResources().getColor(R.color.unmodified_status_text_color)
        );
    }

    private View.OnClickListener mOnItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getClickedItem(v);
            int clickedVibrate = getSelectedVibrate(mClickedItem.getVibrate());
            DialogFragment mDialog = SingleChooseDialogFragment.newInstance(R.string.vibrate_dialog_title, R.array.vibrate_choose_items, clickedVibrate);
            mDialog.setTargetFragment(VibrateFragment.this, 0);
            mDialog.show(getFragmentManager(), null);
        }
    };

    private int getSelectedVibrate(int vibrate){
        switch (vibrate){
            case PreferencesUtil.VIBRATE_ENABLE:
                return 1;
            case PreferencesUtil.VIBRATE_DISABLE:
                return 2;
            case PreferencesUtil.VIBRATE_SILENT:
                return 3;
            default:
                return 0;
        }
    }

    @Override
    public Loader<List<AppInfo>> onCreateLoader(int id, Bundle args) {
        return new VibrateLoader(getActivity());
    }

    @Override
    public void onPositiveButtonClick(int value, int title) {
        int setVibrate;
        switch (value){
            case 1:
                setVibrate = PreferencesUtil.VIBRATE_ENABLE;
                break;
            case 2:
                setVibrate = PreferencesUtil.VIBRATE_DISABLE;
                break;
            case 3:
                setVibrate = PreferencesUtil.VIBRATE_SILENT;
                break;
            default:
                setVibrate = PreferencesUtil.DEFAULT_VIBRATE;
        }
        mClickedItem.setVibrate(setVibrate);
        mAdapter.notifyItemChanged(mClickedPosition);
    }
}
