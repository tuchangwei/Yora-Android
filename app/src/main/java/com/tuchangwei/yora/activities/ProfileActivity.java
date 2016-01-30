package com.tuchangwei.yora.activities;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.soundcloud.android.crop.Crop;
import com.tuchangwei.yora.R;
import com.tuchangwei.yora.views.MainNavDrawer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vale on 1/27/16.
 */
public class ProfileActivity extends BaseAuthenticatedActivity implements View.OnClickListener {

    private static final int REQUEST_SELECT_IMAGE = 100;
    private ImageView avatarView;
    private View avatarProcessFrame;
    private File tempOutputFile;
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");
        setNavDrawer(new MainNavDrawer(this));

        if (!isTablet) {
            View textFields = findViewById(R.id.activity_profile_textFields);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)textFields.getLayoutParams();
            params.setMargins(0,params.getMarginStart(),0,0);
            params.removeRule(RelativeLayout.END_OF);
            params.addRule(RelativeLayout.BELOW,R.id.activity_profile_changeAvatar);
            textFields.setLayoutParams(params);

        }
        avatarView = (ImageView)findViewById(R.id.activity_profile_avatar);
        avatarView.setOnClickListener(this);
        findViewById(R.id.activity_profile_changeAvatar).setOnClickListener(this);

        avatarProcessFrame = findViewById(R.id.activity_profile_avatarProgressFrame);
        avatarProcessFrame.setVisibility(View.GONE);

        tempOutputFile = new File(getExternalCacheDir(),"temp-image.jpg");

    }

    @Override
    public void onClick(View view) {

        int viewID = view.getId();
        if (viewID==R.id.activity_profile_avatar||
                viewID==R.id.activity_profile_changeAvatar) {
            changeAvatar();
        }
    }

    private void changeAvatar() {

        List<Intent> otherImageCaptureIntents = new ArrayList<>();
        List<ResolveInfo> otherImageCaptureActivities = getPackageManager().
                queryIntentActivities(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),0);
        for (ResolveInfo info :
                otherImageCaptureActivities) {
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempOutputFile));
            otherImageCaptureIntents.add(captureIntent);
        }

        Intent selectImageIntent = new Intent(Intent.ACTION_PICK);
        selectImageIntent.setType("image/*");

        Intent chooser = Intent.createChooser(selectImageIntent,"Chooser Avatar");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS,otherImageCaptureIntents.toArray(new Parcelable[otherImageCaptureActivities.size()]));
        
        startActivityForResult(chooser, REQUEST_SELECT_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            tempOutputFile.delete();
            return;
        }
        if (requestCode == REQUEST_SELECT_IMAGE) {
            Uri outputFile;
            Uri tempFileUrl = Uri.fromFile(tempOutputFile);
            //从图库获得照片
            if (data != null && (data.getAction() == null || !data.getAction().equals(MediaStore.ACTION_IMAGE_CAPTURE)))
                outputFile = data.getData();
            else//从照相机获得照片
                outputFile = tempFileUrl;

           Crop.of(outputFile,tempFileUrl).asSquare().start(this);
        } else if (requestCode == Crop.REQUEST_CROP) {
            //// TODO: 1/29/16 Send tempFileUri to server as nav avatar
            avatarView.setImageResource(0);
            avatarView.setImageURI(Uri.fromFile(tempOutputFile));
        }
    }
}
