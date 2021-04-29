package ru.misterparser.nanopoolstats.service;

import ru.misterparser.nanopoolstats.dao.BlockDao;
import ru.misterparser.nanopoolstats.entity.BlockEntity;

import java.util.Collection;
import java.util.List;

public class BlockServiceImpl implements BlockService {

    private final BlockDao blockDao;

    public BlockServiceImpl(BlockDao blockDao) {
        this.blockDao = blockDao;
    }

    @Override
    public void saveAll(Collection<BlockEntity> blocks) {
        blockDao.saveAll(blocks);
    }

    @Override
    public List<BlockEntity> findAll() {
        return blockDao.findAllByOrderByBlockNumber();
    }
}
