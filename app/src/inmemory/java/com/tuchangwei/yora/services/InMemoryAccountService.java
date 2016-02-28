package com.tuchangwei.yora.services;

import com.squareup.otto.Subscribe;
import com.tuchangwei.yora.infrastructure.Auth;
import com.tuchangwei.yora.infrastructure.User;
import com.tuchangwei.yora.infrastructure.YoraApplication;

/**
 * Created by vale on 2/9/16.
 */
public class InMemoryAccountService extends BaseInMemoryService {

    public InMemoryAccountService(YoraApplication application) {
        super(application);
    }

    @Subscribe
    public void updateProfile(final Account.UpdateProfileRequest request) {
        final Account.UpdateProfileResponse response = new Account.UpdateProfileResponse();
        if (request.DisplayName.equals("Vale")) {
            response.setPropertyError("displayName","You may not be named Vale");
        }
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                User user = application.getAuth().getUser();
                user.setDisplayName(request.DisplayName);
                user.setEmail(request.Email);

                bus.post(response);
                bus.post(new Account.UserDetailsUpdatedEvent(user));
            }
        },2000,3000);
    }
    @Subscribe
    public void updateAvatar(final Account.ChangeAvatarRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                User user = application.getAuth().getUser();
                user.setAvatarUrl(request.NewAvatarUri.toString());

                bus.post(new Account.ChangeAvatarResponse());
                bus.post(new Account.UserDetailsUpdatedEvent(user));
            }
        },4000,5000);
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
    @Subscribe
    public void loginWithUserName(final Account.LoginWithUserNameRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.LoginWithUserNameResponse response = new Account.LoginWithUserNameResponse();
                if (request.UserName.equals("Vale")) {
                    response.setPropertyError("userName","Invalid username or password");
                }
                loginUser(response);
                bus.post(response);
            }
        },1000,2000);
    }
    @Subscribe
    public void loginWithExternalToken(Account.LoginWithExternalTokenRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.LoginWithExternalTokenResponse response = new Account.LoginWithExternalTokenResponse();
                loginUser(response);
                bus.post(response);
            }
        },1000,2000);
    }

    @Subscribe
    public void register(Account.RegisterRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.RegisterResponse response = new Account.RegisterResponse();
                loginUser(response);
                bus.post(response);
            }
        },1000,2000);
    }
    @Subscribe
    public void externalRegister(Account.RegisterWithExternalTokenRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.RegisterWithExternalTokenResponse response = new Account.RegisterWithExternalTokenResponse();
                loginUser(response);
                bus.post(response);
            }
        },1000,2000);
    }
    @Subscribe
    public void loginWithLocalToken(Account.LoginWithLocalTokenRequest request) {
        invokeDelayed(new Runnable() {
            @Override
            public void run() {
                Account.LoginWithLocalTokenResponse response = new Account.LoginWithLocalTokenResponse();
                loginUser(response);
                bus.post(response);
            }
        },1000,2000);
    }
    private void loginUser(Account.UserResponse response) {
        Auth auth = application.getAuth();
        User user = auth.getUser();

        user.setId(123);
        user.setDisplayName("Vale Tu");
        user.setUserName("valetu");
        user.setEmail("changweitu@gmail.com");
        user.setAvatarUrl("http://www.gravatar.com/avatar/1?d=identicon");
        user.setLoggedIn(true);

        bus.post(new Account.UserDetailsUpdatedEvent(user));
        auth.setAuthToken("fakeauthtoken");

        response.Id = user.getId();
        response.DisplayName = user.getDisplayName();
        response.UserName = user.getUserName();
        response.Email = user.getEmail();
        response.AvatarUrl = user.getAvatarUrl();
        response.AuthToken = auth.getAuthToken();
    }
}
