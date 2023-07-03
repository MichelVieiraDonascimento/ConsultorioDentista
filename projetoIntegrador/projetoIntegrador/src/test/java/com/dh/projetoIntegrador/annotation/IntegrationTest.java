package com.dh.projetoIntegrador.annotation;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test", "integration-test"})
@AutoConfigureMockMvc(addFilters = false)
public @interface IntegrationTest {
}
