package com.example.dudal_000.leanlinetn;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dudal_000.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.appengine.repackaged.com.google.common.base.Pair;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login_button;
    private Button register_button;
    int attempt_counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton();
        new EndpointsTask().execute(getApplicationContext());
    }

    public void LoginButton() {
        username = (EditText) findViewById(R.id.txt_email);
        password = (EditText) findViewById(R.id.txt_password);
        login_button = (Button) findViewById(R.id.button_login);

        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    class MyTask extends AsyncTask<Pair<Context, String>, Void, String>

                    {
                        public MyApi myApiService = null;
                        private Context context;


                        protected String doInBackground(Pair<Context, String>... params) {
                            if (myApiService == null) {  // Only do this once
                                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                                        new AndroidJsonFactory(), null)
                                        // options for running against local devappserver
                                        // - 10.0.2.2 is localhost's IP address in Android emulator
                                        // - turn off compression when running against local devappserver
                                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                            @Override
                                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                                abstractGoogleClientRequest.setDisableGZipContent(true);
                                            }
                                        });
                                // end options for devappserver

                                myApiService = builder.build();
                            }

                            context = params[0].first;
                            String name = params[0].second;

                            try {
                                return myApiService.sayHi(name).execute().getData();
                            } catch (IOException e) {
                                return e.getMessage();
                            }
                        }

                        @Override
                        protected void onPostExecute(String result) {
                            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                        }
                    }


                }
        );
    }


}
