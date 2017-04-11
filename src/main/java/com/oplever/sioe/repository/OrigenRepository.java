package com.oplever.sioe.repository;

import com.oplever.sioe.domain.Origen;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Origen entity.
 */
@SuppressWarnings("unused")
public interface OrigenRepository extends JpaRepository<Origen,Long> {

}
