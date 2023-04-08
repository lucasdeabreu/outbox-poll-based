package dev.lucasdeabreu.outbox.pollbased.driver;

import org.springframework.data.jpa.repository.JpaRepository;

interface DriverEntityRepository extends JpaRepository<DriverEntity, Integer> {
}
