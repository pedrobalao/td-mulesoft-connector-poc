package com.talkdesk.mule.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.InputStream;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import com.talkdesk.mule.internal.contact.Contact;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class TalkdeskOperations {

  
  @MediaType(value = ANY, strict = false)
  @DisplayName("List account contacts")
  public InputStream getContacts(@Connection TalkdeskConnection connection){
	  return connection.getContacts() ;
	  //return "List account contacts";
  } 
  
  
  @MediaType(value = ANY, strict = false)
  @DisplayName("Read Contact")
  public Contact getContact(@Connection TalkdeskConnection connection, String contactId){
	  return connection.getContact(contactId) ;
	  //return "List account contacts";
  }
}
