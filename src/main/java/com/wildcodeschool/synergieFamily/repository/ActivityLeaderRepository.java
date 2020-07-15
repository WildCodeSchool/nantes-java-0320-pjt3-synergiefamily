package com.wildcodeschool.synergieFamily.repository;

import com.wildcodeschool.synergieFamily.entity.ActivityLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityLeaderRepository extends JpaRepository<ActivityLeader, Long> {

    Optional<ActivityLeader> findByToken(String token);

    @Query("SELECT a FROM ActivityLeader a WHERE (a.lastName= :lastName OR a.firstName= :firstName OR a.email= :email) AND a.disabled <> true ORDER BY a.lastName asc, a.firstName asc, a.email asc")
    List<ActivityLeader> findByLastNameContainingOrFirstNameContainingOrEmailContaining(@Param("lastName") String lastName , @Param("firstName") String firstName, @Param("email") String email);

    @Query("SELECT a FROM ActivityLeader a ORDER BY a.id DESC")
    public List<ActivityLeader> findAll();


    @Query(nativeQuery = true, value = "SELECT DISTINCT activity_leader.* FROM activity_leader" +
            " LEFT JOIN audience_activity_leader ON activity_leader.id=audience_activity_leader.activity_leader_id" +
            " LEFT JOIN audience" +
            " ON audience_activity_leader.audience_id = audience.id" +
            " LEFT JOIN diploma_activity_leader" +
            " ON activity_leader.id = diploma_activity_leader.activity_leader_id" +
            " LEFT JOIN diploma" +
            " ON diploma_activity_leader.diploma_id = diploma.id" +
            " LEFT JOIN value_activity_leader" +
            " ON activity_leader.id = value_activity_leader.activity_leader_id" +
            " LEFT JOIN `value`" +
            " ON value_activity_leader.value_id = `value`.id" +
            " LEFT JOIN skill_activity_leader" +
            " ON activity_leader.id = skill_activity_leader.activity_leader_id" +
            " LEFT JOIN skill" +
            " ON skill_activity_leader.skill_id = skill.id" +
            " LEFT JOIN location" +
            " ON activity_leader.location_id = location.id" +
            " WHERE (firstName IS NULL OR (:firstName = '') OR firstName LIKE %:firstName%)" +
            " AND (lastName IS NULL OR (:lastName = '') OR lastName LIKE %:lastName%)" +
            " AND (email IS NULL OR (:email='') OR email LIKE %:email%)" +
            " AND (phone IS NULL OR (:phone='') OR phone LIKE %:phone%)" +
            " AND (location.address1 IS NULL OR (:address1='') OR location.address1 LIKE %:address1%)" +
            " AND (location.address2 IS NULL OR (:address2='') OR location.address2 LIKE %:address2%)" +
            " AND (location.city IS NULL OR (:city='') OR location.city LIKE %:city%)" +
            " AND (location.postcode IS NULL OR (:postcode='') OR location.postcode LIKE %:postcode%)" +
            /*
            " AND (hasACar IS NULL OR (:hasACar='0') OR hasACar LIKE %:hasACar%)" +
*/
            " AND (experience IS NULL OR (:experience='') OR experience LIKE %:experience%)"
/*
            TODO " AND (`value`.name IS NULL OR (:valeur='') OR `value`.name LIKE %:valeur%)" VOIR POUR PLUSIEURS VALEURS
*/
            // TODO    " AND (startDate IS NULL OR (:startDate='') OR startDate LIKE %:startDate%)" +
            // todo    " AND (endDate IS NULL OR (:endDate='') OR endDate LIKE %:endDate%)"
    )
    public List<ActivityLeader> findAllByFilter(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("address1") String address1,
            @Param("address2") String address2,
            @Param("city") String city,
            @Param("postcode") String postCode,
            @Param("experience") String experience);
    //TODO      @Param("startDate") Date startDate,
    //TODO       @Param("endDate") Date endDate);

    @Query("SELECT a FROM ActivityLeader a  WHERE a.disabled <> true ORDER BY a.id DESC")
    public List<ActivityLeader> findAllActive();
}