package spoon.quantifiers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class EndGame extends AppCompatActivity {

    private int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Display score
        setContentView(R.layout.activity_end_game);

        finalScore = getIntent().getExtras().getInt("qNum");
        TextView finalScoreView = findViewById(R.id.finalScore);
        if(finalScore >= 0)
            finalScoreView.setText(""+finalScore);
        else
            finalScoreView.setText(""+finalScore);

        //Update high scores list
        try {
            int[] scores = new int [5];
            boolean Entered = false;
            int count = 0;
            String line;

            BufferedReader reader = new BufferedReader (
                    new InputStreamReader(getResources().openRawResource(R.raw.highscores), "UTF-8"));

            while ((line = reader.readLine()) != null)
            {
                if (finalScore > Integer.parseInt(line) && Entered == false) {
                    scores[count] = finalScore;
                    Entered = true;
                }
                else {
                    scores[count] = Integer.parseInt(line);
                }
                count++;
            }
            reader.close();

//            BufferedWriter writer = new BufferedWriter (
//                    new OutputStreamWriter(getResources().openRawResource(R.raw.highscores), "UTF-8"));
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
//            for (int i = 0 ; i < count ; i++)
//            {
//                writer.write(scores [i]);
//                writer.newLine();
//            }
//            writer.close ();

            for (int i = 0; i < count; i++) {
                System.out.println("Michael" + scores[i]);
            }
        }
        catch (IOException ex) {
            System.out.println(ex);
        }


    }
}
