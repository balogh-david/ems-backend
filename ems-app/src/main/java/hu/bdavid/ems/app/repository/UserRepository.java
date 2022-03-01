package hu.bdavid.ems.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.bdavid.ems.app.dto.DashboardResponse;
import hu.bdavid.ems.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM USER WHERE COMPANY_ID = ?3 LIMIT ?1, ?2", nativeQuery = true)
    List<User> findCompanyUsers(int from, int to, String companyId);

    @Query(value = "SELECT * FROM USER WHERE ID != ?3 LIMIT ?1, ?2", nativeQuery = true)
    List<User> findUsers(int from, int to, String id);

    @Query(value = "SELECT COUNT(*) AS count, PERMISSION as permission FROM USER GROUP BY PERMISSION", nativeQuery = true)
    List<DashboardResponse> countPermisison();

    User findUserByUserName(String username);

}
