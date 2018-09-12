package spoon.quantifiers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Highscore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        View view = this.getWindow().getDecorView();
        int backColor = Color.argb(100, 65, 65, 65);
        view.setBackgroundColor(backColor);

        SharedPreferences prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
        int[] highscores = new int[5];
        for(int index = 1; index <= 5; index++) {
            highscores[index - 1] = prefs.getInt("score" + index, 0);
        }

        ((TextView)findViewById(R.id.highscore1)).setText("" + highscores[0]);
        ((TextView)findViewById(R.id.highscore2)).setText("" + highscores[1]);
        ((TextView)findViewById(R.id.highscore3)).setText("" + highscores[2]);
        ((TextView)findViewById(R.id.highscore4)).setText("" + highscores[3]);
        ((TextView)findViewById(R.id.highscore5)).setText("" + highscores[4]);
    }


}
