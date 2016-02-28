package com.tuchangwei.yora.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.tuchangwei.yora.R;
import com.tuchangwei.yora.activities.BaseActivity;
import com.tuchangwei.yora.activities.ContactsActivity;
import com.tuchangwei.yora.activities.MainActivity;
import com.tuchangwei.yora.activities.ProfileActivity;
import com.tuchangwei.yora.activities.SentMessagesActivity;
import com.tuchangwei.yora.infrastructure.User;
import com.tuchangwei.yora.services.Account;

/**
 * Created by vale on 1/24/16.
 */
public class MainNavDrawer extends NavDrawer {
    private final TextView displayNameText;
    private final ImageView avatarImage;

    public MainNavDrawer(final BaseActivity activity) {
        super(activity);

        addItem(new ActivityNavDrawerItem(MainActivity.class,"Inbox",null, R.drawable.ic_action_unread,R.id.include_main_nav_drawer_topItems));
        addItem(new ActivityNavDrawerItem(SentMessagesActivity.class,"Sent Messages",null, R.drawable.ic_action_send_now,R.id.include_main_nav_drawer_topItems));
        addItem(new ActivityNavDrawerItem(ContactsActivity.class,"Contacts",null, R.drawable.ic_action_group,R.id.include_main_nav_drawer_topItems));
        addItem(new ActivityNavDrawerItem(ProfileActivity.class,"Profile",null, R.drawable.ic_action_person,R.id.include_main_nav_drawer_topItems));

        addItem(new BasicNavDrawerItem("Logout",null,R.drawable.ic_action_backspace,R.id.include_main_nav_drawer_bottomItems){
            @Override
            public void onClick(View v) {

                activity.getYoraApplication().getAuth().logout();
            }
        });

        displayNameText = (TextView)navDrawerView.findViewById(R.id.include_main_nav_drawer_displayName);
        avatarImage = (ImageView)navDrawerView.findViewById(R.id.include_main_nav_drawer_avatar);

        User loggedInUser = activity.getYoraApplication().getAuth().getUser();
        displayNameText.setText(loggedInUser.getDisplayName());
        
        //// TODO: 1/27/16 change avatarImage to avatarUrl from loggedInUser.
    }
    @Subscribe
    public void onUserDetailsUpdated(Account.UserDetailsUpdatedEvent event) {
        // TODO: 2/20/16 update avatar URL
        displayNameText.setText(event.user.getDisplayName());
    }
}
