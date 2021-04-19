package org.joparo.spring.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    DemoRepository demoRepo;

    @BeforeEach
    public void clear() {
        demoRepo.deleteAll();
    }

    @Test
    public void testTransactions() throws Exception {
        assertTrue(demoRepo.findAll().isEmpty());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/demo/exception-tr"))
                .andDo(print()).andExpect(MockMvcResultMatchers.content()
                .string("Det blev fel. Inget demo sparat"));
        assertTrue(demoRepo.findAll().isEmpty());
    }

    @Test
    public void testNoTransactions() throws Exception {
        assertTrue(demoRepo.findAll().isEmpty());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/demo/exception"))
                .andDo(print()).andExpect(MockMvcResultMatchers.content()
                .string("Det blev fel. Men det fanns ingen transaktion. Demo sparat"));
        assertFalse(demoRepo.findAll().isEmpty());
    }

}
