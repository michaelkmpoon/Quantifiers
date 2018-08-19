package spoon.quantifiers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HowToPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
    }

    public void goToInstructionsClassic(View view){
        Intent classicIntent = new Intent(this, HowToPlayClassic.class);
        startActivity(classicIntent);
    }

    public void goToInstructionsCrazy(View view){
        Intent crazyIntent = new Intent(this, HowToPlayCrazy.class);
        startActivity(crazyIntent);
    }
}
