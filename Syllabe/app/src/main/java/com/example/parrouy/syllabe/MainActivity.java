package com.example.parrouy.syllabe;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private DataBase db;
    private TextView word;
    private TextView s1;
    private TextView s2;
    private Button suivant;
    private Cursor c;
    private ArrayList<String> mot;
    private ArrayList<String> syl1;
    private ArrayList<String> syl2;
    private int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBase(this);
        db.open();

        if(db.getSyllabeEasyWord("bonjour").getCount()==0)
            db.insertWordEasy("bonjour", "bon", "jour");
        if(db.getSyllabeEasyWord("aurevoir").getCount()==0)
            db.insertWordEasy("aurevoir","au","revoir");
        if(db.getSyllabeEasyWord("journée").getCount()==0)
            db.insertWordEasy("journée","jour","née");
        if(db.getSyllabeEasyWord("connard").getCount()==0)
            db.insertWordEasy("connard","co","nnard");
        if(db.getSyllabeEasyWord("pédé").getCount()==0)
            db.insertWordEasy("pédé","pé","dé");

        word= (TextView) findViewById(R.id.word);
        s1= (TextView) findViewById(R.id.s1);
        s2= (TextView) findViewById(R.id.s2);
        suivant = (Button) findViewById(R.id.suivant);
        mot = new ArrayList<String>();
        syl1 = new ArrayList<String>();
        syl2 = new ArrayList<String>();

        c = db.fetchAllEasyWords();
        while(c.moveToNext()){
            mot.add(c.getString(1));
            syl1.add(c.getString(2));
            syl2.add(c.getString(3));
        }
        word.setText(mot.get(0));
        s1.setText(syl1.get(0));
        s2.setText(syl2.get(0));

        suivant.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if(i<5){
                    word.setText(mot.get(i));
                    s1.setText(syl1.get(i));
                    s2.setText(syl2.get(i));
                }
                i++;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
