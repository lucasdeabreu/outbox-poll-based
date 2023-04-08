package dev.lucasdeabreu.outbox.pollbased.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/drivers")
class DriverController {

    private final DriverEntityRepository repository;

    @PostMapping
    public ResponseEntity<DriverEntity> create(@RequestBody final DriverEntity driver) {
        return ResponseEntity.ok(repository.save(driver));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverEntity> update(@PathVariable("id") final Integer id, @RequestBody final DriverEntity driverUpdated) {
        driverUpdated.setId(id);
        return ResponseEntity.ok(repository.save(driverUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Integer id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DriverEntity>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}
