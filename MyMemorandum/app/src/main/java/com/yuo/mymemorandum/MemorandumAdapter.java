package com.yuo.mymemorandum;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL on 2017/8/27.
 */

public class MemorandumAdapter extends RecyclerView.Adapter<MemorandumAdapter.ViewHolder> {
    private List<Memorandum> memorandumList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View memorandum_view;
        TextView title, content, begin_date, end_date, isFinished;

        public ViewHolder(View view){
            super(view);
            memorandum_view = view;
            title = (TextView)view.findViewById(R.id.item_title);
            content = (TextView)view.findViewById(R.id.item_content);
            begin_date = (TextView)view.findViewById(R.id.item_begin_date);
            end_date = (TextView)view.findViewById(R.id.item_end_date);
            isFinished = (TextView)view.findViewById(R.id.item_isFinished);
        }
    }

    public MemorandumAdapter(List<Memorandum> memoranda){
        memorandumList = memoranda;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memorandum_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.memorandum_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = viewHolder.getAdapterPosition();
                Memorandum memorandum = memorandumList.get(position);
                Intent intent = new Intent(parent.getContext(),ShowActivity.class);
                intent.putExtra("edit",memorandum.getId());
                parent.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Memorandum memorandum = memorandumList.get(position);
        holder.title.setText(memorandum.getTitle());
        holder.content.setText(memorandum.getContent());
        holder.begin_date.setText("开始时间：" + memorandum.getBegin_date());
        holder.end_date.setText("截止时间：" + memorandum.getEnd_date());
        holder.isFinished.setText((memorandum.getIs_finished()>=0?"已完成":"未完成"));
        holder.isFinished.setTextColor(memorandum.getIs_finished()>=0? Color.GREEN:Color.RED);
    }

    @Override
    public int getItemCount() {
        return memorandumList.size();
    }
}
