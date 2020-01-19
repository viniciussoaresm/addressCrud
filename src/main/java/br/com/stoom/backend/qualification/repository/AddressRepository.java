package br.com.stoom.backend.qualification.repository;

import br.com.stoom.backend.qualification.pojo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
