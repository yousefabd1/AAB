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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class KharidSharj2 extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    int pick = 1000;
    NumberPicker np ;
    ImageButton mostaghim,kharid;

    String mobile = "";
    String sharjetype = "شارژ مستقیم";
    String sharjnum = "1";

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
        setContentView(R.layout.activity_kharid_sharj2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // title  and direction//
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        getSupportActionBar().setTitle("خرید شارژ");

        // End title and direction //
            mobile = getIntent().getStringExtra("mobilenumber");
        //imagebutton click

        mostaghim = (ImageButton) findViewById(R.id.sharjmostaghim);
        mostaghim.setBackgroundResource(R.drawable.mchs);
        //mostaghim.setBackgroundColor(Color.rgb(243,112,33));
        kharid = (ImageButton) findViewById(R.id.kharidsharj);

        mostaghim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //kharid.setBackgroundColor(Color.TRANSPARENT);
                mostaghim.setBackgroundResource(R.drawable.mchs);
                kharid.setBackgroundResource(R.drawable.khch);

                // mostaghim.setBackgroundColor(Color.rgb(243,112,33));
                sharjetype = "شارژ مستقیم";
                sharjnum = "1";

            }
        });

        kharid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mostaghim.setBackgroundColor(Color.TRANSPARENT);
                mostaghim.setBackgroundResource(R.drawable.mch);
                kharid.setBackgroundResource(R.drawable.khchs);
                //kharid.setBackgroundColor(Color.rgb(243,112,33));
                sharjetype = "خرید شارژ";
                sharjnum = "2";

            }
        });

        //imagebutton click


        //number picker
        np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setMinValue(1);
        np.setMaxValue(5);
        np.setDisplayedValues(new String[]{"1000 تومان", "2000 تومان", "5000 تومان", "10000 تومان", "20000 تومان"});
        np.setOnValueChangedListener(new numberpicker());

        //End number picker


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
                        Intent intent = new Intent(KharidSharj2.this, MainActivity.class);
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        break;
                    case R.id.message:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(KharidSharj2.this, MessageInbox.class);
                        overridePendingTransition(0, 0);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent2);
                        break;
                    case R.id.trasaction:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent3 = new Intent(KharidSharj2.this, Transactions.class);
                        overridePendingTransition(0, 0);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent3);
                        break;
                    case R.id.myCard:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent4 = new Intent(KharidSharj2.this, MyCard.class);
                        overridePendingTransition(0, 0);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent4);
                        break;
                    case R.id.myScor:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent5 = new Intent(KharidSharj2.this, MyScore.class);
                        overridePendingTransition(0, 0);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent5);
                        break;
                    case R.id.pony:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent6 = new Intent(KharidSharj2.this, Pony.class);
                        overridePendingTransition(0, 0);
                        intent6.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent6);
                        break;
                    case R.id.myAccunt:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent7 = new Intent(KharidSharj2.this, UserAccount.class);
                        overridePendingTransition(0, 0);
                        intent7.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent7);
                        break;
                    case R.id.setting:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent8 = new Intent(KharidSharj2.this, Settings.class);
                        overridePendingTransition(0, 0);
                        intent8.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent8);
                        break;
                    case R.id.aboutUs:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent9 = new Intent(KharidSharj2.this, AboutUs.class);
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


    }
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
            Intent intent = new Intent(KharidSharj2.this, MainActivity.class);
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
            View layout = inflater.inflate(R.layout.kharid_sharj2_pop_up_layout,(ViewGroup) findViewById(R.id.popup_5));
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

    private PopupWindow ghabzpw;
    private void showGhabzPopup() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.field_pop_up_layout,(ViewGroup) findViewById(R.id.popup_2));
            ghabzpw = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, 200, true);
            ghabzpw.showAtLocation(layout, Gravity.CENTER, 0, 0);
            //ghabzViewGroup = (ViewGroup) findViewById(R.id.popup_2);
            // ghabzViewGroup.setOnClickListener(cancel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private class numberpicker implements NumberPicker.OnValueChangeListener {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            // int td = np2.getValue();
            /*switch (newVal){
                case 5:
                    TDY = ((-0.015 * myNewValue) + 2.4);

                    break;
                case 6:
                    TDY = ((-0.015 * myNewValue) + 4.2);
                    break;
                case 7:
                    TDY = ((-0.015 * myNewValue) + 6);
                    break;
                case 8:
                    TDY = ((-0.015 * myNewValue) + 7.8);
                    break;
                case 9:
                    TDY = ((-0.015 * myNewValue) + 9.6);
                    break;
                case 10:
                    TDY = ((-0.015 * myNewValue) + 11.4);
                    break;
            }*/

            switch (np.getValue()) {
                case 1:
                    pick = 1000;
                    break;
                case 2:
                    pick = 2000;
                    break;
                case 3:
                    pick = 5000;
                    break;
                case 4:
                    pick = 10000;
                    break;
                case 5:
                    pick = 20000;
                    break;
                default:
                    pick = 1000;
                    //...
            }

            //CorrectionFactorSHR();
            //Toast.makeText(getApplicationContext(), "n" + pick, Toast.LENGTH_LONG).show();

        }
    }
    public void amadepardakht (View view){
        Intent intent = new Intent(KharidSharj2.this, KharidSharj3.class);

        intent.putExtra("mobilenumber", mobile);
        intent.putExtra("sharjetype", sharjetype);
        intent.putExtra("sharjnum", sharjnum);
        intent.putExtra("pick", String.valueOf(pick));

        startActivity(intent);


    }
}
