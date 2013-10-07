package org.inbloom.content.domain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;

import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.solr.RooSolrSearchable;
import org.springframework.scheduling.annotation.Async;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooSerializable
@RooJson
@RooSolrSearchable
public class Alignment {

    /**
     */
    @ManyToOne
    private Standard standard;

    /**
     */
    @ManyToOne
    private Resource resource;

    /**
     */
    @ManyToOne
    private AlignmentType alignmentType;
    
    @Async
    public static void indexAlignments(Collection<Alignment> alignments) {
        List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        for (Alignment alignment : alignments) {
            SolrInputDocument sid = new SolrInputDocument();
            sid.addField("id", "alignment_" + alignment.getId());
            sid.addField("alignment.standard_t", alignment.getStandard());
            sid.addField("alignment.resource_t", alignment.getResource());
            sid.addField("alignment.alignmenttype_t", alignment.getAlignmentType());
            sid.addField("alignment.id_l", alignment.getId());
            // Add summary field to allow searching documents for objects of this type
            sid.addField("alignment_solrsummary_t", new StringBuilder().append(alignment.getStandard()).append(" ").append(alignment.getResource()).append(" ").append(alignment.getAlignmentType()).append(" ").append(alignment.getId()));
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
