package spoon.quantifiers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
    }

    public void classicMode(View view){
        Intent gameIntent = new Intent(GameMode.this, ClassicGame.class);
        gameIntent.putExtra("qNum", 0);
        startActivity(gameIntent);
    }

    public void crazyMode(View view) {
        Intent gameIntent = new Intent(GameMode.this, CrazyGame.class);
        gameIntent.putExtra("qNum", 0);
        startActivity(gameIntent);
    }

    public void highScores(View view){
        Intent highScoresIntent = new Intent(GameMode.this, Highscore.class);
        startActivity(highScoresIntent);
    }
}
