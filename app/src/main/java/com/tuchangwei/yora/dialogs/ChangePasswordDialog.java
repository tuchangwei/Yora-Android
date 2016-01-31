package com.tuchangwei.yora.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tuchangwei.yora.R;

/**
 * Created by vale on 1/31/16.
 */
public class ChangePasswordDialog extends BaseDialogFragment implements View.OnClickListener {

    private EditText currentPassword;
    private EditText newPassword;
    private EditText confirmNewPassword;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_change_password, null, false);

        currentPassword = (EditText)getActivity().findViewById(R.id.dialog_change_password_currentPassword);
        newPassword = (EditText)getActivity().findViewById(R.id.dialog_change_password_newPassword);
        confirmNewPassword = (EditText)getActivity().findViewById(R.id.dialog_change_password_confirmNewPassword);

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
        // TODO: 1/31/16 send new password to server
        Toast.makeText(getActivity(),"Password Updated!",Toast.LENGTH_SHORT);
        dismiss();
    }
}
