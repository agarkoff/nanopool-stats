package ru.misterparser.nanopoolstats.service;

import ru.misterparser.nanopoolstats.entity.BlockEntity;

import java.util.Collection;
import java.util.List;

public interface BlockService {
    void saveAll(Collection<BlockEntity> blocks);
    List<BlockEntity> findAll();
}
