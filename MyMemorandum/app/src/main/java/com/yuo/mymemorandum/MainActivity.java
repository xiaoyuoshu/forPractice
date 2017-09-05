package com.yuo.mymemorandum;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List<Memorandum> memorandumList = new ArrayList<>();
    private DataSupport dataSupport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,InitActivity.class);
                intent.putExtra("init",true);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        memorandumList = DataSupport.findAll(Memorandum.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MemorandumAdapter memorandumAdapter = new MemorandumAdapter(memorandumList);
        recyclerView.setAdapter(memorandumAdapter);
        ProgressBar progressbar = (ProgressBar)findViewById(R.id.probar);
        int progress = 0;
        for (Memorandum m:memorandumList) {
            if(m.getIs_finished()>0) progress += 1;
        }
        progressbar.setMax(memorandumList.size());
        progressbar.setProgress(progress);
        TextView textView = (TextView)findViewById(R.id.finished_num);
        textView.setText("完成情况："  + progress + "/" + memorandumList.size());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            memorandumList = DataSupport.findAll(Memorandum.class);
            refresh();
        } else if (id == R.id.nav_finished) {
            memorandumList = DataSupport.where("is_finished > ?", "0").find(Memorandum.class);
            refresh();
        } else if (id == R.id.nav_unfinished) {
            memorandumList = DataSupport.where("is_finished < ?", "0").find(Memorandum.class);
            refresh();
        } else if (id == R.id.nav_default) {
            memorandumList = DataSupport.findAll(Memorandum.class);
            refresh();
        } else if (id == R.id.nav_end_time) {
            memorandumList = DataSupport.order("end_date").find(Memorandum.class);
            refresh();
        } else if (id == R.id.nav_title) {
            memorandumList = DataSupport.order("title").find(Memorandum.class);
            refresh();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void refresh(){
        onResume();
    }

}
