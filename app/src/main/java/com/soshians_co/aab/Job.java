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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Job extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    String idspin1,idspin2,idspin3,idspin4;
    String spin1,spin2,spin3,spin4;
    String id1 = "0";
    Spinner spinner1,spinner2, spinner3,spinner4;
    List<String> list1;
    ArrayAdapter<String> dataAdapter;
    List<String> list2;
    ArrayAdapter<String> dataAdapter2;
    List<String> list3;
    ArrayAdapter<String> dataAdapter3;
    List<String> list4;
    ArrayAdapter<String> dataAdapter4;

    TextView txt1,txt2;

    EditText fname;
    EditText lname;
    EditText mobile;
    EditText birthday;
    EditText field;
    EditText textsecur;


    ImageButton closepopup;

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
        setContentView(R.layout.activity_job);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // title  and direction//
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        getSupportActionBar().setTitle("استخدام");

        // End title and direction //

        fname = (EditText) findViewById(R.id.name);
        lname = (EditText) findViewById(R.id.lname);
        mobile = (EditText) findViewById(R.id.mob);
        birthday = (EditText) findViewById(R.id.birthday);
        field = (EditText) findViewById(R.id.field);
        textsecur = (EditText) findViewById(R.id.secure);
        txt1 = (TextView) findViewById(R.id.txtsec);
        Random rand = new Random();
        int num = rand.nextInt(9000) + 1000;
        String seccode = String.valueOf(num);
        txt1.setText(seccode);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);




        list1 = new ArrayList<String>();
        list2 = new ArrayList<String>();
        list3 = new ArrayList<String>();
        list4 = new ArrayList<String>();

        idspin1 = "0";
        idspin2 = "0";
        idspin3 = "0";
        idspin4 = "0";


        list1.add("جنسیت");
        list1.add("زن");
        list1.add("مرد");

        dataAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout, list1);
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner1.setAdapter(dataAdapter);

        list2.add("محل تولد");
        list2.add("آذربایجان شرقی");
        list2.add("آذربایجان غربی");
        list2.add("اردبیل");
        list2.add("اصفهان");
        list2.add("البرز");
        list2.add("ایلام");
        list2.add("بوشهر");
        list2.add("تهران");
        list2.add("چهارمحال و بختیاری");
        list2.add("خراسان جنوبی");
        list2.add("خراسان رضوی");
        list2.add("خراسان شمالی");
        list2.add("خوزستان");
        list2.add("زنجان");
        list2.add("سمنان");
        list2.add("سیستان و بلوچستان");
        list2.add("فارس");
        list2.add("قزوین");
        list2.add("قم");
        list2.add("کردستان");
        list2.add("کرمان");
        list2.add("کرمانشاه");
        list2.add("کهگیلویه و بویراحمد");
        list2.add("گلستان");
        list2.add("گیلان");
        list2.add("لرستان");
        list2.add("مازندران");
        list2.add("مرکزی");
        list2.add("هرمزگان");
        list2.add("همدان");
        list2.add("یزد");
        dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout, list2);
        dataAdapter2.setDropDownViewResource(R.layout.spinner_layout);
        spinner2.setAdapter(dataAdapter2);

        list3.add("میزان تحصیلات");
        list3.add("زیر دیپلم");
        list3.add("دیپلم");
        list3.add("لیسانس");
        list3.add("فوق لیسانس");
        list3.add("دکتری");
        dataAdapter3 = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout, list3);
        dataAdapter3.setDropDownViewResource(R.layout.spinner_layout);
        spinner3.setAdapter(dataAdapter3);


        list4.add("نوع همکاری");
        list4.add("بازاریاب");
        list4.add("تعمیرکار");
        list4.add("فروشنده");

        dataAdapter4 = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout, list4);
        dataAdapter4.setDropDownViewResource(R.layout.spinner_layout);
        spinner4.setAdapter(dataAdapter4);

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
                        Intent intent = new Intent(Job.this, MainActivity.class);
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        break;
                    case R.id.message:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(Job.this, MessageInbox.class);
                        overridePendingTransition(0, 0);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent2);
                        break;
                    case R.id.trasaction:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent3 = new Intent(Job.this, Transactions.class);
                        overridePendingTransition(0, 0);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent3);
                        break;
                    case R.id.myCard:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent4 = new Intent(Job.this, MyCard.class);
                        overridePendingTransition(0, 0);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent4);
                        break;
                    case R.id.myScor:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent6 = new Intent(Job.this, MyScore.class);
                        overridePendingTransition(0, 0);
                        intent6.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent6);
                        break;
                    case R.id.pony:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent7 = new Intent(Job.this, Pony.class);
                        overridePendingTransition(0, 0);
                        intent7.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent7);
                        break;
                    case R.id.myAccunt:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent8 = new Intent(Job.this, UserAccount.class);
                        overridePendingTransition(0, 0);
                        intent8.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent8);
                        break;
                    case R.id.setting:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent9 = new Intent(Job.this, Settings.class);
                        overridePendingTransition(0, 0);
                        intent9.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent9);

                        break;
                    case R.id.aboutUs:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                }
                return true;
            }
        });

        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener1());
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener2());
        spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListener3());
        spinner4.setOnItemSelectedListener(new CustomOnItemSelectedListener4());


    }



    public class CustomOnItemSelectedListener1 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            //spin2sel= parent.getItemAtPosition(pos).toString();
            int pos2 =0;
            spin1 = parent.getItemAtPosition(pos).toString();
            if(!(pos ==0)){
                idspin1 = "1";
                pos2 = pos-1;

                id1 = String.valueOf(pos2);
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }



    public class CustomOnItemSelectedListener2 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            spin2 = parent.getItemAtPosition(pos).toString();
            if(!(pos ==0)){
                idspin2 = "1";
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

    public class CustomOnItemSelectedListener3 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            spin3 = parent.getItemAtPosition(pos).toString();
            if(!(pos ==0)){
                idspin3 = "1";
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

    public class CustomOnItemSelectedListener4 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            spin4 = parent.getItemAtPosition(pos).toString();
            if(!(pos ==0)){
                idspin4 = "1";
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }


    public void request (View v){


        String editsec = "";
        String textsec="";
        int len=0;
        String name2 = "";
        String lname2 = "";
        String mobi2 ="";
        String birth2 ="";
        String field2 ="";



        editsec = textsecur.getText().toString();
        textsec = txt1.getText().toString();

        if(editsec.equals(textsec)){
            name2 = fname.getText().toString();
            lname2 = lname.getText().toString();
            mobi2 = mobile.getText().toString();
            birth2 = birthday.getText().toString();
            field2 = field.getText().toString();

            if ((name2.length()!=0) &&(lname2.length()!=0) &&(mobi2.length()!=0)&&(birth2.length()!=0)&&(field2.length()!=0)&& (idspin1.equals("1"))&& (idspin2.equals("1"))&& (idspin3.equals("1"))&& (idspin4.equals("1"))){

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = cm.getActiveNetworkInfo();


                if ((ni != null && ni.isConnected())) {
                    String serverURL = "http://eadver.com/bobwebservice/bob96sos.asmx";
                    AsyncCallWS task = new AsyncCallWS();
                    task.execute(serverURL);
                    Toast.makeText(getApplicationContext(), "لطفا منتظر بمانید", Toast.LENGTH_LONG).show();
                }
                else {
                    showNetPopup();

                }




            }
            else{
                Toast.makeText(getApplicationContext(), "لطفا تمام موارد را تکمیل نمایید", Toast.LENGTH_LONG).show();

            }

        }
        else {
            Toast.makeText(getApplicationContext(), "کد امنیتی را اشتباه وارد کرده اید", Toast.LENGTH_LONG).show();
            Random rand = new Random();
            int num = rand.nextInt(9000) + 1000;
            String seccode = String.valueOf(num);
            txt1.setText(seccode);
        }

    }
    //actionBarDrawerToggle


    public class AsyncCallWS extends AsyncTask<String, Void, String> {
        private static final String METHOD_NAME = "bob_careerReq";
        private static final String NAMESPACE = "http://tempuri.org/";
        private static final String SOAP_ACTION = "http://tempuri.org/bob_careerReq";
        String comment="1";

        String resp;
        String s = "";
        String name = "";
        String lname1 = "";
        String mobil ="";
        String birth2 ="";
        String field1 ="";



        @Override
        protected void onPreExecute() {
            name = fname.getText().toString();
            lname1 = lname.getText().toString();
            mobil = mobile.getText().toString();
            birth2 = birthday.getText().toString();
            field1 = field.getText().toString();
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


            PropertyInfo sex, iddd,birth, firstname, place, mob, degree, field,req;

            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.dotNet = true;
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                iddd = new PropertyInfo();
                iddd.setName("fname");
                iddd.setValue(name);
                request.addProperty(iddd);

                /*dattte = new PropertyInfo();
                dattte.setName("fulldatetime");
                dattte.setValue(date);
                request.addProperty(dattte);*/


                firstname = new PropertyInfo();
                firstname.setName("lname");
                firstname.setValue(lname1);
                request.addProperty(firstname);

                mob = new PropertyInfo();
                mob.setName("mob");
                mob.setValue(mobil);
                request.addProperty(mob);

                sex = new PropertyInfo();
                sex.setName("sex");
                sex.setValue(id1);
                request.addProperty(sex);

                birth = new PropertyInfo();
                birth.setName("bdate");
                birth.setValue(birth2);
                request.addProperty(birth);

                place = new PropertyInfo();
                place.setName("place");
                place.setValue(spin2);
                request.addProperty(place);

                degree = new PropertyInfo();
                degree.setName("degree");
                degree.setValue(spin3);
                request.addProperty(degree);

                field = new PropertyInfo();
                field.setName("field");
                field.setValue(field1);
                request.addProperty(field);

                req = new PropertyInfo();
                req.setName("req");
                req.setValue(spin4);
                request.addProperty(req);



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

                                comment =c.getString("comment");


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



                    if (comment.equals("1")) {

                        Toast.makeText(getApplicationContext(), "سرعت اینترنت شما ضعیف می باشد، مجددا تلاش نمایید", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), comment, Toast.LENGTH_LONG).show();
                    }


        }


    }
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
            Intent intent = new Intent(Job.this, MainActivity.class);
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(Job.this, MainActivity.class);
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);

        }
        return false;
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




}
