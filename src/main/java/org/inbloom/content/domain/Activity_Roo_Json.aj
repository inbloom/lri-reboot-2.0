// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.inbloom.content.domain.Activity;

privileged aspect Activity_Roo_Json {
    
    public String Activity.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public String Activity.toJson(String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
    }
    
    public static Activity Activity.fromJsonToActivity(String json) {
        return new JSONDeserializer<Activity>().use(null, Activity.class).deserialize(json);
    }
    
    public static String Activity.toJsonArray(Collection<Activity> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static String Activity.toJsonArray(Collection<Activity> collection, String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Activity> Activity.fromJsonArrayToActivitys(String json) {
        return new JSONDeserializer<List<Activity>>().use(null, ArrayList.class).use("values", Activity.class).deserialize(json);
    }
    
}
