package com.justdoit.pics.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.etiennelawlor.quickreturn.library.enums.QuickReturnViewType;
import com.etiennelawlor.quickreturn.library.listeners.SpeedyQuickReturnRecyclerViewOnScrollListener;
import com.justdoit.pics.R;
import com.justdoit.pics.adapater.mRecyclerViewAdapter;
import com.justdoit.pics.global.App;
import com.justdoit.pics.global.Constant;

import org.w3c.dom.Text;

/**
 * 用来跳转到需要的测试页面
 * TODO:完成测试后，就要将recyclerview的页面设置为主页面
 * Created by mengwen on 2015/10/26.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private RecyclerView content_container;
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
        content_container =(RecyclerView)findViewById(R.id.content_container);
        content_container.setLayoutManager(new LinearLayoutManager(this));
        content_container.setHasFixedSize(true);
        content_container.setAdapter(new mRecyclerViewAdapter());
        SpeedyQuickReturnRecyclerViewOnScrollListener scrollListener = new SpeedyQuickReturnRecyclerViewOnScrollListener.Builder(this, QuickReturnViewType.BOTH)
                .header(findViewById(R.id.header_tv))
                .footer(findViewById(R.id.footer_tv))
                .slideHeaderUpAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_header_up))
                .slideHeaderDownAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_header_down))
                .slideFooterUpAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_footer_up))
                .slideFooterDownAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_footer_down))
                .build();
        content_container.addOnScrollListener(scrollListener);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 如果登录了就跳转到用户信息页面，否则登录页面
                if (App.isLogin()) {
                    startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
                } else {
                    // 带上要跳转到UserInfo的标识
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.putExtra(Constant.ACTION_KEY, "UserInfoActivity");
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
