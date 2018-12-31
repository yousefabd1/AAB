package com.soshians_co.aab;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterPage extends AppCompatActivity {

    TextView txt1,txt2;
    EditText fname;
    EditText lname;
    EditText mobile;
    EditText textsecur;
    ImageButton closepopup;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        fname = (EditText) findViewById(R.id.name);
        lname = (EditText) findViewById(R.id.Lname);
        mobile = (EditText) findViewById(R.id.mob);
        textsecur = (EditText) findViewById(R.id.secure);
        txt1 = (TextView) findViewById(R.id.txtsec);
        Random rand = new Random();
        int num = rand.nextInt(9000) + 1000;
        String seccode = String.valueOf(num);
        txt1.setText(seccode);
    }

    public void register(View v){

        String editsec = "";
        String textsec="";
        int len=0;
        String name = "";
        String lname1 = "";
        String mobil ="";



        editsec = textsecur.getText().toString();
        textsec = txt1.getText().toString();

        if(editsec.equals(textsec)){
            name = fname.getText().toString();
            lname1 = lname.getText().toString();
            mobil = mobile.getText().toString();

            if ((name.length()!=0) &&(lname1.length()!=0) &&(mobil.length()!=0)){

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
    public void login(View v){
        startActivity(new Intent(RegisterPage.this, LoginPage.class));
    }

    public class AsyncCallWS extends AsyncTask<String, Void, String> {
        private static final String METHOD_NAME = "bob_register";
        private static final String NAMESPACE = "http://tempuri.org/";
        private static final String SOAP_ACTION = "http://tempuri.org/bob_register";
        String comment="1";

        String resp;
        String s = "";
        String name = "";
        String lname1 = "";
        String mobil ="";



        @Override
        protected void onPreExecute() {
            name = fname.getText().toString();
            lname1 = lname.getText().toString();
            mobil = mobile.getText().toString();
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
                mob.setName("username");
                mob.setValue(mobil);
                request.addProperty(mob);



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


            if(comment.equals(mobil)) {
                db.addBOB(new BABDbInterface("0", mobil));
                Toast.makeText(getApplicationContext(), "رمز عبور به شماره همراه شما ارسال خواهد شد", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(new Intent(RegisterPage.this, LoginPage.class));

                    }
                }, 2000);
            }
            if(!comment.equals(mobil))  {
                if(comment.equals("goToLogin")) {
                    db.addBOB(new BABDbInterface("0", mobil));
                    Toast.makeText(RegisterPage.this, "این نام کاربری قبلا ثبت شده است", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(RegisterPage.this, LoginPage.class));

                        }
                    }, 2000);
                }
                else {
                    if (comment.equals("1")) {

                        Toast.makeText(getApplicationContext(), "سرعت اینترنت شما ضعیف می باشد، مجددا تلاش نمایید", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), comment, Toast.LENGTH_LONG).show();
                    }
                }
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

            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);

        }
        return false;
    }

}
