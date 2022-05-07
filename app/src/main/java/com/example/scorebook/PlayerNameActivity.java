package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class PlayerNameActivity extends AppCompatActivity {

    ConverterHelper converter = new ConverterHelper();

    public static final String Extra_PlayersNames = "PlayerNameActivity.Extra_GamesPlayersNames";

    public int numPlayers;

    public TableLayout mTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);
        numPlayers = Integer.parseInt(getIntent().getExtras().getString(NewGameActivity.Extra_GamePlayers));

        mTableLayout = findViewById(R.id.tl_namePlayers);

        init();
    }

    public void init(){
        for(int x=0; x< numPlayers; x++){
            TableRow nameRow = new TableRow(this);

            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(converter.convertDPS(60, this), converter.convertDPS(10, this), converter.convertDPS(60, this),0);

            EditText nameThePlayer = new EditText(this);
            nameThePlayer.setLayoutParams(params);
            nameThePlayer.setEms(10);
            nameThePlayer.setTextSize(24);
            nameThePlayer.setHeight(converter.convertDPS(48,this));
            nameThePlayer.setHint("Player " + (x+1) + ":");
            nameRow.addView(nameThePlayer);
            mTableLayout.addView(nameRow);
        }
    }

    public void submitPlayersNames(View view){
        Intent intent = new Intent();
        intent.putExtra(Extra_PlayersNames, concat());
        setResult(RESULT_OK, intent);
        finish();
    }

    public String concat(){
        StringBuilder names = new StringBuilder();
        for(int x=0; x<mTableLayout.getChildCount(); x++){
            TableRow row = (TableRow)mTableLayout.getChildAt(x);
            EditText item = (EditText)row.getChildAt(0);
            names.append(item.getText().toString());
            names.append(",");
        }
        return names.toString();
    }
}