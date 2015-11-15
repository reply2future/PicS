package com.justdoit.pics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.justdoit.pics.R;
import com.justdoit.pics.fragment.MainFragment;
import com.justdoit.pics.global.App;
import com.justdoit.pics.global.Constant;

/**
 * 用来跳转到需要的测试页面
 * TODO:等待数据接口
 * Created by mengwen on 2015/10/26.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private App mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }



    private void initView() {
        mApp = (App)getApplicationContext();

        toolbar = (Toolbar) findViewById(R.id.toolbar);




        setSupportActionBar(toolbar);
        if (!App.isLogin()) { // 未登录显示默认用户头像
            toolbar.setNavigationIcon(R.mipmap.user_info_def_avatar);
        } else { // 登录了就显示用户头像 TODO:用户头像获取
            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (App.isLogin()) {
                    Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.USER_ID_NAME, App.getUserId());
                    bundle.putString(Constant.USERNAME_NAME, App.getUserName());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    // 带上要跳转到UserInfo的标识
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.putExtra(Constant.ACTION_KEY, "UserInfoActivity");
                    startActivity(intent);
                }

            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, MainFragment.newInstance(MainFragment.NORMAL),"MainFragment")
                .commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
