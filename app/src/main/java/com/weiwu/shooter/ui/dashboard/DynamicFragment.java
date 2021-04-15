package com.weiwu.shooter.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.weiwu.shooter.R;
import com.weiwu.shooter.dao.ItemBean;
import com.weiwu.shooter.ui.home.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DynamicFragment extends Fragment {
    RecyclerView recyclerView;
    SmartRefreshLayout smartRefreshLayout;

    private List<ItemBean> items = new ArrayList<>();
    private String title;

    public static DynamicFragment newInstance(String title) {
        
        Bundle args = new Bundle();
        args.putString("Title", title);
        DynamicFragment fragment = new DynamicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.smart_refresh_recyclierview, container, false);

        Bundle bundle = getArguments();
        title = bundle.getString("Title");

        for(int i = 0; i < 20; i++){
            ItemBean itemBean = new ItemBean();
            itemBean.setName(title + " : " + i);
            items.add(itemBean);
        }

        smartRefreshLayout = root.findViewById(R.id.smart_refresh);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(items);
//        recyclerViewAdapter.setOnRecyclerItemClickListener(new RecyclerViewAdapter.OnRecyclerItemClickListener() {
//            @Override
//            public void onRecyclerItemClick(int position) {
//                //TODO:Click
//            }
//        });
        recyclerView = root.findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        return root;
    }


}
