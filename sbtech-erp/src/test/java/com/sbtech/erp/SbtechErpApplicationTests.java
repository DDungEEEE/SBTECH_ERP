package com.sbtech.erp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = NONE)
class SbtechErpApplicationTests {

	@Test
	void contextLoads() {
	}

}
