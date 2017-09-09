package com.example.hungvannguyen.apptv_android.Presenter;

import com.example.hungvannguyen.apptv_android.Model.Channel;
import com.example.hungvannguyen.apptv_android.Model.ModelChannel;
import com.example.hungvannguyen.apptv_android.View.ViewChannel;

import java.util.List;

/**
 * Created by HungVanNguyen on 9/7/2017.
 */

public class PresenterLoginListChannel implements IPresenterChannel {
    private ViewChannel viewChannel;
    private ModelChannel modelChannel;

    public PresenterLoginListChannel(ViewChannel viewChannel) {
        this.viewChannel = viewChannel;
        modelChannel=new ModelChannel();
    }

    @Override
    public void getList() {
        List<Channel> channels= modelChannel.getListChannel();
        if(channels.size()>0){
            viewChannel.ShowListChannel(channels);
        }
    }
}
