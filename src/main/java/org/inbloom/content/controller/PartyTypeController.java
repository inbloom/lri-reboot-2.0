package org.inbloom.content.controller;
import java.util.HashSet;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocument;
import org.inbloom.content.domain.PartyType;
import org.inbloom.content.domain.Resource;
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

@RequestMapping("/partytypes")
@Controller
@RooWebScaffold(path = "partytypes", formBackingObject = PartyType.class)
@RooWebJson(jsonObject = PartyType.class)
public class PartyTypeController {

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        PartyType partyType = PartyType.fromJsonToPartyType(json);
        partyType.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(partyType.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/search", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> searchSolr(@RequestParam("query") String query) {
		Set<PartyType> partyTypes = new HashSet<PartyType>();
		for (SolrDocument document: PartyType.search(new SolrQuery(query)).getResults()) {
			Long partyTypeId = (Long) document.getFieldValue("partyType.id_l");
			PartyType partyType = PartyType.findPartyType(partyTypeId);
			partyTypes.add(partyType);
		}
		
		JSONSerializer serializer = new JSONSerializer().transform(new IterableTransformer(), Set.class);
		String responseString = serializer.serialize(partyTypes);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
		return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}

}
