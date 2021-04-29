package ru.misterparser.nanopoolstats.service;

import ru.misterparser.nanopoolstats.entity.BlockEntity;
import ru.misterparser.nanopoolstats.entity.RewardStatsByDay;

import java.util.Collection;
import java.util.List;

public interface BlockService {
    long count();
    void saveAll(Collection<BlockEntity> blocks);
    List<BlockEntity> findAll();
    List<RewardStatsByDay> rewardStatsByDay();
}
