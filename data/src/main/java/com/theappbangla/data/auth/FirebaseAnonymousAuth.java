package com.theappbangla.data.auth;

import android.app.Activity;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAnonymousAuth {

    /*
    Firebase Anonymous Auth Class [in Java]
    Written by Shariful Islam Mubin [Dept. of CSE, KUET]
     */

    public static final String TAG = "FirebaseAnonymousAuth";

    private Activity activity;
    private FirebaseAuth mAuth;

    public FirebaseAnonymousAuth(Activity activity) {
        mAuth = FirebaseAuth.getInstance();
        this.activity = activity;
    }

    private FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public void signInAnonymously(OnAnonymousLoginCallback onAnonymousLoginCallback) {
        mAuth.signInAnonymously()
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInAnonymously:success");
                        if (onAnonymousLoginCallback != null)
                            onAnonymousLoginCallback.onAnonymousLoginSuccess(getCurrentUser());

                    } else {
                        if (onAnonymousLoginCallback != null)
                            onAnonymousLoginCallback.onAnonymousLoginError();
                    }
                });
    }

    public interface OnAnonymousLoginCallback {
        void onAnonymousLoginSuccess(FirebaseUser firebaseUser);
        void onAnonymousLoginError();
    }
}
