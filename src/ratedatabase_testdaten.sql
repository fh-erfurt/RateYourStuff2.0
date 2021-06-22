
--
-- Datenbank: `ratedatabase`
--

-- --------------------------------------------------------
--
-- Daten für Tabelle `book_publishers`
--

INSERT INTO `book_publishers` (`id`, `created_at`, `updated_at`, `book_publisher_title`) VALUES
(1, '2021-06-21 19:12:59', '2021-06-21 19:12:59', 'Knaur'),
(2, '2021-06-21 19:12:59', '2021-06-21 19:12:59', 'Heyne'),
(3, '2021-06-21 19:12:59', '2021-06-21 19:12:59', 'Hanser');

-- --------------------------------------------------------

--
-- Daten für Tabelle `game_publishers`
--

INSERT INTO `game_publishers` (`id`, `created_at`, `updated_at`, `game_publisher_title`) VALUES
(1, '2021-06-21 19:14:05', '2021-06-21 19:14:05', 'Square Enix'),
(2, '2021-06-21 19:14:05', '2021-06-21 19:14:05', 'Electronic Arts'),
(3, '2021-06-21 19:14:05', '2021-06-21 19:14:05', 'Blizzard');

-- --------------------------------------------------------
--
-- Daten für Tabelle `networks`
--

INSERT INTO `networks` (`id`, `created_at`, `updated_at`, `network_title`) VALUES
(1, '2021-06-21 19:14:50', '2021-06-21 19:14:50', 'HBO'),
(2, '2021-06-21 19:14:50', '2021-06-21 19:14:50', 'CBS'),
(3, '2021-06-21 19:14:50', '2021-06-21 19:14:50', 'Marvel');

-- --------------------------------------------------------

--
-- Daten für Tabelle `platforms`
--

