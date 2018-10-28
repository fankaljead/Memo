package top.fankaljead.memo.memo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;

import top.fankaljead.memo.R;
import top.fankaljead.memo.utils.DateHelper;

public class NoteActivity extends AppCompatActivity {

    private ImageButton addNoteBack;
    private EditText addNoteContent;
    private TextView addNoteTimeText;
    private Date addNoteTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // 添加备忘录返回，包括保存备忘录和回到MainActivity
        addNoteBack = findViewById(R.id.add_note_bt_back);
        addNoteBack.setOnClickListener(v -> {
            finish();
        });

        addNoteContent = findViewById(R.id.add_note_content);

        addNoteContent.setFocusable(true);

        addNoteContent.requestFocus();

        InputMethodManager inputManager =

                (InputMethodManager)addNoteContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.showSoftInput(addNoteContent, 0);

        addNoteTimeText = findViewById(R.id.add_note_time_text);
        addNoteTime = new Date();
        addNoteTimeText.setText(DateHelper.getFormatDate(addNoteTime));

    }
}
