-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 22. Jun 2021 um 17:08
-- Server-Version: 10.4.18-MariaDB
-- PHP-Version: 7.4.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `ratedatabase`
--

--
-- Daten für Tabelle `book_publishers`
--

INSERT INTO `book_publishers` (`id`, `created_at`, `updated_at`, `book_publisher_title`) VALUES
(1, '2021-06-21 19:12:59', '2021-06-21 19:12:59', 'Knaur'),
(2, '2021-06-21 19:12:59', '2021-06-21 19:12:59', 'Heyne'),
(3, '2021-06-21 19:12:59', '2021-06-21 19:12:59', 'Hanser'),
(4, '2021-07-04 16:08:18', '2021-07-04 16:08:18', 'Anaconda'),
(5, '2021-07-04 16:08:43', '2021-07-04 16:08:43', 'ullstein');


--
-- Daten für Tabelle `game_publishers`
--

INSERT INTO `game_publishers` (`id`, `created_at`, `updated_at`, `game_publisher_title`) VALUES
(1, '2021-06-21 19:14:05', '2021-06-21 19:14:05', 'Square Enix'),
(2, '2021-06-21 19:14:05', '2021-06-21 19:14:05', 'Electronic Arts'),
(3, '2021-06-21 19:14:05', '2021-06-21 19:14:05', 'Blizzard'),
(4, '2021-07-04 16:04:22', '2021-07-04 16:04:22', 'Bandai Namco Games'),
(5, '2021-07-04 16:05:26', '2021-07-04 16:05:26', 'Nintendo');

--
-- Daten für Tabelle `networks`
--

INSERT INTO `networks` (`id`, `created_at`, `updated_at`, `network_title`) VALUES
(1, '2021-06-21 19:14:50', '2021-06-21 19:14:50', 'HBO'),
(2, '2021-06-21 19:14:50', '2021-06-21 19:14:50', 'CBS'),
(3, '2021-06-21 19:14:50', '2021-06-21 19:14:50', 'Marvel'),
(4, '2021-07-04 15:56:14', '2021-07-04 15:56:14', 'Pixar'),
(5, '2021-07-04 15:58:28', '2021-07-04 15:58:28', 'Paramount Pictures'),
(6, '2021-07-04 16:00:30', '2021-07-04 16:00:30', 'Ternion'),
(7, '2021-07-04 16:15:11', '2021-07-04 16:15:11', 'Nickelodeon'),
(8, '2021-07-04 16:17:34', '2021-07-04 16:17:34', 'Netflix'),
(9, '2021-07-04 16:20:01', '2021-07-04 16:20:01', 'Adult Swim'),
(10, '2021-07-04 16:21:39', '2021-07-04 16:21:39', 'NBC');

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


--
-- Daten für Tabelle `media`
--

