package org.joparo.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    DemoService service;

    @GetMapping("/success")
    public String sparaNoException() {
        this.service.sparaDemo();
        return "Beslut sparat!";
    }

    @GetMapping("/exception-tr")
    public String sparaTransactionalException() {
        try {
            this.service.sparaDemoTransactionalException();
        } catch (RuntimeException r) {
            return "Det blev fel. Inget demo sparat";
        }
        return "Jag är en tekanna";
    }

    @GetMapping("/exception")
    public String sparaException() {
        try {
            this.service.sparaDemoNonTransactionalException();
        } catch (RuntimeException r) {
            return "Det blev fel. Men det fanns ingen transaktion. Demo sparat";
        }
        return "Jag är en tekanna";
    }

    @GetMapping("/uppdatera/{id}")
    public String uppdatera(@PathVariable Long id) {
       this.service.update(id);
       return "Demo uppdaterades inte, för det fanns ingen transaktion.";
    }

    @GetMapping("/uppdatera/{id}/tr")
    public String uppdateraMedTransaktion(@PathVariable Long id) {
        this.service.updateTransactional(id);
        return "Demo uppdaterades";
    }

}
