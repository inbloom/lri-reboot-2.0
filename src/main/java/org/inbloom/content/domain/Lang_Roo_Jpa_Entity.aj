// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import org.inbloom.content.domain.Lang;

privileged aspect Lang_Roo_Jpa_Entity {
    
    declare @type: Lang: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Lang.id;
    
    @Version
    @Column(name = "version")
    private Integer Lang.version;
    
    public Long Lang.getId() {
        return this.id;
    }
    
    public void Lang.setId(Long id) {
        this.id = id;
    }
    
    public Integer Lang.getVersion() {
        return this.version;
    }
    
    public void Lang.setVersion(Integer version) {
        this.version = version;
    }
    
}
