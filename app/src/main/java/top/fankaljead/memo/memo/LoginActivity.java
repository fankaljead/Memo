package top.fankaljead.memo.memo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

import top.fankaljead.memo.R;

public class LoginActivity extends AppCompatActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private String accountText;
    private String passwordAccount;
    private Button btLogin;
    private Button btRegister;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit = findViewById(R.id.login_account_input);
        passwordEdit = findViewById(R.id.login_password_input);
        btLogin = findViewById(R.id.button_login);
        btRegister = findViewById(R.id.button_register);

        accountText = accountEdit.getText().toString();
        passwordAccount = passwordEdit.getText().toString();


        btLogin.setOnClickListener(v -> {
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });

        PrettyTime time = new PrettyTime();
        Date date = new Date();
        btRegister.setOnClickListener(v -> {
            Toast.makeText(this,  time.format(date), Toast.LENGTH_LONG).show();
        });

    }
}
