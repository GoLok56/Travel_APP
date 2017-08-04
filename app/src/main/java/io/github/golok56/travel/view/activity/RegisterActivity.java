package io.github.golok56.travel.view.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.github.golok56.travel.R;
import io.github.golok56.travel.database.DBHelper;
import io.github.golok56.travel.database.DBSchema;
import io.github.golok56.travel.model.User;

public class RegisterActivity extends AppCompatActivity {

    private DBHelper mDb;

    EditText mEtEmail;
    EditText mEtUsername;
    EditText mEtName;
    EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
        ActionBar ab = getSupportActionBar();
        if(ab != null){
            setTitle(getString(R.string.register_label));
        }

        mDb = new DBHelper(this);

        mEtEmail = (EditText) findViewById(R.id.et_register_activity_email);
        mEtUsername = (EditText) findViewById(R.id.et_register_activity_username);
        mEtName = (EditText) findViewById(R.id.et_register_activity_name);
        mEtPassword = (EditText) findViewById(R.id.et_register_activity_password);

        Button btnRegister = (Button) findViewById(R.id.btn_register_activity_do_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEtEmail.getText().toString();
                String username = mEtUsername.getText().toString();
                String name = mEtName.getText().toString();
                String password = mEtPassword.getText().toString();
                if(email.isEmpty() || username.isEmpty() || password.isEmpty()){
                    showToast("Tidak boleh ada form yang kosong!");
                } else {
                    if(insert(new User(email, username, name, password))){
                        showToast("Berhasil mendaftar. Silakan login menggunakan username anda!");
                        startActivity(LoginActivity.getIntent(RegisterActivity.this));
                    } else {
                        showToast("Terjadi kesalahan saat mendaftar!");
                    }
                }
            }
        });

    }

    private boolean insert(User newUser){
        SQLiteDatabase wdb = mDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBSchema.TableUser.EMAIL_COLUMN, newUser.getEmail());
        values.put(DBSchema.TableUser.USERNAME_COLUMN, newUser.getUsername());
        values.put(DBSchema.TableUser.NAME_COLUMN, newUser.getName());
        values.put(DBSchema.TableUser.PASSWORD_COLUMN, newUser.getPassword());
        long id =  wdb.insert(DBSchema.TableUser.TABLE_NAME, null, values);
        wdb.close();
        return id != -1;
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    static Intent getIntent(Context context){
        return new Intent(context, RegisterActivity.class);
    }

}
