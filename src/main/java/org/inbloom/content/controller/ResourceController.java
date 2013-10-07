package org.inbloom.content.controller;
import java.util.HashSet;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocument;
import org.inbloom.content.domain.Activity;
import org.inbloom.content.domain.AgeRange;
import org.inbloom.content.domain.Audience;
import org.inbloom.content.domain.Interactivity;
import org.inbloom.content.domain.Lang;
import org.inbloom.content.domain.LearningResource;
import org.inbloom.content.domain.Party;
import org.inbloom.content.domain.PartyType;
import org.inbloom.content.domain.Resource;
import org.inbloom.content.domain.ResourcePartyPartyType;
import org.inbloom.content.domain.Tag;
import org.inbloom.content.domain.Use;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

import flexjson.JSONSerializer;
import flexjson.transformer.IterableTransformer;

@RequestMapping("/resources")
@Controller
@RooWebScaffold(path = "resources", formBackingObject = Resource.class)
@RooWebJson(jsonObject = Resource.class)
public class ResourceController {
	@RequestMapping(value = "/{id1}/langs/{id2}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> putResourcesWithLangsJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @RequestBody String json) {
        Resource resource = Resource.findResource(id1);
        Lang lang = Lang.findLang(id2);
        resource.setLang(lang);
        lang.getResource().add(resource);
        resource.persist();
        lang.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id1}/ageranges/{id2}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> putResourcesWithAgeRangesJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @RequestBody String json) {
        Resource resource = Resource.findResource(id1);
        AgeRange ageRange = AgeRange.findAgeRange(id2);
        resource.getAgeRange().add(ageRange);
        ageRange.getResource().add(resource);
        resource.persist();
        ageRange.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id1}/activitys/{id2}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> putResourcesWithActivitysJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @RequestBody String json) {
        Resource resource = Resource.findResource(id1);
        Activity activity = Activity.findActivity(id2);
        resource.getActivity().add(activity);
        activity.setResource(resource);
        resource.persist();
        activity.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id1}/uses/{id2}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> putResourcesWithUsesJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @RequestBody String json) {
        Resource resource = Resource.findResource(id1);
        Use use = Use.findUse(id2);
        resource.getUse().add(use);
        use.getResource().add(resource);
        resource.persist();
        use.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id1}/audiences/{id2}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> putResourcesWithAudiencesJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @RequestBody String json) {
        Resource resource = Resource.findResource(id1);
        Audience audience = Audience.findAudience(id2);
        resource.getAudience().add(audience);
        audience.getResource().add(resource);
        resource.persist();
        audience.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id1}/tags/{id2}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> putResourcesWithTagsJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @RequestBody String json) {
        Resource resource = Resource.findResource(id1);
        Tag tag = Tag.findTag(id2);
        resource.getTag().add(tag);
        tag.getResource().add(resource);
        resource.persist();
        tag.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id1}/learningresources/{id2}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> putResourcesWithLearningResourcesJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @RequestBody String json) {
        Resource resource = Resource.findResource(id1);
        LearningResource learningResource = LearningResource.findLearningResource(id2);
        resource.setLearningResource(learningResource);
        learningResource.getResource().add(resource);
        resource.persist();
        learningResource.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id1}/interactivitys/{id2}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> putResourcesWithInteractivitysJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @RequestBody String json) {
        Resource resource = Resource.findResource(id1);
        Interactivity interactivity = Interactivity.findInteractivity(id2);
        resource.setInteractivity(interactivity);
        interactivity.getResource().add(resource);
        resource.persist();
        interactivity.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id1}/partys/{id2}/partytypes/{id3}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> putResourcesWithPartysJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @PathVariable("id3") Long id3, @RequestBody String json) {
		Resource resource = Resource.findResource(id1);
		Party party = Party.findParty(id2);
		PartyType partyType = PartyType.findPartyType(id3);
		ResourcePartyPartyType resourcePartyPartyType = new ResourcePartyPartyType();
		resourcePartyPartyType.setParty(party);
		resourcePartyPartyType.setPartyType(partyType);
		resourcePartyPartyType.setResource(resource);
		resource.getResourcePartyPartyType().add(resourcePartyPartyType);
		party.getResourcePartyPartyType().add(resourcePartyPartyType);
		partyType.getResourcePartyPartyType().add(resourcePartyPartyType);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);		
	}

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        Resource resource = Resource.fromJsonToResource(json);
        resource.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(resource.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/search", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> searchSolr(@RequestParam("query") String query) {
		Set<Resource> resources = new HashSet<Resource>();
		for (SolrDocument document: Resource.search(new SolrQuery(query)).getResults()) {
			Long resourceId = (Long) document.getFieldValue("resource.id_l");
			Resource resource = Resource.findResource(resourceId);
			resources.add(resource);
		}
		
		JSONSerializer serializer = new JSONSerializer().transform(new IterableTransformer(), Set.class);
		String responseString = serializer.serialize(resources);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
		return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}
}
