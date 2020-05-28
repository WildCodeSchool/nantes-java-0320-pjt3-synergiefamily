package repository;

import entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LocationRepsitory {

    @Repository
    public interface LocationRepository extends JpaRepository<Location, Long> {
    }
}
