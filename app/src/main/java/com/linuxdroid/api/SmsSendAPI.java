package com.linuxdroid.api;

import android.content.Intent;
import android.telephony.SmsManager;

import com.linuxdroid.api.util.ResultReturner;
import com.linuxdroid.api.util.LinuxdroidApiLogger;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SmsSendAPI {

    static void onReceive(LinuxdroidApiReceiver apiReceiver, final Intent intent) {
        ResultReturner.returnData(apiReceiver, intent, new ResultReturner.WithStringInput() {
            @Override
            public void writeResult(PrintWriter out) {
                final SmsManager smsManager = SmsManager.getDefault();
                String[] recipients = intent.getStringArrayExtra("recipients");

                if (recipients == null) {
                    // Used by old versions of linuxdroid-send-sms.
                    String recipient = intent.getStringExtra("recipient");
                    if (recipient != null) recipients = new String[]{recipient};
                }

                if (recipients == null || recipients.length == 0) {
                    LinuxdroidApiLogger.error("No recipient given");
                } else {
                    final ArrayList<String> messages = smsManager.divideMessage(inputString);
                    for (String recipient : recipients) {
                        smsManager.sendMultipartTextMessage(recipient, null, messages, null, null);
                    }
                }
            }
        });
    }

}
