package com.imageviewdimen.touchspring.monitor.Broadercast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.imageviewdimen.touchspring.monitor.Service.PhoneService;

/**
 * Created by KIM on 2015/6/25 0025.
 */
public class phoneBroaderCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /*if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){
            Log.i("KIMOPL", "call OUT:");
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }else{
            Log.i("KIMOPL", "call in:");

        }*/

        Log.i("KIMOPL", "phoneBroaderCastReceiver");
        Intent service = new Intent(context, PhoneService.class);//显式/隐式
        context.startService(service);//Intent激活组件(Service)

    }
}
