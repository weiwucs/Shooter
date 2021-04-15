package com.weiwu.shooter.ui.dashboard;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import org.w3c.dom.Text;

import java.util.List;

public class TextViewPagerAdapter extends PagerAdapter {

    private List<String> strings;

    public TextViewPagerAdapter(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings == null ? 0 : strings.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TextView textView = new TextView(container.getContext());
        textView.setText(strings.get(position));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(24);
        container.addView(textView);
        return textView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        TextView textView = (TextView)object;
        String text = textView.getText().toString();
        int index = strings.indexOf(text);
        if(index >= 0){
            return index;
        }
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
