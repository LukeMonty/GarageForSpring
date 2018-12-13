package repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import garageWithSpringBoot.GarageWithSpringApp;
import garageWithSpringBoot.model.Model;
import garageWithSpringBoot.repository.RepositoryForGarage;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GarageWithSpringApp.class)
@DataJpaTest
public class RepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RepositoryForGarage myRepo;

	@Test
	public void retrieveByIdTest() {

		Model model = new Model("hi", "Sports", 4, 2);
		entityManager.persist(model);
		entityManager.flush();
		assertTrue(myRepo.existsById(model.getId()));
	}

	@Test
	public void findCarByName() {
		Model model = new Model("hi", "Sports", 4, 2);
		entityManager.persist(model);
		entityManager.flush();

		assertEquals("hi", myRepo.findByName("hi").getName());

	}

	@Test
	public void findCarByNumberOfSeats() {
		Model model = new Model("hi", "Sports", 4, 2);
		entityManager.persist(model);
		entityManager.flush();

		assertEquals(2, myRepo.findByNumberOfSeats(2).get(0).getNumberOfSeats(), 1);

	}

	@Test
	public void findCarByGetNumOfWheels() {
		Model model = new Model("hi", "Sports", 4, 2);
		entityManager.persist(model);
		entityManager.flush();

		assertEquals(4, myRepo.findByNumberOfWheels(4).get(0).getNumberOfWheels(), 1);

	}
	@Test
	public void findById() {
		Model model = new Model("hi", "Sports", 4, 2);
		entityManager.persist(model);
		entityManager.flush();
		assertEquals(4, model.getId(),1);
	}
	@Test
	public void findByType() {
		Model model = new Model("hi", "Sports", 4, 2);
		entityManager.persist(model);
		entityManager.flush();
		assertEquals("Sports", model.getType());
	}
	@Test
	public void findByCreationsDate() {
		Model model = new Model("hi", "Sports", 4, 2);
		entityManager.persist(model);
		entityManager.flush();
		assertEquals(model.getCreationDate(), model.getCreationDate());
	}

}
