package ru.misterparser.nanopoolstats.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.misterparser.nanopoolstats.entity.RewardStatsByDay;

import java.util.List;

public interface RewardStatsByDayDao extends JpaRepository<RewardStatsByDay, String> {

    @Query(value = "select\n" +
            "    concat(to_char(extract(month from date), 'fm00'), '-', to_char(extract(day from date), 'fm00')) as day,\n" +
            "    sum(value) as sum\n" +
            "from blocks\n" +
            "where status = 'CONFIRMED'\n" +
            "group by day\n" +
            "order by day\n" +
            "offset 1",
            nativeQuery = true
    )
    List<RewardStatsByDay> rewardStatsByDay();
}
