package ru.misterparser.nanopoolstats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import ru.misterparser.nanopoolstats.entity.BlockEntity;
import ru.misterparser.nanopoolstats.model.Block;
import ru.misterparser.nanopoolstats.model.Blocks;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class NanopoolStats {

    private static final Logger log = LoggerFactory.getLogger(NanopoolStats.class);

    private static final String BLOCKS_URL_PATTERN = "https://eth.nanopool.org/api/v1/pool/blocks/{0,number,#}/{1,number,#}";
    private static final int LIMIT = 200;

    private final BlockService blockService;
    private final RestTemplate nanopoolRestTemplate;

    public NanopoolStats(BlockService blockService, RestTemplate nanopoolRestTemplate) {
        this.blockService = blockService;
        this.nanopoolRestTemplate = nanopoolRestTemplate;
    }

    public void downloadStats() {
        log.debug("Download block stats from Nanopool");
        int offset = 0;
        boolean empty = false;
        while (!empty) {
            List<Block> blocks = loadPage(offset);
            blockService.saveAll(blocks.stream().map(this::fromModel).collect(Collectors.toList()));
            empty = blocks.isEmpty();
            offset += LIMIT;
        }
    }

    private List<Block> loadPage(int offset) {
        String url = MessageFormat.format(BLOCKS_URL_PATTERN, offset, LIMIT);
        log.debug("Download url {}", url);
        Blocks blocks = nanopoolRestTemplate.getForObject(url, Blocks.class);
        log.debug("Download blocks {}", blocks);
        return blocks.getData();
    }

    private BlockEntity fromModel(Block block) {
        BlockEntity blockEntity = new BlockEntity();
        blockEntity.setBlockNumber(block.getBlockNumber());
        blockEntity.setHash(block.getHash());
        blockEntity.setDate(block.getDate());
        blockEntity.setValue(block.getValue());
        blockEntity.setStatus(block.getStatus());
        blockEntity.setMiner(block.getMiner());
        return blockEntity;
    }
}
