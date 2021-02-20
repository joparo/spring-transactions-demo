package org.joparo.spring.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("allmighty")
@Primary
public class AllMightyServiceImpl implements MightyService {
}
