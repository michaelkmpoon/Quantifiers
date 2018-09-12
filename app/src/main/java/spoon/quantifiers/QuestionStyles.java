package spoon.quantifiers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuestionStyles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_styles);
    }

    public void goToDemo(View view) {
        Intent demoIntent = new Intent(QuestionStyles.this, DemoQuestion.class);
        startActivity(demoIntent);
    }
}
