package com.ghozay19.kamus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghozay19.kamus.DetailActivity;
import com.ghozay19.kamus.Model.EngBhsModel;
import com.ghozay19.kamus.R;

import java.util.ArrayList;

public class EngBhsAdapter extends RecyclerView.Adapter<EngBhsAdapter.EngBhsHolder> {

    private ArrayList<EngBhsModel> mItemData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public EngBhsAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(ArrayList<EngBhsModel> mData) {
        this.mItemData = mData;
        notifyDataSetChanged();
    }


    @Override
    public EngBhsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary, parent, false);
        return new EngBhsHolder(view);
    }


    public void onBindViewHolder(EngBhsHolder holder, final int position) {
        holder.tvWord.setText(mItemData.get(position).getWord());


        holder.tvWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra(DetailActivity.EXTRA_WORD, mItemData.get(position).getWord());
                intent.putExtra(DetailActivity.EXTRA_DETAIL, mItemData.get(position).getDetail());

                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mItemData == null) return 0;
        return mItemData.size();
    }

    public class EngBhsHolder extends RecyclerView.ViewHolder {
        private TextView tvWord;

        public EngBhsHolder(View itemView) {
            super(itemView);

            tvWord = (TextView) itemView.findViewById(R.id.tvWordItem);
        }
    }

}

