
package com.talkdesk.mule.internal.contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("contact")
    @Expose
    private Contact_ contact;

    public Contact_ getContact() {
        return contact;
    }

    public void setContact(Contact_ contact) {
        this.contact = contact;
    }

}
