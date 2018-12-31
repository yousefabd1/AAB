package com.soshians_co.aab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class IntroPage extends AppCompatActivity {
    ImageView imgIntro,imgIntro2;

    DatabaseHandler db = new DatabaseHandler(this);
    ArrayList<BABDbInterface> babItem;

    // Font for all activite //

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    // End Font for all activite //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // hide action bar

        getSupportActionBar().hide();

        // End hide action bar


        //define object
        imgIntro2 = (ImageView) findViewById(R.id.imgIntro2);
        imgIntro = (ImageView) findViewById(R.id.imgIntro);

        //initial object
        Animation bottonTotop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_bottom);
        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        //alingn
        imgIntro2.setAnimation(bottonTotop);
        imgIntro.setAnimation(fadeIn);


    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {

            String count = db.BOBCount();
            String log = "";
            if (count.equals("true")) {
                List<BABDbInterface> bab = db.getAllBOB();
                for (final BABDbInterface cn : bab) {

                    log = cn.getLogin();
                    //eadverItem.add(new EadverDbInterface(cn.getLogin(), cn.getUsername()));

                }

                if(log.equals("1")){
                    startActivity(new Intent(IntroPage.this,MainActivity.class));
                }
                else {
                    startActivity(new Intent(IntroPage.this,LoginPage.class));

                }


            }
            else {

                Intent intent = new Intent(IntroPage.this, RegisterPage.class);
                startActivity(intent);
            }

        }
    }, 5000);



}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro_page, menu);
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        return false;
    }
}
