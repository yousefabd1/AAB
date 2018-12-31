package com.soshians_co.aab;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.util.List;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginPage extends AppCompatActivity {
    TextView txt1,txt20;
    EditText editText1,editText2,editText3,editText4;
    CheckBox chkIos;
    Button btn,btn2,btn3,btn4;
    String log = "";
    LinearLayout l1;
    DatabaseHandler db = new DatabaseHandler(this);
    ImageButton closepopup;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        btn = (Button) findViewById(R.id.forget);
        l1 = (LinearLayout) findViewById(R.id.l1);
        txt1 = (TextView) findViewById(R.id.txtsec);


        Random rand = new Random();
        int num = rand.nextInt(9000) + 1000;
        String seccode = String.valueOf(num);
        txt1.setText(seccode);
        editText3 = (EditText) findViewById(R.id.mobforget);
        editText4 = (EditText) findViewById(R.id.mob);
        editText1= (EditText) findViewById(R.id.secure);
        editText2 = (EditText) findViewById(R.id.password);
        chkIos = (CheckBox) findViewById(R.id.ch2);
        String count = db.BOBCount();

        if (count.equals("true")) {
            List<BABDbInterface> bab = db.getAllBOB();
            for (final BABDbInterface cn : bab) {

                log = cn.getUsername();
                //eadverItem.add(new EadverDbInterface(cn.getLogin(), cn.getUsername()));

            }
        }
        editText4.setText(log);
        editText3.setText(log);

        addListenerOnChkIos();


    }

    public void addListenerOnChkIos() {

        chkIos = (CheckBox) findViewById(R.id.ch2);


        chkIos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    l1.setVisibility(View.VISIBLE);
                    editText3.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);



                } else {
                    l1.setVisibility(View.GONE);
                    editText3.setVisibility(View.GONE);
                    btn.setVisibility(View.GONE);

                }

            }
        });

    }

    public void login(View v){
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




        //startActivity(new Intent(Main1Activity.this, Main2Activity.class));


    }

    public void request(View v){

        String editsec = "";
        String textsec="";
        editsec = editText1.getText().toString();
        textsec = txt1.getText().toString();


        if(editsec.equals(textsec)){
            if(log.equals(editText3.getText().toString())) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = cm.getActiveNetworkInfo();


                if (ni != null && ni.isConnected()) {
                    String serverURL = "http://eadver.com/bobwebservice/bob96sos.asmx";
                    FAsyncCallWS task = new FAsyncCallWS();
                    task.execute(serverURL);
                    Toast.makeText(getApplicationContext(), "لطفا منتظر بمانید", Toast.LENGTH_LONG).show();
                }
                else {
                    showNetPopup();

                }



            }
            else{
                Toast.makeText(getApplicationContext(), "شماره همراه شما با شماره همراه وارد شده در زمان ثبت نام مغایرت دارد", Toast.LENGTH_LONG).show();

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


    public class AsyncCallWS extends AsyncTask<String, Void, String> {
        private static final String METHOD_NAME = "bob_login";
        private static final String NAMESPACE = "http://tempuri.org/";
        private static final String SOAP_ACTION = "http://tempuri.org/bob_login";
        String comment="1";

        String resp;
        String s = "";
        String user = "";
        String pass1 = "";
        String mobil ="";
        String mail1 = "";


        @Override
        protected void onPreExecute() {
            user = editText4.getText().toString();
            pass1 = editText2.getText().toString();

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
                iddd.setValue(user);
                request.addProperty(iddd);

                /*dattte = new PropertyInfo();
                dattte.setName("fulldatetime");
                dattte.setValue(date);
                request.addProperty(dattte);*/


                firstname = new PropertyInfo();
                firstname.setName("pwd");
                firstname.setValue(pass1);
                request.addProperty(firstname);

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

            String count = db.BOBCount();
            String log = "";
            if(comment.equals(user)){

                    List<BABDbInterface> bob = db.getAllBOB();
                    for (final BABDbInterface cn : bob) {

                        log = cn.getUsername();
                        //eadverItem.add(new EadverDbInterface(cn.getLogin(), cn.getUsername()));

                    }
                if(count.equals("true")) {

                    db.updateBOB(new BABDbInterface("1", user));

                }
                else{
                    db.addBOB(new BABDbInterface("1", user));
                }
                startActivity(new Intent(LoginPage.this, MainActivity.class));








            }
            else{
                if(comment.equals("1")) {

                    Toast.makeText(getApplicationContext(), "سرعت اینترنت شما ضعیف می باشد، مجددا تلاش نمایید", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(LoginPage.this, comment, Toast.LENGTH_LONG).show();

                }


            }







        }


    }

    public class FAsyncCallWS extends AsyncTask<String, Void, String> {
        private static final String METHOD_NAME = "bob_resetPwd";
        private static final String NAMESPACE = "http://tempuri.org/";
        private static final String SOAP_ACTION = "http://tempuri.org/bob_resetPwd";
        String comment="1";

        String resp;
        String s = "";
        String user = "";
        String pass1 = "";
        String mobil ="";
        String mail1 = "";


        @Override
        protected void onPreExecute() {
            user = editText3.getText().toString();


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
                iddd.setValue(user);
                request.addProperty(iddd);


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

            if(comment.equals("1"))
            {
                Toast.makeText(getApplicationContext(), "سرعت اینترنت شما ضعیف می باشد، مجددا تلاش نمایید", Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(LoginPage.this, comment, Toast.LENGTH_LONG).show();
            }
        }


    }



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