INSERT INTO `platforms` (`id`, `created_at`, `updated_at`, `platform_title`) VALUES
(1, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Playstation'),
(2, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Playstation 2'),
(3, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Playstation 3'),
(4, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Playstation 4'),
(5, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Playstation 5'),
(6, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Xbox'),
(7, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Xbox 360'),
(8, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Xbox Series S'),
(9, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Xbox Series X'),
(10, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'NES'),
(11, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'SNES'),
(12, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'N64'),
(13, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'GameCube'),
(14, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'WII'),
(15, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'WII U'),
(16, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Nintendo Switch'),
(17, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'GameBoy'),
(18, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'GameBoy Color'),
(19, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'GameBoy Advance'),
(20, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Nintendo DS'),
(21, '2021-06-21 19:15:42', '2021-06-21 19:15:42', 'Nintendo 3 DS'),
(22, '2021-06-21 19:49:15', '2021-06-21 19:49:15', 'PC');

-- --------------------------------------------------------

--
-- Daten für Tabelle `media`
--

INSERT INTO `media` (`id`, `created_at`, `updated_at`, `medium_name`, `picture_path`, `release_date`, `short_description`) VALUES
(1, '2021-06-21 19:34:35', '2021-06-21 19:34:35', 'Aera', NULL, '2015-10-26', 'Im Jahr 2019 herrscht eine neue Weltordnung: Die Götter kehren auf die Erde zurück. Alle Götter – bis auf einen. Während Odin, Zeus, Manitu, Anubis, Shiva und Co. sich ihre alten Kultstätten zurückholen und ihre Anhänger um sich scharen, warten Christen, Moslems und Juden vergeblich. Die einst mächtigsten Religionen der Welt werden bald als bedeutungslose Sekten belächelt. Mit »AERA - Rückkehr der Götter« hat Markus Heitz einen neuen Kosmos geschaffen - ein großes Vergnügen für alle Fans von düsterer Spannung und filmreifer Action!'),
(2, '2021-06-21 19:34:35', '2021-06-21 19:34:35', 'Wächter der Nacht', NULL, '2011-08-19', 'In Russland das Kultbuch schlechthin und erfolgreicher als »Der Herr der Ringe« oder »Harry Potter«: Sergej Lukianenkos »Wächter der Nacht« – eine einzigartige Mischung aus Fantasy und Horror über den ewigen Kampf zwischen den Mächten des Lichts und der Finsternis'),
(3, '2021-06-21 19:39:27', '2021-06-21 19:39:27', 'Theoretische Informatik', NULL, '2018-08-06', 'Das Buch führt umfassend in das Gebiet der theoretischen Informatik ein und behandelt den Stoffumfang, der für das Bachelor-Studium an Universitäten und Hochschulen in den Fächern Informatik und Informationstechnik benötigt wird. Die Darstellung und das didaktische Konzept verfolgen das Ziel, einen durchweg praxisnahen Zugang zu den mitunter sehr theoretisch geprägten Themen zu schaffen. Theoretische Informatik muss nicht trocken sein. Sie kann Spaß machen und genau dies versucht das Buch zu vermitteln. Die verschiedenen Methoden und Verfahren werden anhand konkreter Beispiele eingeführt und durch zahlreiche Querverbindungen wird gezeigt, wie die fundamentalen Ergebnisse der theoretischen Informatik die moderne Informationstechnologie prägen.'),
(4, '2021-06-21 19:42:41', '2021-06-21 19:42:41', 'World of Warcraft', NULL, '2004-11-23', 'World of Warcraft, abgekürzt WoW, ist ein über das Internet spielbares Computer-Rollenspiel (MMORPG) des US-amerikanischen Spielentwicklers Blizzard Entertainment. Es wurde 2004 erstmals veröffentlicht, in Europa erschien es 2005, später auch in anderen Ländern, unter anderem in der Volksrepublik China.'),
(5, '2021-06-21 19:42:41', '2021-06-21 19:42:41', 'It Takes Two', NULL, '2021-03-26', 'It Takes Two ist ein Action-Adventure, dass ihr im Koop mit einem Freund durchspielen könnt. Hier steuert ihr zwei Puppen, welche die Eltern des Protagonisten repräsentieren und dabei sind, sich zu trennen. It Takes Two ist ein rasanter, fantasievoller und irre kreativer Action-Trip.'),
(6, '2021-06-21 19:42:41', '2021-06-21 19:42:41', 'Final Fantasy XV', NULL, '2016-11-29', 'Final Fantasy XV spielt in der Welt Eos. Alle Nationen, bis auf das Königreich Lucis, werden von dem Imperium Niflheim beherrscht. Niflheim nutzt zudem die Friedensverhandlungen aus, um Lucis einzunehmen.'),
(7, '2021-06-21 20:21:37', '2021-06-21 20:21:37', 'Scrubs', NULL, '2001-10-02', 'Die Anfänger ist eine US-amerikanische Dramedy-Sitcom, die 2001 von Bill Lawrence entwickelt wurde. Die neun Staffeln beinhalten 182 Folgen, die bis 2010 ausgestrahlt wurden. Die Serie behandelt die beruflichen und privaten Probleme junger Ärzte seit ihren ersten Gehversuchen im Krankenhausalltag.'),
(8, '2021-06-21 20:21:37', '2021-06-21 20:21:37', 'Mein erster Tag', NULL, '2001-10-02', 'John Dorian, genannt J.D., tritt seine Stelle als Assistenzarzt an'),
(9, '2021-06-21 20:26:12', '2021-06-21 20:26:12', 'Mein Mentor', NULL, '2001-10-02', '\r\nLangsam bekommt J.D. Routine bei seiner Arbeit als Assistenzarzt.'),
(10, '2021-06-21 20:28:16', '2021-06-21 20:28:16', 'Mein Kunstfehler', NULL, '2001-10-02', 'Turk hat neuerdings nur Augen für Carla'),
(11, '2021-06-21 20:52:29', '2021-06-21 20:52:29', 'Tucker and Dale vs. Evil', NULL, '2011-02-10', '\r\nFür ein friedliches Wochenende machen sich Tucker und Dale auf den Weg zu ihrer Ferienhütte in der Wildnis. Als sie auf dem Freeway fast mit einer Gruppe Collegekids kollidieren, ahnen die gutherzigen Hinterwäldler nichts Böses. Doch Dales schüchterne Einfalt und die Vorbehalte der Studenten gegen das rustikale Äußere der Hillbillies lenken eigentlich harmlose Ereignisse in eine fatale Richtung.'),
(12, '2021-06-21 20:52:29', '2021-06-21 20:52:29', 'Braveheart', NULL, '1995-10-05', 'Schottland im 13. Jahrhundert. Der englische König Edward I. führt ein brutales Regiment über das Land. Als William Wallaces Frau von Truppen ermordet wird, schwört er auf Rache.'),
(13, '2021-06-21 20:52:29', '2021-06-21 20:52:29', 'Rush Hour', NULL, '1998-09-18', 'Als diese beiden Cops aus völlig unterschiedlichen Welten temperamentvoll aneinander geraten, sorgt der Kulturschock für die Erkenntnis, dass sie nur Eines gemeinsam haben.');

-- --------------------------------------------------------

--
-- Daten für Tabelle `books`
--

INSERT INTO `books` (`isebook`, `is_print`, `isbn`, `number_of_pages`, `id`, `book_publisher_id`) VALUES
(b'1', b'1', '978-3-426-51861-8', 600, 1, 1),
(b'1', b'1', '978-3-641-06578-2', 528, 2, 2),
(b'1', b'1', '978-3-446-45793-5', 435, 3, 3);


-- --------------------------------------------------------

--
-- Daten für Tabelle `games`
--

INSERT INTO `games` (`age_restriction`, `average_playtime`, `max_number_of_gamers`, `min_number_of_gamers`, `id`, `game_publisher_id`, `platform_id`) VALUES
(12, NULL, NULL, 1, 4, 3, 22),
(12, 17, 2, 2, 5, 2, 4),
(12, 40, 4, 1, 6, 1, 4);



-- --------------------------------------------------------

--
-- Daten für Tabelle `genres`
--

INSERT INTO `genres` (`id`, `created_at`, `updated_at`, `genre_name`) VALUES
(1, '2021-06-21 20:45:25', '2021-06-21 20:45:25', 'Komödie'),
(2, '2021-06-21 20:45:25', '2021-06-21 20:45:25', 'Action'),
(3, '2021-06-21 20:45:25', '2021-06-21 20:45:25', 'Thriller'),
(4, '2021-06-21 20:45:25', '2021-06-21 20:45:25', 'Horror'),
(5, '2021-06-21 20:45:25', '2021-06-21 20:45:25', 'Drama'),
(6, '2021-06-21 20:45:25', '2021-06-21 20:45:25', 'Romantik'),
(7, '2021-06-21 20:45:25', '2021-06-21 20:45:25', 'RPG'),
(8, '2021-06-21 20:45:25', '2021-06-21 20:45:25', 'MMORPG'),
(9, '2021-06-21 20:45:25', '2021-06-21 20:45:25', 'SiFi'),
(10, '2021-06-21 20:45:25', '2021-06-21 20:45:25', 'Adventure');

-- --------------------------------------------------------

--
-- Daten für Tabelle `genres_media`
--

INSERT INTO `genres_media` (`genres_id`, `media_id`) VALUES
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(7, 6),
(8, 4),
(10, 5);

-- --------------------------------------------------------

--
-- Daten für Tabelle `languages`
--

INSERT INTO `languages` (`id`, `created_at`, `updated_at`, `language`) VALUES
(1, '2021-06-21 20:43:48', '2021-06-21 20:43:48', 'Deutsch'),
(2, '2021-06-21 20:43:48', '2021-06-21 20:43:48', 'Englisch'),
(3, '2021-06-21 20:43:48', '2021-06-21 20:43:48', 'Französisch'),
(4, '2021-06-21 20:43:48', '2021-06-21 20:43:48', 'Spanisch'),
(5, '2021-06-21 20:43:48', '2021-06-21 20:43:48', 'Portugisisch '),
(6, '2021-06-21 20:43:48', '2021-06-21 20:43:48', 'Polnisch'),
(7, '2021-06-21 20:43:48', '2021-06-21 20:43:48', 'Schwedisch'),
(8, '2021-06-21 20:43:48', '2021-06-21 20:43:48', 'Norwegisch'),
(9, '2021-06-21 20:43:48', '2021-06-21 20:43:48', 'Dänisch'),
(10, '2021-06-21 20:43:48', '2021-06-21 20:43:48', 'Türkisch');

-- --------------------------------------------------------

--
-- Daten für Tabelle `languages_media`
--

INSERT INTO `languages_media` (`languages_id`, `media_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 12),
(2, 12);


-- --------------------------------------------------------

--
-- Daten für Tabelle `movies`
--

INSERT INTO `movies` (`age_restriction`, `length`, `id`, `network_id`) VALUES
(16, 89, 11, NULL),
(16, 177, 12, NULL),
(12, 98, 13, NULL);

-- --------------------------------------------------------

--
-- Daten für Tabelle `persons`
--

INSERT INTO `persons` (`id`, `created_at`, `updated_at`, `birth_date`, `first_name`, `last_name`) VALUES
(2, '2021-06-21 19:25:27', '2021-06-21 19:25:27', '1971-10-10', 'Markus', 'Heitz'),
(3, '2021-06-21 19:27:09', '2021-06-21 19:27:09', '1968-04-11', 'Sergei', 'Lukjanenko');

-- --------------------------------------------------------

--
-- Daten für Tabelle `person_assignments`
--

INSERT INTO `person_assignments` (`id`, `created_at`, `updated_at`, `is_actor`, `is_author`, `is_director`, `is_producer`, `medium_id`, `person_id`) VALUES
(5, '2021-06-21 19:18:28', '2021-06-21 19:18:28', b'0', b'1', b'0', b'0', NULL, 2),
(13, '2021-06-21 19:28:05', '2021-06-21 19:28:05', NULL, b'1', NULL, NULL, NULL, 3);

-- --------------------------------------------------------

--
-- Daten für Tabelle `platforms_media`
--

INSERT INTO `platforms_media` (`platforms_id`, `media_id`) VALUES
(4, 5),
(4, 6),
(9, 5),
(22, 5);

-- --------------------------------------------------------

--
-- Daten für Tabelle `roles`
--

INSERT INTO `roles` (`id`, `created_at`, `updated_at`, `role_name`) VALUES
(1, '2021-06-21 19:17:34', '2021-06-21 19:17:34', 'User'),
(2, '2021-06-21 19:17:34', '2021-06-21 19:17:34', 'Admin'),
(3, '2021-06-21 19:17:34', '2021-06-21 19:17:34', 'Moderator'),
(4, '2021-06-21 19:17:34', '2021-06-21 19:17:34', 'Customer');


-- --------------------------------------------------------

--
-- Daten für Tabelle `series`
--

INSERT INTO `series` (`age_restriction`, `average_length`, `is_completed`, `id`, `network_id`) VALUES
(12, NULL, b'1', 7, NULL);

-- --------------------------------------------------------

--
-- Daten für Tabelle `seasons`
--

INSERT INTO `seasons` (`id`, `created_at`, `updated_at`, `season_number`, `season_title`, `series_id`) VALUES
(1, '2021-06-21 20:25:06', '2021-06-21 20:25:06', 1, 'Staffel 1', 7);

-- --------------------------------------------------------

--
-- Daten für Tabelle `episodes`
--

INSERT INTO `episodes` (`episode_number`, `length`, `id`, `season_id`) VALUES
(1, 22, 8, 1),
(2, 21, 9, 1),
(3, 21, 10, 1);
-- --------------------------------------------------------

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`id`, `created_at`, `updated_at`, `first_name`, `gender`, `last_name`, `profile_picture_path`, `second_name`, `user_name`, `login_id`) VALUES
(1, '2021-06-21 21:17:56', '2021-06-21 21:17:56', 'Super', 'männlich', 'Typ', NULL, NULL, 'SuperTyp', NULL),
(2, '2021-06-21 21:17:56', '2021-06-21 21:17:56', 'Maxima', 'weiblich', 'Musterfrau', NULL, NULL, 'BadWomand78', NULL),
(3, '2021-06-21 21:17:56', '2021-06-21 21:17:56', 'Kevin', 'divers', 'Kevinson', NULL, NULL, 'DoedelDoedler', NULL);

-- --------------------------------------------------------

