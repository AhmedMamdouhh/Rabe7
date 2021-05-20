package com.rabe7;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.rabe7.community.R;
import com.rabe7.community.manager.connection.ResponseResource;
import com.rabe7.community.ui.auth.AuthActivity;
import com.rabe7.community.ui.main.MainActivity;

import org.aviran.cookiebar2.CookieBar;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    protected Dialog loadingBar;

    @Inject
    public ResponseManager responseManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getApplication())
                .appComponent()
                .inject(this);

        initializeProgress();
        observeResponse();
    }

    protected abstract void inject();

    public void observeResponse() {
        responseManager.getResponseManager().observe(this, new Observer<ResponseResource>() {
            @Override
            public void onChanged(ResponseResource responseResource) {
                try {

                    switch (responseResource.getStatus()) {
                        case LOADING: {
                            showProgress();
                            break;
                        }

                        case AUTHENTICATED:
                        case HIDE_LOADING:{
                            hideProgress();
                            break;
                        }

                        case SUCCESS: {
                            hideProgress();
                            successMessage(responseResource.getMessage());
                            break;
                        }

                        case FAILED: {
                            hideProgress();
                            failedMessage(responseResource.getMessage());
                            break;
                        }

                        case NO_CONNECTION: {
                            hideProgress();
                            noConnection();
                            break;
                        }


                        case LOGOUT: {

                            break;
                        }
                    }

                } catch (NullPointerException ex) {

                }
            }
        });
    }

    public void successMessage(String message) {
        CookieBar.build(BaseActivity.this)
                .setCustomView(R.layout.layout_success)
                .setTitle(R.string.label_success_message_title)
                .setMessage(message)
                .setCookiePosition(CookieBar.BOTTOM)
                .show();
    }

    public void failedMessage(String message) {
        CookieBar.build(BaseActivity.this)
                .setCustomView(R.layout.layout_error)
                .setTitle(R.string.label_error_message_title)
                .setMessage(message)
                .setCookiePosition(CookieBar.BOTTOM)
                .show();
    }

    public void initializeProgress() {
        Log.e(TAG, "initializeProgress: " );
        loadingBar = new Dialog(this, R.style.Theme_AppCompat_DayNight);
        loadingBar.setCancelable(false);
        loadingBar.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = LayoutInflater.from(this);
        View loadingView = inflater.inflate(R.layout.layout_loader, null);
        loadingBar.setContentView(loadingView);
        loadingBar.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#0effffff")));
    }

    public void noConnection() {

        CookieBar.build(BaseActivity.this)
                .setCustomView(R.layout.layout_no_connection)
                .setCustomViewInitializer(new CookieBar.CustomViewInitializer() {
                    @Override
                    public void initView(View view) {

                        ImageView close = view.findViewById(R.id.iv_no_connection_close);

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CookieBar.dismiss(BaseActivity.this);
                            }
                        });

                    }
                })

                .setEnableAutoDismiss(false) // Cookie will stay on display until manually dismissed
                .setCookiePosition(CookieBar.BOTTOM)
                .show();
    }


    public void showProgress() {
        if (loadingBar != null && !this.isFinishing())
            loadingBar.show();
    }

    public void hideProgress() {
        if (loadingBar != null && loadingBar.isShowing() && !this.isFinishing())
            loadingBar.dismiss();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

}
