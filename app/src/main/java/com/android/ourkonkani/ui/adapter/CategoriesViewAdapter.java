package com.android.ourkonkani.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.ourkonkani.R;
import com.android.ourkonkani.ui.interfaces.RVListener;
import com.android.ourkonkani.ui.model.CategoryItemModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesViewAdapter extends RecyclerView.Adapter<CategoriesViewAdapter.ViewHolder> {
    private List<CategoryItemModel> mItems = new ArrayList<>();
    private Context mContext;
    private RVListener mRVListener;

    public CategoriesViewAdapter(Context context, RVListener listener, List<CategoryItemModel> items) {
        mContext = context;
        mRVListener = listener;
        mItems.clear();
        if (items != null)
            mItems.addAll(items);
    }

    @NonNull
    @Override
    public CategoriesViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_category_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewAdapter.ViewHolder viewHolder, final int position) {
        final CategoryItemModel item = mItems.get(position);
        if (item != null) {
            viewHolder.textView.setText(item.name);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRVListener != null) mRVListener.onRVItemClick(view, item, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_category_name)
        TextView textView;

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);

        }
    }
}
