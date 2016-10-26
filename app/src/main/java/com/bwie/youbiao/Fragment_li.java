package com.bwie.youbiao;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bwie.youbiao.bean.BeanData;
import com.bwie.youbiao.utils.HttpUtils;
import com.bwie.youbiao.utils.MyListAdapter;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by w8888 on 2016/10/26.
 */
public class Fragment_li extends Fragment{

    private boolean isViewCreated;
    private boolean isLoadDataCompleted;

        private ListView listView;
        private View v;
        private String path="http://open.qyer.com/qyer/bbs/entry?client_id=qyer_android&client_secret=9fcaae8aefc4f9ac4915";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.item2,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化控件
        initView();

    }

    private void initView() {
        listView= (ListView) v.findViewById(R.id.list);
        isViewCreated=true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&isViewCreated){
            lazyLoad();
        }

    }

    private void lazyLoad() {

        if (!isLoadDataCompleted) {
            initView();
            initData();
            isLoadDataCompleted = true;
            Log.e("---", "随便啦");
        }
        
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoadDataCompleted=false;
    }

    private void initData() {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String str= HttpUtils.Getstr(path);
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Gson gson=new Gson();
                BeanData bean=gson.fromJson(s,BeanData.class);
                List<BeanData.DataEntity.ForumListEntity.GroupEntity>list=bean.getData().getForum_list().get(0).getGroup();

                //适配器
                listView.setAdapter(new MyListAdapter(getContext(),list));


            }
        }.execute();

    }
}
