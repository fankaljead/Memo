package top.fankaljead.memo.memo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.fankaljead.memo.R;
import top.fankaljead.memo.data.User;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    public static final String LOGIN_USER = "login_user";
    @BindView(R.id.login_account_input)
    EditText accountEdit;
    @BindView(R.id.login_password_input)
    EditText passwordEdit;
    String accountText;
    String passwordAccount;
    Intent intent;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        checkLogin();
    }

    // 注册
    @OnClick(R.id.button_register)
    public void register() {
        intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    // 登录
    @OnClick(R.id.button_login)
    public void login() {

        accountText = accountEdit.getText().toString();
        passwordAccount = passwordEdit.getText().toString();

        List<User> userss = LitePal.findAll(User.class);
        Log.d(TAG, "login: users size" + userss.size());
        for (int i = 0; i < userss.size(); i++) {
            Log.d(TAG, "login: user + " + userss.get(i).getName());
        }

        List<User> users = LitePal.where("email = ?", accountText).find(User.class);
        if (users.size() == 0) {
            Toast.makeText(this, "您的账号不存在!", Toast.LENGTH_SHORT).show();
        } else if (users.size() == 1) {
            User user = users.get(0);
            String dataPassword = user.getPassword();
            if (!dataPassword.equals(passwordAccount)) {
                Toast.makeText(this, "您的密码有误!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                user.setIsLogin(User.LOGIN);
                user.save();
                intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra(LOGIN_USER, user);
                startActivity(intent);
            }
        }
    }


    public boolean checkLogin() {
        List<User> users = LitePal.where("isLogin=?", String.valueOf(User.LOGIN)).find(User.class);
        Log.d(TAG, "checkLogin: " + users.size());
        if (users.size() == 0) {
            return false;
        } else if (users.size() == 1) {
            Log.d(TAG, "checkLogin: " + users.get(0).getIsLogin());
            intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(LOGIN_USER, users.get(0));
            startActivity(intent);
            return true;
        } else {
            return false;
        }
    }
}
