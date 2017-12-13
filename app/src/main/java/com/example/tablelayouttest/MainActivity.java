package com.example.tablelayouttest;

import android.annotation.TargetApi;
import android.content.ClipboardManager;
import android.content.Context;
import android.media.MediaCodec;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private String strId = "151080123";
    private String strPassword = "669498";
    private String strViewstate = "dDwyODE2NTM0OTg7Oz7b4TfPH9kfTwPvgA6wFjRDk2mkHg==";
    private String strViewTor = "92719903";
    private String  strRbtList = "%D1%A7%C9%FA";
    private String button1 = "";
    private String hidPdrs = "";
    private String  hidsc = "";
    private String lblanguage = "";
    private String txtsecretcode = "";

    private static final String[]GRID_TITLE = {"9月","星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
    private GridLayout mGridLayout;
    private int mGridMinWidth;

    private static final int COURSE_NUM = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridLayout = (GridLayout) findViewById(R.id.main_gridlayout);
        //初始化表格的最小宽度
        mGridMinWidth = getStreenWidth()/15;
        //设置课程的数量
        setUpCourseScheduleTitle();
        setUpCourseScheduleNum();
        //获取课表
        getScheduleFromWeb();
    }
    //设置首行
    @TargetApi(16)
    private void setUpCourseScheduleTitle(){
        for (int i = 0; i<GRID_TITLE.length; i++){
            String title = GRID_TITLE[i];
            //创建
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setGravity(Gravity.CENTER);
            //设置月份的列
            if (i == 0){
                params.width = mGridMinWidth;
            }else {
                params.width = mGridMinWidth*2;
            }
            //个人设置为30dp
            params.height = (int) getResources().getDimension(R.dimen.course_schedule_height);
            //创建添加到表格的view
            TextView tvTitle = new TextView(this);
            tvTitle.setText(title);
            tvTitle.setGravity(Gravity.CENTER);
            tvTitle.setTextColor(getResources().getColor(android.R.color.background_dark));
            tvTitle.setBackground(getResources().getDrawable(R.drawable.shape));
            //动态添加进Gridtable
            mGridLayout.addView(tvTitle,params);
        }
    }
    //获取屏幕宽
    private int getStreenWidth(){
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }
    //设置课序
    @TargetApi(16)
    private void setUpCourseScheduleNum(){
        //利用代码确定View的位置
        for (int i = 1; i<=COURSE_NUM;i++){
            //GridLayout行列，从0开始计算，所以第一个num在1，0上
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            //设置高度和宽度
            params.width = mGridMinWidth;
            params.height = (int) getResources().getDimension(R.dimen.course_schedule_height);
            //设置view在表格中的位置，spec用来设置view在表格中的位置，view占几个单元格，且view在单元格的状态
            //（是否占满单元格的行，或者占满单元格的列）
            params.rowSpec = GridLayout.spec(i);//行的位置
            params.columnSpec = GridLayout.spec(0);//列的位置
            //上面这两行用于设置表格最左边的第几节课那一列，这个效果相当于竖直排列
            //创建view添加到表格
            TextView tvTitle = new TextView(this);
            tvTitle.setText(i+"");
            tvTitle.setGravity(Gravity.CENTER);
            tvTitle.setTextColor(getResources().getColor(android.R.color.background_dark));
            tvTitle.setBackground(getResources().getDrawable(R.drawable.shape));
            mGridLayout.addView(tvTitle,params);
        }

    }
    //获取html
    private void getScheduleFromWeb(){
        //利用okhttp封装获取课表
        OkHttpUtils
                .post()
                .url("http://210.38.162.117/(gsox2muncbsmwifbasaxnj45)/default2.aspx")
                .addParams("__VIEWSTATE",strViewstate)
                .addParams("__VIEWSTATEGENERATOR",strViewTor)
                .addParams("Button1",button1).addParams("hidPdrs",hidPdrs).addParams("hidsc",hidsc).addParams("lbLanguage",lblanguage)
                .addParams("RadioButtonList1",strRbtList).addParams("TextBox2",strPassword).addParams("txtSecretCode",txtsecretcode)
                .addParams("txtUserName",strId)
                .addHeader("Host","210.38.162.117")
                .addHeader("Referer","http://210.38.162.117/(gsox2muncbsmwifbasaxnj45)/default2.aspx")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        OkHttpUtils
                                .get()
                                .url("http://210.38.162.117/(gsox2muncbsmwifbasaxnj45)/xskbcx.aspx?xh=151080123&xm=%BA%E9%D2%F8%CC%CE&gnmkdm=N121603")
                                .addHeader("Referer", "http://210.38.162.117/(gsox2muncbsmwifbasaxnj45)/xs_main.aspx?xh=151080123")
                                .addHeader("Host", "210.38.162.117")
                                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Request request, Exception e) {

                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        String string = response;
                                        OkHttpUtils
                                                .get()
                                                .url("http://210.38.162.117/(gsox2muncbsmwifbasaxnj45)/xskbcx.aspx?xh=151080123&xm=%BA%E9%D2%F8%CC%CE&gnmkdm=N121603")
                                                .addHeader("Referer", "http://210.38.162.117/(gsox2muncbsmwifbasaxnj45)/xs_main.aspx?xh=151080123")
                                                .addHeader("Host", "210.38.162.117")
                                                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
                                                .build()
                                                .execute(new StringCallback() {
                                                    @Override
                                                    public void onError(Request request, Exception e) {

                                                    }

                                                    @Override
                                                    public void onResponse(String response) {
                                                        OkHttpUtils
                                                                .get()
                                                                .url("http://210.38.162.117/(gsox2muncbsmwifbasaxnj45)/xskbcx.aspx?xh=151080123&xm=%BA%E9%D2%F8%CC%CE&gnmkdm=N121603")
//                                                                .addParams("xqd", "1")
//                                                                .addParams("xnd", "2017-2018")
//                                                                .addParams("__VIEWSTATEGENERATOR", "55530A43")
//                                                                .addParams("__VIEWSTATE", "dDwtMTY3ODA2Njg2OTt0PDtsPGk8MT47PjtsPHQ8O2w8aTwxPjtpPDI+O2k8ND47aTw3PjtpPDk+O2k8MTE+O2k8MTM+O2k8MTU+O2k8MjE+O2k8MjM+O2k8MjU+O2k8Mjc+O2k8Mjk+O2k8MzE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPFxlOz4+Oz47Oz47dDx0PHA8cDxsPERhdGFUZXh0RmllbGQ7RGF0YVZhbHVlRmllbGQ7PjtsPHhuO3huOz4+Oz47dDxpPDM+O0A8MjAxNy0yMDE4OzIwMTYtMjAxNzsyMDE1LTIwMTY7PjtAPDIwMTctMjAxODsyMDE2LTIwMTc7MjAxNS0yMDE2Oz4+O2w8aTwwPjs+Pjs7Pjt0PHQ8OztsPGk8MD47Pj47Oz47dDxwPHA8bDxUZXh0Oz47bDzlrablj7fvvJoxNTEwODAxMjM7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOWnk+WQje+8mua0qumTtua2mzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85a2m6Zmi77ya5Zyw55CG56eR5a2m5LiO5peF5ri45a2m6ZmiOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzkuJPkuJrvvJrlnLDnkIbkv6Hmga/np5HlraY7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOihjOaUv+ePre+8muWcsOeQhjE1MDPnj607Pj47Pjs7Pjt0PDtsPGk8MT47PjtsPHQ8QDA8Ozs7Ozs7Ozs7Oz47Oz47Pj47dDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs+PjtsPGk8MT47PjtsPHQ8QDA8Ozs7Ozs7Ozs7Oz47Oz47Pj47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE+O2k8MD47aTwwPjtsPD47Pj47Pjs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8cDxwPGw8UGFnZUNvdW50O18hSXRlbUNvdW50O18hRGF0YVNvdXJjZUl0ZW1Db3VudDtEYXRhS2V5czs+O2w8aTwxPjtpPDA+O2k8MD47bDw+Oz4+Oz47Ozs7Ozs7Ozs7Pjs7Pjt0PEAwPHA8cDxsPFBhZ2VDb3VudDtfIUl0ZW1Db3VudDtfIURhdGFTb3VyY2VJdGVtQ291bnQ7RGF0YUtleXM7PjtsPGk8MT47aTwwPjtpPDA+O2w8Pjs+Pjs+Ozs7Ozs7Ozs7Oz47Oz47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE+O2k8MD47aTwwPjtsPD47Pj47Pjs7Ozs7Ozs7Ozs+Ozs+Oz4+Oz4+Oz5K9KDHEPdzdB0DzmiGHPfq/IiIQw==")
//                                                                .addParams("__EVENTTARGET", "xnd")
//                                                                .addParams("__EVENTARGUMENT", "")
                                                                .addHeader("Referer", "http://210.38.162.117/(gsox2muncbsmwifbasaxnj45)/xskbcx.aspx?xh=151080123&xm=%BA%E9%D2%F8%CC%CE&gnmkdm=N121603")
                                                                .addHeader("Host", "210.38.162.117")
                                                                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
                                                                .build()
                                                                .execute(new StringCallback() {
                                                                    @Override
                                                                    public void onError(Request request, Exception e) {

                                                                    }

                                                                    @Override
                                                                    public void onResponse(String response) {
                                                                        parseStringHtml(response);
//                                                                        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                                                                        cm.setText(response);
                                                                    }
                                                                });
                                                    }
                                                });

                                    }
                                });


                    }
                });
    }
    //解析课表的html
    private void parseStringHtml(String html){
        final List<Course> courses = new ArrayList<>();
        String tdStr;
        Document document = Jsoup.parse(html);
        Element elementTable1 = document.getElementById("Table1");
        Elements trs = elementTable1.select("tr");
        //去除前两组tr
        trs.remove(0);
        trs.remove(0);
        //遍历tr
        for (int i = 0; i<trs.size();i++){

            Element tr = trs.get(i);
            Elements tds = tr.select("td[align]");

            for (int j = 0; j < tds.size(); j++){
//                if (i == 8 && j==6){
//                    String a = "";
//                }

                Element td = tds.get(j);
                tdStr = td.text();
                //解析td
                if (tdStr.length() != 1){
                    String nameAndCls = regularSchedlue(tdStr);
                    Course course = new Course();
                    course.setClsName(nameAndCls);
                    course.setClsNum(i+1);
                    course.setDay(j+1);
                    if (td.attr("rowspan")!=""){
                        course.setClsCont(Integer.valueOf(td.attr("rowspan")));
                    }
                    else{
                        course.setClsCont(1);
                    }

                    courses.add(course);
                }
            }
        }
//        List<Course> coursess = new ArrayList<Course>();
//        coursess = courses;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                showSche(courses);
            }
        });
    }
    //正则
    private String regularSchedlue(String str){
        String strCls = "";
        Pattern patternName = Pattern.compile("^.+?(\\s{1})");
        Matcher matcherName = patternName.matcher(str);
        matcherName.find();
        String strName = matcherName.group(0);
        String s =  strName;
        Pattern patternCls = Pattern.compile("\\s\\w*\\d$");
        Matcher matcherCls = patternCls.matcher(str);
        if (matcherCls.find()){
            strCls = matcherCls.group(0);
        }

        return strName+"@"+strCls;
    }
    //显示表
    @TargetApi(16)
    private void showSche(List<Course> Courses){
        for (int i = 0; i < Courses.size(); i++){
            Course course = Courses.get(i);
            int row = course.getClsNum();
            int col = course.getDay();
            int size = course.getClsCont();
            //设定在表格中位置及大小
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                    GridLayout.spec(row,2),
                    GridLayout.spec(col)
            );
            //设置宽高
            params.width = mGridMinWidth*2;
            params.height = (int) getResources().getDimension(R.dimen.course_schedule_height);
            params.setGravity(Gravity.FILL);

            TextView textView = new TextView(this);
            textView.setBackground(getResources().getDrawable(R.drawable.shape));
            textView.setTextColor(getResources().getColor(android.R.color.background_dark));
            textView.setText(course.getClsName());
//            textView.setGravity(Gravity.CENTER);
            mGridLayout.addView(textView,params);
        }
    }
}

