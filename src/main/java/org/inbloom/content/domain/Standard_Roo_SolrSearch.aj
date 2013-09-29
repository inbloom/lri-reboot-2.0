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
import org.inbloom.content.domain.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

privileged aspect Standard_Roo_SolrSearch {
    
    @Autowired
    transient SolrServer Standard.solrServer;
    
    public static QueryResponse Standard.search(String queryString) {
        String searchString = "Standard_solrsummary_t:" + queryString;
        return search(new SolrQuery(searchString.toLowerCase()));
    }
    
    public static QueryResponse Standard.search(SolrQuery query) {
        try {
            return solrServer().query(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new QueryResponse();
    }
    
    public static void Standard.indexStandard(Standard standard) {
        List<Standard> standards = new ArrayList<Standard>();
        standards.add(standard);
        indexStandards(standards);
    }
    
    @Async
    public static void Standard.indexStandards(Collection<Standard> standards) {
        List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        for (Standard standard : standards) {
            SolrInputDocument sid = new SolrInputDocument();
            sid.addField("id", "standard_" + standard.getId());
            sid.addField("standard.name_s", standard.getName());
            sid.addField("standard.url_s", standard.getUrl());
            sid.addField("standard.externalid_s", standard.getExternalId());
            sid.addField("standard.parent_t", standard.getParent());
            sid.addField("standard.heading_s", standard.getHeading());
            sid.addField("standard.subheading_s", standard.getSubheading());
            sid.addField("standard.standard_text_s", standard.getStandard_text());
            sid.addField("standard.id_l", standard.getId());
            // Add summary field to allow searching documents for objects of this type
            sid.addField("standard_solrsummary_t", new StringBuilder().append(standard.getName()).append(" ").append(standard.getUrl()).append(" ").append(standard.getExternalId()).append(" ").append(standard.getParent()).append(" ").append(standard.getHeading()).append(" ").append(standard.getSubheading()).append(" ").append(standard.getStandard_text()).append(" ").append(standard.getId()));
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
    public static void Standard.deleteIndex(Standard standard) {
        SolrServer solrServer = solrServer();
        try {
            solrServer.deleteById("standard_" + standard.getId());
            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostUpdate
    @PostPersist
    private void Standard.postPersistOrUpdate() {
        indexStandard(this);
    }
    
    @PreRemove
    private void Standard.preRemove() {
        deleteIndex(this);
    }
    
    public static SolrServer Standard.solrServer() {
        SolrServer _solrServer = new Standard().solrServer;
        if (_solrServer == null) throw new IllegalStateException("Solr server has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return _solrServer;
    }
    
}
