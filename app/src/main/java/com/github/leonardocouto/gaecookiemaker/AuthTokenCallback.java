package com.github.leonardocouto.gaecookiemaker;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AuthTokenCallback implements AccountManagerCallback<Bundle> {

  private final CookieInfo activity;

  public AuthTokenCallback(CookieInfo activity) {
    this.activity = activity;
  }

  public void run(AccountManagerFuture<Bundle> result) {
    try {

      Bundle bundle = result.getResult();
      Intent intent = (Intent) bundle.get(AccountManager.KEY_INTENT);

      if (intent != null) {
        this.activity.startActivity(intent);
      } else {
        onGetAuthToken(bundle);
      }

    } catch (Exception e) {
      Log.e("GET_AUTH_TOKEN", e.getMessage(), e);
    }
  }

  private void onGetAuthToken(Bundle bundle) {
    String auth_token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
    (new GetCookieTask(activity)).execute(auth_token);
  }

}
