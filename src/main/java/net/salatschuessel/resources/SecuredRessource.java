package net.salatschuessel.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredRessource {

  @RequestMapping(value = "test", method = { RequestMethod.POST })
  public String test() {
    return "{\"text\": \"succeeded\"}";
  }

}
