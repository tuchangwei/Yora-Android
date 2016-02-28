package com.tuchangwei.yora.services;

import com.tuchangwei.yora.infrastructure.ServiceResponse;
import com.tuchangwei.yora.services.entities.ContactRequest;
import com.tuchangwei.yora.services.entities.UserDetails;

import java.util.List;

/**
 * Created by vale on 2/23/16.
 */
public final class Contacts {
    private Contacts() {

    }
    public static class GetContactRequestsRequest {
        public boolean FromUs;

        public GetContactRequestsRequest(boolean fromUs) {
            this.FromUs = fromUs;
        }
    }
    public static class GetContactRequestsResponse extends ServiceResponse {
        public List<ContactRequest> Requests;
    }
    public static class GetContactsRequest {
        public boolean IncludePendingContacts;

        public GetContactsRequest(boolean includePendingContacts) {
            IncludePendingContacts = includePendingContacts;
        }
    }
    public static class GetContactsResponse extends ServiceResponse{
        public List<UserDetails> contacts;

    }
    public static class SendContactRequestRequest {
        public int UserId;

        public SendContactRequestRequest(int userId) {
            UserId = userId;
        }
    }
    public static class SendContactRequestResponse extends ServiceResponse {
    }
    public static class RespondToContactRequestRequest {
        public int ContactRequestId;
        public boolean Accept;

        public RespondToContactRequestRequest(int contactRequestId, boolean accept) {
            ContactRequestId = contactRequestId;
            Accept = accept;
        }
    }
    public static class RespondToContactRequestResponse extends ServiceResponse {


    }
}
