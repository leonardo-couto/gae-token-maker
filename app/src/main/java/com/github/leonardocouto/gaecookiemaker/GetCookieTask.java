package com.github.leonardocouto.gaecookiemaker;

import android.os.AsyncTask;
import android.util.Log;

import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetCookieTask extends AsyncTask<String, Void, Boolean> {

  private static final String GAE_APP = "pythonic-test-3.appspot.com";
  private static final String LOGIN_URL = "https://%s/_ah/login?continue=http://localhost/&auth=%s";

  private final CookieInfo activity;
  private String cookie;

  public GetCookieTask(CookieInfo activity) {
    this.activity = activity;
  }

  protected Boolean doInBackground(String ... tokens) {
    try {
      AuthCookieJar cookieJar = new AuthCookieJar();

      OkHttpClient client = new OkHttpClient.Builder()
          .followRedirects(false)
          .cookieJar(cookieJar)
          .build();

      Request getCookie = (new Request.Builder())
          .url(String.format(LOGIN_URL, GAE_APP, tokens[0]))
          .build();

      Response cookieResponse = client.newCall(getCookie).execute();
      if (!cookieResponse.isRedirect()) {
        // Response should be a redirect
        return false;
      }

      for (Cookie cookie : cookieJar.cookies()) {
        if (cookie.name().equals("SACSID")) {
          this.cookie = cookie.name() + "=" + cookie.value();
          return true;
        }
      }

    } catch (Exception e) {
      Log.e("GET_COOKIE_TASK", e.getMessage(), e);
    }


    return false;
  }

  protected void onPostExecute(Boolean result) {
    this.activity.setCookie(this.cookie);
  }
}