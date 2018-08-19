package spoon.quantifiers;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ClassicGame extends AppCompatActivity {

    private static AppDatabase questionsDB;
    private TextView questionView;
    private Question nextQuestion;
    private int questionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_game);

        questionView = findViewById(R.id.question);
        questionNumber = getIntent().getExtras().getInt("qNum", 0);

        if(AppDatabase.INSTANCE == null) {
            questionsDB = AppDatabase.getInstance(this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        questionsDB.myDao().deleteAll();
                        preloadQuestions();
                    }
                    catch (IOException ex) {
                        System.out.println(ex);
                    }

                    askQuestion();
                }
            }).start();
        }
        else {
            questionsDB = AppDatabase.getInstance(this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    askQuestion();
                }
            }).start();
        }
    }


    private void preloadQuestions() throws IOException {
        String[] questions = new String [27];
        String[] answers = new String [27];
        String[] type = new String [27];
        String line = null;
        BufferedReader reader = new BufferedReader (
                new InputStreamReader(getResources().openRawResource(R.raw.questions_and_answers), "UTF-8"));
        int count = 0;

        while ((line = reader.readLine ()) != null)
        {
            questions [count] = line;
            answers [count] = reader.readLine();
            type [count] = reader.readLine();
            count++;
        }
        reader.close ();
        for (int i = 0 ; i < count ; i++)
        {
            Question newQ = new Question();
            newQ.setQuestion(questions[i]);
            newQ.setAnswer(answers[i]);
            newQ.setQType(type[i]);
            questionsDB.myDao().addQuestion(newQ);
        }
    }

    private void askQuestion() {
        if(questionNumber == 0) {
            System.out.println("Got here!!");
            AsyncGetWarmupQ warmupTask = new AsyncGetWarmupQ(questionsDB);
            warmupTask.execute(0);
        }
        else if(questionNumber == 1){
            AsyncGetWarmupQ warmupTask = new AsyncGetWarmupQ(questionsDB);
            warmupTask.execute(1);
        }
        else {
            AsyncGetRandQ randomTask = new AsyncGetRandQ(questionsDB);
            randomTask.execute();
        }
    }

    public void validateAnswer(View view) {
        String userAnswer = "";
        if(true) {
            Intent newQuestionIntent = new Intent(this, ClassicGame.class);
            newQuestionIntent.putExtra("qNum", questionNumber + 1);
            startActivity(newQuestionIntent);
        }
        else {
            Intent endGameIntent = new Intent(this, EndGame.class);
            endGameIntent.putExtra("qNum", questionNumber + 1);
            startActivity(endGameIntent);
        }
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private class AsyncGetWarmupQ extends AsyncTask<Integer, Void, Question> {

        private final AppDatabase db;

        AsyncGetWarmupQ(AppDatabase qdb) {
            db = qdb;
        }

        @Override
        protected Question doInBackground(final Integer... params) {
            List<Question> warmups = db.myDao().getQuestionsByType("warmup");
            if(warmups.size() > 0 && params[0] <= warmups.size()) {
                System.out.println("Size: " + warmups.size());
                return warmups.get(params[0]);
            }
            else
                return new Question();
        }

        @Override
        protected void onPostExecute(Question question) {
            super.onPostExecute(question);
            nextQuestion = question;
            questionView.setText(question.getQuestion());
        }
    }

    private class AsyncGetRandQ extends AsyncTask<Void, Void, Question> {

        private final AppDatabase db;

        AsyncGetRandQ(AppDatabase qdb) {
            db = qdb;
        }

        @Override
        protected Question doInBackground(final Void... params) {
            Question nextQ = db.myDao().getRandomQuestion("classic");
            if(nextQ != null)
                return nextQ;
            else
                return new Question();
        }

        @Override
        protected void onPostExecute(Question nextQ) {
            super.onPostExecute(nextQ);
            nextQuestion = nextQ;
            questionView.setText(nextQuestion.getQuestion());
        }
    }
}
