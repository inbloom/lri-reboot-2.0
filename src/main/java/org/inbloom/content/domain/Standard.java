package org.inbloom.content.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.solr.RooSolrSearchable;

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
}
