package com.example.parrouy.syllabe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
import java.util.Random;


public class FacileActivity extends Activity {

    private DataBase db;
    private TextView title;
    private Button s1;
    private Button s2;
    private Button suivant;
    private Cursor c;
    private ArrayList<String> mot;
    private ArrayList<String> syl1;
    private ArrayList<String> syl2;
    ArrayList<Integer> res;
    private int i=1;
    private String constructionMot="";
    private boolean checks1 = false;
    private boolean checks2 = false;
    private int min;
    private int max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facileactivity);

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
        if(db.getSyllabeEasyWord("maison").getCount()==0)
            db.insertWordEasy("maison", "mai", "son");
        if(db.getSyllabeEasyWord("vélo").getCount()==0)
            db.insertWordEasy("vélo","vé","lo");
        if(db.getSyllabeEasyWord("sucette").getCount()==0)
            db.insertWordEasy("sucette","su","cette");
        if(db.getSyllabeEasyWord("crevette").getCount()==0)
            db.insertWordEasy("crevette","cre","vette");
        if(db.getSyllabeEasyWord("jardin").getCount()==0)
            db.insertWordEasy("jardin","jar","din");

        title= (TextView) findViewById(R.id.title);
        s1= (Button) findViewById(R.id.s1);
        s2= (Button) findViewById(R.id.s2);
        suivant = (Button) findViewById(R.id.suivant);
        mot = new ArrayList<String>();
        syl1 = new ArrayList<String>();
        syl2 = new ArrayList<String>();

        c = db.fetchAllEasyWords();
        max=c.getCount();
        min=1;
        int r;
        res = new ArrayList<Integer>();
        for(int k=0; k<5; k++){
            r = (int) (min+(Math.random()*(max-min)));
            res.add(r);
            System.out.println(r);
        }

        while(c.moveToNext()){
            for(int j=0; j<5;j++){
                if(c.getString(0).equals(res.get(j).toString())){
                    mot.add(c.getString(1));
                    syl1.add(c.getString(2));
                    syl2.add(c.getString(3));
                }
            }
        }
        title.setText(mot.get(0));
        Random random = new Random();
        int rand = (int) random.nextInt(2);
        if(rand==0){
            s1.setText(syl1.get(0));
            s2.setText(syl2.get(0));
        }
        else{
            s1.setText(syl2.get(0));
            s2.setText(syl1.get(0));
        }

        s1.setBackgroundColor(Color.rgb(44, 62, 80));
        s1.setTextColor(Color.rgb(236, 240, 241));
        s2.setBackgroundColor(Color.rgb(44, 62, 80));
        s2.setTextColor(Color.rgb(236, 240, 241));
        s1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                constructionMot += s1.getText().toString();
                if(checks1){
                    checks1=false;
                }
                else{
                    checks1=true;
                }
                if(checks1){
                    s1.setBackgroundColor(Color.rgb(39, 174, 96));
                    s1.setTextColor(Color.rgb(236, 240, 241));
                    s1.setEnabled(false);
                }
                else{
                    s1.setBackgroundColor(Color.rgb(44, 62, 80));
                    s1.setTextColor(Color.rgb(236, 240, 241));
                }
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                constructionMot += s2.getText().toString();
                if(checks2)
                    checks2=false;
                else
                    checks2=true;
                if(checks2){
                    s2.setBackgroundColor(Color.rgb(39, 174, 96));
                    s2.setTextColor(Color.rgb(236, 240, 241));
                    s2.setEnabled(false);
                }
                else{
                    s2.setBackgroundColor(Color.rgb(44, 62, 80));
                    s2.setTextColor(Color.rgb(236, 240, 241));
                }
            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if(constructionMot.equals(title.getText().toString())){
                    if(i==0){
                        Log.e("you",constructionMot);
                        constructionMot="";
                    }
                    if(i<5){
                        title.setText(mot.get(i));
                        Random random2 = new Random();
                        int rand2 = (int) random2.nextInt(2);
                        if(rand2==0){
                            s1.setText(syl1.get(i));
                            s2.setText(syl2.get(i));
                        }
                        else{
                            s1.setText(syl2.get(i));
                            s2.setText(syl1.get(i));
                        }
                        constructionMot="";
                    }
                    i++;
                    checks1=false;
                    checks2=false;
                    s1.setBackgroundColor(Color.rgb(44, 62, 80));
                    s1.setTextColor(Color.rgb(236, 240, 241));
                    s2.setBackgroundColor(Color.rgb(44, 62, 80));
                    s2.setTextColor(Color.rgb(236, 240, 241));
                    s1.setEnabled(true);
                    s2.setEnabled(true);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Erreur, réessaye", Toast.LENGTH_SHORT).show();
                    checks1 = false;
                    checks2 = false;
                    s1.setBackgroundColor(Color.rgb(44, 62, 80));
                    s1.setTextColor(Color.rgb(236, 240, 241));
                    s2.setBackgroundColor(Color.rgb(44, 62, 80));
                    s2.setTextColor(Color.rgb(236, 240, 241));
                    s1.setEnabled(true);
                    s2.setEnabled(true);
                    constructionMot = "";
                }
                if(i==6){
                    title.setText("BRAVO !");
                    s1.setVisibility(View.GONE);
                    s2.setVisibility(View.GONE);
                    suivant.setVisibility(View.GONE);
                    showFin();
                    mot.clear();
                    syl1.clear();
                    syl2.clear();
                    res.clear();
                }
            }
        });
    }

    public void showFin(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getResources().getString(R.string.app_name));
        alertDialog.setMessage("Félicitations, vous avez réussi le niveau Facile !");
        alertDialog.setButton("Fin", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                goToNiveauActivity();
                finish();
            }
        });
        alertDialog.setIcon(R.drawable.ic_launcher);
        alertDialog.show();
    }

    public void goToNiveauActivity(){
        Intent intent = new Intent(this, NiveauActivity.class);
        startActivity(intent);
    }

    public int[] integerList1(int _size) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (int i = 1; i <= _size; i++) {
            intList.add(i);
        }

        int[] randomValues = new int[intList.size()];
        int pos = 0;

        while (intList.size() > 0) {
            pos = (int) (min+(Math.random()*(max-min)));
            randomValues[intList.size() - 1] = intList.get(pos);
            intList.remove(pos);
        }

        return randomValues;
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
