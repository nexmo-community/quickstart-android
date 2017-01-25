package com.google.firebase.quickstart.auth;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nexmo.sdk.NexmoClient;
import com.nexmo.sdk.core.client.ClientBuilderException;
import com.nexmo.sdk.verify.client.VerifyClient;

public class TwoFactorApplication extends Application {
    private VerifyClient verifyClient;
    private NexmoClient nexmoClient;
    private boolean verified;
    private String appID,secret;
    private static final String TAG = "TWOFACTORAPPLICATION";
    public VerifyClient getVerifyClient(boolean verifiedValue) {
        verified = verifiedValue;
        return this.verifyClient;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        acquireVerifyClient();
    }
    public void acquireVerifyClient() {
        Context context = getApplicationContext();
        try {
            this.nexmoClient = new NexmoClient.NexmoClientBuilder()
                    .context(context)
                    .applicationId(getString(R.string.nexmo_application_id))
                    .sharedSecretKey(getString(R.string.nexmo_shared_secret))
                    .build();
        } catch (ClientBuilderException e) {
            e.printStackTrace();
            Log.d(TAG, e.toString());
            return;
        }
        this.verifyClient = new VerifyClient(nexmoClient);
    }
}
