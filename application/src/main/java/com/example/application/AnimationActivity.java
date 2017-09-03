package com.example.application;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class AnimationActivity extends AppCompatActivity {

    private ImageView imgPic;
    private Button btnAlpha, btnScale, btnTranslate, btnRotate;
    private Animation myAnimation;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        intiView();
        initData();

    }

    /**
     * 初始化组件
     */
    private void intiView() {
        imgPic = (ImageView) findViewById(R.id.imgPic);
        btnAlpha = (Button) findViewById(R.id.Alpha);
        btnScale = (Button) findViewById(R.id.Scale);
        btnTranslate = (Button) findViewById(R.id.Translate);
        btnRotate = (Button) findViewById(R.id.Rotate);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.alpha);
                imgPic.startAnimation(myAnimation);
            }
        });
        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.scale);
                imgPic.startAnimation(myAnimation);
            }
        });
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.translate);
                imgPic.startAnimation(myAnimation);
            }
        });
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.rotate);
                imgPic.startAnimation(myAnimation);
            }
        });
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Animation Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
