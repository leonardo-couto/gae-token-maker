package com.github.leonardocouto.gaecookiemaker;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class AuthCookieJar implements CookieJar {

  private List<Cookie> store = new ArrayList<>();

  @Override
  public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
    store = cookies;
  }

  @Override
  public List<Cookie> loadForRequest(HttpUrl url) {
    return this.store;
  }

  public List<Cookie> cookies() {
    return this.store;
  }
}
