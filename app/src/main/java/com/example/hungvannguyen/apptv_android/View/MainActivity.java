package com.example.hungvannguyen.apptv_android.View;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hungvannguyen.apptv_android.Adapter.AdapterChannel;
import com.example.hungvannguyen.apptv_android.Model.Channel;
import com.example.hungvannguyen.apptv_android.Model.ModelChannel;
import com.example.hungvannguyen.apptv_android.OnClickLinstener.OnClickChannel;
import com.example.hungvannguyen.apptv_android.Presenter.PresenterLoginListChannel;
import com.example.hungvannguyen.apptv_android.R;
import com.example.hungvannguyen.apptv_android.Utils.Define;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewChannel, OnClickChannel {
    private RecyclerView rv;
    private PresenterLoginListChannel presenterLoginListChannel;
    private AdapterChannel adapterChannel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControl();
    }

    private void getControl() {
        rv= (RecyclerView) findViewById(R.id.rv_channel);
        presenterLoginListChannel =new PresenterLoginListChannel(this);
        presenterLoginListChannel.getList();

    }

    @Override
    public void ShowListChannel(List<Channel> channels) {
        adapterChannel= new AdapterChannel(channels,this);
        rv.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
        adapterChannel.setOnCliked(this);
        rv.setAdapter(adapterChannel);
    }

    @Override
    public void isClicked(View view, String title) {
        ModelChannel modelChannel = new ModelChannel();
        Channel chane=modelChannel.getUrlChannel(title);
        openVideo(chane.getLink());
    }

    private void openVideo(String url){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName(Define.IPTV_CORE_PACKAGE_NAME, Define.IPTV_CORE_CLASS_NAME);
            Uri videoUri = Uri.parse(url);
            intent.setDataAndType( videoUri, "application/x-mpegURL" );
            intent.setPackage( "com.mxtech.videoplayer.ad" );
            startActivity( intent );
        } catch (ActivityNotFoundException e) {
            // IPTV core app is not installed, let's ask the user to install it.
            showIptvCoreNotFoundDialog();
        }
    }
    public void showIptvCoreNotFoundDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_core_not_installed_title);
        builder.setMessage(R.string.dialog_core_not_installed_message);
        builder.setPositiveButton(R.string.dialog_button_install,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int id)
                    {
                        try {
                            // try to open Google Play app first
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Define.IPTV_CORE_PACKAGE_NAME)));
                        } catch (ActivityNotFoundException e) {
                            // if Google Play is not found for some reason, let's open browser
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + Define.IPTV_CORE_PACKAGE_NAME)));
                        }
                    }
                });
        builder.setNegativeButton(R.string.dialog_button_cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int id)
                    {
                        // if cancelled - just close the app
                        finish();
                    }
                });
        builder.setCancelable(false);
        builder.create().show();
    }

}
