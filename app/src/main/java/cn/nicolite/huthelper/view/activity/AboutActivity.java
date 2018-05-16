package cn.nicolite.huthelper.view.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import cn.nicolite.huthelper.R;
import cn.nicolite.huthelper.base.activity.BaseActivity;
import cn.nicolite.huthelper.model.Constants;
import cn.nicolite.huthelper.utils.ToastUtil;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 关于页面
 * Created by nicolite on 17-10-22.
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.version)
    TextView version;

    @Override
    protected void initConfig(Bundle savedInstanceState) {
        setImmersiveStatusBar(true);
        setDeepColorStatusBar(true);
        setSlideExit(true);
    }

    @Override
    protected void initBundleData(Bundle bundle) {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void doBusiness() {
        toolbarTitle.setText("关于");

        Glide
                .with(this)
                .load(R.drawable.logo256)
                .bitmapTransform(new CropCircleTransformation(this))
                .skipMemoryCache(true)
                .dontAnimate()
                .into(logo);

        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
            version.setText(String.valueOf(pi.versionName + " (" + pi.versionCode + ")"));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.toolbar_back, R.id.help, R.id.permission_message, R.id.blog,
            R.id.openSource, R.id.rating})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.help:
                Bundle bundle = new Bundle();
                bundle.putInt("type", WebViewActivity.TYPE_HELP);
                bundle.putString("url", Constants.HELP);
                bundle.putString("title", "帮助");
                startActivity(WebViewActivity.class, bundle);
                break;
            case R.id.permission_message:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("type", WebViewActivity.TYPE_PERMISSION);
                bundle1.putString("url", Constants.PERMISSON);
                bundle1.putString("title", "软件许可协议");
                startActivity(WebViewActivity.class, bundle1);
                break;
            case R.id.blog:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type", WebViewActivity.TYPE_BLOG);
                bundle2.putString("url", Constants.BLOG);
                bundle2.putString("title", "NICOLITE");
                startActivity(WebViewActivity.class, bundle2);
                break;
            case R.id.openSource:
                Bundle bundle3 = new Bundle();
                bundle3.putInt("type", WebViewActivity.TYPE_OPEN_SOURCE);
                bundle3.putString("url", Constants.OPEN_SOURCE);
                bundle3.putString("title", "HutHelper");
                startActivity(WebViewActivity.class, bundle3);
                break;
            case R.id.rating:
                try {
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                    ToastUtil.showToastShort("您的手机没有安装Android应用市场");
                    e.printStackTrace();
                }
                break;
        }
    }
}
