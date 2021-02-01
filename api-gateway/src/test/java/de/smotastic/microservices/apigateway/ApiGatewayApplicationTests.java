package de.smotastic.microservices.apigateway;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApiGatewayApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldRedirectToOauth2Provider_whenNoAuthGiven() throws Exception {
		mockMvc.perform(get("/ping")) //
				.andExpect(status().is3xxRedirection()) //
				.andExpect(header().string(HttpHeaders.LOCATION, is("/sso/login"))); //
	}

	@Test
	@WithMockUser
	void shouldGetPing_whenUserIsAuthenticated() throws Exception {
		mockMvc.perform(get("/ping")) //
				.andExpect(status().is2xxSuccessful()) //
				.andExpect(content().string("pingo"));
	}

}