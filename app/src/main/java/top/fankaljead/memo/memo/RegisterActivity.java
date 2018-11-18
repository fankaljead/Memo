package top.fankaljead.memo.memo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.fankaljead.memo.R;
import top.fankaljead.memo.data.User;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    @BindView(R.id.register_bt_back)
    ImageButton registerBtBack;
    @BindView(R.id.register_username)
    EditText registerUserName;
    @BindView(R.id.register_email)
    EditText registerUserEmail;
    @BindView(R.id.register_tel)
    EditText registerUserTel;
    @BindView(R.id.register_password)
    EditText registerUserPassword;
    @BindView(R.id.register_done)
    ImageButton registerDone;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regster);
        unbinder = ButterKnife.bind(this);
    }

    // 注册事件
    @OnClick(R.id.register_done)
    public void registerEvent() {
        try {
            User user = new User();
            user.setEmail(registerUserEmail.getText().toString());
            user.setName(registerUserName.getText().toString());
            user.setPassword(registerUserPassword.getText().toString());
            user.setTel(registerUserTel.getText().toString());
            user.setUuid(UUID.randomUUID().toString());
            Log.d(TAG, "registerEvent: " + user.toString());
            user.save();
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }

    // 返回
    @OnClick(R.id.register_bt_back)
    public void registerBack() {
        finish();
    }

}
