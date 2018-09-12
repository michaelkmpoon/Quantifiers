package spoon.quantifiers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DemoQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_question);
    }

    public void goToPractice(View view) {
        Intent practiceIntent = new Intent(DemoQuestion.this, Practice.class);
        startActivity(practiceIntent);
    }
}
