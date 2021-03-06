package com.soshians_co.aab;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class KharidSharj4 extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    String response="";
    ImageButton closepopup;

    ProgressBar mProgressBar;




    String pick = "1000";
    String mobile = "";
    String sharjetype = "شارژ مستقیم";
    String sharjnum = "1";
    String productId="";





    String serverURL;
    String pishno = "";
    String operatortype="MTN";
    private String status = "";
    private String orderHash = "";
    private String paymentInfo = "";
    private String errorMessage = "";
    private String url = "00";
    private String status2 = "";
    private String refId = "";
    private String date = "";
    private String productstype = "";
    private String productsname = "";
    private String productsprice = "";
    private String productscount = "";
    private String productsdetailscellphone = "";



    //popup//
    Button Close;
    //end popup//

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    // End font//

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kharid_sharj4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // title  and direction//
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        getSupportActionBar().setTitle("پرداخت");

        // End title and direction //
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mobile = getIntent().getStringExtra("mobilenumber");
        sharjetype = getIntent().getStringExtra("sharjetype");
        sharjnum = getIntent().getStringExtra("sharjnum");
        pick = getIntent().getStringExtra("pick");
        String pishno = mobile.substring(0,3);
        if (pishno.equals("093") || pishno.equals("090")){
            operatortype = "MTN";

            if(pick.equals("1000")){
                productId = "CC-MTN-1000";
            }
            if(pick.equals("2000")){
                productId = "CC-MTN-2000";
            }
            if(pick.equals("5000")){
                productId = "CC-MTN-5000";
            }
            if(pick.equals("10000")){
                productId = "CC-MTN-10000";
            }
            if(pick.equals("20000")){
                productId = "CC-MTN-20000";
            }


        }
        if (pishno.equals("099") || pishno.equals("091")){

            operatortype = "MCI";
            if(pick.equals("1000")){
                productId = "CC-MCI-1000";
            }
            if(pick.equals("2000")){
                productId = "CC-MCI-2000";
            }
            if(pick.equals("5000")){
                productId = "CC-MCI-5000";
            }
            if(pick.equals("10000")){
                productId = "CC-MCI-10000";
            }
            if(pick.equals("20000")){
                productId = "CC-MCI-20000";
            }

        }
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected()) {

        if( sharjnum.equals("1")) {




                serverURL = "https://chr724.ir/services/v3/EasyCharge/topup";
                JSONObject jsonobj = new JSONObject();
                new SendData().execute(jsonobj.toString());
                mProgressBar.setVisibility(View.VISIBLE);

        }
        if(sharjnum.equals("2"))
        {

                serverURL = "https://chr724.ir/services/v3/EasyCharge/BuyProduct";
                JSONObject jsonobj = new JSONObject();
                new SendData2().execute(jsonobj.toString());
                mProgressBar.setVisibility(View.VISIBLE);

        }
        }
        else {
            showNetPopup();
        }


        /*try {

            jsonobj.put("type","MTN");
            jsonobj.put("amount", "1000");
            jsonobj.put("cellphone", "09360114913");
            jsonobj.put("email", "abdolmalekiyousef@gmail.com");
            jsonobj.put("webserviceId", "59bf62c7-1154-493e-bff2-7a805bef3768");
            jsonobj.put("redirectUrl", "http://www.soshians-co.com");
            jsonobj.put("issuer", "Mellat");
            jsonobj.put("redirectToPage", "True");
            jsonobj.put("scriptVersion", "Android");
            jsonobj.put("firstOutputType", "Json");
            jsonobj.put("secondOutputType", "json");

        } catch (JSONException e) {
            e.printStackTrace();
        }*/



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
                        Intent intent = new Intent(KharidSharj4.this, MainActivity.class);
                        overridePendingTransition(0, 0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        break;
                    case R.id.message:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(KharidSharj4.this, MessageInbox.class);
                        overridePendingTransition(0, 0);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent2);
                        break;
                    case R.id.trasaction:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent3 = new Intent(KharidSharj4.this, Transactions.class);
                        overridePendingTransition(0, 0);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent3);
                        break;
                    case R.id.myCard:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent4 = new Intent(KharidSharj4.this, MyCard.class);
                        overridePendingTransition(0, 0);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent4);
                        break;
                    case R.id.myScor:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent5 = new Intent(KharidSharj4.this, MyScore.class);
                        overridePendingTransition(0, 0);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent5);
                        break;
                    case R.id.pony:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent6 = new Intent(KharidSharj4.this, Pony.class);
                        overridePendingTransition(0, 0);
                        intent6.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent6);
                        break;
                    case R.id.myAccunt:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent7 = new Intent(KharidSharj4.this, UserAccount.class);
                        overridePendingTransition(0, 0);
                        intent7.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent7);
                        break;
                    case R.id.setting:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent8 = new Intent(KharidSharj4.this, Settings.class);
                        overridePendingTransition(0, 0);
                        intent8.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent8);
                        break;
                    case R.id.aboutUs:
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent intent9 = new Intent(KharidSharj4.this, AboutUs.class);
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





    ///sharje mostaghim
    public class SendData extends AsyncTask<String, Integer, String> {
        String s = "yes";
        JSONArray JSONArr;
        StringBuilder sb = new StringBuilder();

        @Override
        protected void onPreExecute() {

        }



        @Override
        protected String doInBackground(String... params) {


            try {
                URL url1 = new URL(serverURL);
                Map<String,Object> param2 = new LinkedHashMap<>();
                param2.put("type",operatortype);
                param2.put("amount", pick);
                param2.put("cellphone", mobile);
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
            /*HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(serverURL);
            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("type",operatortype));
                nameValuePairs.add(new BasicNameValuePair("amount", pick));
                nameValuePairs.add(new BasicNameValuePair("cellphone", mobile));
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
                if (s != null) {
                    if (s.startsWith("{")) {
                        //object

                        JSONObject json= (JSONObject) new JSONTokener(s).nextValue();
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


                        }


                    }
                }



            } catch (Exception e) {
                e.printStackTrace();
                response = e.getMessage();
            }


            return url ;
        }
        @Override
        protected void onPostExecute(String result) {

            int x=0;
            if(status.equals("Error"))
            {
                mProgressBar.setVisibility(View.GONE);
                x=1;
                Toast.makeText(KharidSharj4.this, "" + errorMessage, Toast.LENGTH_LONG).show();
            }
            if(status.equals("Success")) {
                mProgressBar.setVisibility(View.GONE);
                x=1;
                final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(result));
                startActivity(intent);
            }
            if(x==0){
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(KharidSharj4.this, "خطای در اتصال به اینترنت!", Toast.LENGTH_LONG).show();
            }
            /*WebView browser = (WebView) findViewById(R.id.webview);
            browser.getSettings().setJavaScriptEnabled(true);
            browser.loadUrl( result);
            ///faster load
            browser.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            browser.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);*/



        }
    }


    /////kharid sharj
    public class SendData2 extends AsyncTask<String, Integer, String> {
        String s = "yes";
        JSONArray JSONArr;
        StringBuilder sb = new StringBuilder();

        @Override
        protected void onPreExecute() {

        }



        @Override
        protected String doInBackground(String... params) {

            try {
                URL url1 = new URL(serverURL);
                Map<String,Object> param2 = new LinkedHashMap<>();
                param2.put("productId",productId);
                param2.put("cellphone", mobile);
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
                nameValuePairs.add(new BasicNameValuePair("productId",productId));
                nameValuePairs.add(new BasicNameValuePair("cellphone", mobile));
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
                if (s != null) {
                    if (s.startsWith("{")) {
                        //object

                        JSONObject json= (JSONObject) new JSONTokener(s).nextValue();
                        status = (String) json.get("status");


                        //paymentInfo = (String) json.get("paymentInfo.url");
                        if(status.equals("Error"))
                        {
                            errorMessage =(String) json.get("errorMessage");
                        }
                        if(status.equals("Success")){
                            JSONObject json2 = json.getJSONObject("paymentInfo");
                            orderHash = (String) json.get("orderHash");
                            url = (String) json2.get("url");

                        }
                    } else if (s.startsWith("[")) {
                        //JSONObject jsonObj = new JSONObject(s);
                        JSONArr = new JSONArray(s);
                        for (int i = 0; i < JSONArr.length(); i++) {
                            JSONObject c = JSONArr.getJSONObject(i);
                            status = c.getString("status");
                            orderHash = c.getString("orderHash");
                            paymentInfo = c.getString("paymentInfo");
                            errorMessage = c.getString("errorMessage");


                        }


                    }
                }



            } catch (Exception e) {
                e.printStackTrace();
                response = e.getMessage();
            }


            return url ;
        }
        @Override
        protected void onPostExecute(String result) {

            int x=0;
            if(status.equals("Error"))
            {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(KharidSharj4.this, "" + errorMessage, Toast.LENGTH_LONG).show();
                x=1;
            }
            if(status.equals("Success")) {
                mProgressBar.setVisibility(View.GONE);
                x=1;
                final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(result));
                startActivity(intent);

            }
            if(x==0){
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(KharidSharj4.this, "خطای در اتصال به اینترنت!", Toast.LENGTH_LONG).show();
            }

            /*WebView browser = (WebView) findViewById(R.id.webview);
            browser.getSettings().setJavaScriptEnabled(true);
            browser.loadUrl( result);
            ///faster load
            browser.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            browser.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);*/



        }
    }




   /* public class AsyncCallWS extends AsyncTask<String, Void, String> {
        private static final String METHOD_NAME = "topup";
        private static final String NAMESPACE = "http://tempuri.org/";
        private static final String SOAP_ACTION = "http://tempuri.org/topup";
        //////////////////////
        private SoundPool sounds;
        private int sExplosion;
        String x = "";
        /////////////////////
        String resp;


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            String s = "yes";
            SoapPrimitive response;
            JSONArray JSONArr;
            PropertyInfo type, amount, cellphone, email,webserviceId, redirectUrl,issuer,redirectToPage, scriptVersion,firstOutputType,secondOutputType ;
             try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                 type = new PropertyInfo();
                 type.setName("type");
                 type.setValue("MTN");
                 request.addProperty(type);

                 amount = new PropertyInfo();
                 amount.setName("amount");
                 amount.setValue("1000");
                 request.addProperty(amount);

                 cellphone = new PropertyInfo();
                 cellphone.setName("cellphone");
                 cellphone.setValue("09360114913");
                 request.addProperty(cellphone);

                 email = new PropertyInfo();
                 email.setName("email");
                 email.setValue("abdolmalekiyousef@gmail.com");
                 request.addProperty(email);

                 webserviceId = new PropertyInfo();
                 webserviceId.setName("webserviceId");
                 webserviceId.setValue("59bf62c7-1154-493e-bff2-7a805bef3768");
                 request.addProperty(webserviceId);

                 redirectUrl = new PropertyInfo();
                 redirectUrl.setName("redirectUrl");
                 redirectUrl.setValue("www.soshians-co.com");
                 request.addProperty(redirectUrl);

                 issuer = new PropertyInfo();
                 issuer.setName("issuer");
                 issuer.setValue("Mellat");
                 request.addProperty(issuer);

                 redirectToPage = new PropertyInfo();
                 redirectToPage.setName("redirectToPage");
                 redirectToPage.setValue("True");
                 request.addProperty(redirectToPage);

                 scriptVersion = new PropertyInfo();
                 scriptVersion.setName("scriptVersion");
                 scriptVersion.setValue("Android");
                 request.addProperty(scriptVersion);

                 firstOutputType = new PropertyInfo();
                 firstOutputType.setName("firstOutputType");
                 firstOutputType.setValue("Json");
                 request.addProperty(firstOutputType);

                 secondOutputType = new PropertyInfo();
                 secondOutputType.setName("secondOutputType");
                 secondOutputType.setValue("Json");
                 request.addProperty(secondOutputType);

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
                            //object
                        } else if (s.startsWith("[")) {
                            //JSONObject jsonObj = new JSONObject(s);
                            JSONArr = new JSONArray(s);
                            for (int i = 0; i < JSONArr.length(); i++) {
                                JSONObject c = JSONArr.getJSONObject(i);

                                status = c.getString("status");
                                orderHash = c.getString("orderHash");
                                paymentInfo = c.getString("paymentInfo");
                                errorMessage = c.getString("errorMessage");


                            }


                        }
                    }


                    //////////
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
            for(int i = 0;i<=3;i++) {
                Toast.makeText(KharidSharj4.this, "" + result, Toast.LENGTH_LONG).show();
            }



        }
    }
*/

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
            Intent intent = new Intent(KharidSharj4.this, MainActivity.class);
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

    public void pardakht(View view){

    }

}


