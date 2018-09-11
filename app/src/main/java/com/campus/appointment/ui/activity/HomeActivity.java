package com.campus.appointment.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.ui.fragment.FriendsFragment;
import com.campus.appointment.ui.fragment.HomeFragment;
import com.campus.appointment.ui.fragment.SquareFragment;
import com.campus.appointment.ui.fragment.UserFragment;
import com.luck.picture.lib.permissions.RxPermissions;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.BACKGROUND_STYLE_STATIC;
import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED_NO_TITLE;

public class HomeActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    @InjectView(R.id.main_container)
    FrameLayout mainContainer;
    @InjectView(R.id.bottom_bar)
    BottomNavigationBar bottomBar;
    private FriendsFragment friendsFragment;
    private HomeFragment homeFragment;
    private SquareFragment squareFragment;
    private UserFragment userFragment;
    private FragmentManager fragmentManager;

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        bottomBar.addItem(new BottomNavigationItem(R.mipmap.main_bottom_mainpage, "").setActiveColorResource(R.color.yellow))
                .addItem(new BottomNavigationItem(R.mipmap.main_bottom_camera, "").setActiveColorResource(R.color.yellow))
                .addItem(new BottomNavigationItem(R.mipmap.main_bottom_chat, "").setActiveColorResource(R.color.yellow))
                .addItem(new BottomNavigationItem(R.mipmap.main_bottom_user, "").setActiveColorResource(R.color.yellow))
                .setMode(MODE_FIXED_NO_TITLE)
                .setBackgroundStyle(BACKGROUND_STYLE_STATIC)
                .setTabSelectedListener(this)
                .initialise();
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.add(R.id.main_container, homeFragment);
        transaction.commit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onTabSelected(int position) {
        showPositionFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {
        showPositionFragment(position);
    }

    private void showPositionFragment(int position) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_container, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (squareFragment == null) {
                    squareFragment = new SquareFragment();
                    transaction.add(R.id.main_container, squareFragment);
                } else {
                    transaction.show(squareFragment);
                }
                break;
            case 2:
                if (friendsFragment == null) {
                    friendsFragment = new FriendsFragment();
                    transaction.add(R.id.main_container, friendsFragment);
                } else {
                    transaction.show(friendsFragment);
                }
                break;
            case 3:
                if (userFragment == null) {
                    userFragment = new UserFragment();
                    transaction.add(R.id.main_container, userFragment);
                } else {
                    transaction.show(userFragment);
                }
                break;
        }
        transaction.commit();
    }


    public void hideAllFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (squareFragment != null) {
            transaction.hide(squareFragment);
        }
        if (friendsFragment != null) {
            transaction.hide(friendsFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }
}
