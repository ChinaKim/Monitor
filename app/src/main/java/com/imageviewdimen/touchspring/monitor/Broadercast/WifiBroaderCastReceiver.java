package com.imageviewdimen.touchspring.monitor.Broadercast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * Created by KIM on 2015/6/29 0029.
 */
public class WifiBroaderCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {//未连接

        }else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {//连接

        }
    }


}
