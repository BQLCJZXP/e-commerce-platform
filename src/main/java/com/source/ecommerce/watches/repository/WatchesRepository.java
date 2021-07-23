package com.source.ecommerce.watches.repository;

import com.source.ecommerce.watches.model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface WatchesRepository extends JpaRepository<Watch, Long> {

    Watch findByWatchId(String watchId);

    @Query("from Watch w where w.watchId in :watchIds")
    Set<Watch> findByWatchId(@Param("watchIds") List<String> watchIds);

}
