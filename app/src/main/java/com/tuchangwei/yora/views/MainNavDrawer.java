package com.tuchangwei.yora.views;

import android.view.View;
import android.widget.Toast;

import com.tuchangwei.yora.R;
import com.tuchangwei.yora.activities.BaseActivity;
import com.tuchangwei.yora.activities.MainActivity;

/**
 * Created by vale on 1/24/16.
 */
public class MainNavDrawer extends NavDrawer {
    public MainNavDrawer(final BaseActivity activity) {
        super(activity);

        addItem(new ActivityNavDrawerItem(MainActivity.class,"Inbox",null, R.drawable.ic_action_unread,R.id.include_main_nav_drawer_topItems));
        addItem(new BasicNavDrawerItem("Logout",null,R.drawable.ic_action_backspace,R.id.include_main_nav_drawer_bottomItems){
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"You have logged out!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
