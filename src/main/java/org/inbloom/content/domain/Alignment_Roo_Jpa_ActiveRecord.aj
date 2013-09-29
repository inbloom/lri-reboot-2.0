// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.inbloom.content.domain.Alignment;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Alignment_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Alignment.entityManager;
    
    public static final EntityManager Alignment.entityManager() {
        EntityManager em = new Alignment().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Alignment.countAlignments() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Alignment o", Long.class).getSingleResult();
    }
    
    public static List<Alignment> Alignment.findAllAlignments() {
        return entityManager().createQuery("SELECT o FROM Alignment o", Alignment.class).getResultList();
    }
    
    public static Alignment Alignment.findAlignment(Long id) {
        if (id == null) return null;
        return entityManager().find(Alignment.class, id);
    }
    
    public static List<Alignment> Alignment.findAlignmentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Alignment o", Alignment.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Alignment.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Alignment.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Alignment attached = Alignment.findAlignment(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Alignment.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Alignment.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Alignment Alignment.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Alignment merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
