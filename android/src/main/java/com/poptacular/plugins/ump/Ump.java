package com.poptacular.plugins.ump;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class Ump {

    static String TAG = "Ump";

    private ConsentInformation consentInformation;
    private ConsentForm consentForm;

    public void initialise(Activity activity) {
        Log.d(TAG, "initialise");

        // Set tag for underage of consent. false means users are not underage.
        ConsentRequestParameters params = new ConsentRequestParameters
                .Builder()
                .setTagForUnderAgeOfConsent(false)
                .build();

        consentInformation = UserMessagingPlatform.getConsentInformation(activity);
        consentInformation.requestConsentInfoUpdate(
                activity,
                params,
                new ConsentInformation.OnConsentInfoUpdateSuccessListener() {
                    @Override
                    public void onConsentInfoUpdateSuccess() {
                        // The consent information state was updated.
                        Log.d(TAG, "initialise | onConsentInfoUpdateSuccess");

                        // You are now ready to check if a form is available.
                        if (consentInformation.isConsentFormAvailable()) {
                            Log.d(TAG, "initialise | onConsentInfoUpdateSuccess | isConsentFormAvailable=true");
                            loadForm(activity);


                        }

                    }
                },
                new ConsentInformation.OnConsentInfoUpdateFailureListener() {
                    @Override
                    public void onConsentInfoUpdateFailure(FormError formError) {
                        // Handle the error.
                        Log.d(TAG, "initialise | onConsentInfoUpdateFailure: " + formError.getMessage());
                    }
                });

    }

    public void loadForm(Activity activity) {
        UserMessagingPlatform.loadConsentForm(
                activity,
                new UserMessagingPlatform.OnConsentFormLoadSuccessListener() {
                    @Override
                    public void onConsentFormLoadSuccess(ConsentForm consentForm) {
                        consentForm = consentForm;
                        Log.d(TAG, "loadForm | onConsentFormLoadSuccess");
                        if(consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.REQUIRED) {
                            consentForm.show(
                                    activity,
                                    new ConsentForm.OnConsentFormDismissedListener() {
                                        @Override
                                        public void onConsentFormDismissed(@Nullable FormError formError) {
                                            // Handle dismissal by reloading form.
                                            Log.d(TAG, "loadForm | onConsentFormDismissed");
                                            loadForm(activity);

                                            Intent intent = new Intent("consent-complete");
                                            LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
                                        }
                                    });

                        }

                    }
                },
                new UserMessagingPlatform.OnConsentFormLoadFailureListener() {
                    @Override
                    public void onConsentFormLoadFailure(FormError formError) {
                        /// Handle Error.
                        Log.d(TAG, "loadForm | onConsentFormLoadFailure: " + formError.getMessage());
                    }
                }
        );
    }

}
