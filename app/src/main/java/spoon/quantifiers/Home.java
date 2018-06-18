package spoon.quantifiers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void goToHowToPlay(View view){
        Intent instructionsIntent = new Intent(this, HowToPlay.class);
        startActivity(instructionsIntent);
    }
    public void goToGameMode(View view){
        Intent gameModeIntent = new Intent(this, GameMode.class);
        startActivity(gameModeIntent);
    }
}
