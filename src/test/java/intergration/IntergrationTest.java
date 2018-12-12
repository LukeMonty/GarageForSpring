package intergration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;

import garageWithSpringBoot.GarageWithSpringApp;
import garageWithSpringBoot.model.Model;
import garageWithSpringBoot.repository.RepositoryForGarage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GarageWithSpringApp.class})
@AutoConfigureMockMvc
public class IntergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private RepositoryForGarage repo;

	@Before
	public void clearDB() {
		repo.deleteAll();
	}

	
	@Test
	public void s() {
		
	}
	
	@Test
	public void findAndGetVehicle() throws Exception { 
		
		repo.save(new Model("hi", "Sports", 4, 2));
		mvc.perform(get("/api/vehicle").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name", is("hi")));
	}

	@Test
	public void addAVehicleToDatabaseTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/vehicle").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"name\" : \"hi\", \"type\" : \"Sports\", \"number_Of_wheels\" : 4, \"numberOfSeats\" : 2 }"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", is("hi")));
	}
	
}
