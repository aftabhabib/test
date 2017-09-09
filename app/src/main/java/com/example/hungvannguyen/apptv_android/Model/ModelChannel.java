package com.example.hungvannguyen.apptv_android.Model;

import android.util.Log;

import com.example.hungvannguyen.apptv_android.DownloadJson.DownloadJson;
import com.example.hungvannguyen.apptv_android.Utils.Define;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by HungVanNguyen on 9/7/2017.
 */

public class ModelChannel {
    public List<Channel> getListChannel(){
        List<Channel> channels  = new ArrayList<>();
        String url= Define.url;
        DownloadJson downloadJson= new DownloadJson(url);
        downloadJson.execute();
        try {
            String data =downloadJson.get();
            JSONArray jsonArray= new JSONArray(data);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                Channel channel =new Channel();
                channel.setLogo(jsonObject.getString("logo"));
                channel.setTitle(jsonObject.getString("id"));
                channels.add(channel);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return channels;
    }
    public Channel getUrlChannel(String id){
        Channel channel = new Channel();
        String url=Define.urlchannel;
        DownloadJson downloadJson= new DownloadJson(url+id);
        downloadJson.execute();
        try {
            String data =downloadJson.get();
          JSONObject jsonObject = new JSONObject(data);
            channel.setLink(jsonObject.getString("url"));
            channel.setType(jsonObject.getString("type"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return channel;
    }
}
