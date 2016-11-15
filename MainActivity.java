package org.esiea.hanotaux.projetandroidinf3044;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private static final int NOTIF_ID = 0;
    protected DatePickerDialog dpd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_hw = (TextView) findViewById(R.id.tv_hello_world);
        //String now = DateUtils.formatDateTime(getApplicationContext(),(new Date()).getTime());
        tv_hw.setText("DATE");
        DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        };

        dpd = new DatePickerDialog(this, odsl, 1996,10,10);

    }

    public void toast (View v) {
        Toast.makeText(getApplicationContext(),getString(R.string.msg), Toast.LENGTH_LONG).show();
        //dpd.show();
         NotificationCompat.Builder not = (NotificationCompat.Builder) new NotificationCompat.Builder(this).
                setSmallIcon(R.mipmap.ic_launcher).
                setContentText("Text notification").
                setContentTitle("WAIT");
        not.setPriority(10);
        NotificationManager notM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        notM.notify(NOTIF_ID, not.build());

        Intent in = new Intent(this,Second_activity.class);
        startActivity(in);
    }
}
