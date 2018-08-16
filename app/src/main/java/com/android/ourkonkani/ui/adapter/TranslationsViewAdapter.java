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

public class TranslationsViewAdapter extends RecyclerView.Adapter<TranslationsViewAdapter.ViewHolder> {
    private List<CategoryItemModel> mItems = new ArrayList<>();
    private Context mContext;
    private RVListener mRVListener;

    public TranslationsViewAdapter(Context context, RVListener listener, List<CategoryItemModel> items) {
        mContext = context;
        mRVListener = listener;
        mItems.clear();
        if (items != null)
            mItems.addAll(items);
    }

    @NonNull
    @Override
    public TranslationsViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_translation_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TranslationsViewAdapter.ViewHolder viewHolder, final int position) {
        final CategoryItemModel item = mItems.get(position);
        if (item != null) {
            viewHolder.textView.setText(item.key);
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
        @BindView(R.id.tv_translation_name)
        TextView textView;

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);

        }
    }
}