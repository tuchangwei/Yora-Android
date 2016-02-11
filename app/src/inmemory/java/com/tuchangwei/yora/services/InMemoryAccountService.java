package com.tuchangwei.yora.services;

import com.squareup.otto.Subscribe;
import com.tuchangwei.yora.infrastructure.YoraApplication;

/**
 * Created by vale on 2/9/16.
 */
public class InMemoryAccountService extends BaseInMemoryService {
    private YoraApplication application;

    public InMemoryAccountService(YoraApplication application) {
        super(application);
    }

    @Subscribe
    public void updateProfile(Account.UpdateProfileRequest request) {
        Account.UpdateProfileResponse response = new Account.UpdateProfileResponse();
        if (request.DisplayName.equals("Vale")) {
            response.setPropertyError("displayName","You may not be named Vale");
        }
        postDelayed(response);
    }
    @Subscribe
    public void updateAvatar(Account.ChangeAvatarRequest request) {
        postDelayed(new Account.ChangeAvatarResponse());
    }
    @Subscribe
    public void changePassword(Account.ChangePasswordRequest request) {

        Account.ChangePasswordResponse response = new Account.ChangePasswordResponse();
        if (!request.NewPassword.equals(request.ConfirmNewPassword)) {
            response.setPropertyError("confirmNewPassword","Passwords must match!");
        }
        if (request.NewPassword.length()<3) {
            response.setPropertyError("newPassword","Password must be larger than 3 characters");
        }
        postDelayed(response);
    }
}
