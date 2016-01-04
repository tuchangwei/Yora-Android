package com.tuchangwei.yora.infrastructure;

import android.content.Context;

public class Auth {
    private  final Context context;
    private User user;

    public Auth(Context context) {
        this.context = context;
        this.user = new User();
    }

    public User getUser() {
        return user;
    }
}
