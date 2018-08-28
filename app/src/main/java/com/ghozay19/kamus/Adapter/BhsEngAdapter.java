package com.ghozay19.kamus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghozay19.kamus.DetailActivity;
import com.ghozay19.kamus.Model.BhsEngModel;
import com.ghozay19.kamus.Model.EngBhsModel;
import com.ghozay19.kamus.R;

import java.util.ArrayList;

public class BhsEngAdapter extends RecyclerView.Adapter<BhsEngAdapter.BhsEngHolder> {

    private ArrayList<BhsEngModel> mItemData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public BhsEngAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(ArrayList<BhsEngModel> mData) {
        this.mItemData = mData;
        notifyDataSetChanged();
    }


    @Override
    public BhsEngHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary, parent, false);
        return new BhsEngHolder(view);
    }


    public void onBindViewHolder(BhsEngHolder holder, final int position) {
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

    public class BhsEngHolder extends RecyclerView.ViewHolder {
        private TextView tvWord;

        public BhsEngHolder(View itemView) {
            super(itemView);

            tvWord = (TextView) itemView.findViewById(R.id.tvWordItem);
        }
    }

}

