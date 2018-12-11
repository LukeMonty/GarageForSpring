package garageWithSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import garageWithSpringBoot.model.Model;

@Repository
public interface RepositoryForGarage extends JpaRepository<Model, Long> {

}