INSERT INTO `media` (`id`, `created_at`, `updated_at`, `medium_name`, `picture_path`, `release_date`, `short_description`) VALUES
(1, '2021-06-21 19:34:35', '2021-06-21 19:34:35', 'Aera', '1/poster.jpg', '2015-10-26', 'Im Jahr 2019 herrscht eine neue Weltordnung: Die Götter kehren auf die Erde zurück. Alle Götter – bis auf einen. Während Odin, Zeus, Manitu, Anubis, Shiva und Co. sich ihre alten Kultstätten zurückholen und ihre Anhänger um sich scharen, warten Christen, Moslems und Juden vergeblich. Die einst mächtigsten Religionen der Welt werden bald als bedeutungslose Sekten belächelt. Mit »AERA - Rückkehr der Götter« hat Markus Heitz einen neuen Kosmos geschaffen - ein großes Vergnügen für alle Fans von düsterer Spannung und filmreifer Action!'),
(2, '2021-06-21 19:34:35', '2021-06-21 19:34:35', 'Wächter der Nacht', '2/poster.jpg', '2011-08-19', 'In Russland das Kultbuch schlechthin und erfolgreicher als »Der Herr der Ringe« oder »Harry Potter«: Sergej Lukianenkos »Wächter der Nacht« – eine einzigartige Mischung aus Fantasy und Horror über den ewigen Kampf zwischen den Mächten des Lichts und der Finsternis'),
(3, '2021-06-21 19:39:27', '2021-06-21 19:39:27', 'Theoretische Informatik', '3/poster.jpg', '2018-08-06', 'Das Buch führt umfassend in das Gebiet der theoretischen Informatik ein und behandelt den Stoffumfang, der für das Bachelor-Studium an Universitäten und Hochschulen in den Fächern Informatik und Informationstechnik benötigt wird. Die Darstellung und das didaktische Konzept verfolgen das Ziel, einen durchweg praxisnahen Zugang zu den mitunter sehr theoretisch geprägten Themen zu schaffen. Theoretische Informatik muss nicht trocken sein. Sie kann Spaß machen und genau dies versucht das Buch zu vermitteln. Die verschiedenen Methoden und Verfahren werden anhand konkreter Beispiele eingeführt und durch zahlreiche Querverbindungen wird gezeigt, wie die fundamentalen Ergebnisse der theoretischen Informatik die moderne Informationstechnologie prägen.'),
(4, '2021-06-21 19:42:41', '2021-06-21 19:42:41', 'World of Warcraft', '4/poster.jpg', '2004-11-23', 'World of Warcraft, abgekürzt WoW, ist ein über das Internet spielbares Computer-Rollenspiel (MMORPG) des US-amerikanischen Spielentwicklers Blizzard Entertainment. Es wurde 2004 erstmals veröffentlicht, in Europa erschien es 2005, später auch in anderen Ländern, unter anderem in der Volksrepublik China.'),
(5, '2021-06-21 19:42:41', '2021-06-21 19:42:41', 'It Takes Two', '5/poster.jpg', '2021-03-26', 'It Takes Two ist ein Action-Adventure, dass ihr im Koop mit einem Freund durchspielen könnt. Hier steuert ihr zwei Puppen, welche die Eltern des Protagonisten repräsentieren und dabei sind, sich zu trennen. It Takes Two ist ein rasanter, fantasievoller und irre kreativer Action-Trip.'),
(6, '2021-06-21 19:42:41', '2021-06-21 19:42:41', 'Final Fantasy XV', '6/poster.jpg', '2016-11-29', 'Final Fantasy XV spielt in der Welt Eos. Alle Nationen, bis auf das Königreich Lucis, werden von dem Imperium Niflheim beherrscht. Niflheim nutzt zudem die Friedensverhandlungen aus, um Lucis einzunehmen.'),
(7, '2021-06-21 20:21:37', '2021-06-21 20:21:37', 'Scrubs', '7/poster.jpg', '2001-10-02', 'Die Anfänger ist eine US-amerikanische Dramedy-Sitcom, die 2001 von Bill Lawrence entwickelt wurde. Die neun Staffeln beinhalten 182 Folgen, die bis 2010 ausgestrahlt wurden. Die Serie behandelt die beruflichen und privaten Probleme junger Ärzte seit ihren ersten Gehversuchen im Krankenhausalltag.'),
(8, '2021-06-21 20:21:37', '2021-06-21 20:21:37', 'Mein erster Tag', '8/poster.jpg', '2001-10-02', 'John Dorian, genannt J.D., tritt seine Stelle als Assistenzarzt an'),
(9, '2021-06-21 20:26:12', '2021-06-21 20:26:12', 'Mein Mentor', '9/poster.jpg', '2001-10-02', '\r\nLangsam bekommt J.D. Routine bei seiner Arbeit als Assistenzarzt.'),
(10, '2021-06-21 20:28:16', '2021-06-21 20:28:16', 'Mein Kunstfehler', '10/poster.jpg', '2001-10-02', 'Turk hat neuerdings nur Augen für Carla'),
(11, '2021-06-21 20:52:29', '2021-06-21 20:52:29', 'Tucker and Dale vs. Evil', '11/poster.jpg', '2011-02-10', '\r\nFür ein friedliches Wochenende machen sich Tucker und Dale auf den Weg zu ihrer Ferienhütte in der Wildnis. Als sie auf dem Freeway fast mit einer Gruppe Collegekids kollidieren, ahnen die gutherzigen Hinterwäldler nichts Böses. Doch Dales schüchterne Einfalt und die Vorbehalte der Studenten gegen das rustikale Äußere der Hillbillies lenken eigentlich harmlose Ereignisse in eine fatale Richtung.'),
(12, '2021-06-21 20:52:29', '2021-06-21 20:52:29', 'Braveheart', '12/poster.jpg', '1995-10-05', 'Schottland im 13. Jahrhundert. Der englische König Edward I. führt ein brutales Regiment über das Land. Als William Wallaces Frau von Truppen ermordet wird, schwört er auf Rache.'),
(13, '2021-06-21 20:52:29', '2021-06-21 20:52:29', 'Rush Hour', '13/poster.jpg', '1998-09-18', 'Als diese beiden Cops aus völlig unterschiedlichen Welten temperamentvoll aneinander geraten, sorgt der Kulturschock für die Erkenntnis, dass sie nur Eines gemeinsam haben.'),
(14, '2021-07-04 14:06:47', '2021-07-04 14:06:47', 'Transformers', '14/poster.jpg', '2007-07-04', 'Auf dem Planeten Cybertron lebte eine Rasse intelligenter, eigenständig agierender Roboter, die über die Fähigkeit verfügen, ihre Körper in andere Formen zu verwandeln. Einst regierten Optimus Prime und Megatron gemeinsam den Planeten, doch insgeheim scharte der machthungrige Megatron eine Armee ihm treu ergebener Gefolgsleute, die Decepticons, um sich. Er verfolgt das Ziel, Macht über das gesamte Universum zu erlangen. Das soll verhindert werden.'),
(15, '2021-07-04 14:10:12', '2021-07-04 14:10:12', 'Toy Story 2', '15/poster.jpg', '2000-02-03', 'Woody wird von einem fanatischen Spielzeugsammler entführt. Seine einzige Hoffnung besteht nun darin, dass Buzz Lightyear und seine Freunde ihn durch eine halsbrecherische Rettungsaktion aus den Fängen des bösen Spielzeugsammlers befreien. Währenddessen trifft Woody auf drei andere Wild-West-Spielzeuge, die gemeinsam mit ihm an ein Spielzeugmuseum in Tokio verkauft werden sollen. Woody will fliehen, da er Andy und seine Freunde vermisst.'),
(16, '2021-07-04 14:12:25', '2021-07-04 14:12:25', 'Interstellar', '16/poster.jpg', '2014-11-06', 'Die Menschheit steht bereits kurz vor ihrer Auslöschung durch Klimawandel und Nahrungsmangel, als ein mysteriöser Riss im Raum-Zeit-Gewebe entdeckt wird, der eine Chance zum Überleben verheißt. Der ehemalige NASA-Pilot Cooper wird mit einem Forscherteam auf eine Mission durch das Wurmloch in ein anderes Sonnensystem geschickt, um nach neuen Lebensräumen zu suchen. Es ist eine Reise ins Ungewisse, denn Wurmlöcher sind noch kaum erforscht, und niemand weiß, was auf der anderen Seite wartet.'),
(17, '2021-07-04 14:13:47', '2021-07-04 14:13:47', 'Idiocracy', '17/poster.jpg', '2007-01-25', 'Joe Bowers arbeitet als Bibliothekar bei der Army und ist nicht unbedingt der intelligenteste Zeitgenosse. Ein optimales Versuchskaninchen also für das streng geheime Projekt des Pentagons. Ein Experiment, bei dem Menschen in einen Winterschlaf versetzt werden, um sie zu einem späteren Zeitpunkt wieder zum Leben zu erwecken. Das auf ein Jahr angesetzte Projekt gerät in Vergessenheit und Joe Bowers und die ihm damals als fruchtbare Partnerin zugeteilte Prostituierte Rita erwachen im Jahre 2505.'),
(18, '2021-07-04 14:15:47', '2021-07-04 14:15:47', 'Final Fantasy X', '18/poster.jpg', '2001-07-19', 'Die Handlung dieses Rollenspiels dreht sich um die Reise von Tidus und Yuna, die Sin besiegen wollen. Sin stellt gewissermaßen das Übel in der Welt von Spira dar.'),
(19, '2021-07-04 14:17:40', '2021-07-04 14:17:40', 'Dark Souls', '19/poster.jpg', '2011-09-22', 'Die ganze Geschichte von Dark Souls: Gwyn zerteilt seine Seele. Um den Zerfall der Ersten Flamme aufzuhalten, zerteilt Gwyn seine Seele und gibt diese Stücke an seine Getreuen. Ein Stück erhält Seath, der Schuppenlose. Das andere erhalten die Vier Könige, die über Neu Londo wachten.'),
(20, '2021-07-04 14:19:06', '2021-07-04 14:19:06', 'The Legend of Zelda: Breath of the Wild', '20/poster.jpg', '2017-03-03', 'Nachdem die Verheerung Ganon in ihren Reinkarnationen immer wieder das Land Hyrule angreift, kann 10.000 Jahre vor Einsetzen der Handlung von Breath of the Wild der Zyklus durch die Konstruktion von vier Maschinen, den sogenannte Titanen, und zahlreichen kleineren Maschinen, den Wächtern, unterbrochen werden.'),
(21, '2021-07-04 14:21:13', '2021-07-04 14:21:13', 'Dante die göttliche Komödie', '21/poster.jpg', '2005-04-30', 'Dantes Versepos erzählt die Läuterung eines empfindsamen, oft schwermütigen Icherzählers durch die drei Reiche der jenseitigen Welt - durch die Hölle (Inferno), das Fegefeuer (Purgatorio) und das Paradies (Paradiso). Der römische Dichter Vergil führt den Helden sicher durch die Reiche des Jenseits.'),
(22, '2021-07-04 14:22:36', '2021-07-04 14:22:36', 'Isaac Asimov Die Foundation-Trilogie', '22/poster.jpg', '1963-01-01', 'Der Foundation-Zyklus ist Teil des Werkes des Science-Fiction-Schriftstellers Isaac Asimov. Den bekanntesten und ursprünglich zentralen Teil bildet die Foundation-Trilogie, die den Untergang eines galaktischen Imperiums und den darauffolgenden Neuaufbau einer interstellaren menschlichen Zivilisation beschreibt.'),
(23, '2021-07-04 14:29:24', '2021-07-04 14:29:24', '1984', '23/poster.jpg', '1949-06-01', 'Im totalitären Staat Ozeanien lebt der kleine Angestellte Winston Smith ein erbärmliches Leben unter den alles überwachenden Augen des regierenden Großen Bruders, dessen Kameras und Mikrofone jede Bewegung seiner Untertanen verfolgen. Tagsüber ist Winstons Smiths Aufgabe, im Ministerium für Wahrheit die Geschichte zu fälschen, indem er alte Nachrichtenmeldungen und Dokumente rückwirkend so ändert, dass sie der geänderten offiziellen Sichtweise des Staates entsprechen.'),
(24, '2021-07-04 14:31:32', '2021-07-04 14:31:32', 'Avatar der Herr der Elemente', '24/poster.jpg', '2005-02-21', 'In dieser Serie muss der zwölfjährige Aang der Welt Frieden und Gleichgewicht bringen, indem er den Krieg der Feuernation mit dem Rest der Welt beendet. Im Laufe der Handlung trifft er auf Freunde, Gefährten und Verbündete, die ihn unterstützen, wird aber ständig von der Feuernation gejagt.'),
(25, '2021-07-04 14:32:38', '2021-07-04 14:32:38', 'Enthüllungen zu Mitternacht ', '25/poster.jpg', '2020-04-20', 'Die Serie dreht sich um den Spacecaster Clancy, der Interviews mit anderen Wesen aus verschiedenen Welten des Multiversums führt.'),
(26, '2021-07-04 14:34:28', '2021-07-04 14:34:28', 'Love Death & Robots', '26/poster.jpg', '2019-03-15', 'Sie besteht aus animierten Kurzfilmen verschiedener Künstler aus der ganzen Welt. Jeder Kurzfilm hat eine in sich abgeschlossene Handlung.'),
(27, '2021-07-04 14:38:25', '2021-07-04 14:38:25', 'Rick and Morty', '27/poster.jpg', '2013-12-02', 'Rick and Morty erzählt von einem genialen Wissenschaftler und Erfinder und seinem weniger genialen Enkelsohn. Gemeinsam bestreiten die beiden täglich neue Abenteuer – ganz zum Missfallen von Mortys Eltern, die in Rick eher einen potentiellen Störenfried sehen.'),
(28, '2021-07-04 14:40:43', '2021-07-04 14:40:43', 'Mein Rundumschlag', '28/poster.jpg', '2002-09-26', 'Nach Jordans Enthüllungen stehen sich die Kollegen distanziert gegenüber.'),
(29, '2021-07-04 14:42:37', '2021-07-04 14:42:37', 'Mein Nachtdienst', '29/poster.jpg', '2002-10-03', 'J.D., Turk und Elliot absolvieren zum ersten Mal alleine eine Nachschicht.'),
(30, '2021-07-04 14:44:42', '2021-07-04 14:44:42', 'Mein drittes Jahr', '30/poster.jpg', '2003-10-02', 'Elliot entscheidet sich für eine radikale Persöhnlichkeitsänderung.');

