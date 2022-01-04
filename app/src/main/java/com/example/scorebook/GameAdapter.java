package com.example.scorebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{
    private ArrayList<CardGame> mGames;
    private Context mContext;

    GameAdapter(Context context, ArrayList<CardGame> games){
        this.mGames = games;
        this.mContext = context;
    }

    @NonNull
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {
        CardGame currentGame = mGames.get(position);

        holder.bindTo(currentGame);
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mGameTitle;
        private TextView mGameDesc;
        private ImageView mGameImage;
        private TextView mGamePlayers;

        private String players = mContext.getString(R.string.playersCardView);

        ViewHolder(View itemView){
            super(itemView);
            mGameTitle = itemView.findViewById(R.id.card_title);
            mGameDesc = itemView.findViewById(R.id.card_desc);
            mGameImage = itemView.findViewById(R.id.card_image);
            mGamePlayers = itemView.findViewById(R.id.card_players);

            itemView.setOnClickListener(this);
        }

        void bindTo(CardGame currentGame){
            mGameTitle.setText(currentGame.getTitle());
            mGameDesc.setText(currentGame.getDesc());
            mGamePlayers.setText(players + currentGame.getPlayers());
            Glide.with(mContext).load(currentGame.getImageResource()).into(mGameImage);
        }

        @Override
        public void onClick(View v) {
            CardGame currentGame = mGames.get(getAdapterPosition());
            Intent newGame = new Intent(mContext, GameActivity.class);
            newGame.putExtra("players", currentGame.getPlayers());
            newGame.putExtra("title", currentGame.getTitle());
            mContext.startActivity(newGame);
        }
    }
}
