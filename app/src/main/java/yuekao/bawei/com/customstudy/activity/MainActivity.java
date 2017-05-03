package yuekao.bawei.com.customstudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yuekao.bawei.com.customstudy.R;
import yuekao.bawei.com.customstudy.view.ZFXView;

public class MainActivity extends AppCompatActivity {

    private ZFXView zfx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zfx = (ZFXView) findViewById(R.id.zfx);
       zfx.setOnCircleClickListener(new ZFXView.OnsrolleyClickLiener() {
           @Override
           public void srolleyClickLiener(String text) {
               if(text.equals("圆环外")){
                   Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                   startActivity(intent);
               }
           }
       });
    }
}
