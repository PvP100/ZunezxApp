package com.example.zunezxapp.base;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.example.zunezxapp.R;

import java.util.ArrayList;

public class ViewController {
    private int layoutId;
    private FragmentManager fragmentManager;
    private BaseFragment currentFragment = null;
    private ArrayList<BaseFragment> listFragment = new ArrayList<>();

    public ViewController(int layoutId, FragmentManager fragmentManager) {
        this.layoutId = layoutId;
        this.fragmentManager = fragmentManager;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(BaseFragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public <T extends BaseFragment> void replaceFragment (Class<T> type, Bundle data) {
        try {
            currentFragment = type.newInstance();
        } catch (IllegalAccessException e) {
            Log.d("ViewController", "Error replace Fragment");
        } catch (InstantiationException e) {
            Log.d("ViewController", "Error replace Fragment");
        }

        currentFragment.setArguments(data);
        currentFragment.setVC(this);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.trans_left_in, R.anim.trans_left_out,
                R.anim.trans_right_in, R.anim.trans_right_out
        );
        if (currentFragment != null) {
            fragmentTransaction.replace(layoutId, currentFragment).commitAllowingStateLoss();
            listFragment.clear();
            listFragment.add(currentFragment);
        }
    }

    public <T extends BaseFragment> void addFragment(Class<T> type, Bundle data, boolean hasAnimation, boolean isHideOldFragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            if (isHideOldFragment) {
                fragmentTransaction.hide(currentFragment);
            }
        }
        if (hasAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.trans_right_in, R.anim.trans_right_in);
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.animation_none, R.anim.animation_none);
        }
        T newFragment = null;
        try {
            newFragment = type.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (newFragment != null && data != null) {
            newFragment.setArguments(data);
        }
        if (newFragment != null) {
            newFragment.setVC(this);
            fragmentTransaction.add(layoutId, newFragment, type.getSimpleName());
            if (currentFragment != null) {
                if (hasAnimation) {

                }
                fragmentTransaction.setMaxLifecycle(currentFragment, Lifecycle.State.STARTED);
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
        if (newFragment != null) {
            listFragment.add(newFragment);
        }
        currentFragment = newFragment;
    }

    public boolean backFromAddFragment(Bundle data) {
        if (listFragment.size() >= 2) {
            listFragment.remove(listFragment.size() - 1);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.trans_right_out, R.anim.trans_right_out);
            if (currentFragment != null) {
                fragmentTransaction.remove(currentFragment);
            }
            currentFragment = listFragment.get(listFragment.size() - 1);
            if (data != null) {
                currentFragment.setArguments(data);
            }
            if (currentFragment != null) {
                fragmentTransaction.setMaxLifecycle(currentFragment,Lifecycle.State.RESUMED);
                currentFragment.setVC(this);
            }
            fragmentTransaction.setCustomAnimations(R.anim.animation_none, R.anim.animation_none);
            fragmentTransaction.show(currentFragment);
            fragmentTransaction.commitAllowingStateLoss();
            currentFragment.backFromAddFragment();
            return true;
        } else {
            return false;
        }
    }
}
