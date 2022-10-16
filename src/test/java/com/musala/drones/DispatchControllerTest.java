/**
 * 
 */
package com.musala.drones;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author New
 *
 */



@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
@AutoConfigureMockMvc(addFilters = false)
public class DispatchControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Test
	public void registerDroneShouldReturnValdStatusCode() throws Exception {
		this.mockMvc.perform(get("/api/ops/drone/items/get/11"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("code", is("400")));
	}

	@Test
	public void getDroneBatteryCapacityBySerialNumberShouldReturnValidStatusCode() throws Exception {
		this.mockMvc.perform(get("/api/ops/drone/capacity/get/222"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("code", is("400")));
	}
	
	//We are looking for a drone  that does not exist. Status code should not be 200. Istead we expect 400 as aresponse
	@Test
	public void getDroneByStatehouldReturnValidStatusCode() throws Exception {
		this.mockMvc.perform(get("/api/ops/drone/state/get/NONE"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("code", is("400")));
	}
	
}
