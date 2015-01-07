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
public class Service1 extends Service {

    private final String service2ProcessName = "com.cyc.processguard:service2";

    private ServiceGuard serviceGuard = new ServiceGuard.Stub() {

        @Override
        public void stopService() throws RemoteException {
            Intent i = new Intent(Service1.this, Service2.class);
            Service1.this.stopService(i);
        }

        @Override
        public void startService() throws RemoteException {
            Intent i = new Intent(Service1.this, Service2.class);
            Service1.this.startService(i);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.info("Service1 onCreate ...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!CheakProcessRunningUtils.isProcessRunning(Service1.this, service2ProcessName)) {
                        LogUtils.error("Service2 is finished ...");
                        try {
                            serviceGuard.startService();
                            LogUtils.error("Restart Service2 ...");
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
        return START_STICKY;
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
