package ru.misterparser.nanopoolstats.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block implements Comparable<Block> {

    @JsonProperty("block_number")
    private Long blockNumber;

    private String hash;

    private Instant date;

    private BigDecimal value;

    private int status;

    private String miner;

    public Long getBlockNumber() {
        return blockNumber;
    }

    public String getHash() {
        return hash;
    }

    public Instant getDate() {
        return date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public int getStatus() {
        return status;
    }

    public String getMiner() {
        return miner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        return blockNumber.equals(block.blockNumber);
    }

    @Override
    public int hashCode() {
        return blockNumber.hashCode();
    }

    @Override
    public int compareTo(Block o) {
        return getDate().compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockNumber=" + blockNumber +
                ", hash='" + hash + '\'' +
                ", date=" + date +
                ", value=" + value +
                ", status=" + status +
                ", miner=" + miner +
                '}';
    }
}
