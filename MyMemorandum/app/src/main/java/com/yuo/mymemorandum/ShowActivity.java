package com.yuo.mymemorandum;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.Calendar;

public class ShowActivity extends AppCompatActivity {
    private int id;
    private TextView title, content, begin_date, end_date;
    private MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ActionBar ABar=getSupportActionBar();
        ABar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        id = intent.getIntExtra("edit",0);

        title = (TextView)findViewById(R.id.show_title);
        content = (TextView)findViewById(R.id.show_content);
        begin_date = (TextView)findViewById(R.id.show_begin_date);
        end_date = (TextView)findViewById(R.id.show_end_date);


        Memorandum memorandum = DataSupport.where("id == ?", Integer.toString(id)).find(Memorandum.class).get(0);
        title.setText(memorandum.getTitle());
        content.setText(memorandum.getContent());
        begin_date.setText("开始时间：" + memorandum.getBegin_date());
        end_date.setText("截止时间：" + memorandum.getEnd_date());

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:{
                finish();
                break;
            }
            case R.id.show_item:{
                Intent intent = new Intent(ShowActivity.this,InitActivity.class);
                intent.putExtra("edit", id);
                startActivity(intent);
                break;
            }
            case R.id.show_item_delete:{
                AlertDialog.Builder builder=new AlertDialog.Builder(ShowActivity.this);
                builder.setTitle("你确定要删除吗？");
                builder.setMessage("你可能会丢失尚未保存的内容！");
                builder.setCancelable(true);
                builder.setPositiveButton("是", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        id = intent.getIntExtra("edit",0);
                        DataSupport.deleteAll(Memorandum.class, "id = ?", Integer.toString(id));
                        finish();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
                break;
            }
            case R.id.show_item_isFinished:{
                Memorandum memorandum_pre = DataSupport.where("id == ?", Integer.toString(id)).find(Memorandum.class).get(0);
                Memorandum memorandum = new Memorandum();
                memorandum.setIs_finished(-memorandum_pre.getIs_finished());
                memorandum.updateAll("id = ?", Integer.toString(id));
                finish();
            }
            default:break;
        }
        return true;
    }
}
