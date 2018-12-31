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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
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
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Sharjeinternet2 extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ImageButton closepopup;
    LinearLayout adslapper, basteapper;
    NumberPicker np ;
    ProgressBar mProgressBar;

    String ServiceName3,ProfileName3,ServicePrice3,ServiceID3;

    String uniqkey = "";


    public ListView listView;
    ArrayList<PackageItem> Items;
    public PackageAdapter listAdapter;

    int opId = 1;


    String serverURL;
    String response="";

    ImageView irancell,haval;
    String mobile = "" , operatortype = "";

    EditText shenaseadsl, mobilenumber;

    //popup//
    Button Close, baresi, next;
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
        setContentView(R.layout.activity_sharjeinternet2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        getSupportActionBar().setTitle("شارژ اینترنت همراه");


        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();




        mobile = getIntent().getStringExtra("mobilenumber");
        operatortype = getIntent().getStringExtra("typeoperator");

        if(operatortype.equals("H")){
            opId =2;
            Toast.makeText(Sharjeinternet2.this, "در حال حاضر فقط بسته های ایرانسل قابل ارائه می باشد!", Toast.LENGTH_LONG).show();
        }
        if(operatortype.equals("I")){
            opId =1;
        }

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        Items = new ArrayList<PackageItem>();

        listView = (ListView) findViewById(R.id.listmm);
        listAdapter = new PackageAdapter(this, R.layout.package_item,Items);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                PackageItem item = (PackageItem) parent.getItemAtPosition(position);


                ServiceName3= item.getServiceName();
                ProfileName3 = item.getProfileName();
                ServicePrice3 =item.getServicePrice();
                ServiceID3 = item.getServiceID();
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = cm.getActiveNetworkInfo();

                double d;
                uniqkey = "";
                for (int i = 1; i <= 16; i++) {
                    d = Math.random() * 10;
                    uniqkey = uniqkey + ((int)d);
                    if (i % 4 == 0 && i != 16) {
                        uniqkey = uniqkey + "-";
                    }
                }
                uniqkey = uniqkey +"-" + ServicePrice3 + "-"+ ServiceID3;
                //System.out.println("response from server:  " + uniqkey);






            }
        });





        if (ni != null && ni.isConnected()) {

            if( opId==1) {
                serverURL = "http://ws.toshanet.ir";
                new SendData().execute(serverURL);
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
        else {
            showNetPopup();

        }


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
                        Intent intent = new Intent(Sharjeinternet2.this, MainActivity.class);
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        break;
                    case R.id.message:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(Sharjeinternet2.this, MessageInbox.class);
                        overridePendingTransition(0, 0);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent2);
                        break;
                    case R.id.trasaction:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent3 = new Intent(Sharjeinternet2.this, Transactions.class);
                        overridePendingTransition(0, 0);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent3);
                        break;
                    case R.id.myCard:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent4 = new Intent(Sharjeinternet2.this, MyCard.class);
                        overridePendingTransition(0, 0);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent4);
                        break;
                    case R.id.myScor:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent5 = new Intent(Sharjeinternet2.this, MyScore.class);
                        overridePendingTransition(0, 0);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent5);
                        break;
                    case R.id.pony:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent6 = new Intent(Sharjeinternet2.this, Pony.class);
                        overridePendingTransition(0, 0);
                        intent6.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent6);
                        break;
                    case R.id.myAccunt:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent7 = new Intent(Sharjeinternet2.this, UserAccount.class);
                        overridePendingTransition(0, 0);
                        intent7.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent7);
                        break;
                    case R.id.setting:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent8 = new Intent(Sharjeinternet2.this, Settings.class);
                        overridePendingTransition(0, 0);
                        intent8.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent8);
                        break;
                    case R.id.aboutUs:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent9 = new Intent(Sharjeinternet2.this, AboutUs.class);
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


    public class SendData extends AsyncTask<String, Void, String> {
        private static final String METHOD_NAME = "GetInternetPackages";
        private static final String NAMESPACE = "http://www.toranjsoft.com/";
        private static final String SOAP_ACTION = "http://www.toranjsoft.com/GetInternetPackages";
        private String [] ServiceID = new String[10000] ;
        private String [] ProviderTitle = new String[10000] ;
        private String [] ServiceName = new String[10000] ;
        private String [] ServicePrice = new String[10000] ;
        private String [] ProfileName = new String[10000];


        int count = 0;
        String resp;


        @Override
        protected void onPreExecute() {




        }

        @Override
        protected String doInBackground(String... params) {
            String s = "yes";

            SoapPrimitive response;
            PropertyInfo OperatorID;
            Bundle bundleResult = new Bundle();
            JSONObject JSONObj;
            JSONArray JSONArr;
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                OperatorID = new PropertyInfo();
                OperatorID.setName("OperatorID");
                OperatorID.setValue(1);
                request.addProperty(OperatorID);


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
                   // System.out.println("response from server" + s);

                    if (s != null) {
                        if (s.startsWith("{")) {
                            //object
                        } else if (s.startsWith("[")) {
                            //JSONObject jsonObj = new JSONObject(s);
                            JSONArr = new JSONArray(s);
                            for (int i = 0; i < JSONArr.length(); i++) {
                                JSONObject c = JSONArr.getJSONObject(i);
                                ServiceID [i] = c.getString("ServiceID");
                                ProviderTitle[i] = c.getString("ProviderTitle");
                                ServiceName[i] = c.getString("ServiceName");
                                ServicePrice[i] = c.getString("ServicePrice");
                                ProfileName[i] = c.getString("ProfileName");
                                count++;




                            }

                        }
                    }



                    resp = "";

                }
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;

        }

        @Override
        protected void onPostExecute(String result) {

            int j = 0;
            int i = count;
            for (;j<1;) {
                j++;
                listAdapter.clear();
                Items.clear();
                listAdapter.setGridData(Items);

                for (i = 0; i < count; i++) {
                    final String ServiceName2 = ServiceName[i];
                    final String ProfileName2 = ProfileName[i];
                    final String ServicePrice2 = ServicePrice[i];
                    final String ServiceID2 = ServiceID[i];


                    Items.add(new PackageItem(ServiceName2,ServicePrice2,ProfileName2,ServiceID2));

                }
                listAdapter.setGridData(Items);

            mProgressBar.setVisibility(View.GONE);


        }



        }

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
            Intent intent = new Intent(Sharjeinternet2.this, MainActivity.class);
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
            View layout = inflater.inflate(R.layout.help_sharj_internet2_pop_up_layout,(ViewGroup) findViewById(R.id.popup_6));
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
    private void showNetPopup() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.net_pop_up_layout,(ViewGroup) findViewById(R.id.popup_2));
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
    /*private View.OnClickListener cancel = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };*/


}
