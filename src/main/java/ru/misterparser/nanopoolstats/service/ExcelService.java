package ru.misterparser.nanopoolstats.service;

import ru.misterparser.nanopoolstats.entity.RewardStatsByDay;

import java.util.List;

public interface ExcelService {
    void export(List<RewardStatsByDay> rewardStatsByDay);
}
