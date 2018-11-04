package top.fankaljead.memo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.Serializable;
import java.util.List;

import top.fankaljead.memo.R;
import top.fankaljead.memo.adapters.base.ItemEntity;
import top.fankaljead.memo.adapters.base.ItemType;
import top.fankaljead.memo.data.Note;
import top.fankaljead.memo.memo.NoteActivity;
import top.fankaljead.memo.utils.DateHelper;

//public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
public class NoteAdapter extends BaseMultiItemQuickAdapter<ItemEntity, BaseViewHolder> {


//    private Context mContext;
//    private List<Note> mNoteList;
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public CardView cardView;
//        public TextView noteContent;
//        public TextView noteTime;
//        public LinearLayout llItem;
//
//        public ViewHolder(View view) {
//            super(view);
//            cardView = (CardView) view;
//            noteContent = view.findViewById(R.id.note_content);
//            noteTime = view.findViewById(R.id.note_time);
//            llItem = view.findViewById(R.id.ll_item);
//        }
//
//    }

//    public NoteAdapter(List<Note> mNoteList) {
//        this.mNoteList = mNoteList;
//    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (mContext == null) {
//            mContext = parent.getContext();
//        }
//        View view = LayoutInflater.from(mContext).inflate(R.layout.note_item, parent, false);
//
//        ViewHolder holder = new ViewHolder(view);
//        holder.noteContent.setOnClickListener(v -> {
//            int position = holder.getAdapterPosition();
////            Intent intent = new Intent(mContext, NoteActivity.class);
////            intent.putExtra(NoteActivity.EDIT_NOTE, mNoteList.get(position));
////            mContext.startActivity(intent);
//            Toast.makeText(mContext, "click " + position, Toast.LENGTH_LONG).show();
//        });
//
//        return holder;
//    }

    @Override
    protected void convert(BaseViewHolder helper, ItemEntity item) {
        switch (helper.getItemViewType()){
            case ItemType.TEXT_AND_TITLE:
                final String time = item.getField(MainItemField.TIME);
                final String content = item.getField(MainItemField.TEXT);
                Log.d(TAG, "convert: time" + time);
                Log.d(TAG, "convert: content" + content);
                helper.setText(R.id.note_time, time);
                if(content.length() > 7){
                    helper.setText(R.id.note_content, content.substring(0,7) + "...");
                }
                else {
                    helper.setText(R.id.note_content, content);
                }
                break;
            default:
                break;
        }
    }

    public NoteAdapter(List<ItemEntity> data) {
        super(data);
        addItemType(ItemType.TEXT_AND_TITLE, R.layout.note_item);
    }
//    @Override
//    protected void convert(BaseViewHolder helper, ItemEntity item) {
//        switch (helper.getItemViewType()){
//            case ItemType.TEXT_AND_TITLE:
//                final String title = item.getField(MainItemField.TITLE);
//                final String time = item.getField(MainItemField.TIME);
//                final String content = item.getField(MainItemField.TEXT);
//                helper.setText(R.id.title, title);
//                helper.setText(R.id.time, time);
//                if(content.length() > 7){
//                    helper.setText(R.id.content, content.substring(0,7) + "...");
//                }
//                else {
//                    helper.setText(R.id.content, content);
//                }
//                break;
//            default:
//                break;
//        }
//    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Note note = mNoteList.get(position);
//        holder.noteContent.setText(note.getContent());
////        holder.noteTime.setText(note.getCreateTime().toString());
//        holder.noteTime.setText(DateHelper.getFormatDate(note.getCreateTime()));
//    }

//    @Override
//    public int getItemCount() {
//        return mNoteList.size();
//    }
//
//    public void addItem(int position, Note note){
//        mNoteList.add(position, note);
//        notifyItemInserted(position);
//    }
//
//    public void removeItem(int position){
//        mNoteList.remove(position);
//        notifyItemRemoved(position);
//    }



}
