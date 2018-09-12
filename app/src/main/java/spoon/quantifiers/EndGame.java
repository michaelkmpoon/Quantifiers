package spoon.quantifiers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        TextView finalScoreView = findViewById(R.id.finalScore);
        if(finalScore >= 0)
            finalScoreView.setText(""+finalScore);
        else
            finalScoreView.setText(""+finalScore);

        SharedPreferences prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
        int[] highscores = new int[5];
        for(int index = 1; index <= 5; index++) {
            highscores[index - 1] = prefs.getInt("score" + index, 0);
        }

        boolean scoreAdded = false;
        int oldScoreIndex = 0;
        SharedPreferences.Editor editor = prefs.edit();

        for(int i = 1; i <= 5; i++) {
            if(!scoreAdded && finalScore > highscores[oldScoreIndex]) {
                editor.putInt("score" + i, finalScore);
                editor.apply();
                scoreAdded = true;
            }
            else {
                editor.putInt("score" + i, highscores[oldScoreIndex]);
                editor.apply();
                oldScoreIndex++;
            }
        }

        int[] newHighscores = new int[5];
        for(int index = 1; index <= 5; index++) {
            newHighscores[index - 1] = prefs.getInt("score" + index, 0);
        }

//        try {
//            //if file high scores does not exist, make it
//            File file = getBaseContext().getFileStreamPath("highscores.txt");
//            if (!file.exists()) {
//                file.createNewFile();
//                OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput("highscores.txt", Context.MODE_PRIVATE));
//                for (int i = 0; i < 5; i++) {
//                    writer.write("0");
//                    writer.write("\r\n");
//                }
//                writer.close();
//            }
//            //read current high scores list
//            InputStream inputStream = context.openFileInput("highscores.txt");
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//            while ((line = reader.readLine()) != null)
//            {
//                // System.out.println("read score" + scores[count]);
//                System.out.println("parse" + Integer.parseInt(line));
//                System.out.println("cheese" + finalScore);
//                if(count >= 5)
//                    break;
//
//                if (finalScore > Integer.parseInt(line) && !Entered) {
//                    scores.add(count, finalScore);
//                    scores.add(count + 1, Integer.parseInt(line));
//                    System.out.println("Michael Poon1" + scores.get(count));
//                    Entered = true;
//                    count++;
//                }
//                else {
//                    scores.add(count, Integer.parseInt(line));
//                    System.out.println("Michael Poon2" + scores.get(count));
//                }
//                count++;
//            }
//            reader.close();
//
//            //write current high scores list
//            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput("highscores.txt", Context.MODE_PRIVATE));
//            for (int i = 0; i < count; i++) {
//                writer.write(scores.get(i));
//                writer.write("\n");
//            }
//            writer.close();
//        }
//        catch (FileNotFoundException ex) {
//            System.out.print(ex);
//        } catch(IOException ex) {
//            System.out.println(ex);
//        }
//
//        for (int i = 0; i < count; i++) {
//            System.out.println("Michael" + scores.get(i));
//        }

    }
}

