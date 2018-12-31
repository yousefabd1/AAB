package com.soshians_co.aab;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.szugyi.circlemenu.view.CircleImageView;
import com.szugyi.circlemenu.view.CircleLayout;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements CircleLayout.OnItemSelectedListener, CircleLayout.OnItemClickListener, CircleLayout.OnRotationFinishedListener, CircleLayout.OnCenterClickListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    //circle menu //
    protected CircleLayout circleLayout;
    protected TextView selectedTextView;
    //end circle menu //

    //popup//
    Button Close;
    //end popup//

    //Font//
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    // End font//

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set listeners circle menu
        circleLayout = (CircleLayout) findViewById(R.id.circle_layout);
        circleLayout.setOnItemSelectedListener(this);
        circleLayout.setOnItemClickListener(this);
        circleLayout.setOnRotationFinishedListener(this);
        circleLayout.setOnCenterClickListener(this);

        selectedTextView = (TextView) findViewById(R.id.selected_textView);

        String name = null;
        View view = circleLayout.getSelectedItem();
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        }
        selectedTextView.setText(name + "، کلیک کنید");

        final String finalName = name;
        selectedTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (finalName.equals("شارژ اینترنت"))
                {
                    Intent intent = new Intent(MainActivity.this, Sharjeinternet.class);
                    startActivity(intent);
                }
                if (finalName.equals("خرید شارژ"))
                {
                    Intent intent = new Intent(MainActivity.this, KharidSharj.class);
                    startActivity(intent);
                }
                if (finalName.equals("پرداخت قبض"))
                {
                    Intent intent2 = new Intent(MainActivity.this, Ghabze.class);
                    startActivity(intent2);
                }
                if (finalName.equals("فروشگاه"))
                {
                    Intent intent = new Intent(MainActivity.this, Store.class);
                    startActivity(intent);

                }
                if (finalName.equals("استخدام"))
                {
                    Intent intent = new Intent(MainActivity.this, Job.class);
                    startActivity(intent);

                }


            }
        });
        //view.setBackgroundColor(Color.TRANSPARENT);

        // end Set listeners circle menu


             // title  and direction//
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        getSupportActionBar().setTitle("باب");

        // End title and direction //

        // menu //

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.home:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.message:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(MainActivity.this, MessageInbox.class);
                        overridePendingTransition(0, 0);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent2);
                        break;
                    case R.id.trasaction:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent3 = new Intent(MainActivity.this, Transactions.class);
                        overridePendingTransition(0, 0);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent3);
                        break;
                    case R.id.myCard:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent4 = new Intent(MainActivity.this, MyCard.class);
                        overridePendingTransition(0, 0);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent4);
                        break;
                    case R.id.myScor:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent5 = new Intent(MainActivity.this, MyScore.class);
                        overridePendingTransition(0, 0);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent5);
                        break;
                    case R.id.pony:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent6 = new Intent(MainActivity.this, Pony.class);
                        overridePendingTransition(0, 0);
                        intent6.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent6);
                        break;
                    case R.id.myAccunt:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent7 = new Intent(MainActivity.this, UserAccount.class);
                        overridePendingTransition(0, 0);
                        intent7.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent7);
                        break;
                    case R.id.setting:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent8 = new Intent(MainActivity.this, Settings.class);
                        overridePendingTransition(0, 0);
                        intent8.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent8);
                        break;
                    case R.id.aboutUs:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent9 = new Intent(MainActivity.this, AboutUs.class);
                        overridePendingTransition(0, 0);
                        intent9.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent9);
                        break;

                }
                return true;
            }
        });

        // End menu //


    }
    //circle menu slected
    @Override
    public void onItemSelected(final View view) {
        final String name;
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
            } else {
            name = null;
        }

        //selectedTextView.setText(name);
        final String finalName = name;

        selectedTextView.setText( name +"، کلیک کنید");

        selectedTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (name.equals("شارژ اینترنت")) {
                    Intent intent = new Intent(MainActivity.this, Sharjeinternet.class);
                    startActivity(intent);
                }
                if (name.equals("خرید شارژ")) {
                    Intent intent = new Intent(MainActivity.this, KharidSharj.class);
                    startActivity(intent);
                }
                if (name.equals("پرداخت قبض")) {
                    Intent intent2 = new Intent(MainActivity.this, Ghabze.class);
                    startActivity(intent2);
                }
                if (name.equals("فروشگاه")) {

                    Intent intent3 = new Intent(MainActivity.this, Store.class);
                    startActivity(intent3);

                }
                if (name.equals("استخدام")) {
                    Intent intent5 = new Intent(MainActivity.this, Job.class);
                    startActivity(intent5);

                }


            }
        });



        switch (view.getId()) {
            case R.id.internet_charge:
                //view.setBackgroundColor(Color.TRANSPARENT);
                // Handle internet_charge selection
                break;
            case R.id.mobile_charge:
                // Handle mobile_charge selection
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(MainActivity.this, KharidSharj.class);
                        //overridePendingTransition(0, 0);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        // finish();
                        //overridePendingTransition(0, 0);
                        startActivity(intent);

                    }
                }, 660);*/
                break;
            case R.id.ghabze:
                // Handle Ghabze selection
              /* new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animation animation = new TranslateAnimation(0, 360, view.getWidth() / 2, view.getHeight() / 2) {
                        };
                        animation.setDuration(500);
                        view.startAnimation(animation);

                        }
                }, 700);*/
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(MainActivity.this, Ghabze.class);
                        //overridePendingTransition(0, 0);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                       // finish();
                        //overridePendingTransition(0, 0);
                        startActivity(intent);

                    }
                }, 660);*/
                break;
            case R.id.store:
                // Handle Ghabze selection
                break;
            case R.id.estekhtam:
                // Handle Ghabze selection
                break;
        }
    }
    //End circle menu slected

    // circle menu clicked
    @Override
    public void onItemClick(View view) {
       /* String name = null;
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        }

        String text = getResources().getString(R.string.start_app, name);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        */

        switch (view.getId()) {
            case R.id.internet_charge:
                // Handle internet_charge click
                Intent intent3 = new Intent(MainActivity.this, Sharjeinternet.class);
                startActivity(intent3);
                break;
            case R.id.mobile_charge:
                // Handle mobile_charge click
                  Intent intent = new Intent(MainActivity.this, KharidSharj.class);
                  startActivity(intent);


                break;
            case R.id.ghabze:
                // Handle Ghabze click
               Intent intent2 = new Intent(MainActivity.this, Ghabze.class);
                startActivity(intent2);
                break;
            case R.id.store:

                Intent intent4 = new Intent(MainActivity.this, Store.class);
                startActivity(intent4);
                // Handle store click
                break;
            case R.id.estekhtam:
                // Handle estekhtam click
                Intent intent5 = new Intent(MainActivity.this, Job.class);
                startActivity(intent5);
                break;
        }
    }
    //End circle menu clicked

    //circle menu animation
    @Override
    public void onRotationFinished(View view) {
        Animation animation = new RotateAnimation(0, 360, view.getWidth() / 2, view.getHeight() / 2);
        animation.setDuration(500);
        view.startAnimation(animation);
        //view.setBackgroundColor(Color.TRANSPARENT);
    }
    // End circle menu animation

    // circle menu center click
    @Override
    public void onCenterClick() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    // End circle menu center click

    //actionBarDrawerToggle
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    //End actionBarDrawerToggle

    //actionbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.help) {
            showPopup();
        }
        if (id == R.id.menu_logo) {
            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //End actionbar menu

    //popup
    private PopupWindow pw;
    private void showPopup() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.pop_up_layout,(ViewGroup) findViewById(R.id.popup_1));
            pw = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
            Close = (Button) layout.findViewById(R.id.close_popup);
            Close.setOnClickListener(cancel_button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };
    //End popup

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

