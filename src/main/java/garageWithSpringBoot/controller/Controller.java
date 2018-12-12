package garageWithSpringBoot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import garageWithSpringBoot.exception.ResourceNotFoundException;
import garageWithSpringBoot.model.Model;
import garageWithSpringBoot.repository.RepositoryForGarage;

@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	RepositoryForGarage myRepo;

	@PostMapping("/vehicle")
	public Model createVehicle(@Valid @RequestBody Model springRepo) {

		return myRepo.save(springRepo);

	}

	@GetMapping("/vehicle/{id}")
	public Model getVehicleById(@PathVariable(value = "id") Long vehicleId) {
		return myRepo.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Model", "id", vehicleId));
	}

	@GetMapping("/vehicle")
	public List<Model> getAllVehicles() {

		return myRepo.findAll();
	}
	@GetMapping("/vehicle/noOW/{numberOfWheels}")
	public List<Model> getByNumberOfWheels(@PathVariable(value = "numberOfWheels") Integer num) {
		List<Model> temp = new ArrayList<>();
		temp.addAll(myRepo.findByNumberOfWheels(num));
		return temp;
	}
	@GetMapping("/vehicle/noOS/{numberOfSeats}")
	public List<Model> getByNumberOfSeats(@PathVariable(value = "numberOfSeats") Integer num) {
		List<Model> temp = new ArrayList<>();
		temp.addAll(myRepo.findByNumberOfSeats(num));
		return temp;
	}
	@PutMapping("/vehicle/{id}")
	public Model updateVehicle(@PathVariable(value = "id") Long vehicleID, @Valid @RequestBody Model vehicleDetails) {
		Model data = myRepo.findById(vehicleID)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", vehicleID));

		data.setName(vehicleDetails.getName());
		data.setNumberOfWheels(vehicleDetails.getNumberOfWheels());
		data.setNumberOfSeats(vehicleDetails.getNumberOfSeats());

		
		return myRepo.save(data);

	}

	@DeleteMapping("/vehicle/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long vehicleID) {
		Model model = myRepo.findById(vehicleID)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", vehicleID));

		myRepo.delete(model);
		return ResponseEntity.ok(model);

	}

	@DeleteMapping("/vehicle/")
	public void deleteAllVehicles() {
		myRepo.deleteAll();
	}

}
