package com.example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.Localidad;

@Repository("localidadrepositorio")
public interface LocalidadRepository extends JpaRepository<Localidad, Serializable> {

}
