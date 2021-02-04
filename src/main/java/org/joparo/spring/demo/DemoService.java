package org.joparo.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class DemoService {

    @Autowired
    DemoRepository repo;

    @Transactional
    public void sparaDemo() {
        Demo b = new Demo();
        b.setName("Demo!");
        repo.save(b);
    }

    /**
     * Transaktionen kommer rullas tillbaka. Inget sparas i DB.
     */
    @Transactional
    public void sparaDemoTransactionalException() {
        Demo b = new Demo();
        b.setName("Demo, kommer rullas tillbaka!");
        repo.save(b);
        throw new RuntimeException("Roll back");
    }

    /**
     * Ingen explicit transaktion. Demo sparas trots exception.
     */
    public void sparaDemoNonTransactionalException() {
        Demo b = new Demo();
        b.setName("Demo! Ingen transaktion så sparas trots exception!");
        repo.save(b);
        throw new RuntimeException("Roll back");
    }

    /**
     * Ingen transaktion, och inget explicit anrop till save - inget sparas.
     * @param id
     */
    public void update(Long id) {
        this.repo.findById(id).orElseThrow(NoSuchElementException::new).setName("UPDATED!");
    }

    /**
     * Transaktion innebär att inget explicit anrop till save krävs.
     * @param id
     */
    @Transactional
    public void updateTransactional(Long id) {
        this.repo.findById(id).orElseThrow(NoSuchElementException::new).setName("UPDATED!");
    }

}
