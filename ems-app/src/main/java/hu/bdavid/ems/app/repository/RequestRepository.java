package hu.bdavid.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.bdavid.ems.app.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {

    @Query(value = "SELECT * FROM REQUEST WHERE STATUS = ?3 LIMIT ?1, ?2", nativeQuery = true)
    List<Request> findRequests(int from, int to, String status);

    @Query(value = "SELECT * FROM REQUEST WHERE STATUS = ?3 AND CREATOR = ?4 LIMIT ?1, ?2", nativeQuery = true)
    List<Request> findUserRequests(int from, int to, String status, String userName);

    @Query(value = "SELECT * FROM REQUEST JOIN USER U on REQUEST.CREATOR = U.USERNAME WHERE STATUS = ?3 AND U.COMPANY_ID = ?4 LIMIT ?1, ?2", nativeQuery = true)
    List<Request> findCompanyRequests(int from, int to, String status, String companyId);

}
