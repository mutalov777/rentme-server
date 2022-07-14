package uz.unicorn.rentme.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.unicorn.rentme.entity.Advertisement;
import uz.unicorn.rentme.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, BaseRepository {

    List<Advertisement> findAllByCreatedBy(Long userId, Pageable pageable);

    @Query(
            value = "select a.* from public.advertisement a where a.deleted is false and a.max_duration>=:30 order by random()",
            nativeQuery = true
    )
    List<Advertisement> findAllMonthly(Pageable pageable);

    @Query(
            value = "select a.* from public.advertisement a where a.deleted is false and a.max_duration<=30 order by random()",
            nativeQuery = true
    )
    List<Advertisement> findAllDaily(Pageable pageable);

//    @Query(value = "from Advertisement a where a.deleted is false and a.maxDuration <= 30 order by random")
//    List<Advertisement> findAllDaily(Pageable pageable);

//    @Query(value = "from Advertisement a order by a.createdAt desc")
//    List<Advertisement> findAllByLast(Pageable pageable);

    @Modifying
    @Query(value = "insert into public.auth_user_advertisement " +
            "(auth_user_id,advertisement_id) values (:userId,:id)", nativeQuery = true)
    void saveMyAdvertisement(Long id, Long userId);

    @Modifying
    @Query(value = "delete from public.auth_user_advertisement aua where aua.advertisement_id=:id and aua.auth_user_id=:sessionId ", nativeQuery = true)
    void deleteMyAdvertisement(Long id, Long sessionId);

    @Query(value = "select aua.auth_user_id from public.auth_user_advertisement aua where aua.advertisement_id=:id and aua.auth_user_id=:sessionId ", nativeQuery = true)
    Optional<Long> findAuthUserIdAdvertisementsById(Long id, Long sessionId);

    @Query(value = "from Advertisement a where a.transport.model.brand.id = :brand_id")
    List<Advertisement> findAllByBrandId(@Param(value = "brand_id") Long brandId);

}
