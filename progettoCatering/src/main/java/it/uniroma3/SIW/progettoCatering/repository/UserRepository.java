package it.uniroma3.SIW.progettoCatering.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.SIW.progettoCatering.model.User;


public interface UserRepository extends CrudRepository<User, Long> {

}