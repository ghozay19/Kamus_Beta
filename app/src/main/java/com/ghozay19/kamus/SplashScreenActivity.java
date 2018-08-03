package com.ghozay19.kamus;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ghozay19.kamus.Database.BhsEngHelper;
import com.ghozay19.kamus.Database.EngBhsHelper;
import com.ghozay19.kamus.Model.BhsEngModel;
import com.ghozay19.kamus.Model.EngBhsModel;
import com.ghozay19.kamus.Prefs.AppPrefence;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        final String TAG = LoadData.class.getSimpleName();
        EngBhsHelper engBhsHelper;
        BhsEngHelper bhsEngHelper;
        AppPrefence appPrefence;


        @Override
        protected void onPreExecute() {
            bhsEngHelper = new BhsEngHelper(SplashScreenActivity.this);
            engBhsHelper = new EngBhsHelper(SplashScreenActivity.this);
            appPrefence = new AppPrefence(SplashScreenActivity.this);
        }


        @Override
        protected Void doInBackground(Void... voids) {

            Boolean firstRun = appPrefence.getFirstRun();

            if (firstRun) {
                ArrayList<EngBhsModel> engBhsModels = preLoadRawEng();
                ArrayList<BhsEngModel> bhsEngModels = preLoadRawBhs();


                engBhsHelper.open();
                engBhsHelper.beginTransactionEng();

                try {
                    for (EngBhsModel engModel : engBhsModels) {
                        engBhsHelper.insertTransactionEng(engModel);
                    }
                    engBhsHelper.setTransactionSuccessEng();
                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: Exception");
                }

                engBhsHelper.endTransactionEng();
                engBhsHelper.closeEng();
                bhsEngHelper.open();
                bhsEngHelper.beginTransactionBhs();

                try {
                    for (BhsEngModel bhsModel : bhsEngModels) {
                        bhsEngHelper.insertTransactionBhs(bhsModel);
                    }
                    bhsEngHelper.setTransactionSuccessBhs();
                } catch (Exception e) {
                    Log.e(TAG, "doInBackgorund Exception");
                }
                bhsEngHelper.endTransactionBhs();
                bhsEngHelper.closeBhs();


                appPrefence.setFirstRun(false);

            } else {
                try {
                    synchronized (this) {
                        this.wait(2000);
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    public ArrayList<EngBhsModel> preLoadRawEng() {
        ArrayList<EngBhsModel> engBhsModels = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict_eng = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict_eng));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splistr = line.split("\t");

                EngBhsModel engBhsModel;

                engBhsModel = new EngBhsModel(splistr[0], splistr[1]);
                engBhsModels.add(engBhsModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return engBhsModels;
    }

    public ArrayList<BhsEngModel> preLoadRawBhs() {
        ArrayList<BhsEngModel> bhsEngModels = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict_eng = res.openRawResource(R.raw.indonesia_english);

            reader = new BufferedReader(new InputStreamReader(raw_dict_eng));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splistr = line.split("\t");

                BhsEngModel bhsEngModel;

                bhsEngModel = new BhsEngModel(splistr[0], splistr[1]);
                bhsEngModels.add(bhsEngModel);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bhsEngModels;
    }

}


