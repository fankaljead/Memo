package top.fankaljead.memo.memo;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import top.fankaljead.memo.R;
import top.fankaljead.memo.data.Note;
import top.fankaljead.memo.utils.DateHelper;
import top.fankaljead.memo.utils.StringUtil;

public class NoteActivity extends AppCompatActivity {

    public static final String EDIT_NOTE = "edit_note";
    private static final String TAG = "NoteActivity";
    private String userUuid;

    @BindView(R.id.add_note_bt_back)
    ImageButton addNoteBack;
    @BindView(R.id.add_note_content)
    EditText addNoteContent;
    @BindView(R.id.add_note_time_text)
    TextView addNoteTimeText;
    private Unbinder unbinder;
    Intent intent;

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        unbinder = ButterKnife.bind(this);
        // 添加备忘录返回，包括保存备忘录和回到MainActivity
//        addNoteBack = findViewById(R.id.add_note_bt_back);
//
//        addNoteContent = findViewById(R.id.add_note_content);

//        intent = getIntent();
//        note = (Note) intent.getSerializableExtra(NoteActivity.EDIT_NOTE);
//        Log.d(TAG, "intent note: " + note);
//        if (note != null) {
//            addNoteTimeText.setText(DateHelper.getFormatDate(note.getCreateTime()));
//            addNoteContent.setText(note.getContent());
//        } else {
//            note = new Note();
//            addNoteTime = new Date();
//            addNoteTimeText.setText(DateHelper.getFormatDate(addNoteTime));
//            note.setCreateTime(addNoteTime);
//        }
        userUuid = getIntent().getStringExtra(MainActivity.USER_UUID);
        Log.d(TAG, "setContent: " + userUuid);
        note = setContent();
        addNoteContent.setFocusable(true);
        Calendar calendar = Calendar.getInstance();
        addNoteTimeText.setText(String.valueOf(calendar.get(Calendar.YEAR)) + "年" + String.valueOf(calendar.get(Calendar.MONTH) + 1)
                + "月" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "日"
                + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + "时" + String.valueOf(calendar.get(Calendar.MINUTE)) + "分");
        addNoteContent.requestFocus();

//        InputMethodManager inputManager =
//
//                (InputMethodManager)addNoteContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//
//        inputManager.showSoftInput(addNoteContent, 0);

        Timer timer =new Timer();

        timer.schedule(new TimerTask() {

            @Override

            public void run() {

                InputMethodManager manager =
                        (InputMethodManager) addNoteContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                manager.showSoftInput(addNoteContent,0);

            }

        },200);


//        addNoteTimeText = findViewById(R.id.add_note_time_text);


//        addNoteBack.setOnClickListener(v -> {
//            Log.d(TAG, "save " + addNoteContent.getText().toString());
//            Intent intent = new Intent(NoteActivity.this, MainActivity.class);
////            save();
//            startActivity(intent);
//        });
    }

    @OnClick(R.id.add_note_bt_back)
    public void onSavedClick() {
        Log.d(TAG, "save " + addNoteContent.getText().toString());
        save();
        intent = new Intent(NoteActivity.this, MainActivity.class);

        startActivity(intent);
        finish();
    }

    private void save() {
        final String content = addNoteContent.getText().toString();

        if (content.length() != 0) {
            note.setContent(content);
            note.setUserUuid(userUuid);
            final Calendar calendar = Calendar.getInstance();
            note.setTagId(0);
            note.setCreateTime(String.valueOf(calendar.get(Calendar.YEAR)) + "年" + String.valueOf(calendar.get(Calendar.MONTH) + 1)
                    + "月" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "日"
                    + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + "时" + String.valueOf(calendar.get(Calendar.MINUTE)) + "分");
            note.setUuid(UUID.randomUUID().toString());
            System.out.println("save save");
            note.save();
        } else if (note.getContent() == null) {
            note.delete();
        }
    }



    private Note setContent() {
        final String uuid = getIntent().getStringExtra(StaticCommonData.UUID);
        if (StringUtil.isNull(uuid)) {
            return new Note();
        }
        final Note note = LitePal.where("uuid = \"" + uuid + "\"").find(Note.class).get(0);
//        String[] contentString = note.getContent().split("\n");
//        final int size = contentString.length;
//        for (int i = 0; i < size; i++) {
//            content.addEditTextAtIndex(i, contentString[i]);
//        }
//        addNoteTimeText.setText(note.getCreateTime());
        addNoteContent.setText(note.getContent());
        return note;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        save();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
