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
        startActivity(gameIntent);
    }
}
