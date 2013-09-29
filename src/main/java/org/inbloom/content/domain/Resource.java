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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.solr.RooSolrSearchable;
import org.springframework.scheduling.annotation.Async;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooSerializable
@RooJson
@RooSolrSearchable
public class Resource {

    /**
     */
    private String name;

    /**
     */
    private String externalGUID;

    /**
     */
    private String URL;

    /**
     */
    private String description;

    /**
     */
    private String copyrightYear;

    /**
     */
    private String useRightsURL;

    /**
     */
    private String isBasedOnURL;

    /**
     */
    private String timeRequired;

    /**
     */
    @ManyToOne
    private Lang lang;

    /**
     */
    @ManyToOne
    private Interactivity interactivity;

    /**
     */
    @ManyToOne
    private LearningResource learningResource;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Audience> audience = new ArrayList<Audience>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tag = new ArrayList<Tag>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Use> use = new ArrayList<Use>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<AgeRange> ageRange = new ArrayList<AgeRange>();

    /**
     */
    private String sourceText;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "resource")
    private Set<Alignment> alignment = new HashSet<Alignment>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "resource")
    private Set<ResourcePartyPartyType> resourcePartyPartyType = new HashSet<ResourcePartyPartyType>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "resource")
    private Set<Activity> activity = new HashSet<Activity>();

	@Async
    public static void indexResources(Collection<Resource> resources) {
        List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        for (Resource resource : resources) {
            SolrInputDocument sid = new SolrInputDocument();
            sid.addField("id", "resource_" + resource.getId());
            sid.addField("resource.name_s", resource.getName());
            sid.addField("resource.externalguid_s", resource.getExternalGUID());
            sid.addField("resource.url_s", resource.getURL());
            sid.addField("resource.description_s", resource.getDescription());
            sid.addField("resource.copyrightyear_s", resource.getCopyrightYear());
            sid.addField("resource.userightsurl_s", resource.getUseRightsURL());
            sid.addField("resource.isbasedonurl_s", resource.getIsBasedOnURL());
            sid.addField("resource.timerequired_s", resource.getTimeRequired());
            sid.addField("resource.lang_s", resource.getLang().getName());
            sid.addField("resource.interactivity_s", resource.getInteractivity().getName());
            sid.addField("resource.learningresource_t", resource.getLearningResource());
            sid.addField("resource.sourcetext_s", resource.getSourceText());
            sid.addField("resource.id_l", resource.getId());
            // Add summary field to allow searching documents for objects of this type
            sid.addField("resource_solrsummary_t", new StringBuilder().append(resource.getName()).append(" ").append(resource.getExternalGUID()).append(" ").append(resource.getURL()).append(" ").append(resource.getDescription()).append(" ").append(resource.getCopyrightYear()).append(" ").append(resource.getUseRightsURL()).append(" ").append(resource.getIsBasedOnURL()).append(" ").append(resource.getTimeRequired()).append(" ").append(resource.getLang().getName()).append(" ").append(resource.getInteractivity().getName()).append(" ").append(resource.getLearningResource()).append(" ").append(resource.getSourceText()).append(" ").append(resource.getId()));
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
