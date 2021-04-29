package ru.misterparser.nanopoolstats.model;

import java.util.Collections;
import java.util.List;

public class Blocks {

    private Boolean status;
    private final List<Block> data = Collections.emptyList();

    public boolean isStatus() {
        return status;
    }

    public List<Block> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Blocks{" +
                "status=" + status +
                ", data.size=" + data.size() +
                '}';
    }
}
