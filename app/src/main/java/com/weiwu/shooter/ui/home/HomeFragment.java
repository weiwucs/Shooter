package com.weiwu.shooter.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.weiwu.shooter.R;
import com.weiwu.shooter.dao.CardBean;
import com.weiwu.shooter.dao.ImageBean;
import com.weiwu.shooter.dao.ItemBean;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private XRecyclerView xRecyclerView;
    private RecyclerViewCardAdapter recyclerViewCardAdapter;
//    private HomeViewModel homeViewModel;
//    private List<ItemBean> items = new ArrayList<>();
    private List<CardBean> items = new ArrayList<>();

    private int refreshTime = 0;
    private int times = 0;
    final int itemLimit = 5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);  //activity - fragment(C-view-V-layout-M-viewholder)

//        final TextView textView = root.findViewById(R.id.text_home); // root-layout -> subset-view -> sub-menu -> sub-item
//        //use context to balance these view
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

//        for(int i = 0; i < 100; i++){
//            ItemBean itemBean = new ItemBean();
//            itemBean.setName("itembean" + i);
//            items.add(itemBean);
//        }
        for(int i = 0; i < 5; i++){
            items.add(initCardBean("0" + i,"2020-11-30","1000" + i ,"" + i,"SH000" + i));
        }

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.topappbar);
        toolbar.setTitle("Shooter");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: navigation on click listener
            }
        });

        //easy to use
        Banner banner = root.findViewById(R.id.banner);
        banner.addBannerLifecycleObserver(getActivity())
                .setAdapter(new BannerImageAdapter(ImageBean.getTestData()))
                .setIndicator(new CircleIndicator(getActivity()));

        xRecyclerView = root.findViewById(R.id.recyclerview);
//        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()); //use getActivity() to get the context
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setHasFixedSize(true);
//        xRecyclerView.setNestedScrollingEnabled(false);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);

//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(items);
//        recyclerView.setAdapter(recyclerViewAdapter);
//
//        recyclerViewAdapter.setOnRecyclerItemClickListener(new RecyclerViewAdapter.OnRecyclerItemClickListener() {
//            @Override
//            public void onRecyclerItemClick(int position) {
////                Log.e("leo", "onRecyclerItemClick:" + position);
//            }
//        });

        recyclerViewCardAdapter = new RecyclerViewCardAdapter((ArrayList<CardBean>) items);
        xRecyclerView.setAdapter(recyclerViewCardAdapter);

        xRecyclerView.getDefaultFootView().setLoadingHint("自定义加载中提示");
        xRecyclerView.getDefaultFootView().setNoMoreHint("自定义加载完毕提示");

        // When the item number of the screen number is list.size-2,we call the onLoadMore
        xRecyclerView.setLimitNumberToCallLoadMore(2);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        items.clear();
                        for(int i = 1; i < itemLimit + 1 ;i++){
                            items.add(initCardBean("0" + i,"2020-11-30","1000" + i ,"" + i,"SH000" + i));
                        }
                        recyclerViewCardAdapter.notifyDataSetChanged();
                        if(xRecyclerView != null)
                            xRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                Log.e("aaaaa","call onLoadMore");
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            for(int i = 2; i < itemLimit + 2;i++){
                                items.add(initCardBean("0" + i,"2020-11-30","1000" + i ,"" + i,"SH000" + i));
                            }
                            if(xRecyclerView != null) {
                                xRecyclerView.loadMoreComplete();
                                recyclerViewCardAdapter.notifyDataSetChanged();
                            }
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for(int i = 0; i < itemLimit ;i++){
                                items.add(initCardBean("0" + i,"2020-11-30","1000" + i ,"" + i,"SH000" + i));
                            }
                            if(xRecyclerView != null) {
                                xRecyclerView.setNoMore(true);
                                recyclerViewCardAdapter.notifyDataSetChanged();
                            }
                        }
                    }, 1000);
                }
                times ++;
            }
        });

//        // any time,when you finish your activity or fragment,call this below
//        if(xRecyclerView != null){
//            xRecyclerView.destroy(); // this will totally release XR's memory
//            xRecyclerView = null;
//        }

        return root;
    }

    private CardBean initCardBean(String str1,String str2,String str3,String str4,String str5){
        return new CardBean(str1,str2,str3,str4,str5);
    }
}