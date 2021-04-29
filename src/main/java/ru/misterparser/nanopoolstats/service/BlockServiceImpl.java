package ru.misterparser.nanopoolstats.service;

import ru.misterparser.nanopoolstats.dao.BlockDao;
import ru.misterparser.nanopoolstats.dao.RewardStatsByDayDao;
import ru.misterparser.nanopoolstats.entity.BlockEntity;
import ru.misterparser.nanopoolstats.entity.RewardStatsByDay;

import java.util.Collection;
import java.util.List;

public class BlockServiceImpl implements BlockService {

    private final BlockDao blockDao;
    private final RewardStatsByDayDao rewardStatsByDayDao;

    public BlockServiceImpl(BlockDao blockDao, RewardStatsByDayDao rewardStatsByDayDao) {
        this.blockDao = blockDao;
        this.rewardStatsByDayDao = rewardStatsByDayDao;
    }

    @Override
    public long count() {
        return blockDao.count();
    }

    @Override
    public void saveAll(Collection<BlockEntity> blocks) {
        blockDao.saveAll(blocks);
    }

    @Override
    public List<BlockEntity> findAll() {
        return blockDao.findAllByOrderByBlockNumber();
    }

    @Override
    public List<RewardStatsByDay> rewardStatsByDay() {
        return rewardStatsByDayDao.rewardStatsByDay();
    }
}
