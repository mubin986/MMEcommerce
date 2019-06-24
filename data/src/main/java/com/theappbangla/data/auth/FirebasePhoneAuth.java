package com.theappbangla.data.auth;

import android.app.Activity;
import android.util.Log;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.*;

import java.util.concurrent.TimeUnit;


public class FirebasePhoneAuth extends PhoneAuthProvider.OnVerificationStateChangedCallbacks {

    /*
    Firebase Phone Auth Class [in Java]
    Written by Shariful Islam Mubin [Dept. of CSE, KUET]
     */

    private Activity activity;
    private PhoneAuthCallback callback;

    private FirebaseAuth mAuth;
    private PhoneAuthProvider phoneAuthProvider;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId, phoneNumber, countryCode = "";
    private int timeout = 60; // in seconds


    public FirebasePhoneAuth(Activity activity, String countryCode, String phoneNumber, PhoneAuthCallback phoneAuthCallback) {
        this.countryCode = countryCode;
        init(activity, phoneNumber, phoneAuthCallback);
    }

    public FirebasePhoneAuth(Activity activity, String phoneNumber, PhoneAuthCallback phoneAuthCallback) {
        init(activity, phoneNumber, phoneAuthCallback);
    }

    private void init(Activity activity, String phoneNumber, PhoneAuthCallback phoneAuthCallback) {
        this.activity = activity;
        this.phoneNumber = phoneNumber;
        this.callback = phoneAuthCallback;

        phoneAuthProvider = PhoneAuthProvider.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void sendCode() {
        Log.d("sendCode: ", countryCode + phoneNumber);
        phoneAuthProvider.verifyPhoneNumber(
                countryCode + phoneNumber,             // Phone number to verify
                timeout,                                 // Timeout duration
                TimeUnit.SECONDS,                       // Unit of timeout
                activity,                              // Activity (for callback binding)
                this);  // OnVerificationStateChangedCallbacks
    }

    public void verifyCode(String code) {
        PhoneAuthCredential credential = null;
        try {
            credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            signInWithPhoneAuthCredential(credential, callback);
        } catch (Exception e) {
            callback.onUnknownError();
        }
    }

    public void resendVerificationCode() {
        phoneAuthProvider.verifyPhoneNumber(
                countryCode + phoneNumber,                  // Phone number to verify
                timeout,                                      // Timeout duration
                TimeUnit.SECONDS,                            // Unit of timeout
                activity,                                   // Activity (for callback binding)
                this,        // OnVerificationStateChangedCallbacks
                mResendToken);                            // ForceResendingToken from callbacks
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential, final PhoneAuthCallback callback) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult() != null)
                            callback.onSuccess(task.getResult().getUser().getUid());
                        else callback.onUnknownError();
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            callback.onInvalidVerificationCode();
                        } else {
                            callback.onUnknownError();
                        }
                    }
                });
    }

    // PhoneAuthProvider.OnVerificationStateChangedCallbacks
    @Override
    public void onVerificationCompleted(PhoneAuthCredential credential) {
        String code = credential.getSmsCode();
        if (code != null) {
            verifyCode(code);
        }
    }

    @Override
    public void onVerificationFailed(FirebaseException e) {
        if (e instanceof FirebaseAuthInvalidCredentialsException) {
            callback.onInvalidRequest();
        } else if (e instanceof FirebaseTooManyRequestsException) {
            callback.onTooManyRequests();
        } else {
            callback.onUnknownError();
        }
    }

    @Override
    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        mVerificationId = verificationId;
        mResendToken = forceResendingToken;
        callback.onCodeSent(verificationId);
    }

    // Callback Interface
    public interface PhoneAuthCallback {
        void onCodeSent(String verificationId);
        void onSuccess(String uid);
        void onInvalidRequest();
        void onTooManyRequests();
        void onInvalidVerificationCode();
        void onUnknownError();
    }

}
