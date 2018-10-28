package top.fankaljead.memo.memo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;

import top.fankaljead.memo.R;

public class RegisterActivity extends AppCompatActivity {

    private ImageButton registerBtBack;
    private EditText registerUserName;
    private EditText registerUserEmail;
    private EditText registerUserTel;
    private EditText registerUserPassword;
    private ImageButton registerDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regster);

        registerUserName = findViewById(R.id.register_username);
        registerUserEmail = findViewById(R.id.register_email);
        registerUserTel = findViewById(R.id.register_tel);
        registerUserPassword = findViewById(R.id.register_password);
        registerDone = findViewById(R.id.register_done);

        // 返回
        registerBtBack = findViewById(R.id.register_bt_back);
        registerBtBack.setOnClickListener(v -> {
            finish();
        });


        // 注册事件
        registerDone.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

}
