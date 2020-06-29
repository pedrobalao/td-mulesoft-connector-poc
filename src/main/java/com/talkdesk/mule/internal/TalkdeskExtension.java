package com.talkdesk.mule.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.api.meta.Category;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;


/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "talkdesk")
@ConnectionProviders(TalkdeskConnectionProvider.class)
@Extension(name = "Talkdesk", vendor= "Talkdesk Inc", category=Category.COMMUNITY)
@Operations({TalkdeskOperations.class})
public class TalkdeskExtension {

}
