package com.soshians_co.aab;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyScore extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ImageButton closepopup;
    String log = "";

    DatabaseHandler db = new DatabaseHandler(this);


    TextView txt1;


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
        setContentView(R.layout.activity_my_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// title  and direction//
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        getSupportActionBar().setTitle("امتیازات من");


        txt1 = (TextView) findViewById(R.id.txtscore);

        String count = db.BOBCount();

        if (count.equals("true")) {
            List<BABDbInterface> bab = db.getAllBOB();
            for (final BABDbInterface cn : bab) {

                log = cn.getUsername();
                //eadverItem.add(new EadverDbInterface(cn.getLogin(), cn.getUsername()));

            }
        }
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();


        if (ni != null && ni.isConnected()) {
            String serverURL = "http://eadver.com/bobwebservice/bob96sos.asmx";
            AsyncCallWS task = new AsyncCallWS();
            task.execute(serverURL);
            //Toast.makeText(getApplicationContext(), "لطفا منتظر بمانید", Toast.LENGTH_LONG).show();
        }
        else {
            showNetPopup();

        }

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
                        Intent intent = new Intent(MyScore.this, MainActivity.class);
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        break;
                    case R.id.message:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(MyScore.this, MessageInbox.class);
                        overridePendingTransition(0, 0);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent2);
                        break;
                    case R.id.trasaction:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent3 = new Intent(MyScore.this, Transactions.class);
                        overridePendingTransition(0, 0);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent3);
                        break;
                    case R.id.myCard:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent4 = new Intent(MyScore.this, MyCard.class);
                        overridePendingTransition(0, 0);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent4);
                        break;
                    case R.id.myScor:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.pony:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent6 = new Intent(MyScore.this, Pony.class);
                        overridePendingTransition(0, 0);
                        intent6.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent6);
                        break;
                    case R.id.myAccunt:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent7 = new Intent(MyScore.this, UserAccount.class);
                        overridePendingTransition(0, 0);
                        intent7.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent7);
                        break;
                    case R.id.setting:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent8 = new Intent(MyScore.this, Settings.class);
                        overridePendingTransition(0, 0);
                        intent8.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent8);
                        break;
                    case R.id.aboutUs:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent9 = new Intent(MyScore.this, AboutUs.class);
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

        }
        if (id == R.id.menu_logo) {
            Intent intent = new Intent(MyScore.this, MainActivity.class);
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


    public class AsyncCallWS extends AsyncTask<String, Void, String> {
        private static final String METHOD_NAME = "bob_getPoan";
        private static final String NAMESPACE = "http://tempuri.org/";
        private static final String SOAP_ACTION = "http://tempuri.org/bob_getPoan";
        String comment="y";

        String resp;
        String s = "";
        String name = "";
        String lname1 = "";
        String mobil ="";



        @Override
        protected void onPreExecute() {

            //mail1 = mail.getText().toString();
        }

        @Override
        protected String doInBackground(String... params) {

            String s = "yes";
            String timestamp = "";
            SoapPrimitive response;

            Bundle bundleResult = new Bundle();
            JSONObject JSONObj;
            JSONArray JSONArr;


            PropertyInfo sanat, iddd,dattte, firstname, lastname, mob;

            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.dotNet = true;
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                iddd = new PropertyInfo();
                iddd.setName("username");
                iddd.setValue(log);
                request.addProperty(iddd);

                /*dattte = new PropertyInfo();
                dattte.setName("fulldatetime");
                dattte.setValue(date);
                request.addProperty(dattte);*/






                envelope.setOutputSoapObject(request);
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(params[0]);
                try {
                    transport.call(SOAP_ACTION, envelope);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (envelope.bodyIn != null) {
                    response = (SoapPrimitive) envelope.getResponse();

                    s = response.toString();
                    if (s != null) {
                        if (s.startsWith("{")) {
                            JSONArr = new JSONArray(s);
                            for ( int i = 0; i < JSONArr.length(); i++) {

                                JSONObject c = JSONArr.getJSONObject(i);


                            }
                            //object
                        } else if (s.startsWith("[")) {
                            //JSONObject jsonObj = new JSONObject(s);
                            JSONArr = new JSONArray(s);


                            for ( int i = 0; i < JSONArr.length(); i++) {
                                JSONObject c = JSONArr.getJSONObject(i);

                                comment =c.getString("title");


                            }


                        }
                    }
                    resp = comment;

                }
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;

        }

        @Override
        protected void onPostExecute(String result) {



                if(comment.equals("y")) {

                    Toast.makeText(getApplicationContext(), "سرعت اینترنت شما ضعیف می باشد، مجددا تلاش نمایید", Toast.LENGTH_LONG).show();

                }
                else{
                    txt1.setText(comment);
                }

        }


    }

    private PopupWindow ghabzpw;
    private void showNetPopup() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.net_pop_up_layout,(ViewGroup) findViewById(R.id.popup_10));
            ghabzpw = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, 400, true);
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(MyScore.this, MainActivity.class);
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);

        }
        return false;
    }


}
