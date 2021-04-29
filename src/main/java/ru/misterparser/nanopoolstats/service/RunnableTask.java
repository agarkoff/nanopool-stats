package ru.misterparser.nanopoolstats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.misterparser.nanopoolstats.entity.BlockEntity;

import java.util.List;

public class RunnableTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(RunnableTask.class);

    private final NanopoolStats nanopoolStats;
    private final BlockService blockService;
    private final ExcelService excelService;

    public RunnableTask(NanopoolStats nanopoolStats, BlockService blockService, ExcelService excelService) {
        this.nanopoolStats = nanopoolStats;
        this.blockService = blockService;
        this.excelService = excelService;
    }

    @Override
    public void run() {
        nanopoolStats.downloadStats();
        List<BlockEntity> blocks = blockService.findAll();
        log.debug("Count saved blocks {}", blocks.size());
        excelService.export(blockService.rewardStatsByDay());
    }
}
