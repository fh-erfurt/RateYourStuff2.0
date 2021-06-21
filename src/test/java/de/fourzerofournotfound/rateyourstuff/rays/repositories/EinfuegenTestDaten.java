package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import de.fourzerofournotfound.rateyourstuff.rays.models.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class EinfuegenTestDaten {

    @Autowired
    BookPublisherRepository bookPublisherRepository; // done

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GamePublisherRepository gamePublisherRepository; //Done

    @Autowired
    private NetworkRepository networkRepository; //Done

    @Autowired
    private PlatformRepository platformRepository; //Done

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginRoleRepository loginRoleRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Test
    void save_Test_Data() {
        BookPublisher newBookpubliser1 = new BookPublisher("Knaur");
        BookPublisher newBookpubliser2 = new BookPublisher("Hanser");
        BookPublisher newBookpubliser3 = new BookPublisher("Heyne");

        bookPublisherRepository.save(newBookpubliser1);
        bookPublisherRepository.save(newBookpubliser2);
        bookPublisherRepository.save(newBookpubliser3);

        GamePublisher newGamePublisher1 = new GamePublisher("Square Enix");
        GamePublisher newGamePublisher2 = new GamePublisher("Eletronic Arts");
        GamePublisher newGamePublisher3 = new GamePublisher("Blizzard");

        gamePublisherRepository.save(newGamePublisher1);
        gamePublisherRepository.save(newGamePublisher2);
        gamePublisherRepository.save(newGamePublisher3);

        Network newNetwork1 = new Network("HBO");
        Network newNetwork2 = new Network("CBS");
        Network newNetwork3 = new Network("Marvel");

        networkRepository.save(newNetwork1);
        networkRepository.save(newNetwork2);
        networkRepository.save(newNetwork3);

        Platform newPlatform1 = new Platform("Playstation");
        Platform newPlatform2 = new Platform("Playstation 2");
        Platform newPlatform3 = new Platform("Playstation 3");
        Platform newPlatform4 = new Platform("Playstation 4");
        Platform newPlatform5 = new Platform("Playstation 5");
        Platform newPlatform6 = new Platform("Xbox");
        Platform newPlatform7 = new Platform("Xbox 360");
        Platform newPlatform8 = new Platform("Xbox Series S");
        Platform newPlatform9 = new Platform("Xbox Series X");
        Platform newPlatform10 = new Platform("NES");
        Platform newPlatform11 = new Platform("SNES");
        Platform newPlatform12 = new Platform("N64");
        Platform newPlatform13 = new Platform("GameCube");
        Platform newPlatform14 = new Platform("WII");
        Platform newPlatform15 = new Platform("WII U");
        Platform newPlatform16 = new Platform("Nintendo Switch");
        Platform newPlatform17 = new Platform("Game Boy");
        Platform newPlatform18 = new Platform("Game Boy Color");
        Platform newPlatform19 = new Platform("Game Boy Advance");
        Platform newPlatform20 = new Platform("Nintendo DS");
        Platform newPlatform21 = new Platform("Nintendo 3DS");

        platformRepository.save(newPlatform1);
        platformRepository.save(newPlatform2);
        platformRepository.save(newPlatform3);
        platformRepository.save(newPlatform4);
        platformRepository.save(newPlatform5);
        platformRepository.save(newPlatform6);
        platformRepository.save(newPlatform7);
        platformRepository.save(newPlatform8);
        platformRepository.save(newPlatform9);
        platformRepository.save(newPlatform10);
        platformRepository.save(newPlatform11);
        platformRepository.save(newPlatform12);
        platformRepository.save(newPlatform13);
        platformRepository.save(newPlatform14);
        platformRepository.save(newPlatform15);
        platformRepository.save(newPlatform16);
        platformRepository.save(newPlatform17);
        platformRepository.save(newPlatform18);
        platformRepository.save(newPlatform19);
        platformRepository.save(newPlatform20);
        platformRepository.save(newPlatform21);

        Role role1 = Role.builder().roleName("User").build();
        Role role2 = Role.builder().roleName("Admin").build();
        Role role3 = Role.builder().roleName("Moderator").build();
        Role role4 = Role.builder().roleName("Customer").build();

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        roleRepository.save(role4);




        Book neewBook1 = Book.builder().mediumName("Aera")
                .isEBook(true)
                .isPrint(true)
                .numberOfPages(577)
                .isbn("978-3-426-51861-8")
                .releaseDate(LocalDate.of(2015,10,26))
                .shortDescription("Im Jahr 2019 herrscht eine neue Weltordnung: Die Götter kehren auf die Erde zurück. Alle Götter – bis auf einen. Während Odin, Zeus, Manitu, Anubis, Shiva und Co. sich ihre alten Kultstätten zurückholen und ihre Anhänger um sich scharen, warten Christen, Moslems und Juden vergeblich. Die einst mächtigsten Religionen der Welt werden bald als bedeutungslose Sekten belächelt. Mit »AERA - Rückkehr der Götter« hat Markus Heitz einen neuen Kosmos geschaffen - ein großes Vergnügen für alle Fans von düsterer Spannung und filmreifer Action!")
                .build();


    }


}
