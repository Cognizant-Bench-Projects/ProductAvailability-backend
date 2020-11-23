package com.cognizant;

import com.cognizant.model.Location;
import com.cognizant.repository.LocationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductAvailabilityLocationServiceApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	LocationRepository locationRepository;

	Location location1;
	Location location2;

	@BeforeEach
	public void setup() {
		location1 = new Location(1, "Irving", "75063");
		location2 = new Location(2, "New York", "10024");
		locationRepository.save(location1);
		locationRepository.save(location2);
	}

	@AfterEach
	public void cleanup() {
		locationRepository.deleteAll();
	}

	@Test
	void contextLoads() {
	}

}
