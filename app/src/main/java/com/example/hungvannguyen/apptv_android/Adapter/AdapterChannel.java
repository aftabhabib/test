package com.example.hungvannguyen.apptv_android.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hungvannguyen.apptv_android.Model.Channel;
import com.example.hungvannguyen.apptv_android.OnClickLinstener.OnClickChannel;
import com.example.hungvannguyen.apptv_android.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HungVanNguyen on 9/7/2017.
 */

public class AdapterChannel extends RecyclerView.Adapter<AdapterChannel.HolderChannel> {
    private List<Channel> channels;
    private Context context;
    private OnClickChannel onCliked;

    public AdapterChannel(List<Channel> channels, Context context) {
        this.channels = channels;
        this.context = context;
    }

    @Override
    public HolderChannel onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_rv_channel,parent,false);
        HolderChannel holderChannel = new HolderChannel(view);
        return holderChannel;
    }

    @Override
    public void onBindViewHolder(HolderChannel holder, int position) {
        Channel channel =channels.get(position);
        holder.textView.setText(channel.getTitle());
        Picasso.with(context).load(channel.getLogo()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public class HolderChannel extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;
        public HolderChannel(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_channel);
            textView= (TextView) itemView.findViewById(R.id.tv_channel);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        if(onCliked!=null){
            onCliked.isClicked(v,channels.get(getAdapterPosition()).getTitle());
        }
        }
    }

    public void setOnCliked(OnClickChannel onCliked) {
        this.onCliked = onCliked;
    }
}
