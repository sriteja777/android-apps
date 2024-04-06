    package com.test.biometric;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.security.Signature;
import java.security.SignatureException;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showBiometricPrompt(View v) {
        BiometricPrompt.AuthenticationCallback authenticationCallback = getAuthenticationCallback();
        BiometricPrompt mBiometricPrompt = new BiometricPrompt(this, getMainThreadExecutor(), authenticationCallback);
        // Set prompt info
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setDescription("Description")
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setNegativeButtonText("Cancel")
                .build();

        // Show biometric prompt
//        if (signature != null) {
            Log.i(TAG, "Show biometric prompt");
            mBiometricPrompt.authenticate(promptInfo);
//            mBiometricPrompt.authenticate(promptInfo, new BiometricPrompt.CryptoObject(signature));
//        }
    }

    private BiometricPrompt.AuthenticationCallback getAuthenticationCallback() {
        // Callback for biometric authentication result
        return new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                Log.e(TAG, "Error code: " + errorCode + "error String: " + errString);
                Toast.makeText(getApplicationContext(), "error: " + errorCode + " " + errString, Toast.LENGTH_SHORT).show();
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                Log.i(TAG, "onAuthenticationSucceeded");
                super.onAuthenticationSucceeded(result);
//                if (result.getCryptoObject() != null &&
//                        result.getCryptoObject().getSignature() != null) {
//                    try {
////                        Signature signature = result.getCryptoObject().getSignature();
////                        signature.update(mToBeSignedMessage.getBytes());
////                        String signatureString = Base64.encodeToString(signature.sign(), Base64.URL_SAFE);
////                        // Normally, ToBeSignedMessage and Signature are sent to the server and then verified
////                        Log.i(TAG, "Message: " + mToBeSignedMessage);
////                        Log.i(TAG, "Signature (Base64 Encoded): " + signatureString);
                        Toast.makeText(getApplicationContext(), "Got authenticated", Toast.LENGTH_SHORT).show();
//                    } catch (SignatureException e) {
//                        throw new RuntimeException();
//                    }
//                } else {
//                    // Error
//                    Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "auth failed", Toast.LENGTH_SHORT).show();

            }

        };
    }

    private Executor getMainThreadExecutor() {
        return new MainThreadExecutor();
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable r) {
            handler.post(r);
        }
    }



}