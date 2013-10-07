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
public class AlignmentType {

    /**
     */
    private String name;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "alignmentType")
    private Set<Alignment> alignment = new HashSet<Alignment>();
    
    @Async
    public static void indexAlignmentTypes(Collection<AlignmentType> alignmenttypes) {
        List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        for (AlignmentType alignmentType : alignmenttypes) {
            SolrInputDocument sid = new SolrInputDocument();
            sid.addField("id", "alignmenttype_" + alignmentType.getId());
            sid.addField("alignmentType.name_s", alignmentType.getName());
            sid.addField("alignmentType.id_l", alignmentType.getId());
            // Add summary field to allow searching documents for objects of this type
            sid.addField("alignmenttype_solrsummary_t", new StringBuilder().append(alignmentType.getName()).append(" ").append(alignmentType.getId()));
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
