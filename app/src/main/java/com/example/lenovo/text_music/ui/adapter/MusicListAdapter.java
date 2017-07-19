package com.example.lenovo.text_music.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.bean.MusicBean;
import com.example.lenovo.text_music.callbreak.RecyclerClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2017/7/6.
 * 歌曲列表的适配器
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MyViewHolder> {

    private Context context;
    //数据源
    private ArrayList<MusicBean> list;
    //点击事件回调接口
    private RecyclerClickListener recyclerClickListener;
    // 正在播放的歌曲下标
    private int BGIndex = -1;

    /**
     * 构造
     *
     * @param list 数据源
     */
    public MusicListAdapter(Context context, ArrayList<MusicBean> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 重写viewholder
     * 必须继承 RecyclerView.ViewHolder
     * 构造方法中可以实现 初始化控件
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_music_list_tv_name)
        TextView itemMusicListTvName;
        @BindView(R.id.item_music_list_tv_artist)
        TextView itemMusicListTvArtist;
        @BindView(R.id.item_music_list_row_lay)
        LinearLayout itemMusicListRowLay;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_music_list_local, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.itemMusicListTvName.setText(list.get(position).getMusic_name());
        holder.itemMusicListTvArtist.setText(list.get(position).getMusic_artst());

        //判断 当前正在播放的下标 是否等于当前item
        if (this.BGIndex == position) {
            //如果等于  改变当前item背景
            holder.itemMusicListRowLay.setBackgroundResource(R.color.item_bg);
        } else {
            //如果不等于  设置当前item背景为默认颜色
//            holder.itemMusicListRowLay.setBackgroundResource(R.color.item_bg_def);
        }

//        给item里边的最外层控件添加点击事件
        holder.itemMusicListRowLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                调用 点击事件接口的方法
                recyclerClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //设置item点击事件方法
    public void setOnItemClickListener(RecyclerClickListener clickListener) {
        //拿到 点击事件回调接口 对象
        this.recyclerClickListener = clickListener;
    }

    //设置当前播放歌曲的下标 的方法
    public void setBGIndex(int index) {
        this.BGIndex = index;
        //设置完 要刷新列表
        notifyDataSetChanged();
    }
}
