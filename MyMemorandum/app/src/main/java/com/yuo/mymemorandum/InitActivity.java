package com.yuo.mymemorandum;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.Calendar;

public class InitActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private TimePicker timePicker;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        ActionBar ABar=getSupportActionBar();
        ABar.setDisplayHomeAsUpEnabled(true);
        title = (EditText)findViewById(R.id.memorandum_title);
        content = (EditText)findViewById(R.id.memorandum_content);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        Intent intent = getIntent();
        if(intent.hasExtra("edit")){
            int id = intent.getIntExtra("edit", 0);
            Memorandum memorandum = DataSupport.where("id == ?", Integer.toString(id)).find(Memorandum.class).get(0);
            title.setText(memorandum.getTitle());
            content.setText(memorandum.getContent());
            //2017年10月7日13时5分
            String[] split= memorandum.getEnd_date().split("[年月日时分]");
            datePicker.updateDate(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
            timePicker.setHour(Integer.parseInt(split[3]));
            timePicker.setMinute(Integer.parseInt(split[4]));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.init_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:{
                showDialog();
                break;
            }
            case R.id.init_item:{
                Intent intent = getIntent();
                if(intent.hasExtra("init")){
                    Memorandum memorandum = new Memorandum();
                    Calendar calendar = Calendar.getInstance();
                    String begin_date =
                            calendar.get(Calendar.YEAR) + "年"
                            + (calendar.get(Calendar.MONTH)+1) + "月"
                            + calendar.get(Calendar.DAY_OF_MONTH) + "日"
                            + calendar.get(Calendar.HOUR_OF_DAY) + "时"
                            + calendar.get(Calendar.MINUTE) + "分";
                    String end_date =
                            datePicker.getYear() + "年"
                            + (datePicker.getMonth()+1) + "月"
                            + datePicker.getDayOfMonth() + "日"
                            + timePicker.getHour() + "时"
                            + timePicker.getMinute() + "分";
                    memorandum.setTag("常规");
                    memorandum.setTitle(title.getText().toString());
                    memorandum.setContent(content.getText().toString());
                    memorandum.setBegin_date(begin_date);
                    memorandum.setEnd_date(end_date);
                    memorandum.setIs_finished(-1);
                    memorandum.save();
                    finish();
                }

                else if(intent.hasExtra("edit")){
                    int id = intent.getIntExtra("edit", 0);
                    Memorandum memorandum_pre = DataSupport.where("id == ?", Integer.toString(id)).find(Memorandum.class).get(0);
                    Memorandum memorandum = new Memorandum();
                    String end_date =
                            datePicker.getYear() + "年"
                                    + (datePicker.getMonth()+1) + "月"
                                    + datePicker.getDayOfMonth() + "日"
                                    + timePicker.getHour() + "时"
                                    + timePicker.getMinute() + "分";
                    memorandum.setTag("常规");
                    memorandum.setTitle(title.getText().toString());
                    memorandum.setContent(content.getText().toString());
                    memorandum.setBegin_date(memorandum_pre.getBegin_date());
                    memorandum.setEnd_date(end_date);
                    memorandum.setIs_finished(-1);
                    memorandum.updateAll("id = ?", Integer.toString(id));
                    finish();
                }

                else;
                //Toast.makeText(this, "Succeed", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(InitActivity.this);
        builder.setTitle("你确定要返回吗？");
        builder.setMessage("你可能会丢失尚未保存的内容！");
        builder.setCancelable(true);
        builder.setPositiveButton("是", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }
    @Override
    public void onBackPressed() {
        showDialog();
    }

}
