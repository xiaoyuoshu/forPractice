package com.yuo.mymemorandum;

import org.litepal.crud.DataSupport;

/**
 * Created by DELL on 2017/8/26.
 */

public class Memorandum extends DataSupport{
    private int id;
    private String tag;//标签
    private String title;//标题
    private String content;//内容
    private String begin_date;//开始日期
    private String end_date;//截止日期
    private int is_finished;//是否完成

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getBegin_date() {
        return begin_date;
    }
    public String getEnd_date() {
        return end_date;
    }
    public String getTag() {
        return tag;
    }
    public int getIs_finished() {
        return is_finished;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public void setIs_finished(int is_finished) {
        this.is_finished = is_finished;
    }
}
