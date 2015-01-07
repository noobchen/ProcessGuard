package com.cyc.processguard.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.cyc.processguard.aidl.ServiceGuard;
import com.cyc.processguard.utils.CheakProcessRunningUtils;
import com.cyc.processguard.utils.LogUtils;

/**
 * Created by Administrator on 2015/1/7.
 */
public class Service2 extends Service{
    private final String service1ProcessName = "com.cyc.processguard:service1";

    private ServiceGuard serviceGuard = new ServiceGuard.Stub() {

        @Override
        public void stopService() throws RemoteException {
            Intent i = new Intent(Service2.this, Service1.class);
            Service2.this.stopService(i);
        }

        @Override
        public void startService() throws RemoteException {
            Intent i = new Intent(Service2.this, Service1.class);
            Service2.this.startService(i);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.info("Service2 onCreate ...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!CheakProcessRunningUtils.isProcessRunning(Service2.this, service1ProcessName)) {
                        LogUtils.error("Service1 is finished ...");
                        try {
                            serviceGuard.startService();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
