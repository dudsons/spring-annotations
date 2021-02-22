package pl.mr.springannotations.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    private final static Logger logger = LoggerFactory.getLogger(BookingService.class);
    private final JdbcTemplate jdbcTemplate;

    public BookingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void bookPerson(String person){
        logger.info("Booking " + person + " on seat..." );
        jdbcTemplate.update("insert into bookings (first_name) values(?)",person);
    }

    @Transactional
    public void bookPersons (String... persons){
        for (String person: persons) {
            logger.info("Booking " + person + " on seat..." );
            jdbcTemplate.update("insert into bookings(first_name) values (?)",person);
        }
    }

    public List<String> findAllPersons () {
        return jdbcTemplate.query("SELECT first_name from bookings",
                                  (rs,rowNum)->rs.getString("first_name"));
    }
}
