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

import java.util.List;

public class CardGameAdapter extends RecyclerView.Adapter<CardGameAdapter.GameViewHolder> {
    private final LayoutInflater mInflater;
    private List<CardGame> mGames;
    private Context mContext;

    CardGameAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;

    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.list_item, parent, false);
        return new GameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        CardGame currentGame = mGames.get(position);
        holder.bindTo(currentGame);
    }

    @Override
    public int getItemCount() {
        if(mGames != null){
            return  mGames.size();
        }
        else {
            return 0;
        }
    }

    void setGames(List<CardGame> games){
        mGames = games;
        notifyDataSetChanged();
    }

    public CardGame getGameAtPosition(int position){
        return mGames.get(position);
    }




    class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mGameTitle;
        private TextView mGameDesc;
        private ImageView mGameImage;
        private TextView mGamePlayers;

        private String players = mContext.getString(R.string.playersCardView);

        private GameViewHolder(View itemView){
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
            newGame.putExtra("playerNames", currentGame.getPlayerNames());
            mContext.startActivity(newGame);
        }
    }
}
