package ru.misterparser.nanopoolstats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import ru.misterparser.nanopoolstats.entity.BlockEntity;

import java.util.List;

public class NanopoolStatsCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(NanopoolStatsCommandLineRunner.class);

    private final ConfigurableApplicationContext ctx;
    private final NanopoolStats nanopoolStats;
    private final BlockService blockService;

    public NanopoolStatsCommandLineRunner(ConfigurableApplicationContext ctx, NanopoolStats nanopoolStats, BlockService blockService) {
        this.ctx = ctx;
        this.nanopoolStats = nanopoolStats;
        this.blockService = blockService;
    }

    @Override
    public void run(String... args) {
        nanopoolStats.downloadStats();
        List<BlockEntity> blocks = blockService.findAll();
        log.debug("Count saved blocks {}", blocks.size());
        ctx.close();
    }
}
