package org.joparo.spring.demo.content;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContentRepository {

    final List<Content> contentList = new ArrayList<>();
    {
        Content c = new Content();
        c.setFirstname("John");
        c.setLastname("Doe");
        c.setId("1");
        contentList.add(c);
    }

    public Optional<Content> findById(String id) {
        return contentList.stream().filter(c -> c.getId().equals(id)).findAny();
    }

}
