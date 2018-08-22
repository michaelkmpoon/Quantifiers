package spoon.quantifiers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

public class HowToPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
    }

    public void goToQuestionStyles(View view) {
        Intent qTypeIntent = new Intent(HowToPlay.this, QuestionStyles.class);
        startActivity(qTypeIntent);
    }
}
