// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.inbloom.content.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.inbloom.content.domain.ResourcePartyPartyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

privileged aspect ResourcePartyPartyType_Roo_SolrSearch {
    
    @Autowired
    transient SolrServer ResourcePartyPartyType.solrServer;
    
    public static QueryResponse ResourcePartyPartyType.search(String queryString) {
        String searchString = "ResourcePartyPartyType_solrsummary_t:" + queryString;
        return search(new SolrQuery(searchString.toLowerCase()));
    }
    
    public static QueryResponse ResourcePartyPartyType.search(SolrQuery query) {
        try {
            return solrServer().query(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new QueryResponse();
    }
    
    public static void ResourcePartyPartyType.indexResourcePartyPartyType(ResourcePartyPartyType resourcePartyPartyType) {
        List<ResourcePartyPartyType> resourcepartypartytypes = new ArrayList<ResourcePartyPartyType>();
        resourcepartypartytypes.add(resourcePartyPartyType);
        indexResourcePartyPartyTypes(resourcepartypartytypes);
    }
    
    @Async
    public static void ResourcePartyPartyType.indexResourcePartyPartyTypes(Collection<ResourcePartyPartyType> resourcepartypartytypes) {
        List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        for (ResourcePartyPartyType resourcePartyPartyType : resourcepartypartytypes) {
            SolrInputDocument sid = new SolrInputDocument();
            sid.addField("id", "resourcepartypartytype_" + resourcePartyPartyType.getId());
            sid.addField("resourcePartyPartyType.resource_t", resourcePartyPartyType.getResource());
            sid.addField("resourcePartyPartyType.party_t", resourcePartyPartyType.getParty());
            sid.addField("resourcePartyPartyType.partytype_t", resourcePartyPartyType.getPartyType());
            sid.addField("resourcePartyPartyType.id_l", resourcePartyPartyType.getId());
            // Add summary field to allow searching documents for objects of this type
            sid.addField("resourcepartypartytype_solrsummary_t", new StringBuilder().append(resourcePartyPartyType.getResource()).append(" ").append(resourcePartyPartyType.getParty()).append(" ").append(resourcePartyPartyType.getPartyType()).append(" ").append(resourcePartyPartyType.getId()));
            documents.add(sid);
        }
        try {
            SolrServer solrServer = solrServer();
            solrServer.add(documents);
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Async
    public static void ResourcePartyPartyType.deleteIndex(ResourcePartyPartyType resourcePartyPartyType) {
        SolrServer solrServer = solrServer();
        try {
            solrServer.deleteById("resourcepartypartytype_" + resourcePartyPartyType.getId());
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostUpdate
    @PostPersist
    private void ResourcePartyPartyType.postPersistOrUpdate() {
        indexResourcePartyPartyType(this);
    }
    
    @PreRemove
    private void ResourcePartyPartyType.preRemove() {
        deleteIndex(this);
    }
    
    public static SolrServer ResourcePartyPartyType.solrServer() {
        SolrServer _solrServer = new ResourcePartyPartyType().solrServer;
        if (_solrServer == null) throw new IllegalStateException("Solr server has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return _solrServer;
    }
    
}
