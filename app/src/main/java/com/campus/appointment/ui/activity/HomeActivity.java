package com.campus.appointment.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.ui.fragment.FriendsFragment;
import com.campus.appointment.ui.fragment.HomeFragment;
import com.campus.appointment.ui.fragment.SquareFragment;
import com.campus.appointment.ui.fragment.UserFragment;

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


    @Override
    public int intiLayout() {
        return R.layout.activity_main;
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
        friendsFragment = FriendsFragment.getInstance();
        homeFragment = HomeFragment.getInstance();
        squareFragment = SquareFragment.getInstance();
        userFragment = UserFragment.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, homeFragment).commitAllowingStateLoss();
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
        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, homeFragment).commitAllowingStateLoss();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, squareFragment).commitAllowingStateLoss();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, friendsFragment).commitAllowingStateLoss();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, userFragment).commitAllowingStateLoss();
                break;
        }
    }
}
