package shrutosom.bala.csv2api.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Csv2APIRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(Csv2APIRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public Csv2APIRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean createAndUpdateTable() {

        LOGGER.info("Create Table Customers");


        /* Check Table if Exists */
        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
        return false;
    }

}
