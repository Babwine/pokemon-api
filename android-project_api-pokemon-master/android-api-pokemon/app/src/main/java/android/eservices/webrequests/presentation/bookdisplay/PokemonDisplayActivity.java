package android.eservices.webrequests.presentation.bookdisplay;

import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.bookdisplay.grid.fragment.GridFragment;
import android.eservices.webrequests.presentation.bookdisplay.list.fragment.ListFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PokemonDisplayActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);


        final ListFragment listFragment = ListFragment.newInstance();
        final GridFragment gridFragment = GridFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return listFragment;
                }
                return gridFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return ListFragment.TAB_NAME;
                }
                return GridFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });


    }


}
