package org.inbloom.content.controller;
import org.inbloom.content.domain.ResourcePartyPartyType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/resourcepartypartytypes")
@Controller
@RooWebScaffold(path = "resourcepartypartytypes", formBackingObject = ResourcePartyPartyType.class)
@RooWebJson(jsonObject = ResourcePartyPartyType.class)
public class ResourcePartyPartyTypeController {

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        ResourcePartyPartyType resourcePartyPartyType = ResourcePartyPartyType.fromJsonToResourcePartyPartyType(json);
        resourcePartyPartyType.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(resourcePartyPartyType.getId().toString()).build().toString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
