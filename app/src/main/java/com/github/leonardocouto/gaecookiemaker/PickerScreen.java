package com.github.leonardocouto.gaecookiemaker;

import com.google.android.gms.common.AccountPicker;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class PickerScreen extends Activity {

  private final int REQUEST_CODE_PICK_ACCOUNT = 1122;

  protected Intent intent;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.picker_screen);

    String[] accountTypes = new String[]{"com.google"};

    Intent intent = AccountPicker.newChooseAccountIntent(null, null, accountTypes, false, null, null, null, null);
    startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE_PICK_ACCOUNT) {
      if (resultCode == RESULT_OK) {

        String accountType = data.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE);
        String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        Account account = new Account(accountName, accountType);
        Intent intent = new Intent(this, CookieInfo.class);
        intent.putExtra("account", account);
        startActivity(intent);

      } else if (resultCode == RESULT_CANCELED) {
        Toast.makeText(this, R.string.pick_account, Toast.LENGTH_SHORT).show();
      }
    }
  }

}