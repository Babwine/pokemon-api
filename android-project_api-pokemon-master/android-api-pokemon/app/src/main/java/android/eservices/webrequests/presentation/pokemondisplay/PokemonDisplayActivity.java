package android.eservices.webrequests.presentation.pokemondisplay;

import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.pokemondisplay.grid.fragment.GridFragment;
import android.eservices.webrequests.presentation.pokemondisplay.list.fragment.ListFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * The main Activity class
 */
public class PokemonDisplayActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }

    /**
     * A function to setup the viewpager of the app and its tabs (the fragments used)
     */
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
