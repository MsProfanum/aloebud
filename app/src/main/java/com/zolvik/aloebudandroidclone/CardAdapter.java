package com.zolvik.aloebudandroidclone;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private ArrayList<CardItem> mCardList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mLine1, mLine2;

        public CardViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mLine1 = itemView.findViewById(R.id.text_line1);
            mLine2 = itemView.findViewById(R.id.text_line2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);
                }
            });

        }
    }

    public CardAdapter(ArrayList<CardItem> cardList) {
        mCardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card, viewGroup, false);
        CardViewHolder cvh = new CardViewHolder(v, mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int i) {
        CardItem currentCard = mCardList.get(i);

        cardViewHolder.mImageView.setImageResource(currentCard.getmImage());
        cardViewHolder.mLine1.setText(currentCard.getmLine1());
        cardViewHolder.mLine2.setText(currentCard.getmLine2());

    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }

}
