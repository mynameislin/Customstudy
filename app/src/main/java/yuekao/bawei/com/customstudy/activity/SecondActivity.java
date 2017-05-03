package yuekao.bawei.com.customstudy.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import yuekao.bawei.com.customstudy.R;
import yuekao.bawei.com.customstudy.adapter.MyRecyclerViewAdapter;
import yuekao.bawei.com.customstudy.bean.Data;
import yuekao.bawei.com.customstudy.utils.GlideHuanCun;
import yuekao.bawei.com.customstudy.utils.RecycleViewDivider;

public class SecondActivity extends AppCompatActivity {
    private MyRecyclerViewAdapter my;
    private RecyclerView rcl;
    private TextView dingwei;
    private TextView qingchu;
    private String url="http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
            String sre= (String) msg.obj;
                Gson gson=new Gson();
                Data data = gson.fromJson(sre, Data.class);
                List<Data.DataBean> data1 = data.getData();
                if(my==null){
                    my = new MyRecyclerViewAdapter(data1,SecondActivity.this);
                    rcl.setAdapter(my);
                }

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //找控件
        initView();
        //得到数据
        initData();


    }

    private void initData() {
        OkHttpClient client=new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message=new Message();
                message.what=1;
                message.obj=string;
                handler.sendMessage(message);
            }
        });

    }

    private void initView() {
        rcl = (RecyclerView) findViewById(R.id.rcl);
        dingwei = (TextView) findViewById(R.id.dingwei);
        qingchu = (TextView) findViewById(R.id.qingchu);
        //设置recyclerView的样式
        rcl.setLayoutManager(new LinearLayoutManager(SecondActivity.this,LinearLayoutManager.VERTICAL,false));
        //设置RecyclerView的分割线
        rcl.addItemDecoration(new RecycleViewDivider(SecondActivity.this,LinearLayoutManager.HORIZONTAL,20,R.color.colorAccent));
        dingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,DituActivity.class);
                startActivity(intent);
            }
        });
        String cacheSize = GlideHuanCun.getInstance().getCacheSize(SecondActivity.this);
        Log.i("xxx",cacheSize);
        qingchu.setText("清楚缓存"+cacheSize);
        qingchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlideHuanCun.getInstance().clearImageDiskCache(SecondActivity.this);
                qingchu.setText("清楚缓存0.0KB");
            }
        });
    }
}
