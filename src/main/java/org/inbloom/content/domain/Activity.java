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
public class Activity {

    /**
     */
    @ManyToOne
    private Resource resource;

    /**
     */
    private String actor;

    /**
     */
    private String verb;

    /**
     */
    private String val;
    
    @Async
    public static void indexActivitys(Collection<Activity> activitys) {
        List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        for (Activity activity : activitys) {
            SolrInputDocument sid = new SolrInputDocument();
            sid.addField("id", "activity_" + activity.getId());
            sid.addField("activity.resource_t", activity.getResource());
            sid.addField("activity.actor_s", activity.getActor());
            sid.addField("activity.verb_s", activity.getVerb());
            sid.addField("activity.val_s", activity.getVal());
            sid.addField("activity.id_l", activity.getId());
            // Add summary field to allow searching documents for objects of this type
            sid.addField("activity_solrsummary_t", new StringBuilder().append(activity.getResource()).append(" ").append(activity.getActor()).append(" ").append(activity.getVerb()).append(" ").append(activity.getVal()).append(" ").append(activity.getId()));
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
