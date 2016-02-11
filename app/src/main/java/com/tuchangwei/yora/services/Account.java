package com.tuchangwei.yora.services;

import android.net.Uri;

import com.tuchangwei.yora.infrastructure.ServiceResponse;
import com.tuchangwei.yora.infrastructure.User;

import java.security.PublicKey;


public final class Account {
    private Account() {

    }
    public static class UserResponse extends ServiceResponse{
        public int Id;
        public String AvatarUrl;
        public String DisplayName;
        public String UserName;
        public String Email;
        public String AuthToken;
        public boolean HasPassword;

    }
    public static class LoginWithUserNameRequest {
        public String UserName;
        public String Password;

        public LoginWithUserNameRequest(String userName, String password) {
            UserName = userName;
            Password = password;
        }
    }
    public static class LoginWithUserNameResponse extends UserResponse {

    }
    public static class LoginWithLocalTokenRequest {
        public String AuthToken;

        public LoginWithLocalTokenRequest(String authToken) {
            AuthToken = authToken;
        }
    }
    public static class LoginWithLocalTokenResponse extends UserResponse {

    }

    public static class LoginWithExternalTokenRequest {
        public String Provider;
        public String Token;
        public String ClientId;

        public LoginWithExternalTokenRequest(String provider, String token) {
            Provider = provider;
            Token = token;
            ClientId = "android";
        }
    }
    public static class LoginWithExternalTokenResponse {

    }
    public static class RegisterRequest {
        public String UserName;
        public String Email;
        public String Password;
        public String ClientId;

        public RegisterRequest(String userName, String email, String password) {
            UserName = userName;
            Email = email;
            Password = password;
            ClientId = "android";
        }
    }
    public static class RegisterResponse extends UserResponse {

    }
    public static class RegisterWithExternalTokenRequest {
        public String UserName;
        public String Email;
        public String Provider;
        public String Token;
        public String ClientId;

        public RegisterWithExternalTokenRequest(String userName, String email, String provider, String token) {
            UserName = userName;
            Email = email;
            Provider = provider;
            Token = token;
            ClientId = "android";

        }
    }
    public static class RegisterWithExternalTokenResponse extends UserResponse {

    }
    public static class ChangeAvatarRequest {
        public Uri NewAvatarUri;
        public ChangeAvatarRequest(Uri newAvatarUri) {
            this.NewAvatarUri = newAvatarUri;
        }
    }
    public static class ChangeAvatarResponse extends ServiceResponse {

    }
    public static class UpdateProfileRequest {
        public String DisplayName;
        public String Email;

        public UpdateProfileRequest(String displayName, String email) {
            DisplayName = displayName;
            Email = email;
        }
    }
    public static class UpdateProfileResponse extends ServiceResponse {

    }

    public static class ChangePasswordRequest {
        public String CurrentPassword;
        public String NewPassword;
        public String ConfirmNewPassword;

        public ChangePasswordRequest(String currentPassword, String newPassword, String confirmNewPassword) {
            CurrentPassword = currentPassword;
            NewPassword = newPassword;
            ConfirmNewPassword = confirmNewPassword;
        }
    }

    public static class ChangePasswordResponse extends ServiceResponse {}
    public static class UserDetailsUpdatedEvent {
        public User user;

        public UserDetailsUpdatedEvent(User user) {
            this.user = user;
        }
    }
}
