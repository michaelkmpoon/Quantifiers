package spoon.quantifiers;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Highscore extends AppCompatActivity {

    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        mode = "classic";

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.black));

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

    public void switchScores(View view) {
        if(mode.equals("classic")) {
            mode = "crazy";

            ImageView background = findViewById(R.id.background);
            background.setImageResource(R.drawable.high_scores_crazy);

            Button button = findViewById(R.id.button5);
            button.setText("VIEW CLASSIC HIGHSCORES");

            SharedPreferences prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
            int[] highscores = new int[5];
            for(int index = 1; index <= 5; index++) {
                highscores[index - 1] = prefs.getInt("crazyScore" + index, 0);
            }

            ((TextView)findViewById(R.id.highscore1)).setText("" + highscores[0]);
            ((TextView)findViewById(R.id.highscore2)).setText("" + highscores[1]);
            ((TextView)findViewById(R.id.highscore3)).setText("" + highscores[2]);
            ((TextView)findViewById(R.id.highscore4)).setText("" + highscores[3]);
            ((TextView)findViewById(R.id.highscore5)).setText("" + highscores[4]);

        }
        else {
            mode = "classic";

            ImageView background = findViewById(R.id.background);
            background.setImageResource(R.drawable.high_scores_classic);

            Button button = findViewById(R.id.button5);
            button.setText("VIEW CRAZY HIGHSCORES");

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

}
