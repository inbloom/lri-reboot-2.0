package org.inbloom.content.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.inbloom.content.domain.Pathway;
import org.inbloom.content.domain.PathwayNode;
import org.inbloom.content.domain.Standard;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import flexjson.JSONSerializer;
import flexjson.transformer.IterableTransformer;

@RequestMapping("/standards")
@Controller
@RooWebScaffold(path = "standards", formBackingObject = Standard.class)
@RooWebJson(jsonObject = Standard.class)
public class StandardController {
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Standard> result = Standard.findStandardsWithNoParent();
        return new ResponseEntity<String>(Standard.toJsonArray(result), headers, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{id}/pathwaynodes", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getPathwayNodesJson(@PathVariable("id") Long id) {
		Standard standard = Standard.findStandard(id);
		Set<PathwayNode> pathwayNodes = standard.getPathwayNode();
		JSONSerializer serializer = new JSONSerializer().transform(new IterableTransformer(), Set.class); //.include("id","framework","heading","subheading","externalId","parent.id").exclude("*");
		String responseString = serializer.serialize(pathwayNodes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}

	/**
	@RequestMapping(value = "/{id}/pathways", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getPathwaysJson(@PathVariable("id") Long id) {
		Standard standard = Standard.findStandard(id);
		Set<PathwayNode> pathwayNodes = standard.getPathwayNode();
		Set<Pathway> pathways = new HashSet<Pathway>(); // Why does it have to be a HashSet? It just does...
		for (PathwayNode pathwayNode : pathwayNodes) {
			Pathway pathway = pathwayNode.getPathway();
			pathways.add(pathway);
		}
		JSONSerializer serializer = new JSONSerializer().transform(new IterableTransformer(), Set.class); //.include("id","framework","heading","subheading","externalId","parent.id").exclude("*");
		String responseString = serializer.serialize(pathways);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}
*/
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> postStandardsJsonWithParents(@PathVariable("id") Long id, @RequestBody String json) {
		Standard standard2 = Standard.fromJsonToStandard(json);
		Standard standard1 = Standard.findStandard(id);
		standard2.setParent(standard1);
		standard1.getChild().add(standard2);
		standard2.persist();
		standard1.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentServletMapping().path("/standards/").path(standard2.getId().toString()).build().toString());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// The following methods were adapted from the removed FrameworkController.java
	@RequestMapping(value = "/{id}/standards", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getStandardsJson(@PathVariable("id") Long id) {
		//TODO: This is a nasty hack and needs to be fixed!
		Standard framework = Standard.findStandard(id);
		Set<Standard> standards = new HashSet<Standard>(); //framework.getChild();
		standards.add(framework);
		JSONSerializer serializer = new JSONSerializer().transform(new IterableTransformer(), Set.class).include(
				"*.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child.child",
				"*.child.child.child.child.child.child",
				"*.child.child.child.child.child",
				"*.child.child.child.child",
				"*.child.child.child",
				"*.child.child",
				"*.child",
				"*.id","*.externalId","*.heading","*.subheading","*.standard_text").exclude("*");
		String responseString = serializer.serialize(standards);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/pathways", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getPathwaysJson(@PathVariable("id") Long id) {
		Standard framework = Standard.findStandard(id);
		Set<Pathway> pathways = framework.getPathway();
		JSONSerializer serializer = new JSONSerializer().transform(new IterableTransformer(), Set.class);
				//.include("id","framework","heading","subheading","externalId","parent.id").exclude("*");
		String responseString = serializer.serialize(pathways);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/pathways", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> postPathwaysJson(@PathVariable("id") Long id, @RequestBody String json) {
		Pathway pathway = Pathway.fromJsonToPathway(json);
		Standard framework = Standard.findStandard(id);
		pathway.setStandard(framework);
		framework.getPathway().add(pathway);
		pathway.persist();
		framework.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/pathways/").path(framework.getId().toString()).build().toString());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);		
	}

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        Standard standard = Standard.fromJsonToStandard(json);
        standard.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(standard.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
