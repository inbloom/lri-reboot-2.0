// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.inbloom.content.domain.PathwayNode;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PathwayNode_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PathwayNode.entityManager;
    
    public static final EntityManager PathwayNode.entityManager() {
        EntityManager em = new PathwayNode().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PathwayNode.countPathwayNodes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PathwayNode o", Long.class).getSingleResult();
    }
    
    public static List<PathwayNode> PathwayNode.findAllPathwayNodes() {
        return entityManager().createQuery("SELECT o FROM PathwayNode o", PathwayNode.class).getResultList();
    }
    
    public static PathwayNode PathwayNode.findPathwayNode(Long id) {
        if (id == null) return null;
        return entityManager().find(PathwayNode.class, id);
    }
    
    public static List<PathwayNode> PathwayNode.findPathwayNodeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PathwayNode o", PathwayNode.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PathwayNode.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PathwayNode.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PathwayNode attached = PathwayNode.findPathwayNode(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PathwayNode.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PathwayNode.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PathwayNode PathwayNode.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PathwayNode merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
