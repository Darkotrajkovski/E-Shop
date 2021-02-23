package com.darkotrajkovski.wpaud1.repository.jpa;

import com.darkotrajkovski.wpaud1.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
