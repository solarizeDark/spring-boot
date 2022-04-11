package ru.fedusiv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fedusiv.entities.Student;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByNameAndSurname(String name, String surname);

    @Query(value = "select student from Student student where student.age >= :left and student.age <= :right")
    List<Student> findInAgeRange(@Param("left") Integer left, @Param("right") Integer right);

    @Query(value = "select exists (select 1 from students where students.id = :id)", nativeQuery = true)
    Boolean exists(@Param("id") Long id);

    @Query(value = "delete from students where id = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void delete(@Param("id") Long id);

}
