// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import java.util.Set;
import org.inbloom.content.domain.Interactivity;
import org.inbloom.content.domain.Resource;

privileged aspect Interactivity_Roo_JavaBean {
    
    public String Interactivity.getName() {
        return this.name;
    }
    
    public void Interactivity.setName(String name) {
        this.name = name;
    }
    
    public Set<Resource> Interactivity.getResource() {
        return this.resource;
    }
    
    public void Interactivity.setResource(Set<Resource> resource) {
        this.resource = resource;
    }
    
}
