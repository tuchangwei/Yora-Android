package com.tuchangwei.yora.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.tuchangwei.yora.R;
import com.tuchangwei.yora.services.Account;

/**
 * Created by vale on 1/31/16.
 */
public class ChangePasswordDialog extends BaseDialogFragment implements View.OnClickListener {

    private EditText currentPassword;
    private EditText newPassword;
    private EditText confirmNewPassword;
    private Dialog progressBarDialog;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_change_password, null, false);

        currentPassword = (EditText)dialogView.findViewById(R.id.dialog_change_password_currentPassword);
        newPassword = (EditText)dialogView.findViewById(R.id.dialog_change_password_newPassword);
        confirmNewPassword = (EditText)dialogView.findViewById(R.id.dialog_change_password_confirmNewPassword);

        if (!application.getAuth().getUser().isHasPassword()) {
            currentPassword.setVisibility(View.GONE);
        }
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                .setPositiveButton("Update",null)
                .setNegativeButton("Cancel",null)
                .setTitle("Change password")
                .show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(this);
        return alertDialog;

    }

    @Override
    public void onClick(View view) {
        progressBarDialog = new ProgressDialog.Builder(getActivity())
                .setTitle("Change Password")
                .setCancelable(false)
                .show();
        bus.post(new Account.ChangePasswordRequest(currentPassword.getText().toString()
                ,newPassword.getText().toString()
                ,confirmNewPassword.getText().toString()));

    }

    @Subscribe
    public void passwordChanged(Account.ChangePasswordResponse response) {

        progressBarDialog.dismiss();
        progressBarDialog = null;
        if (response.didSucceed()) {
            Toast.makeText(getActivity(),"Password Changed",Toast.LENGTH_LONG).show();
            dismiss();
            application.getAuth().getUser().setHasPassword(true);
            return;
        }
        currentPassword.setError(response.getPropertyError("currentPassword"));
        newPassword.setError(response.getPropertyError("newPassword"));
        confirmNewPassword.setError(response.getPropertyError("confirmNewPassword"));
        response.showErrorToast(getActivity());
    }
}
