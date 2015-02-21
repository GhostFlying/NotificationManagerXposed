package com.ghostflying.nfmxposed.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by ghostflying on 2/20/15.
 */
public class SingleChooseDialogFragment extends DialogFragment {
    // the fragment initialization parameters
    private static final String ARG_TITLE = "title";
    private static final String ARG_ITEMS = "items";
    private static final String ARG_ITEM_CHECKED = "checked";

    private int mTitle;
    private int mItems;
    private int mChecked;

    protected OnDialogButtonClickListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title the resoucce id of the title of the dialog.
     * @param items the resource id of items to choose.
     * @return A new instance of fragment SingleChooseDialogFragment.
     */
    public static SingleChooseDialogFragment newInstance(int title, int items, int checked) {
        SingleChooseDialogFragment fragment = new SingleChooseDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE, title);
        args.putInt(ARG_ITEMS, items);
        args.putInt(ARG_ITEM_CHECKED, checked);
        fragment.setArguments(args);
        return fragment;
    }

    public SingleChooseDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getInt(ARG_TITLE);
            mItems = getArguments().getInt(ARG_ITEMS);
            mChecked = getArguments().getInt(ARG_ITEM_CHECKED);
        }
        try {
            mListener = (OnDialogButtonClickListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getTargetFragment().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setTitle(mTitle)
                .setSingleChoiceItems(mItems, mChecked, onClickListener)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onPositiveButtonClick(mChecked, mTitle);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }

    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mChecked = which;
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnDialogButtonClickListener {
        public void onPositiveButtonClick(int value, int title);
    }
}
