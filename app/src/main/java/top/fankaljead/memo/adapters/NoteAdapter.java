package top.fankaljead.memo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import top.fankaljead.memo.R;
import top.fankaljead.memo.data.Note;
import top.fankaljead.memo.utils.DateHelper;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private Context mContext;
    private List<Note> mNoteList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView noteContent;
        public TextView noteTime;
        public LinearLayout llItem;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            noteContent = view.findViewById(R.id.note_content);
            noteTime = view.findViewById(R.id.note_time);
            llItem = view.findViewById(R.id.ll_item);
        }
    }

    public NoteAdapter(List<Note> mNoteList) {
        this.mNoteList = mNoteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = mNoteList.get(position);
        holder.noteContent.setText(note.getContent());
//        holder.noteTime.setText(note.getCreateTime().toString());
        holder.noteTime.setText(DateHelper.getFormatDate(note.getCreateTime()));
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public void addItem(int position, Note note){
        mNoteList.add(position, note);
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        mNoteList.remove(position);
        notifyItemRemoved(position);
    }

}
