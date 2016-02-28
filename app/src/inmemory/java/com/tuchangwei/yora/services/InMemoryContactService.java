package com.tuchangwei.yora.services;

import android.content.Intent;

import com.squareup.otto.Subscribe;
import com.tuchangwei.yora.infrastructure.YoraApplication;
import com.tuchangwei.yora.services.entities.ContactRequest;
import com.tuchangwei.yora.services.entities.UserDetails;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by vale on 2/23/16.
 */
public class InMemoryContactService extends BaseInMemoryService {
    public InMemoryContactService(YoraApplication application) {
        super(application);
    }

    @Subscribe
    public void getContactRequests(Contacts.GetContactRequestsRequest request) {
        Contacts.GetContactRequestsResponse response = new Contacts.GetContactRequestsResponse();
        response.Requests = new ArrayList<>();
        for (int i=0; i< 3; i++) {
            response.Requests.add(new ContactRequest(i, request.FromUs,createFakeUser(i,false),new GregorianCalendar()));
        }
        postDelayed(response);
    }

    @Subscribe
    public void getContacts(Contacts.GetContactsRequest request){
        Contacts.GetContactsResponse response =  new Contacts.GetContactsResponse();
        response.contacts = new ArrayList<>();
        for (int i=0;i <10; i++){
            response.contacts.add(createFakeUser(i,true));
        }
        postDelayed(response);
    }
    @Subscribe
    public void sendContactRequest(Contacts.SendContactRequestRequest request) {
        if (request.UserId==2) {

            Contacts.SendContactRequestResponse response = new Contacts.SendContactRequestResponse();
            response.setOperationError("Something bad happened!");
        } else {
            postDelayed(new Contacts.SendContactRequestResponse());
        }
    }
    @Subscribe
    public void respondToContactsRequest(Contacts.RespondToContactRequestRequest request){
        postDelayed(new Contacts.RespondToContactRequestResponse() );
    }
    private UserDetails createFakeUser(int id, boolean isContact) {
        String idString = Integer.toString(id);
        return new UserDetails(
                id,
                isContact,
                "Contact " + idString,
                "Contact " + idString,
                "http://www.gravatar.com/avatar/" + idString + "?d=identicon&s=64"
        );
    }
}
