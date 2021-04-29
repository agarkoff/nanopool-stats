package ru.misterparser.nanopoolstats.entity;

import ru.misterparser.nanopoolstats.model.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "blocks")
public class BlockEntity {

    @Id
    private Long blockNumber;

    private String hash;

    private Instant date;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String miner;

    public Long getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(Long blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockEntity that = (BlockEntity) o;

        return blockNumber.equals(that.blockNumber);
    }

    @Override
    public int hashCode() {
        return blockNumber.hashCode();
    }

    @Override
    public String toString() {
        return "BlockEntity{" +
                "blockNumber=" + blockNumber +
                ", hash='" + hash + '\'' +
                ", date=" + date +
                ", value=" + value +
                ", status=" + status +
                ", miner=" + miner +
                '}';
    }
}
