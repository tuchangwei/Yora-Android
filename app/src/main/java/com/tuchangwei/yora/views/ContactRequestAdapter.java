package com.tuchangwei.yora.views;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tuchangwei.yora.R;
import com.tuchangwei.yora.activities.BaseActivity;
import com.tuchangwei.yora.services.entities.ContactRequest;

import org.w3c.dom.Text;

/**
 * Created by vale on 3/1/16.
 */
public class ContactRequestAdapter extends ArrayAdapter<ContactRequest> {
    private LayoutInflater inflater;
    public ContactRequestAdapter(BaseActivity activity) {
        super(activity, 0);
        inflater = activity.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactRequest request = getItem(position);

        ViewHolder holder;
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.list_item_contact_request,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else  {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.DisplayName.setText(request.getUser().getDisplayName());
        String avatarUrl = request.getUser().getAvatarUrl();
        Picasso.with(getContext()).load(avatarUrl).into(holder.Avatar, new Callback() {
            @Override
            public void onSuccess() {
                Log.e(ContactRequestAdapter.this.getClass().getSimpleName(),"Success");
            }

            @Override
            public void onError() {
                Log.e(ContactRequestAdapter.this.getClass().getSimpleName(),"Error");
            }
        });

        String createdAt = DateUtils.formatDateTime(getContext(),
                request.getCreateAt().getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_SHOW_TIME);
        if (request.isFromUs()) {
            holder.CreatedAt.setText("Sent at " + createdAt);
        } else {
            holder.CreatedAt.setText("Received " + createdAt);
        }
        return convertView;
    }

    private class ViewHolder {
        public TextView DisplayName;
        public TextView CreatedAt;
        public ImageView Avatar;
        public ViewHolder(View view){
            DisplayName = (TextView)view.findViewById(R.id.list_item_contact_request_displayName);
            CreatedAt = (TextView)view.findViewById(R.id.list_item_contact_request_createdAt);
            Avatar = (ImageView) view.findViewById(R.id.list_item_contact_request_avatar);
        }
    }
}
