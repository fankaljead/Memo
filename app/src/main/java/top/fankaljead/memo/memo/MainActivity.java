package top.fankaljead.memo.memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import top.fankaljead.memo.R;
import top.fankaljead.memo.adapters.MainItemEntity;
import top.fankaljead.memo.adapters.NoteAdapter;
import top.fankaljead.memo.adapters.base.ItemEntity;
import top.fankaljead.memo.data.Note;
import top.fankaljead.memo.data.User;
import top.fankaljead.memo.utils.DateHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private PopupWindow deletePop;
    private TextView cancel;
    private TextView delete;
    private List<ItemEntity> mainItemEntities;
    private RecyclerView recyclerView;
    private SearchView noteSearchView;
    private FloatingActionButton tagAdd;
    private TextView mail;
    private TextView name;

    private Note[] notes = {
//            new Note(0, 0, new Date(), "你好"),
//            new Note(1, 0, new Date(), "hello"),
//            new Note(2, 0, new Date(), "halo"),
//            new Note(3, 0, new Date(), "a lo hao"),

//            new Note( 0, new Date(), "你好"),
//            new Note( 0, new Date(), "hello"),
//            new Note( 0, new Date(), "halo"),
//            new Note( 0, new Date(), "a lo hao"),
    };

    private List<Note> noteList = new ArrayList<>();
    private NoteAdapter adapter;
    Intent intent;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = getIntent();
        user= (User) intent.getSerializableExtra(LoginActivity.LOGIN_USER);
        Log.d(TAG, "onCreate: user" + user.getName());


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navView.setCheckedItem(R.id.nav_work);
        navView.setNavigationItemSelectedListener(v -> {
            mDrawerLayout.closeDrawers();
            return true;
        });


        // 处理添加note事件
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            // 可以用作消息提示
//            Snackbar.make(v, "Data deleted", Snackbar.LENGTH_LONG)
//                    .setAction("Undo", v2 -> {
//                        Toast.makeText(MainActivity.this, "Fab clicked", Toast.LENGTH_LONG).show();
//                    }).show();
            intent = new Intent(MainActivity.this, NoteActivity.class);
            startActivity(intent);
            finish();
        });

//        initialNotes();
        initData();

//        navView.getHeaderView(0)
        mail = navView.getHeaderView(0).findViewById(R.id.mail);
        name = navView.getHeaderView(0).findViewById(R.id.username);
        mail.setText(user.getEmail());
        name.setText(user.getName());

        navView.getHeaderView(0).setOnClickListener(v -> {
            logout(v, user);
        });
        recyclerView = findViewById(R.id.recycler_view);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteAdapter(mainItemEntities);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                LogUtil.v(String.valueOf(position));
                intent = new Intent(MainActivity.this, NoteActivity.class);
//                intent.putExtra(NoteActivity.EDIT_NOTE, noteList.get(position));
                intent.putExtra(StaticCommonData.UUID, noteList.get(position).getUuid());
                startActivity(intent);
                finish();
            }


            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showDeletePop(view, position);
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        noteSearchView = findViewById(R.id.note_searchview);
        noteSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchData(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mainItemEntities.clear();
//                mainItemEntities = new ArrayList<>();
//                noteList = LitePal.findAll(Note.class, );
                searchData(s);
                return false;
            }
        });

        tagAdd = findViewById(R.id.tag_add);
        tagAdd.setOnClickListener(v -> {
            menuItemSelect();
        });

    }

    // 退出登录
    private void logout(View v, User user) {
        Log.d(TAG, "checkLogin: " + user.getIsLogin());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("退出登录");    //设置对话框标题
//        builder.setIcon(android.R.drawable.btn_star);   //设置对话框标题前的图标

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this, "你输入的是: " + edit.getText().toString(), Toast.LENGTH_SHORT).show();
                user.setIsLogin(User.UN_LOGIN);
                user.updateAll();
                Log.d(TAG, "checkLogin: lick " + user.getIsLogin());
                List<User> users = LitePal.findAll(User.class);
                for (User u: users
                     ) {
                    Log.d(TAG, "checkLogin: get users " + u.getName() + " " + u.getIsLogin());
                }
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this, "你点了取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
        AlertDialog dialog = builder.create();  //创建对话框
        dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
        dialog.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.nav_work:
                menuItemSelect();
                break;

            default:
        }

        return true;
    }


    private void initialNotes() {
//        for (int i = 0; i < notes.length; i++) {
//            notes[i].save();
////            noteList.add(notes[i]);
//        }

        noteList = LitePal.findAll(Note.class);
//        System.out.println("noteList.size(): " + noteList.size());
    }

    @Override
    protected void onPause() {
        super.onPause();
        noteList = LitePal.findAll(Note.class);
    }

    private void showDeletePop(View view, final int index){
        if(deletePop == null){
            View popView = getLayoutInflater().inflate(R.layout.pop_delete, null);
            deletePop = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
            deletePop.setBackgroundDrawable(new ColorDrawable());
            deletePop.setOutsideTouchable(true);
            cancel = popView.findViewById(R.id.cancel);
            delete = popView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LitePal.deleteAll(Note.class, "uuid = \"" + LitePal.limit(1).offset(index).find(Note.class).get(0).getUuid() + "\"");

                    mainItemEntities.remove(index);
//                    final String string = String.valueOf(LitePal.count(Memo.class)) + "个通讯录";
//                    memoNum.setText(string);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    deletePop.dismiss();
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletePop.dismiss();
                }
            });
        }
        deletePop.showAsDropDown(view);
    }

    private void initData(){
        mainItemEntities = new ArrayList<>();
        noteList = LitePal.findAll(Note.class);
        final int size = noteList.size();
        for (int i = 0; i < size; i++){
            mainItemEntities.add(new MainItemEntity(noteList.get(i).getCreateTime(), noteList.get(i).getContent()));
        }
        adapter = new NoteAdapter(mainItemEntities);
    }

    private void searchData(String s) {
        Log.d(TAG, "onQueryTextChange: noteList + " + s);
        noteList = LitePal.where("content like ?", "%" + s + "%").find(Note.class);
        Log.d(TAG, "onQueryTextChange: noteList + " + noteList.size());
        final int size = noteList.size();
        for (int i = 0; i < size; i++){
            mainItemEntities.add(new MainItemEntity(noteList.get(i).getCreateTime(), noteList.get(i).getContent()));
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    private void menuItemSelect() {
        Log.d(TAG, "menuItemSelect: menuitem clicked");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("标签名称");    //设置对话框标题
//        builder.setIcon(android.R.drawable.btn_star);   //设置对话框标题前的图标
        final EditText edit = new EditText(this);
        builder.setView(edit);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你输入的是: " + edit.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你点了取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
        AlertDialog dialog = builder.create();  //创建对话框
        dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
        dialog.show();
    }
}

