package org.inbloom.content.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.solr.RooSolrSearchable;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooSerializable
@RooJson
@RooSolrSearchable
public class PathwayNode {

    /**
     */
    @ManyToOne
    private Standard standard;

    /**
     */
    @ManyToOne
    private Pathway pathway;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<PathwayNode> priorStep = new ArrayList<PathwayNode>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "priorStep")
    private Set<PathwayNode> nextStep = new HashSet<PathwayNode>();
}
