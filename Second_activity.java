package org.esiea.hanotaux.projetandroidinf3044;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Second_activity extends AppCompatActivity {

    public static final String BIERE_UPDATE="BIERE_UPDATE";
    public android.support.v7.widget.RecyclerView rv;
    public BiersAdapter br;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde_activity);
        GetBiersService.startActionFoo(this);

        IntentFilter inF = new IntentFilter(BIERE_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(),inF);
        rv = (android.support.v7.widget.RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        br = new BiersAdapter(getBiersFromFile());
        rv.setAdapter(br);

    }

    public class BierUpdate extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != intent)
                Log.d("OKz",intent.getAction());
            NotificationCompat.Builder not = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext()).
                    setSmallIcon(R.mipmap.ic_launcher).
                    setContentText("Download Complete").
                    setContentTitle("Bieres DL");
            not.setPriority(100);
            NotificationManager notM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            notM.notify(1, not.build());
            br.setNewBiere(getBiersFromFile());
        }
    }

    public JSONArray getBiersFromFile(){
        try {
            InputStream is = new FileInputStream(getCacheDir()+"/bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
        return new JSONArray();
    }
}
