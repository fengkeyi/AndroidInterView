package com.cn.demo.jatpack.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cn.demo.jatpack.R;
import com.cn.demo.jatpack.bean.Device;
import com.cn.demo.jatpack.bean.DeviceGroup;
import com.cn.demo.jatpack.bean.NullTitle;
import com.cn.demo.jatpack.databinding.LayoutFlowItemBinding;
import com.cn.demo.jatpack.databinding.RecyclerviewDetailBinding;
import com.cn.demo.jatpack.databinding.RecyclerviewItemBinding;
import com.cn.demo.jatpack.databinding.RecyclerviewTitleBinding;
import com.cn.demo.jatpack.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DetectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int TYPE_TITLE = 0x10;
    private final static int TYPE_DETAIL = 0x11;
    private final static int TYPE_GROUP = 0x12;

    private Context mContext;

    private List mDatas = new ArrayList<>();


    public DetectionAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List data) {
        if (data == null || data.size() <= 0) {
            return;
        }
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position) instanceof DeviceGroup) {
            DeviceGroup group = (DeviceGroup) mDatas.get(position);
            if (group.showGroup()) {
                return TYPE_GROUP;
            } else {
                return TYPE_DETAIL;
            }
        } else if (mDatas.get(position) instanceof NullTitle) {
            return TYPE_TITLE;
        } else {
            throw new RuntimeException("detection data type wrong");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_GROUP:
                RecyclerviewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_item,parent, false);
                return new DeviceGroupViewHolder(binding);
            case TYPE_TITLE:
                RecyclerviewTitleBinding titleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_title,parent, false);
                return new GroupTitleViewHolder(titleBinding);
            case TYPE_DETAIL:
                RecyclerviewDetailBinding detailBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_detail,parent, false);
                return new GroupDetailViewHolder(detailBinding);
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case TYPE_GROUP:
                final DeviceGroup group = (DeviceGroup) mDatas.get(position);
                DeviceGroupViewHolder groupViewHolder = (DeviceGroupViewHolder)holder;
                groupViewHolder.bindData(mContext,group);
                groupViewHolder.bindEvent(group, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("FKY_REC", "onClick:"+group.getName());
                        if (!group.isExpand()) {
                            try {
                                DeviceGroup clone = group.clone();
                                clone.setType(DeviceGroup.TYPE_DETAIL);
                                mDatas.add(position + 1, clone);
                                notifyDataSetChanged();
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                            group.setExpand(true);
                        } else {
                            mDatas.remove(position + 1);
                            notifyDataSetChanged();
                            group.setExpand(false);
                        }
                    }
                });
                break;
            case TYPE_TITLE:
                NullTitle title = (NullTitle) mDatas.get(position);
                GroupTitleViewHolder titleViewHolder = (GroupTitleViewHolder) holder;
                titleViewHolder.bindDate(mContext, title);
                break;
            case TYPE_DETAIL:
                DeviceGroup deviceGroup = (DeviceGroup) mDatas.get(position);
                GroupDetailViewHolder groupDetailViewHolder = (GroupDetailViewHolder)holder;
                groupDetailViewHolder.bindData(mContext,deviceGroup);
                break;
            default:
                throw new RuntimeException("wrong type");
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class DeviceGroupViewHolder extends RecyclerView.ViewHolder{
        final RecyclerviewItemBinding binding;
        private DeviceGroupViewHolder(@NonNull RecyclerviewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindData(Context context,DeviceGroup group) {
            binding.imgStatus.setImageResource(group.isNormal() ? R.mipmap.iocn_status_normal : R.mipmap.icon_status_error);
            binding.imgExpand.setVisibility(group.getDevices().size() > 1 ? View.VISIBLE : View.GONE);
            binding.imgExpand.setImageResource(group.isExpand()?R.mipmap.icon_close_item:R.mipmap.icon_expand_item);
            if (!group.isNormal() && group.getDevices().size() > 1) {
                binding.tvErrorCount.setVisibility(View.VISIBLE);
                Util.setCount(context,binding.tvErrorCount,R.string.str_error_count,Util.getUnormalCount(group));
            } else {
                binding.tvErrorCount.setVisibility(View.GONE);
            }
            String title = Util.getDes(group.getName());
            if (group.getDevices().size() > 1) {
                title += context.getString(R.string.str_item_total_count, group.getDevices().size() + "");
            }
            binding.tvTitle.setText(title);
        }

        private void bindEvent(DeviceGroup group,View.OnClickListener listener) {
            if (group.getDevices().size() > 1) {
                binding.getRoot().setOnClickListener(listener);
            } else {
                binding.getRoot().setOnClickListener(null);
            }
        }

    }

    class GroupTitleViewHolder extends RecyclerView.ViewHolder{
        final RecyclerviewTitleBinding binding;
        private GroupTitleViewHolder(@NonNull RecyclerviewTitleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindDate(Context context,NullTitle title) {
            if (title.isNormal()) {
                binding.tvTitleContact.setVisibility(View.GONE);
            }
            binding.tvTitleContent.setText(context.getString(title.getResId(),title.getCount()));
        }

    }

    class GroupDetailViewHolder extends RecyclerView.ViewHolder{
        final RecyclerviewDetailBinding binding;
        private GroupDetailViewHolder(@NonNull RecyclerviewDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindData(Context context, DeviceGroup group) {
            binding.gridDetail.removeAllViews();
            for (Device device : group.getDevices()) {
                binding.gridDetail.addView(getView(context,device));
            }
        }

        private View getView(Context context, Device device) {
            LayoutFlowItemBinding flowItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_flow_item, (ViewGroup) binding.getRoot().getParent(), false);
            flowItemBinding.imgGridStatus.setImageResource(device.isNormal() ? R.mipmap.iocn_status_normal : R.mipmap.icon_status_error);
            flowItemBinding.tvDetail.setText(device.getMessage());
            return flowItemBinding.getRoot();
        }

    }



}
