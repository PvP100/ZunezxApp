package com.example.zunezxapp.ui.main;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.HomeViewPagerAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentMainBinding;
import com.example.zunezxapp.ui.history.HistoryFragment;
import com.example.zunezxapp.ui.home.HomeFragment;
import com.example.zunezxapp.ui.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment<MainFragmentViewModel, FragmentMainBinding> {
    @Override
    protected MainFragmentViewModel creatViewModel() {
        return null;
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager(), getLifecycle(), listFragment());
        binding.viewpagerMain.setAdapter(homeViewPagerAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.bottomNaviMain.setOnItemSelectedListener(it -> {
            switch (it.getItemId()) {
                case R.id.home_page_fragment:
                    binding.viewpagerMain.setCurrentItem(0);
                    break;
                case R.id.profile_fragment:
                    binding.viewpagerMain.setCurrentItem(1);
                    break;
                case R.id.history_fragment:
                    binding.viewpagerMain.setCurrentItem(2);
                    break;
                default:
                    binding.viewpagerMain.setCurrentItem(0);
                    break;
            }
            return true;
        });

        binding.viewpagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        binding.bottomNaviMain.getMenu().findItem(R.id.home_page_fragment).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNaviMain.getMenu().findItem(R.id.profile_fragment).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNaviMain.getMenu().findItem(R.id.history_fragment).setChecked(true);
                        break;
                    default:
                        binding.bottomNaviMain.getMenu().findItem(R.id.home_page_fragment).setChecked(true);
                        break;
                }
            }
        });
    }

    @Override
    protected boolean backPressed() {
        return true;
    }

    private List<Fragment> listFragment() {
        List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new HomeFragment());
        listFragment.add(new ProfileFragment());
        listFragment.add(new HistoryFragment());
        return listFragment;
    }
}
