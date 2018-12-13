package garageWithSpringBoot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import garageWithSpringBoot.model.Model;
import garageWithSpringBoot.model.Order;
import garageWithSpringBoot.repository.*;
import garageWithSpringBoot.exception.ResourceNotFoundException;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RepositoryForGarage springBootRepo;

	@GetMapping("/vehicle/{vehicleId}/orders")
	public Page<Order> getAllOrdersByPersonId(@PathVariable(value = "person_id") Long personId,
												Pageable pageable) {
		return orderRepository.findByPersonId(personId, pageable);
	}

	@PostMapping("/vehicle/{vehicleId}/orders")
	public Order createComment(@PathVariable (value = "vehicleId") Long id,
								@Valid @RequestBody Order order) {
		return springBootRepo.findById(id).map(repo -> {
			order.setPerson(repo);
			return orderRepository.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", order));
	}

	@PutMapping("/vehicle/{vehicleId}/orders/{orderId}")
	public Order updateOrder(@PathVariable (value = "vehicleId") Long vehicleId,
							@PathVariable (value = "orderId") Long orderId,
							@Valid @RequestBody Order orderRequest) {
		if(!springBootRepo.existsById(vehicleId)) {
			throw new ResourceNotFoundException("Vehicle", "id", orderRequest);
		}
		return orderRepository.findById(orderId).map(order -> {
			order.setTitle(orderRequest.getTitle());
			return orderRepository.save(order);
		}).orElseThrow(() -> new ResourceNotFoundException("OrderId", "id", orderRequest));
	}
	
	@DeleteMapping("/person/{vehicleId}/orders/{orderId}")
	public ResponseEntity<?> deleteComment(@PathVariable (value = "vehicleId") Long vehicleId,
											@PathVariable (value = "orderId") Long orderId) {
		if(!springBootRepo.existsById(vehicleId)) {
			throw new ResourceNotFoundException("Person", "id", vehicleId);
		}
		
		return orderRepository.findById(orderId).map(order  ->  {
			orderRepository.delete(order);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("OrderId", orderId.toString(), null));
		
	}

}
