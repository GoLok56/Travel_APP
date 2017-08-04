package io.github.golok56.travel.view.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.github.golok56.travel.R;
import io.github.golok56.travel.database.DBHelper;
import io.github.golok56.travel.database.DBSchema;

public class ForgetPasswordActivity extends AppCompatActivity {

    DBHelper mDb;

    EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mDb = new DBHelper(this);
        etUsername = (EditText) findViewById(R.id.et_activity_forget_pass_username);

        findViewById(R.id.btn_activity_forget_pass_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase wdb = mDb.getWritableDatabase();

                String username = etUsername.getText().toString();

                String[] columns = { DBSchema.TableUser.PASSWORD_COLUMN };
                String selection = DBSchema.TableUser.USERNAME_COLUMN + "=?";
                String[] selArgs = { username };
                Cursor cursor = wdb.query(
                        DBSchema.TableUser.TABLE_NAME,
                        columns,
                        selection,
                        selArgs,
                        null,
                        null,
                        null
                );

                if(cursor.moveToFirst()){
                    String password = cursor.getString(cursor.getColumnIndex(DBSchema.TableUser.PASSWORD_COLUMN));
                    showToast("Password anda adalah " + password + "!");
                    startActivity(LoginActivity.getIntent(ForgetPasswordActivity.this));
                } else {
                    showToast("Username tidak ditemukan!");
                }

                cursor.close();
            }
        });
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    static Intent getIntent(Context context){
        return new Intent(context, ForgetPasswordActivity.class);
    }

}
