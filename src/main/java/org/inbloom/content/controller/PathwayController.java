package org.inbloom.content.controller;
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

@RequestMapping("/pathways")
@Controller
@RooWebScaffold(path = "pathways", formBackingObject = Pathway.class)
@RooWebJson(jsonObject = Pathway.class)
public class PathwayController {
	@RequestMapping(value = "/{id}/standards", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getFrameworksJson(@PathVariable("id") Long id) {
		Pathway pathway = Pathway.findPathway(id);
		Standard standard = pathway.getStandard();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(standard.toJson(), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/pathwaynodes", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getPathwayNodesJson(@PathVariable("id") Long id) {
		Pathway pathway = Pathway.findPathway(id);
		Set<PathwayNode> pathwayNodes = pathway.getPathwayNode();
		JSONSerializer serializer = new JSONSerializer().transform(new IterableTransformer(), Set.class); //.include("id","framework","heading","subheading","externalId","parent.id").exclude("*");
		String responseString = serializer.serialize(pathwayNodes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id1}/standards/{id2}/pathwaynodes", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> postPathwayNodesJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @RequestBody String json) {
        PathwayNode pathwayNode = PathwayNode.fromJsonToPathwayNode(json);
        Pathway pathway = Pathway.findPathway(id1);
        Standard standard = Standard.findStandard(id2);
        pathway.getPathwayNode().add(pathwayNode);
        standard.getPathwayNode().add(pathwayNode);
        pathwayNode.setPathway(pathway);
        pathwayNode.setStandard(standard);
        pathwayNode.persist();
        standard.persist();
        pathway.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/pathwaynodes/").path(pathwayNode.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id1}/standards/{id2}/pathwaynodes/{id3}", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> postPathwayNodesWithParentsJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @PathVariable("id3") Long id3, @RequestBody String json) {
        PathwayNode pathwayNode = PathwayNode.fromJsonToPathwayNode(json);
        Pathway pathway = Pathway.findPathway(id1);
        Standard standard = Standard.findStandard(id2);
        PathwayNode pathwayNode2 = PathwayNode.findPathwayNode(id3);
        pathway.getPathwayNode().add(pathwayNode);
        standard.getPathwayNode().add(pathwayNode);
        pathwayNode2.getNextStep().add(pathwayNode);
        pathwayNode.setPathway(pathway);
        pathwayNode.setStandard(standard);
        pathwayNode.getPriorStep().add(pathwayNode2);
        pathwayNode.persist();
        standard.persist();
        pathway.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/pathwaynodes/").path(pathwayNode.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        Pathway pathway = Pathway.fromJsonToPathway(json);
        pathway.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(pathway.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
