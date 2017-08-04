package io.github.golok56.travel.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.github.golok56.travel.R;
import io.github.golok56.travel.database.DBHelper;
import io.github.golok56.travel.database.DBSchema;
import io.github.golok56.travel.model.User;
import io.github.golok56.travel.util.PreferenceManager;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_EXTRA = "user";

    private DBHelper mDb;

    private SharedPreferences mSp;

    private EditText mEtUsername;
    private EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDb = new DBHelper(this);
        mSp = getSharedPreferences(PreferenceManager.PREF_NAME, Context.MODE_PRIVATE);

        if (mSp.getBoolean(PreferenceManager.IS_LOGIN, false)) {
            User user = getUser(mSp.getString(
                    PreferenceManager.USER_USERNAME, null),
                    mSp.getString(PreferenceManager.USER_PASSWORD, null
                    ));
            startActivity(DashboardActivity.getIntent(this, user));
        }

        setContentView(R.layout.activity_login);

        mEtUsername = (EditText) findViewById(R.id.et_login_activity_username);
        mEtPassword = (EditText) findViewById(R.id.et_login_activity_password);

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            setTitle(getString(R.string.login_label));
        }

        findViewById(R.id.btn_login_activity_do_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEtUsername.getText().toString();
                String password = mEtPassword.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    showToast("Username atau Password harus diisi!");
                } else {
                    User user = getUser(username, password);
                    if (user != null) {
                        SharedPreferences.Editor prefEditor = mSp.edit();
                        prefEditor.putBoolean(PreferenceManager.IS_LOGIN, true);
                        prefEditor.putString(PreferenceManager.USER_USERNAME, user.getUsername());
                        prefEditor.putString(PreferenceManager.USER_PASSWORD, user.getPassword());
                        prefEditor.apply();
                        startActivity(DashboardActivity.getIntent(LoginActivity.this, user));
                    } else {
                        showToast("Username atau Password salah! Silakan masukkan ulang!");
                    }
                }
            }
        });

        findViewById(R.id.btn_login_activity_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(RegisterActivity.getIntent(LoginActivity.this));
            }
        });

        findViewById(R.id.tv_activity_login_forget_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ForgetPasswordActivity.getIntent(LoginActivity.this));
            }
        });
    }

    private User getUser(String username, String password) {
        SQLiteDatabase wdb = mDb.getWritableDatabase();

        String[] columns = {
                DBSchema.TableUser._ID,
                DBSchema.TableUser.EMAIL_COLUMN,
                DBSchema.TableUser.NAME_COLUMN
        };
        String selection = DBSchema.TableUser.USERNAME_COLUMN + "=? and " + DBSchema.TableUser.PASSWORD_COLUMN + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = wdb.query(
                DBSchema.TableUser.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            String email = cursor.getString(cursor.getColumnIndex(DBSchema.TableUser.EMAIL_COLUMN));
            String name = cursor.getString(cursor.getColumnIndex(DBSchema.TableUser.NAME_COLUMN));
            int id = cursor.getInt(cursor.getColumnIndex(DBSchema.TableUser._ID));
            cursor.close();
            return new User(id, email, username, name, password);
        } else {
            cursor.close();
            return null;
        }

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public static Intent getIntent(FragmentActivity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.finish();
        return intent;
    }

    static Intent getIntent(AppCompatActivity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.finish();
        return intent;
    }
}
