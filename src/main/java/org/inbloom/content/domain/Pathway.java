package org.inbloom.content.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.util.HashSet;
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
public class Pathway {

    /**
     */
    private String name;

    /**
     */
    @ManyToOne
    private Standard standard;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "pathway")
    private Set<PathwayNode> pathwayNode = new HashSet<PathwayNode>();
}
