
package com.talkdesk.mule.internal.contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("integrations")
    @Expose
    private Integrations integrations;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Integrations getIntegrations() {
        return integrations;
    }

    public void setIntegrations(Integrations integrations) {
        this.integrations = integrations;
    }

}
