package com.mvalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvalho.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
