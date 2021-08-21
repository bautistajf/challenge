package org.challenge.scope.repository;


import org.challenge.entity.CollaboratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratorRepository extends JpaRepository<CollaboratorEntity, Long> {

}
