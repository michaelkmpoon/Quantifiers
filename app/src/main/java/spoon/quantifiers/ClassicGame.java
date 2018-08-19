package spoon.quantifiers;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_game);

        questionView = findViewById(R.id.question);

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

                    AsyncGetWarmupQ warmupTask = new AsyncGetWarmupQ(questionsDB);
                    warmupTask.execute(0);
                }
            }).start();
        }
        else {
            questionsDB = AppDatabase.getInstance(this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    AsyncGetWarmupQ warmupTask = new AsyncGetWarmupQ(questionsDB);
                    warmupTask.execute(0);
                }
            }).start();
        }
    }


    private void preloadQuestions() throws IOException {
        String[] questions = new String [25];
        String[] answers = new String [25];
        System.out.println("Sami");
        String line = null;
        BufferedReader reader = new BufferedReader (
                new InputStreamReader(getResources().openRawResource(R.raw.questions_and_answers), "UTF-8"));
        int count = 0;

        while ((line = reader.readLine ()) != null)
        {
            questions [count] = line;
            answers [count] = reader.readLine ();
            count++;
        }
        reader.close ();
        for (int i = 0 ; i < count ; i++)
        {
            Question newQ = new Question();
            newQ.setQuestion(questions[i]);
            newQ.setAnswer(answers[i]);
            newQ.setQType("warmup");
            questionsDB.myDao().addQuestion(newQ);
        }
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private class AsyncGetWarmupQ extends AsyncTask<Integer, Void, String> {

        private final AppDatabase db;

        AsyncGetWarmupQ(AppDatabase qdb) {
            db = qdb;
        }

        @Override
        protected String doInBackground(final Integer... params) {
            List<Question> warmups = db.myDao().getQuestionsByType("warmup");
            String text = "";
            System.out.println("Num of Qs: " + warmups.size());
            if(warmups.size() > 0) {
                for (int i = 0; i < warmups.size(); i++) {
                    text = text + " New Q: " + warmups.get(i).getQuestion();
                }
                return text;
            }
            else
                return "";
        }

        @Override
        protected void onPostExecute(String question) {
            super.onPostExecute(question);
            // nextQuestion = question;
            questionView.setText(question);
        }
    }

    private class AsyncGetRandQ extends AsyncTask<Void, Void, Question> {

        private final AppDatabase db;

        AsyncGetRandQ(AppDatabase qdb) {
            db = qdb;
        }

        @Override
        protected Question doInBackground(final Void... params) {
            return db.myDao().getRandomQuestion("classic");
        }

        @Override
        protected void onPostExecute(Question nextQ) {
            super.onPostExecute(nextQ);
            nextQuestion = nextQ;
            questionView.setText(nextQuestion.getQuestion());
        }
    }
}
