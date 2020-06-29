package com.talkdesk.mule.internal.contact;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact_ {

@SerializedName("id")
@Expose
private String id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("company")
@Expose
private String company;
@SerializedName("phones")
@Expose
private List<Phone> phones = null;
@SerializedName("_links")
@Expose
private Links links;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getCompany() {
return company;
}

public void setCompany(String company) {
this.company = company;
}

public List<Phone> getPhones() {
return phones;
}

public void setPhones(List<Phone> phones) {
this.phones = phones;
}

public Links getLinks() {
return links;
}

public void setLinks(Links links) {
this.links = links;
}

}
