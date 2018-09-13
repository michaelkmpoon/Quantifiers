package spoon.quantifiers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class EndGame extends AppCompatActivity {

    private int finalScore;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        context = this;

        //Display score
        setContentView(R.layout.activity_end_game);

        finalScore = getIntent().getExtras().getInt("qNum");
        int gameMode = getIntent().getExtras().getInt("mode", 0);
        TextView finalScoreView = findViewById(R.id.finalScore);
        if(finalScore >= 0)
            finalScoreView.setText(""+finalScore);
        else
            finalScoreView.setText(""+finalScore);

        if(gameMode == 1) {
            SharedPreferences prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
            int[] highscores = new int[5];
            for (int index = 1; index <= 5; index++) {
                highscores[index - 1] = prefs.getInt("crazyScore" + index, 0);
            }

            boolean scoreAdded = false;
            int oldScoreIndex = 0;
            SharedPreferences.Editor editor = prefs.edit();

            for (int i = 1; i <= 5; i++) {
                if (!scoreAdded && finalScore > highscores[oldScoreIndex]) {
                    editor.putInt("crazyScore" + i, finalScore);
                    editor.apply();
                    scoreAdded = true;
                } else {
                    editor.putInt("crazyScore" + i, highscores[oldScoreIndex]);
                    editor.apply();
                    oldScoreIndex++;
                }
            }

            int[] newHighscores = new int[5];
            for (int index = 1; index <= 5; index++) {
                newHighscores[index - 1] = prefs.getInt("crazyScore" + index, 0);
            }
        }
        else{
            SharedPreferences prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
            int[] highscores = new int[5];
            for (int index = 1; index <= 5; index++) {
                highscores[index - 1] = prefs.getInt("score" + index, 0);
            }

            boolean scoreAdded = false;
            int oldScoreIndex = 0;
            SharedPreferences.Editor editor = prefs.edit();

            for (int i = 1; i <= 5; i++) {
                if (!scoreAdded && finalScore > highscores[oldScoreIndex]) {
                    editor.putInt("score" + i, finalScore);
                    editor.apply();
                    scoreAdded = true;
                } else {
                    editor.putInt("score" + i, highscores[oldScoreIndex]);
                    editor.apply();
                    oldScoreIndex++;
                }
            }

            int[] newHighscores = new int[5];
            for (int index = 1; index <= 5; index++) {
                newHighscores[index - 1] = prefs.getInt("score" + index, 0);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}

