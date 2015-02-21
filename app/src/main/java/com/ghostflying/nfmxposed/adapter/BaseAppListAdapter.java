package com.ghostflying.nfmxposed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghostflying.nfmxposed.R;
import com.ghostflying.nfmxposed.model.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ghostflying on 2/19/15.
 */
public abstract class BaseAppListAdapter extends RecyclerView.Adapter<BaseAppListAdapter.ViewHolder> {
    private List<AppInfo> mDataSet;
    private View.OnClickListener mItemClickListener;

    protected int mModifiedTextColor;
    protected int mUnmodifiedTextColor;

    public BaseAppListAdapter(View.OnClickListener itemClickListener, int modifiedTextColor, int unmodifiedTextColor){
        mDataSet = new ArrayList<>();
        mItemClickListener = itemClickListener;
        mModifiedTextColor = modifiedTextColor;
        mUnmodifiedTextColor = unmodifiedTextColor;
    }

    public void addData(List<AppInfo> appInfos){
        mDataSet.addAll(appInfos);
    }

    public AppInfo getDataItem(int position){
        return mDataSet.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_item, parent, false);
        v.setOnClickListener(mItemClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mIcon.setImageDrawable(mDataSet.get(position).getIcon());
        holder.mAppName.setText(mDataSet.get(position).getAppName());
        setStatusText(mDataSet.get(position), holder.mStatus);
    }

    protected abstract void setStatusText(AppInfo info, TextView status);

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.app_icon)
        ImageView mIcon;
        @InjectView(R.id.app_name)
        TextView mAppName;
        @InjectView(R.id.app_status)
        TextView mStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
