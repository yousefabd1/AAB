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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Ghabze extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    String serverURL;
    String response="";
    private String status = "";
    private String orderHash = "";
    private String paymentInfo = "";
    private String errorMessage = "";
    private String url = "00";
    ProgressBar mProgressBar;


    ImageButton closepopup;

    EditText shenaseGhabz,shenasePardakht;

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
        setContentView(R.layout.activity_ghabze);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // title  and direction//
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        getSupportActionBar().setTitle("پرداخت قبض");

        // End title and direction //
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        shenaseGhabz = (EditText) findViewById(R.id.shenaseghabz);
        shenasePardakht = (EditText) findViewById(R.id.shenasepardakht);


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
                        Intent intent = new Intent(Ghabze.this, MainActivity.class);
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        break;
                    case R.id.message:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(Ghabze.this, MessageInbox.class);
                        overridePendingTransition(0, 0);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent2);
                        break;
                    case R.id.trasaction:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent3 = new Intent(Ghabze.this, Transactions.class);
                        overridePendingTransition(0, 0);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent3);
                        break;
                    case R.id.myCard:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent4 = new Intent(Ghabze.this, MyCard.class);
                        overridePendingTransition(0, 0);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent4);
                        break;
                    case R.id.myScor:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent5 = new Intent(Ghabze.this, MyScore.class);
                        overridePendingTransition(0, 0);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent5);
                        break;
                    case R.id.pony:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent6 = new Intent(Ghabze.this, Pony.class);
                        overridePendingTransition(0, 0);
                        intent6.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent6);
                        break;
                    case R.id.myAccunt:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent7 = new Intent(Ghabze.this, UserAccount.class);
                        overridePendingTransition(0, 0);
                        intent7.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent7);
                        break;
                    case R.id.setting:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent8 = new Intent(Ghabze.this, Settings.class);
                        overridePendingTransition(0, 0);
                        intent8.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent8);
                        break;
                    case R.id.aboutUs:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent9 = new Intent(Ghabze.this, AboutUs.class);
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
            Intent intent = new Intent(Ghabze.this, MainActivity.class);
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
            View layout = inflater.inflate(R.layout.help_ghabz_pop_up_layout,(ViewGroup) findViewById(R.id.popup_3));
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
    /*private View.OnClickListener cancel = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };*/

    public void nextStep(View view){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();


        if (ni != null && ni.isConnected()) {
            serverURL = "https://chr724.ir/services/v3/EasyCharge/bill";
            JSONObject jsonobj = new JSONObject();
            new SendData().execute(jsonobj.toString());
            mProgressBar.setVisibility(View.VISIBLE);
        }
        else {
            showNetPopup();

        }




    }

    ///scanner barcode
    public void barCodekhan(View view){
        /*Object localObject = new Intent("com.google.zxing.client.android.SCAN");
        ((Intent)localObject).putExtra("SCAN_MODE", "CODE_128");
        ((Intent)localObject).putExtra("SCAN_FORMATS", "CODE_128");
        startActivityForResult((Intent)localObject, 1);*/
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(Ghabze.this, MainActivity.class);
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);

        }
        return false;
    }

    public class SendData extends AsyncTask<String, Integer, String> {
        String s = "yes";
        JSONArray JSONArr;
        String shenaseGh = shenaseGhabz.getText().toString();
        String shenasePa = shenasePardakht.getText().toString();
        int f = 0;
        StringBuilder sb = new StringBuilder();
        @Override
        protected void onPreExecute() {

        }



        @Override
        protected String doInBackground(String... params) {

            try {
            URL url1 = new URL(serverURL);
            Map<String,Object> param2 = new LinkedHashMap<>();
            param2.put("billId",shenaseGh);
            param2.put("paymentId", shenasePa);
            param2.put("cellphone", "09360114913");
                param2.put("email", "abdolmalekiyousef@gmail.com");
                param2.put("webserviceId", "59bf62c7-1154-493e-bff2-7a805bef3768");
                param2.put("redirectUrl", "http://www.soshians-co.com");
                param2.put("issuer", "Mellat");
                param2.put("redirectToPage", "");
                param2.put("scriptVersion", "Android");
                param2.put("firstOutputType", "json");
                param2.put("secondOutputType", "view");
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : param2.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url1.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                for (int c; (c = in.read()) >= 0;)
                    sb.append((char)c);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block

            } catch (IOException e) {
                // TODO Auto-generated catch block

            }


            //postData(params[0]);
           /* HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(serverURL);
            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("billId",shenaseGh));
                nameValuePairs.add(new BasicNameValuePair("paymentId", shenasePa));
                nameValuePairs.add(new BasicNameValuePair("cellphone", "09360114913"));
                nameValuePairs.add(new BasicNameValuePair("email", "abdolmalekiyousef@gmail.com"));
                nameValuePairs.add(new BasicNameValuePair("webserviceId", "59bf62c7-1154-493e-bff2-7a805bef3768"));
                nameValuePairs.add(new BasicNameValuePair("redirectUrl", "http://www.soshians-co.com"));
                nameValuePairs.add(new BasicNameValuePair("issuer", "Mellat"));
                nameValuePairs.add(new BasicNameValuePair("redirectToPage", ""));
                nameValuePairs.add(new BasicNameValuePair("scriptVersion", "Android"));
                nameValuePairs.add(new BasicNameValuePair("firstOutputType", "json"));
                nameValuePairs.add(new BasicNameValuePair("secondOutputType", "view" ));


                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // nameValuePairs.add(new BasicNameValuePair("json", params[0]));
//sets a request header so the page receving the request
                //will know what to do with it


                // httpPost.setEntity((HttpEntity) new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse res = httpclient.execute(httpPost);

                InputStream content = res.getEntity().getContent();

                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));

                String s = "";
                while ((s = buffer.readLine()) != null) {
                    response += s;


                }
                //System.out.println("response from server" + response);



            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block

            } catch (IOException e) {
                // TODO Auto-generated catch block

            }*/
            try {
                s = sb.toString();
                //System.out.println("response from server" + s);
                if (s != null) {
                    if (s.startsWith("{")) {
                        //object

                        JSONObject json= (JSONObject) new JSONTokener(s).nextValue();

                        //System.out.println("response from server 2" + json2);
                        //status = (String) json.get("status");

                        status = (String) json.get("status");


                        //paymentInfo = (String) json.get("paymentInfo.url");
                        if(status.equals("Error"))
                        {
                            errorMessage =(String) json.get("errorMessage");
                        }
                        if(status.equals("Success")) {
                            JSONObject json2 = json.getJSONObject("paymentInfo");
                            orderHash = (String) json.get("orderHash");
                            url = (String) json2.get("url");
                            f=4;

                        }


                        //System.out.println("response from server 20" +status);

                    } else if (s.startsWith("[")) {
                        //JSONObject jsonObj = new JSONObject(s);
                        JSONArr = new JSONArray(s);
                        for (int i = 0; i < JSONArr.length(); i++) {
                            JSONObject c = JSONArr.getJSONObject(i);
                            status = c.getString("status");
                            orderHash = c.getString("orderHash");
                            paymentInfo = c.getString("paymentInfo");
                            errorMessage = c.getString("errorMessage");
                            f=5;


                        }


                    }
                }



            } catch (Exception e) {
                e.printStackTrace();
                response = e.getMessage();
                f=6;
            }


            return url ;
        }
        @Override
        protected void onPostExecute(String result) {

            int x = 0;

            if(status.equals("Error"))
            {
                Toast.makeText(Ghabze.this, "" + errorMessage, Toast.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.GONE);
                x=1;
            }
            if(status.equals("Success")){
                mProgressBar.setVisibility(View.GONE);
                Intent intent = new Intent(Ghabze.this, PardakhtGhabz.class);

                    StringBuffer buffer = new StringBuffer(shenaseGh);
                    String ghabztype = buffer.reverse().toString();
                    ghabztype = ghabztype.substring(1, 2);


                    x=1;
                    StringBuffer buffer2 = new StringBuffer(shenasePa);
                    String mablagh = buffer2.reverse().toString();
                    String mablagh2 = mablagh.substring(0, 5);
                    mablagh = mablagh.toString().replace(mablagh2, "");
                    StringBuffer buffer3 = new StringBuffer(mablagh);
                    mablagh = buffer3.reverse().toString() + "000";

                    while(mablagh.substring(0, 1).equals("0"))
                    {
                        mablagh = mablagh.substring(1);
                    }


                    intent.putExtra("mablagh", mablagh);
                    intent.putExtra("ghabztype", ghabztype);
                    intent.putExtra("shenaseGhabz", shenaseGh);
                    intent.putExtra("shenasePardakht", shenasePa);
                    startActivity(intent);

            }
            if(x==0) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(Ghabze.this, "خطای در اتصال به اینترنت!", Toast.LENGTH_LONG).show();
            }




        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            String shgh = scanContent.substring(0, 13);
            String shpa = scanContent.replace(shgh, "");
            shenaseGhabz.setText( shgh);
            shenasePardakht.setText(shpa);
        }
        else{

            /*Toast toast = Toast.makeText(getApplicationContext(), "اطلاعاتی دریافت نشد", Toast.LENGTH_SHORT);
            toast.show();*/
        }
    }


}
