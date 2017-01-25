package com.google.firebase.quickstart.auth;

import android.app.ProgressDialog;

import android.os.Bundle;

import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import com.nexmo.sdk.verify.client.VerifyClient;
import com.nexmo.sdk.verify.event.UserObject;
import com.nexmo.sdk.verify.event.VerifyClientListener;
import com.nexmo.sdk.verify.event.VerifyError;

public class BaseActivity extends AppCompatActivity {

//Declaration
    public TwoFactorApplication twoFactorApp;
    protected boolean verified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        twoFactorApp = (TwoFactorApplication) this.getApplication();
    }

    protected void addVerificationListener() {
        twoFactorApp.getVerifyClient(verified).addVerifyListener(new VerifyClientListener() {
            @Override
            public void onVerifyInProgress(VerifyClient verifyClient, UserObject user) {
            }
            @Override
            public void onUserVerified(VerifyClient verifyClient, UserObject user) {
            }
            @Override
            public void onError(VerifyClient verifyClient, VerifyError errorCode, UserObject user) {
            }
            @Override
            public void onException(IOException exception) {
            }
        });
    }

    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

}