--
-- Daten für Tabelle `series`
--

INSERT INTO `series` (`age_restriction`, `average_length`, `is_completed`, `id`, `network_id`) VALUES
(12, 22, b'1', 7, 10),
(6, 23, b'1', 24, 7),
(16, 23, b'0', 25, 8),
(17, 12, b'0', 26, 8),
(16, 22, b'0', 27, 9);

--
-- Daten für Tabelle `seasons`
--

INSERT INTO `seasons` (`id`, `created_at`, `updated_at`, `season_number`, `season_title`, `series_id`) VALUES
(1, '2021-06-21 20:25:06', '2021-06-21 20:25:06', 1, 'Staffel 1', 7),
(2, '2021-07-04 16:22:34', '2021-07-04 16:22:34', 2, 'Staffel 2', 7),
(3, '2021-07-04 16:23:22', '2021-07-04 16:23:22', 3, 'Staffel 3', 7),
(4, '2021-07-04 16:23:38', '2021-07-04 16:23:38', 4, 'Staffel 4', 7),
(5, '2021-07-04 16:23:52', '2021-07-04 16:23:52', 5, 'Staffel 5', 7),
(6, '2021-07-04 16:24:02', '2021-07-04 16:24:02', 6, 'Staffel 6', 7),
(7, '2021-07-04 16:24:12', '2021-07-04 16:24:12', 7, 'Staffel 7', 7),
(8, '2021-07-04 16:24:21', '2021-07-04 16:24:21', 8, 'Staffel 8', 7),
(9, '2021-07-04 16:24:35', '2021-07-04 16:24:35', 9, 'Staffel 9', 7),
(10, '2021-07-04 16:25:00', '2021-07-04 16:25:00', 1, 'Wasser', 24),
(11, '2021-07-04 16:26:14', '2021-07-04 16:26:14', 2, 'Erde', 24),
(12, '2021-07-04 16:26:23', '2021-07-04 16:26:23', 3, 'Feuer', 24),
(13, '2021-07-04 16:28:00', '2021-07-04 16:28:00', 1, 'Staffel 1', 25),
(14, '2021-07-04 16:28:17', '2021-07-04 16:28:17', 1, 'Staffel 1', 26),
(15, '2021-07-04 16:28:34', '2021-07-04 16:28:34', 2, 'Staffel 2', 26),
(16, '2021-07-04 16:29:00', '2021-07-04 16:29:00', 1, 'Staffel 1', 27),
(17, '2021-07-04 16:29:13', '2021-07-04 16:29:13', 2, 'Staffel 2', 27),
(18, '2021-07-04 16:29:22', '2021-07-04 16:29:22', 3, 'Staffel 3', 27),
(19, '2021-07-04 16:29:32', '2021-07-04 16:29:32', 4, 'Staffel 4', 27),
(20, '2021-07-04 16:29:42', '2021-07-04 16:29:42', 5, 'Staffel 5', 27);

