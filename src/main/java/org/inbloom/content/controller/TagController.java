package org.inbloom.content.controller;
import java.util.HashSet;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocument;
import org.inbloom.content.domain.Resource;
import org.inbloom.content.domain.Tag;
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

@RequestMapping("/tags")
@Controller
@RooWebScaffold(path = "tags", formBackingObject = Tag.class)
@RooWebJson(jsonObject = Tag.class)
public class TagController {

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        Tag tag = Tag.fromJsonToTag(json);
        tag.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(tag.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/search", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> searchSolr(@RequestParam("query") String query) {
		Set<Tag> tags = new HashSet<Tag>();
		for (SolrDocument document: Tag.search(new SolrQuery(query)).getResults()) {
			Long tagId = (Long) document.getFieldValue("tag.id_l");
			Tag tag = Tag.findTag(tagId);
			tags.add(tag);
		}
		
		JSONSerializer serializer = new JSONSerializer().transform(new IterableTransformer(), Set.class);
		String responseString = serializer.serialize(tags);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
		return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}
	
}
