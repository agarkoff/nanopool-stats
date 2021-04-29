package ru.misterparser.nanopoolstats;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;
import ru.misterparser.nanopoolstats.dao.BlockDao;
import ru.misterparser.nanopoolstats.entity.BlockEntity;
import ru.misterparser.nanopoolstats.service.BlockService;
import ru.misterparser.nanopoolstats.service.BlockServiceImpl;
import ru.misterparser.nanopoolstats.service.NanopoolStats;
import ru.misterparser.nanopoolstats.service.NanopoolStatsCommandLineRunner;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource("classpath:persistence-sqlite.properties")
public class ApplicationConfiguration {

    private final Environment env;

    public ApplicationConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("driverClassName")));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("user"));
        dataSource.setPassword(env.getProperty("password"));
        return dataSource;
    }

    @Bean
    public NanopoolStats nanopoolStats(BlockService blockService, RestTemplate nanopoolRestTemplate) {
        return new NanopoolStats(blockService, nanopoolRestTemplate);
    }

    @Bean
    public NanopoolStatsCommandLineRunner commandLineRunner(ConfigurableApplicationContext ctx, NanopoolStats nanopoolStats, BlockService blockService) {
        return new NanopoolStatsCommandLineRunner(ctx, nanopoolStats, blockService);
    }

    @Bean
    public BlockService blockService(BlockDao blockDao) {
        return new BlockServiceImpl(blockDao);
    }

    @Bean
    public JpaRepositoryFactoryBean<BlockDao, BlockEntity, Long> blockDao() {
        return new JpaRepositoryFactoryBean<>(BlockDao.class);
    }

    @Bean
    public RestTemplate nanopoolRestTemplate() {
        return new RestTemplateBuilder(rt-> rt.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");
            return execution.execute(request, body);
        })).build();
    }
}