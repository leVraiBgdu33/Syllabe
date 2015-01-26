package com.example.parrouy.syllabe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Parrouy on 26/01/2015.
 */
public class NiveauActivity extends Activity {

    private Button facile;
    private Button moyen;
    private Button difficile;
    private ImageButton retour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niveauactivity);

        facile = (Button) findViewById(R.id.facile);
        facile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                goToFacileActivity();
                finish();
            }
        });

        retour = (ImageButton) findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                goToMainActivity();
                finish();
            }
        });
    }

    public void goToFacileActivity(){
        Intent intent = new Intent(this,FacileActivity.class);
        startActivity(intent);
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
