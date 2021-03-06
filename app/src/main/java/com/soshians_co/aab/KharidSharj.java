package com.soshians_co.aab;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class KharidSharj extends AppCompatActivity {
    private static final int SELECT_PHONE_NUMBER =0;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ImageButton closepopup;
    ImageView irancell,haval;
    String mobile = "";
    String typeoperator = "";

    EditText mobilenumber;

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
        setContentView(R.layout.activity_kharid_sharj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // title  and direction//
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        getSupportActionBar().setTitle("خرید شارژ");




        // End title and direction //

        irancell = (ImageView) findViewById(R.id.irancell);
        haval = (ImageView) findViewById(R.id.hamrah);

        mobilenumber = (EditText) findViewById(R.id.mobilenumber);
        mobilenumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                /*if(s.length()!=11 ||s.length()!=13 ){
                    Toast.makeText(KharidSharj.this, "شماره تلفن نامعتبر است", Toast.LENGTH_SHORT).show();

                }*/
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                int flag = 0;
                if(s.length() >= 5) {
                    String pishno = mobilenumber.getText().toString().substring(0, 3);
                    String pishno2 = mobilenumber.getText().toString().substring(0, 5);
                    if (pishno.equals("093") || pishno.equals("090")||pishno2.equals("+9893") || pishno2.equals("+9890")) {

                        haval.setVisibility(View.GONE);
                        irancell.setVisibility(View.VISIBLE);
                        typeoperator = "I";
                        flag =1;

                    }
                    if (pishno.equals("099") || pishno.equals("091")||pishno2.equals("+9899") || pishno2.equals("+9891")) {

                        haval.setVisibility(View.VISIBLE);
                        irancell.setVisibility(View.GONE);
                        typeoperator = "H";
                        flag = 1;

                    }
                    if((s.length()==11) && (flag != 1) && !(pishno.equals("+98"))) {
                        showGhabzPopup();
                        mobile ="";
                        haval.setVisibility(View.GONE);
                        irancell.setVisibility(View.GONE);

                    }
                    if((s.length()==11) && (flag == 1)) {
                        mobile = s.toString();

                    }
                    if(s.length()==13){
                        if (pishno.equals("+98")) {
                            mobile = s.toString().replace("+98", "0");
                            flag = 1;

                        }
                        if (!pishno.equals("+98")) {
                            showGhabzPopup();
                            mobile ="";
                            haval.setVisibility(View.GONE);
                            irancell.setVisibility(View.GONE);

                        }

                    }
                    if(flag == 0){
                        haval.setVisibility(View.GONE);
                        irancell.setVisibility(View.GONE);
                        showGhabzPopup();
                        mobile ="";

                    }
                    if((s.length()==12)) {
                        haval.setVisibility(View.GONE);
                        irancell.setVisibility(View.GONE);
                        showGhabzPopup();
                        mobile ="";

                    }


                }
            }
        });

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
                        Intent intent = new Intent(KharidSharj.this, MainActivity.class);
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        break;
                    case R.id.message:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(KharidSharj.this, MessageInbox.class);
                        overridePendingTransition(0, 0);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent2);
                        break;
                    case R.id.trasaction:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent3 = new Intent(KharidSharj.this, Transactions.class);
                        overridePendingTransition(0, 0);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent3);
                        break;
                    case R.id.myCard:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent4 = new Intent(KharidSharj.this, MyCard.class);
                        overridePendingTransition(0, 0);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent4);
                        break;
                    case R.id.myScor:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent5 = new Intent(KharidSharj.this, MyScore.class);
                        overridePendingTransition(0, 0);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent5);
                        break;
                    case R.id.pony:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent6 = new Intent(KharidSharj.this, Pony.class);
                        overridePendingTransition(0, 0);
                        intent6.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent6);
                        break;
                    case R.id.myAccunt:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent7 = new Intent(KharidSharj.this, UserAccount.class);
                        overridePendingTransition(0, 0);
                        intent7.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent7);
                        break;
                    case R.id.setting:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent8 = new Intent(KharidSharj.this, Settings.class);
                        overridePendingTransition(0, 0);
                        intent8.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent8);
                        break;
                    case R.id.aboutUs:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent9 = new Intent(KharidSharj.this, AboutUs.class);
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
            Intent intent = new Intent(KharidSharj.this, MainActivity.class);
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
            View layout = inflater.inflate(R.layout.help_kharid_sharj_pop_up_layout,(ViewGroup) findViewById(R.id.popup_5));
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
            closepopup = (ImageButton) layout.findViewById(R.id.closepopup);
            closepopup.setOnClickListener(cancel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private View.OnClickListener cancel = new View.OnClickListener() {
        public void onClick(View v) {
            ghabzpw.dismiss();
        }
    };

    // Add contact to edit text
    public void contact(View view){
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(i, SELECT_PHONE_NUMBER);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*if (requestCode == SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            // Get the URI and query the content provider for the phone number
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getApplicationContext().getContentResolver().query(contactUri, projection, null, null, null);

            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                mobilenumber.setText(number);

                // Do something with the phone number

            }

            cursor.close();
        }*/



        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SELECT_PHONE_NUMBER:
                    Cursor cursor = null;
                    try {
                        String phoneNo = null;
                        String name = null;

                        Uri uri = data.getData();
                        cursor = getContentResolver().query(uri, null, null, null, null);
                        cursor.moveToFirst();
                        int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                        phoneNo = cursor.getString(phoneIndex);
                        name = cursor.getString(nameIndex);
                        phoneNo = phoneNo.replace(" ","");
                        mobilenumber.setText(phoneNo);


                        Toast.makeText(KharidSharj.this, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
            Toast.makeText(KharidSharj.this, "خطا! عملیات ناموفق", Toast.LENGTH_SHORT).show();
        }
    }

    // End Add contact to edit text

    public void ghaymat(View view){
        Intent intent = new Intent(KharidSharj.this, KharidSharj2.class);

        //String mobile = mobilenumber.getText().toString();
        if(mobile.length()!=11)
        {
            showGhabzPopup();
        }
        else
        {
            intent.putExtra("mobilenumber", mobile);
            intent.putExtra("typeoperator", typeoperator);
            startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(KharidSharj.this, MainActivity.class);
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }
        return false;
    }



}
