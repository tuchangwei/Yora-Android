package com.tuchangwei.yora.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.squareup.otto.Subscribe;
import com.tuchangwei.yora.R;
import com.tuchangwei.yora.activities.BaseActivity;
import com.tuchangwei.yora.services.Contacts;
import com.tuchangwei.yora.views.ContactRequestAdapter;

/**
 * Created by vale on 2/23/16.
 */
public class PendingContactRequestsFragment extends BaseFragment {
    private View progressFrame;
    private ContactRequestAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_contact_requests,container,false);
        progressFrame = view.findViewById(R.id.fragment_pending_contact_requests_progressFame);
        adapter = new ContactRequestAdapter((BaseActivity)getActivity());
        ListView listView = (ListView)view.findViewById(R.id.fragment_pending_contact_requests_list);
        listView.setAdapter(adapter);

        bus.post(new Contacts.GetContactRequestsRequest(true));
        return view;
    }
    @Subscribe
    public void onGetContactRequests(Contacts.GetContactRequestsResponse response) {
        response.showErrorToast(getActivity());

        progressFrame.animate()
                .alpha(0)
                .setDuration(250)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        progressFrame.setVisibility(View.GONE);
                    }
                })
                .start();
        adapter.clear();
        adapter.addAll(response.Requests);
    }
}
