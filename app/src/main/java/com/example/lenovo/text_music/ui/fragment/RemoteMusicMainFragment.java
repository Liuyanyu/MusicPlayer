package com.example.lenovo.text_music.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.text_music.PunlicFlags;
import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.bean.RemoteMusicListBean;
import com.example.lenovo.text_music.inject.component.DaggerRemoteMusicMainComponent;
import com.example.lenovo.text_music.inject.module.RemoteMusicMainModule;
import com.example.lenovo.text_music.presenter.contract.RemoteMusicMainContract;
import com.example.lenovo.text_music.presenter.impl.RemoteMusicMainPresenter;
import com.example.lenovo.text_music.ui.ImageMode;
import com.example.lenovo.text_music.ui.activity.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/7/14.
 */

public class RemoteMusicMainFragment extends BaseFragment implements RemoteMusicMainContract.View {
    @BindView(R.id.fragment_remote_main_hot_icon)
    ImageView HotIcon;
    @BindView(R.id.fragment_remote_main_hot_list1)
    TextView HotList1;
    @BindView(R.id.fragment_remote_main_hot_list2)
    TextView HotList2;
    @BindView(R.id.fragment_remote_main_hot_list3)
    TextView HotList3;
    @BindView(R.id.fragment_remote_main_hot)
    LinearLayout MainHot;
    @BindView(R.id.fragment_remote_main_new_icon)
    ImageView NewIcon;
    @BindView(R.id.fragment_remote_main_new_list1)
    TextView NewList1;
    @BindView(R.id.fragment_remote_main_new_list2)
    TextView NewList2;
    @BindView(R.id.fragment_remote_main_new_list3)
    TextView NewList3;
    @BindView(R.id.fragment_remote_main_new)
    LinearLayout MainNew;
    @BindView(R.id.fragment_remote_main_chinese_icon)
    ImageView ChineseIcon;
    @BindView(R.id.fragment_remote_main_chinese_list1)
    TextView ChineseList1;
    @BindView(R.id.fragment_remote_main_chinese_list2)
    TextView ChineseList2;
    @BindView(R.id.fragment_remote_main_chinese_list3)
    TextView ChineseList3;
    @BindView(R.id.fragment_remote_main_chinese)
    LinearLayout MainChinese;
    @BindView(R.id.fragment_remote_main_ktv_hot_icon)
    ImageView KtvHotIcon;
    @BindView(R.id.fragment_remote_main_ktv_hot_list1)
    TextView KtvHotList1;
    @BindView(R.id.fragment_remote_main_ktv_hot_list2)
    TextView KtvHotList2;
    @BindView(R.id.fragment_remote_main_ktv_hot_list3)
    TextView KtvHotList3;
    @BindView(R.id.fragment_remote_main_ktv_hot)
    LinearLayout KtvHot;
    Unbinder unbinder;

    @Inject
    RemoteMusicMainPresenter remoteMusicMainPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote_music_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerRemoteMusicMainComponent
                .builder()
                .remoteMusicMainModule(new RemoteMusicMainModule(this))
                .build()
                .inject(this);

        remoteMusicMainPresenter.getMusicMsg();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fragment_remote_main_hot, R.id.fragment_remote_main_new, R.id.fragment_remote_main_chinese, R.id.fragment_remote_main_ktv_hot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_remote_main_new:
                ((MainActivity) getActivity()).intent2remoteList(PunlicFlags.MUSIC_API_TYPE_NEW);
                break;
            case R.id.fragment_remote_main_hot:
                ((MainActivity) getActivity()).intent2remoteList(PunlicFlags.MUSIC_API_TYPE_HOT);
                break;
            case R.id.fragment_remote_main_chinese:
                ((MainActivity) getActivity()).intent2remoteList(PunlicFlags.MUSIC_API_TYPE_CHINESE);
                break;
            case R.id.fragment_remote_main_ktv_hot:
                ((MainActivity) getActivity()).intent2remoteList(PunlicFlags.MUSIC_API_TYPE_KTV);
                break;
        }
    }

    @Override
    public void onResponseNew(RemoteMusicListBean remoteMusicListBean) {

        ImageMode.setImg(this, remoteMusicListBean.getBillboard().getPic_s192(), NewIcon);
        NewList1.setText(remoteMusicListBean.getSong_list().get(0).getTitle());
        NewList2.setText(remoteMusicListBean.getSong_list().get(1).getTitle());
        NewList3.setText(remoteMusicListBean.getSong_list().get(2).getTitle());
    }

    @Override
    public void onResponseHot(RemoteMusicListBean remoteMusicListBean) {
        ImageMode.setImg(this, remoteMusicListBean.getBillboard().getPic_s192(), HotIcon);
        HotList1.setText(remoteMusicListBean.getSong_list().get(0).getTitle());
        HotList2.setText(remoteMusicListBean.getSong_list().get(1).getTitle());
        HotList3.setText(remoteMusicListBean.getSong_list().get(2).getTitle());
    }

    @Override
    public void onResponseChinese(RemoteMusicListBean remoteMusicListBean) {
        ImageMode.setImg(this, remoteMusicListBean.getBillboard().getPic_s192(), ChineseIcon);
        ChineseList1.setText(remoteMusicListBean.getSong_list().get(0).getTitle());
        ChineseList2.setText(remoteMusicListBean.getSong_list().get(1).getTitle());
        ChineseList3.setText(remoteMusicListBean.getSong_list().get(2).getTitle());
    }

    @Override
    public void onResponseKtv(RemoteMusicListBean remoteMusicListBean) {
        ImageMode.setImg(this, remoteMusicListBean.getBillboard().getPic_s192(), KtvHotIcon);
        KtvHotList1.setText(remoteMusicListBean.getSong_list().get(0).getTitle());
        KtvHotList2.setText(remoteMusicListBean.getSong_list().get(1).getTitle());
        KtvHotList3.setText(remoteMusicListBean.getSong_list().get(2).getTitle());
    }
}
