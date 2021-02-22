package pl.mr.springannotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pl.mr.springannotations.service.BookingService;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
    private final BookingService bookingService;

    public AppRunner(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void run(String... args) throws Exception {
        bookingService.bookPerson("Marek");

        Assert.isTrue(bookingService.findAllPersons().size()==1
        ,"First booking should working with no problem");
        logger.info("Marek added correct");

        try{
            bookingService.bookPersons("Darek","Aleksander");
        }catch (RuntimeException e){
            logger.info("v--- Aleksander is to long for DB ---v");
            logger.error(e.getMessage());
        }

        logger.info("So far booked persons are:");
        for (String person: bookingService.findAllPersons()){
            logger.info(person);
        }

        logger.info("You should not see Aleksander because he violated DB constrains ");
        logger.info("You should not see Darek because he should be rolled back in same transaction");

        Assert.isTrue(bookingService.findAllPersons().size()==1,
                      "Aleksander should triggered a rollback");


    }
}
