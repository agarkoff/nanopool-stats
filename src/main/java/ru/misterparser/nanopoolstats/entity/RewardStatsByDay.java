package ru.misterparser.nanopoolstats.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class RewardStatsByDay {

    @Id
    private String day;
    private BigDecimal sum;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "RewardStatsByDay{" +
                "day='" + day + '\'' +
                ", sum=" + sum +
                '}';
    }
}
