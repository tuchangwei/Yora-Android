package com.tuchangwei.yora.services.entities;

import java.util.Calendar;

/**
 * Created by vale on 2/23/16.
 */
public class ContactRequest {
    private int id;
    private boolean isFromUs;
    private UserDetails user;
    private Calendar createAt;

    public ContactRequest(int id, boolean isFromUs, UserDetails user, Calendar createAt) {
        this.id = id;
        this.isFromUs = isFromUs;
        this.user = user;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public boolean isFromUs() {
        return isFromUs;
    }

    public UserDetails getUser() {
        return user;
    }

    public Calendar getCreateAt() {
        return createAt;
    }
}
