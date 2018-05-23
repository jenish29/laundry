package com.locationbasedcompany.pc.laundry;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.v4.view.PagerAdapter;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import org.w3c.dom.Text;

/**
 * Created by pc on 5/21/18.
 */

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    View currentView;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    private boolean didChangeGuideLinePercentage = false;

    public int[] slide_images = {
            R.drawable.onboarding_schedule,
            R.drawable.onbaording_clean,
            R.drawable.onboarding_return
    };

    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slideImage);
        slideImageView.setImageResource(slide_images[position]);
        container.addView(view);

        view.setTag(position);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }




}
