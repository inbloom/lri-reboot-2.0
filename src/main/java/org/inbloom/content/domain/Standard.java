package org.inbloom.content.domain;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;

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
public class Standard {

    /**
     */
    private String name;

    /**
     */
    private String url;

    /**
     */
    private String externalId;

    /**
     */
    @ManyToOne
    private Standard parent;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private Set<Standard> child = new HashSet<Standard>();

    /**
     */
    private String heading;

    /**
     */
    private String subheading;

    /**
     */
    private String standard_text;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "standard")
    private Set<Alignment> alignment = new HashSet<Alignment>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "standard")
    private Set<Pathway> pathway = new HashSet<Pathway>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "standard")
    private Set<PathwayNode> pathwayNode = new HashSet<PathwayNode>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "standard")
    private Set<LearningMap> learningMap = new HashSet<LearningMap>();

    public static List<Standard> findStandardsWithNoParent() {
        return entityManager().createQuery("SELECT o FROM Standard o WHERE parent IS NULL", Standard.class).getResultList();
    }
    
    @Async
    public static void indexStandards(Collection<Standard> standards) {
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

}
