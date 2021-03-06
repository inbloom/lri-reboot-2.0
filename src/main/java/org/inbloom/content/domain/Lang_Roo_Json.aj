// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.inbloom.content.domain.Lang;

privileged aspect Lang_Roo_Json {
    
    public String Lang.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public String Lang.toJson(String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
    }
    
    public static Lang Lang.fromJsonToLang(String json) {
        return new JSONDeserializer<Lang>().use(null, Lang.class).deserialize(json);
    }
    
    public static String Lang.toJsonArray(Collection<Lang> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static String Lang.toJsonArray(Collection<Lang> collection, String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Lang> Lang.fromJsonArrayToLangs(String json) {
        return new JSONDeserializer<List<Lang>>().use(null, ArrayList.class).use("values", Lang.class).deserialize(json);
    }
    
}
