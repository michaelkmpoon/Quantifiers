package spoon.quantifiers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Practice extends AppCompatActivity {

    private Spinner[] answerFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        Spinner spinner1 = (Spinner) findViewById(R.id.leftBracket);
        Spinner spinner2 = (Spinner) findViewById(R.id.numeric_spinner);
        Spinner spinner3 = (Spinner) findViewById(R.id.numeric_spinner_2);
        Spinner spinner4 = (Spinner) findViewById(R.id.rightBracket);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_numeric = ArrayAdapter.createFromResource(this,
                R.array.numeric_array, R.layout.custom_spinner);
        ArrayAdapter<CharSequence> adapter_leftBracket = ArrayAdapter.createFromResource(this,
                R.array.leftBracket, R.layout.custom_spinner);
        ArrayAdapter<CharSequence> adapter_rightBracket = ArrayAdapter.createFromResource(this,
                R.array.rightBracket, R.layout.custom_spinner);
        // Specify the layout to use when the list of choices appears
        adapter_numeric.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_leftBracket.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_rightBracket.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter_leftBracket);
        spinner2.setAdapter(adapter_numeric);
        spinner3.setAdapter(adapter_numeric);
        spinner4.setAdapter(adapter_rightBracket);

        answerFields = new Spinner[] {spinner1, spinner2, spinner3, spinner4};
    }

    public void validateAnswer(View view) {
        String userAnswer = "";

        for(int i = 0; i < answerFields.length; i++) {
            userAnswer = userAnswer + answerFields[i].getSelectedItem().toString();
            if(i == 1)
                userAnswer = userAnswer + ", ";
        }

        TextView feedback = findViewById(R.id.feedback);
        Button submit = findViewById(R.id.practiceButton);

        if(userAnswer.equals("(-∞, 0]")) {
            feedback.setText("Correct!!");
            submit.setText(R.string.backHome);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goHome = new Intent(Practice.this, Home.class);
                    startActivity(goHome);
                }
            });
        }
        else {
            feedback.setText("Try Again.");
        }
    }
}
