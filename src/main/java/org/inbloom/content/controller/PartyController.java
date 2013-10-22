package org.inbloom.content.controller;
import java.util.HashSet;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocument;
import org.inbloom.content.domain.Party;
import org.inbloom.content.domain.PartyType;
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

@RequestMapping("/partys")
@Controller
@RooWebScaffold(path = "partys", formBackingObject = Party.class)
@RooWebJson(jsonObject = Party.class)
public class PartyController {

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        Party party = Party.fromJsonToParty(json);
        party.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(party.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/search", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> searchSolr(@RequestParam("query") String query) {
		Set<Party> partys = new HashSet<Party>();
		for (SolrDocument document: Party.search(new SolrQuery(query)).getResults()) {
			Long partyId = (Long) document.getFieldValue("party.id_l");
			Party party = Party.findParty(partyId);
			partys.add(party);
		}
		
		JSONSerializer serializer = new JSONSerializer().transform(new IterableTransformer(), Set.class);
		String responseString = serializer.serialize(partys);
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json");
		return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}
}
