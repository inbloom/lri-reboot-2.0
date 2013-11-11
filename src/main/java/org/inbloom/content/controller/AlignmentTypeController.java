package org.inbloom.content.controller;
import java.util.HashSet;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocument;
import org.inbloom.content.domain.AlignmentType;
import org.inbloom.content.domain.Standard;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

import flexjson.JSONSerializer;
import flexjson.transformer.IterableTransformer;

@RequestMapping("/alignmenttypes")
@Controller
@RooWebScaffold(path = "alignmenttypes", formBackingObject = AlignmentType.class)
@RooWebJson(jsonObject = AlignmentType.class)
public class AlignmentTypeController {

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        AlignmentType alignmentType = AlignmentType.fromJsonToAlignmentType(json);
        alignmentType.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(alignmentType.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/search", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> searchSolr(@RequestParam("query") String query) {
		Set<AlignmentType> alignmentTypes = new HashSet<AlignmentType>();
		for (SolrDocument document: AlignmentType.search(new SolrQuery(query)).getResults()) {
			Long alignmentTypeId = (Long) document.getFieldValue("alignmentType.id_l");
			AlignmentType alignmentType = AlignmentType.findAlignmentType(alignmentTypeId);
			alignmentTypes.add(alignmentType);
		}
		
		JSONSerializer serializer = new JSONSerializer().transform(new IterableTransformer(), Set.class);
		String responseString = serializer.serialize(alignmentTypes);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
		return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}
}
