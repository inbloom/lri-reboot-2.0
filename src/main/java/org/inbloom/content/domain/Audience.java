package org.inbloom.content.domain;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.solr.RooSolrSearchable;
import org.springframework.scheduling.annotation.Async;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooSerializable
@RooJson
@RooSolrSearchable
public class Audience {

    /**
     */
    private String name;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "audience")
    private Set<Resource> resource = new HashSet<Resource>();
    
    @Async
    public static void indexAudiences(Collection<Audience> audiences) {
        List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        for (Audience audience : audiences) {
            SolrInputDocument sid = new SolrInputDocument();
            sid.addField("id", "audience_" + audience.getId());
            sid.addField("audience.name_s", audience.getName());
            sid.addField("audience.id_l", audience.getId());
            // Add summary field to allow searching documents for objects of this type
            sid.addField("audience_solrsummary_t", new StringBuilder().append(audience.getName()).append(" ").append(audience.getId()));
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

}
