package org.joparo.spring.demo.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    ContentRepository repo;

    @GetMapping(value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public Content getContentAsJson(@PathVariable String id) {
        return repo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @GetMapping(value = "/{id}", produces= MediaType.APPLICATION_XML_VALUE)
    public Content getContentAsXml(@PathVariable String id) {
        return repo.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
