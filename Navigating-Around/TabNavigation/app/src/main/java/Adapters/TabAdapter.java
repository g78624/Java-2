//Kyle Kauck

package Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Fragments.FeaturedFragment;
import Fragments.ImageFragment;
import Fragments.RecentFragment;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {

        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        if (i == 0){

            return new FeaturedFragment();

        } else if (i == 1){

            return new RecentFragment();

        } else if (i == 2){

            return new ImageFragment();

        }

        return null;

    }

    @Override
    public int getCount() {

        return 3;

    }

}
