package com.weiwu.shooter.ui.dashboard;

import android.content.Context;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.weiwu.shooter.R;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private List<Fragment> fragmentList;
    private ViewPagerAdapter viewPagerAdapter;
    private MagicIndicator magicIndicator;
    private CommonNavigator commonNavigator;
    private ViewPager viewPager;


    private String[] titles = new String[]{"动态", "文章","更多"};
    private List<String> title_list = Arrays.asList(titles);

//    private Toast toast;
//    private int screenWidth = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        fragmentList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            DynamicFragment dynamicFragment = DynamicFragment.newInstance(title_list.get(i));
            fragmentList.add(dynamicFragment);
        }
        

//        SmartRefreshLayout smartRefreshLayout = root.findViewById(R.id.refreshLayout);
//        smartRefreshLayout.setEnableLoadMore(false);
//        smartRefreshLayout.setOnRefreshListener((OnRefreshListener) getActivity());

        viewPager = root.findViewById(R.id.viewPager);
//        viewPager.setAdapter(new TextViewPagerAdapter(title_list));
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),1, fragmentList, title_list);
        viewPager.setAdapter(viewPagerAdapter);

        magicIndicator = root.findViewById(R.id.magic_indicator);
        magicIndicator.setBackgroundColor(getResources().getColor(R.color.white));
        commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return title_list != null ? title_list.size() : 0;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(title_list.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#ff000000"));
                simplePagerTitleView.setSelectedColor(R.color.light_blue);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 30));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor(getString(R.string.light_blue)));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
//        Log.i("Tag", "come here..o");
//        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
//        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        titleContainer.setDividerPadding(UIUtil.dip2px(getActivity(), 15));
//        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
//        Log.i("Tag", "come here...");
        ViewPagerHelper.bind(magicIndicator, viewPager);
//        toast = Toast.makeText(getContext(), "hhh", Toast.LENGTH_SHORT);
        Log.i("Tag", "come here....");
        return root;
    }
}