--
-- Daten für Tabelle `books`
--

INSERT INTO `books` (`isebook`, `is_print`, `isbn`, `number_of_pages`, `id`, `book_publisher_id`) VALUES
(b'1', b'1', '978-3-426-51861-8', 600, 1, 1),
(b'1', b'1', '978-3-641-06578-2', 528, 2, 2),
(b'1', b'1', '978-3-446-45793-5', 435, 3, 3),
(b'1', b'1', '978-3-938484-11-1', 544, 21, 4),
(b'1', b'1', '978-3-453-31867', 879, 22, 2),
(b'1', b'1', '978-3-548-23410-6', 383, 23, 5);


--
-- Daten für Tabelle `episodes`
--

INSERT INTO `episodes` (`episode_number`, `length`, `id`, `season_id`) VALUES
(1, 22, 8, 1),
(2, 21, 9, 1),
(3, 21, 10, 1),
(1, 20, 28, 2),
(2, 20, 29, 2),
(1, 20, 30, 3);

--
-- Daten für Tabelle `games`
--

INSERT INTO `games` (`age_restriction`, `average_playtime`, `max_number_of_gamers`, `min_number_of_gamers`, `id`, `game_publisher_id`) VALUES
(12, NULL, NULL, 1, 4, 3),
(12, 17, 2, 2, 5, 2),
(12, 40, 4, 1, 6, 1),
(12, 80, NULL, 1, 18, 1),
(16, 60, NULL, 1, 19, 4),
(12, 50, NULL, 1, 20, 5);


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

