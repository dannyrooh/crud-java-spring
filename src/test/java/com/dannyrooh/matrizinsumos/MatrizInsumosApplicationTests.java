package com.dannyrooh.matrizinsumos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MatrizInsumosApplicationTests {

	@Test
	void contextLoads() {
		assertThat(System.getProperty("java.version"))
				.startsWith("11.0.20");
	}

}
