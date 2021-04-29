package ru.misterparser.nanopoolstats.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.bouncycastle.util.Arrays;

public enum Status {

    UNCONFIRMED(0),
    CONFIRMED(1),
    UNCLE(11, 12);

    private final int[] codes;

    Status(int... codes) {
        this.codes = codes;
    }

    @JsonCreator
    public static Status create(int code) {
        for (Status s : values()) {
            if (Arrays.contains(s.codes, code)) {
                return s;
            }
        }
        throw new IllegalArgumentException();
    }
}
