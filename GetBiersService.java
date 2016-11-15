package org.esiea.hanotaux.projetandroidinf3044;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class GetBiersService extends IntentService {
    private static final String ACTION_FOO = "org.esiea.hanotaux.projetandroidinf3044.action.FOO";
    private static final String TAG = "GBS";

    public GetBiersService() {
        super("GetBiersService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context) {
        Intent intent = new Intent(context, GetBiersService.class);
        intent.setAction(ACTION_FOO);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                handleActionFoo();
            }
        }
    }
    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo() {
       Log.d(TAG,"Thread service name : " + Thread.currentThread().getName());
        URL url = null;
        try {
            url = new URL ("http://binouze.fabrigli.fr/bieres.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                copyInputStreamToFile(connection.getInputStream(),
                        new File(getCacheDir(), "/bieres.json"));
                Log.d(TAG,"BIERE DL");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Second_activity.BIERE_UPDATE));
    }

    private void copyInputStreamToFile (InputStream in, File file){
        try {
            OutputStream ou =  new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len=in.read(buf))>0){
                ou.write(buf,0,len);
            }
            ou.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
