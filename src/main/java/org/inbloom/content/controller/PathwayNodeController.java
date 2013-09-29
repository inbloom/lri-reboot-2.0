package org.inbloom.content.controller;
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

@RequestMapping("/pathwaynodes")
@Controller
@RooWebScaffold(path = "pathwaynodes", formBackingObject = PathwayNode.class)
@RooWebJson(jsonObject = PathwayNode.class)
public class PathwayNodeController {
	@RequestMapping(value = "/{id}/standards", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getStandardsJson(@PathVariable("id") Long id) {
		PathwayNode pathwayNode = PathwayNode.findPathwayNode(id);
		Standard standard = pathwayNode.getStandard();
		String responseString = standard.toJson();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(responseString, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id1}/pathwaynodes/{id2}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> putPathwayNodesWithParentsJson(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2, @RequestBody String json) {
        PathwayNode pathwayNode1 = PathwayNode.findPathwayNode(id1);
        PathwayNode pathwayNode2 = PathwayNode.findPathwayNode(id2);
        pathwayNode1.getNextStep().add(pathwayNode2);
        pathwayNode2.getPriorStep().add(pathwayNode1);
        pathwayNode1.persist();
        pathwayNode2.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }


	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        PathwayNode pathwayNode = PathwayNode.fromJsonToPathwayNode(json);
        pathwayNode.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(pathwayNode.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
