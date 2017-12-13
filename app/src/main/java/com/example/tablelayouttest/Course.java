package com.example.tablelayouttest;

import org.litepal.crud.DataSupport;

/**
 * Created by hyt on 2017/12/1.
 */

public class Course extends DataSupport{
    private int day;
    private int clsNum;
    private int clsCont;
    private int color;
    private String clsName;

    public int getClsCont() {
        return clsCont;
    }

    public void setClsCont(int clsCont) {
        this.clsCont = clsCont;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public int getClsNum() {
        return clsNum;
    }

    public void setClsNum(int clsNum) {
        this.clsNum = clsNum;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