--
-- Daten für Tabelle `media_genres`
--

INSERT INTO `media_genres` (`genres_id`, `media_id`) VALUES
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(7, 6),
(8, 4),
(10, 5);

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

--
-- Daten für Tabelle `media_languages`
--

INSERT INTO `media_languages` (`languages_id`, `media_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 12),
(2, 12);

--
-- Daten für Tabelle `movies`
--

INSERT INTO `movies` (`age_restriction`, `length`, `id`, `network_id`) VALUES
(16, 89, 11, NULL),
(16, 177, 12, NULL),
(12, 98, 13, NULL),
(12, 143, 14, NULL),
(0, 89, 15, 4),
(12, 169, 16, 5),
(12, 84, 17, 6);


--
-- Daten für Tabelle `roles`
--

INSERT INTO `roles` (`id`, `created_at`, `updated_at`, `role_name`) VALUES
(1, '2021-06-21 19:17:34', '2021-06-21 19:17:34', 'User'),
(2, '2021-06-21 19:17:34', '2021-06-21 19:17:34', 'Admin'),
(3, '2021-06-21 19:17:34', '2021-06-21 19:17:34', 'Moderator'),
(4, '2021-06-21 19:17:34', '2021-06-21 19:17:34', 'Business');

--
-- Daten für Tabelle `logins`
--

INSERT INTO `logins` (`id`, `created_at`, `updated_at`, `email`, `failed_login_count`, `is_enabled`, `last_login`, `password_hash`, `password_reset_created_at`, `password_reset_hash`) VALUES
(1, '2021-06-30 20:35:49', '2021-06-30 20:38:04', 'chris@rays.com', 0, b'0', NULL, '$2a$10$j9UIcuHfJV9ENas.kAW8pevUCibNKIaeUxo3ABdPanvleQNXk860G', NULL, NULL),
(2, '2021-06-30 20:38:56', '2021-06-30 20:38:56', 'robin@rays.com', 0, b'0', NULL, '$2a$10$j9UIcuHfJV9ENas.kAW8pevUCibNKIaeUxo3ABdPanvleQNXk860G', NULL, NULL),
(3, '2021-06-30 20:39:20', '2021-06-30 20:39:20', 'mickey@rays.com', 0, b'0', NULL, '$2a$10$j9UIcuHfJV9ENas.kAW8pevUCibNKIaeUxo3ABdPanvleQNXk860G', NULL, NULL),
(4, '2021-06-30 20:39:41', '2021-06-30 20:39:41', 'john@rays.com', 0, b'0', NULL, '$2a$10$j9UIcuHfJV9ENas.kAW8pevUCibNKIaeUxo3ABdPanvleQNXk860G', NULL, NULL),
(5, '2021-06-30 20:40:12', '2021-06-30 20:40:12', 'schnoernia@web.com', 0, b'0', NULL, '$2a$10$j9UIcuHfJV9ENas.kAW8pee/MdD.dmDeUAYJoi9x.zk675pfblLK6', NULL, NULL),
(6, '2021-06-30 20:40:47', '2021-06-30 20:40:47', 'r.kruse@web.com', 0, b'0', NULL, '$2a$10$j9UIcuHfJV9ENas.kAW8pee/MdD.dmDeUAYJoi9x.zk675pfblLK6', NULL, NULL);

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`id`, `created_at`, `updated_at`, `first_name`, `gender`, `last_name`, `profile_picture_path`, `second_name`, `user_name`, `login_id`, `role_id`) VALUES
(1, '2021-06-30 20:35:49', '2021-06-30 20:35:49', 'Christoph', 'male', 'Frischmuth', NULL, '', 'ChrisTheSquirrel', 1,2),
(2, '2021-06-30 20:38:56', '2021-06-30 20:38:56', 'Robin', 'male', 'beck', NULL, '', 'LittleBigPlaye', 2,2),
(3, '2021-06-30 20:39:20', '2021-06-30 20:39:20', 'Mickey', 'male', 'Knop', NULL, '', 'mikmin', 3,2),
(4, '2021-06-30 20:39:41', '2021-06-30 20:39:41', 'John', 'male', 'Klippstein', NULL, '', 'Avartos', 4,2),
(5, '2021-06-30 20:40:12', '2021-06-30 20:40:12', 'Saskia', 'female', 'Wohlers', NULL, '', 'Schnoernia', 5,1),
(6, '2021-06-30 20:40:47', '2021-06-30 20:40:47', 'Rolf', 'male', 'Kruse', NULL, '', 'Rolf_Kruse', 6,1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
