package com.example.hungvannguyen.apptv_android.DownloadJson;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HungVanNguyen on 9/7/2017.
 */

public class DownloadJson extends AsyncTask<String,Void,String>{

    String url="";
    StringBuilder dulieu;
    public DownloadJson(String url) {
        this.url = url;

    }
    @Override
    protected String doInBackground(String... params) {
        String data = null;
        try{
            URL url1= new URL(url);
            URLConnection httpURLConnection=  url1.openConnection();
            httpURLConnection.connect();
            InputStream inputStream= httpURLConnection.getInputStream();
            InputStreamReader reader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(reader);
            dulieu= new StringBuilder();
            String line="";
            while ((line=bufferedReader.readLine())!= null){
                dulieu.append(line);
            }

            data=dulieu.toString();
            bufferedReader.close();
            Log.d("dulieu",data);
            reader.close();
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
//    private String methodGet(HttpURLConnection httpURLConnection){
//        String data="";
//        InputStream inputStream=null;
//        try {
//            httpURLConnection.connect();
//            inputStream=httpURLConnection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader =new BufferedReader(reader);
//            dulieu= new StringBuilder();
//            String line= "";
//            while ((line=bufferedReader.readLine())!= null){
//                dulieu.append(line);
//            }
//            data=dulieu.toString();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return data;
//    }

}
