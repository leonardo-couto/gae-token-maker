package com.github.leonardocouto.gaecookiemaker;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class CookieInfo extends Activity {

  private EditText text;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.cookie_info);

    this.text = (EditText) this.findViewById(R.id.cookie);
  }

  @Override
  protected void onResume() {
    super.onResume();
    Intent intent = getIntent();
    AccountManager accountManager = AccountManager.get(getApplicationContext());
    Account account = (Account) intent.getExtras().get("account");
    accountManager.getAuthToken(account, "ah", null, false, new AuthTokenCallback(this), null);
  }

  public void setCookie(String cookie) {
    this.text.setText(cookie);
  }

}
