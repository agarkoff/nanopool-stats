package ru.misterparser.nanopoolstats.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.misterparser.nanopoolstats.entity.BlockEntity;

import java.util.List;

public interface BlockDao extends JpaRepository<BlockEntity, Long> {

    List<BlockEntity> findAllByOrderByBlockNumber();

//    @Query("select RewardStatsByMonth from blocks")
//    List<RewardStatsByMonth> rewardByMonth();
}
