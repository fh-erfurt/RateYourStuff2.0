SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";
INSERT INTO `book_publishers` (
    `id`,
    `created_at`,
    `updated_at`,
    `book_publisher_title`
)
VALUES (
           1,
           '2021-06-21 19:12:59',
           '2021-06-21 19:12:59',
           'Knaur'
       ),
       (
           2,
           '2021-06-21 19:12:59',
           '2021-06-21 19:12:59',
           'Heyne'
       ),
       (
           3,
           '2021-06-21 19:12:59',
           '2021-06-21 19:12:59',
           'Hanser'
       ),
       (
           4,
           '2021-07-04 16:08:18',
           '2021-07-04 16:08:18',
           'Anaconda'
       ),
       (
           5,
           '2021-07-04 16:08:43',
           '2021-07-04 16:08:43',
           'ullstein'
       );
INSERT INTO `game_publishers` (
    `id`,
    `created_at`,
    `updated_at`,
    `game_publisher_title`
)
VALUES (
           1,
           '2021-06-21 19:14:05',
           '2021-06-21 19:14:05',
           'Square Enix'
       ),
       (
           2,
           '2021-06-21 19:14:05',
           '2021-06-21 19:14:05',
           'Electronic Arts'
       ),
       (
           3,
           '2021-06-21 19:14:05',
           '2021-06-21 19:14:05',
           'Blizzard'
       ),
       (
           4,
           '2021-07-04 16:04:22',
           '2021-07-04 16:04:22',
           'Bandai Namco Games'
       ),
       (
           5,
           '2021-07-04 16:05:26',
           '2021-07-04 16:05:26',
           'Nintendo'
       ),
       (
           6,
           '2021-08-14 14:14:44',
           '2021-08-14 14:14:44',
           'Sony Interactive Entertainment'
       ),
       (
           7,
           '2021-08-14 14:38:35',
           '2021-08-14 14:38:35',
           'Buddy Productions GmbH'
       ),
       (
           8,
           '2021-08-14 14:44:31',
           '2021-08-14 14:44:31',
           'Konami'
       );
INSERT INTO `networks` (
    `id`,
    `created_at`,
    `updated_at`,
    `network_title`
)
VALUES (
           1,
           '2021-06-21 19:14:50',
           '2021-06-21 19:14:50',
           'HBO'
       ),
       (
           2,
           '2021-06-21 19:14:50',
           '2021-06-21 19:14:50',
           'CBS'
       ),
       (
           3,
           '2021-06-21 19:14:50',
           '2021-06-21 19:14:50',
           'Marvel'
       ),
       (
           4,
           '2021-07-04 15:56:14',
           '2021-07-04 15:56:14',
           'Pixar'
       ),
       (
           5,
           '2021-07-04 15:58:28',
           '2021-07-04 15:58:28',
           'Paramount Pictures'
       ),
       (
           6,
           '2021-07-04 16:00:30',
           '2021-07-04 16:00:30',
           'Ternion'
       ),
       (
           7,
           '2021-07-04 16:15:11',
           '2021-07-04 16:15:11',
           'Nickelodeon'
       ),
       (
           8,
           '2021-07-04 16:17:34',
           '2021-07-04 16:17:34',
           'Netflix'
       ),
       (
           9,
           '2021-07-04 16:20:01',
           '2021-07-04 16:20:01',
           'Adult Swim'
       ),
       (
           10,
           '2021-07-04 16:21:39',
           '2021-07-04 16:21:39',
           'NBC'
       ),
       (
           11,
           '2021-08-14 14:19:56',
           '2021-08-14 14:19:56',
           '20th Century Fox'
       ),
       (
           12,
           '2021-08-14 14:33:57',
           '2021-08-14 14:33:57',
           'EMS GmbH'
       );
INSERT INTO `platforms` (
    `id`,
    `created_at`,
    `updated_at`,
    `platform_title`
)
VALUES (
           1,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Playstation'
       ),
       (
           2,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Playstation 2'
       ),
       (
           3,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Playstation 3'
       ),
       (
           4,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Playstation 4'
       ),
       (
           5,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Playstation 5'
       ),
       (
           6,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Xbox'
       ),
       (
           7,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Xbox 360'
       ),
       (
           8,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Xbox Series S'
       ),
       (
           9,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Xbox Series X'
       ),
       (
           10,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'NES'
       ),
       (
           11,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'SNES'
       ),
       (
           12,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'N64'
       ),
       (
           13,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'GameCube'
       ),
       (
           14,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'WII'
       ),
       (
           15,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'WII U'
       ),
       (
           16,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Nintendo Switch'
       ),
       (
           17,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'GameBoy'
       ),
       (
           18,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'GameBoy Color'
       ),
       (
           19,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'GameBoy Advance'
       ),
       (
           20,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Nintendo DS'
       ),
       (
           21,
           '2021-06-21 19:15:42',
           '2021-06-21 19:15:42',
           'Nintendo 3 DS'
       ),
       (
           22,
           '2021-06-21 19:49:15',
           '2021-06-21 19:49:15',
           'PC'
       );
INSERT INTO `media` (
    `id`,
    `created_at`,
    `updated_at`,
    `medium_name`,
    `picture_path`,
    `release_date`,
    `short_description`
)
VALUES (
           1,
           '2021-06-21 19:34:35',
           '2021-06-21 19:34:35',
           'Aera',
           '1/poster.jpg',
           '2015-10-26',
           'Im Jahr 2019 herrscht eine neue Weltordnung: Die Götter kehren auf die Erde zurück. Alle Götter – bis auf einen. Während Odin, Zeus, Manitu, Anubis, Shiva und Co. sich ihre alten Kultstätten zurückholen und ihre Anhänger um sich scharen, warten Christen, Moslems und Juden vergeblich. Die einst mächtigsten Religionen der Welt werden bald als bedeutungslose Sekten belächelt. Mit »AERA - Rückkehr der Götter« hat Markus Heitz einen neuen Kosmos geschaffen - ein großes Vergnügen für alle Fans von düsterer Spannung und filmreifer Action!'
       ),
       (
           2,
           '2021-06-21 19:34:35',
           '2021-06-21 19:34:35',
           'Wächter der Nacht',
           '2/poster.jpg',
           '2011-08-19',
           'In Russland das Kultbuch schlechthin und erfolgreicher als »Der Herr der Ringe« oder »Harry Potter«: Sergej Lukianenkos »Wächter der Nacht« – eine einzigartige Mischung aus Fantasy und Horror über den ewigen Kampf zwischen den Mächten des Lichts und der Finsternis'
       ),
       (
           3,
           '2021-06-21 19:39:27',
           '2021-06-21 19:39:27',
           'Theoretische Informatik',
           '3/poster.jpg',
           '2018-08-06',
           'Das Buch führt umfassend in das Gebiet der theoretischen Informatik ein und behandelt den Stoffumfang, der für das Bachelor-Studium an Universitäten und Hochschulen in den Fächern Informatik und Informationstechnik benötigt wird. Die Darstellung und das didaktische Konzept verfolgen das Ziel, einen durchweg praxisnahen Zugang zu den mitunter sehr theoretisch geprägten Themen zu schaffen. Theoretische Informatik muss nicht trocken sein. Sie kann Spaß machen und genau dies versucht das Buch zu vermitteln. Die verschiedenen Methoden und Verfahren werden anhand konkreter Beispiele eingeführt und durch zahlreiche Querverbindungen wird gezeigt, wie die fundamentalen Ergebnisse der theoretischen Informatik die moderne Informationstechnologie prägen.'
       ),
       (
           4,
           '2021-06-21 19:42:41',
           '2021-06-21 19:42:41',
           'World of Warcraft',
           '4/poster.jpg',
           '2004-11-23',
           'World of Warcraft, abgekürzt WoW, ist ein über das Internet spielbares Computer-Rollenspiel (MMORPG) des US-amerikanischen Spielentwicklers Blizzard Entertainment. Es wurde 2004 erstmals veröffentlicht, in Europa erschien es 2005, später auch in anderen Ländern, unter anderem in der Volksrepublik China.'
       ),
       (
           5,
           '2021-06-21 19:42:41',
           '2021-06-21 19:42:41',
           'It Takes Two',
           '5/poster.jpg',
           '2021-03-26',
           'It Takes Two ist ein Action-Adventure, dass ihr im Koop mit einem Freund durchspielen könnt. Hier steuert ihr zwei Puppen, welche die Eltern des Protagonisten repräsentieren und dabei sind, sich zu trennen. It Takes Two ist ein rasanter, fantasievoller und irre kreativer Action-Trip.'
       ),
       (
           6,
           '2021-06-21 19:42:41',
           '2021-06-21 19:42:41',
           'Final Fantasy XV',
           '6/poster.jpg',
           '2016-11-29',
           'Final Fantasy XV spielt in der Welt Eos. Alle Nationen, bis auf das Königreich Lucis, werden von dem Imperium Niflheim beherrscht. Niflheim nutzt zudem die Friedensverhandlungen aus, um Lucis einzunehmen.'
       ),
       (
           7,
           '2021-06-21 20:21:37',
           '2021-06-21 20:21:37',
           'Scrubs',
           '7/poster.jpg',
           '2001-10-02',
           'Die Anfänger ist eine US-amerikanische Dramedy-Sitcom, die 2001 von Bill Lawrence entwickelt wurde. Die neun Staffeln beinhalten 182 Folgen, die bis 2010 ausgestrahlt wurden. Die Serie behandelt die beruflichen und privaten Probleme junger Ärzte seit ihren ersten Gehversuchen im Krankenhausalltag.'
       ),
       (
           8,
           '2021-06-21 20:21:37',
           '2021-06-21 20:21:37',
           'Mein erster Tag',
           '8/poster.jpg',
           '2001-10-02',
           'John Dorian, genannt J.D., tritt seine Stelle als Assistenzarzt an'
       ),
       (
           9,
           '2021-06-21 20:26:12',
           '2021-06-21 20:26:12',
           'Mein Mentor',
           '9/poster.jpg',
           '2001-10-02',
           '\r\nLangsam bekommt J.D. Routine bei seiner Arbeit als Assistenzarzt.'
       ),
       (
           10,
           '2021-06-21 20:28:16',
           '2021-06-21 20:28:16',
           'Mein Kunstfehler',
           '10/poster.jpg',
           '2001-10-02',
           'Turk hat neuerdings nur Augen für Carla'
       ),
       (
           11,
           '2021-06-21 20:52:29',
           '2021-06-21 20:52:29',
           'Tucker and Dale vs. Evil',
           '11/poster.jpg',
           '2011-02-10',
           '\r\nFür ein friedliches Wochenende machen sich Tucker und Dale auf den Weg zu ihrer Ferienhütte in der Wildnis. Als sie auf dem Freeway fast mit einer Gruppe Collegekids kollidieren, ahnen die gutherzigen Hinterwäldler nichts Böses. Doch Dales schüchterne Einfalt und die Vorbehalte der Studenten gegen das rustikale Äußere der Hillbillies lenken eigentlich harmlose Ereignisse in eine fatale Richtung.'
       ),
       (
           12,
           '2021-06-21 20:52:29',
           '2021-06-21 20:52:29',
           'Braveheart',
           '12/poster.jpg',
           '1995-10-05',
           'Schottland im 13. Jahrhundert. Der englische König Edward I. führt ein brutales Regiment über das Land. Als William Wallaces Frau von Truppen ermordet wird, schwört er auf Rache.'
       ),
       (
           13,
           '2021-06-21 20:52:29',
           '2021-06-21 20:52:29',
           'Rush Hour',
           '13/poster.jpg',
           '1998-09-18',
           'Als diese beiden Cops aus völlig unterschiedlichen Welten temperamentvoll aneinander geraten, sorgt der Kulturschock für die Erkenntnis, dass sie nur Eines gemeinsam haben.'
       ),
       (
           14,
           '2021-07-04 14:06:47',
           '2021-07-04 14:06:47',
           'Transformers',
           '14/poster.jpg',
           '2007-07-04',
           'Auf dem Planeten Cybertron lebte eine Rasse intelligenter, eigenständig agierender Roboter, die über die Fähigkeit verfügen, ihre Körper in andere Formen zu verwandeln. Einst regierten Optimus Prime und Megatron gemeinsam den Planeten, doch insgeheim scharte der machthungrige Megatron eine Armee ihm treu ergebener Gefolgsleute, die Decepticons, um sich. Er verfolgt das Ziel, Macht über das gesamte Universum zu erlangen. Das soll verhindert werden.'
       ),
       (
           15,
           '2021-07-04 14:10:12',
           '2021-07-04 14:10:12',
           'Toy Story 2',
           '15/poster.jpg',
           '2000-02-03',
           'Woody wird von einem fanatischen Spielzeugsammler entführt. Seine einzige Hoffnung besteht nun darin, dass Buzz Lightyear und seine Freunde ihn durch eine halsbrecherische Rettungsaktion aus den Fängen des bösen Spielzeugsammlers befreien. Währenddessen trifft Woody auf drei andere Wild-West-Spielzeuge, die gemeinsam mit ihm an ein Spielzeugmuseum in Tokio verkauft werden sollen. Woody will fliehen, da er Andy und seine Freunde vermisst.'
       ),
       (
           16,
           '2021-07-04 14:12:25',
           '2021-07-04 14:12:25',
           'Interstellar',
           '16/poster.jpg',
           '2014-11-06',
           'Die Menschheit steht bereits kurz vor ihrer Auslöschung durch Klimawandel und Nahrungsmangel, als ein mysteriöser Riss im Raum-Zeit-Gewebe entdeckt wird, der eine Chance zum Überleben verheißt. Der ehemalige NASA-Pilot Cooper wird mit einem Forscherteam auf eine Mission durch das Wurmloch in ein anderes Sonnensystem geschickt, um nach neuen Lebensräumen zu suchen. Es ist eine Reise ins Ungewisse, denn Wurmlöcher sind noch kaum erforscht, und niemand weiß, was auf der anderen Seite wartet.'
       ),
       (
           17,
           '2021-07-04 14:13:47',
           '2021-07-04 14:13:47',
           'Idiocracy',
           '17/poster.jpg',
           '2007-01-25',
           'Joe Bowers arbeitet als Bibliothekar bei der Army und ist nicht unbedingt der intelligenteste Zeitgenosse. Ein optimales Versuchskaninchen also für das streng geheime Projekt des Pentagons. Ein Experiment, bei dem Menschen in einen Winterschlaf versetzt werden, um sie zu einem späteren Zeitpunkt wieder zum Leben zu erwecken. Das auf ein Jahr angesetzte Projekt gerät in Vergessenheit und Joe Bowers und die ihm damals als fruchtbare Partnerin zugeteilte Prostituierte Rita erwachen im Jahre 2505.'
       ),
       (
           18,
           '2021-07-04 14:15:47',
           '2021-07-04 14:15:47',
           'Final Fantasy X',
           '18/poster.jpg',
           '2001-07-19',
           'Die Handlung dieses Rollenspiels dreht sich um die Reise von Tidus und Yuna, die Sin besiegen wollen. Sin stellt gewissermaßen das Übel in der Welt von Spira dar.'
       ),
       (
           19,
           '2021-07-04 14:17:40',
           '2021-07-04 14:17:40',
           'Dark Souls',
           '19/poster.jpg',
           '2011-09-22',
           'Die ganze Geschichte von Dark Souls: Gwyn zerteilt seine Seele. Um den Zerfall der Ersten Flamme aufzuhalten, zerteilt Gwyn seine Seele und gibt diese Stücke an seine Getreuen. Ein Stück erhält Seath, der Schuppenlose. Das andere erhalten die Vier Könige, die über Neu Londo wachten.'
       ),
       (
           20,
           '2021-07-04 14:19:06',
           '2021-07-04 14:19:06',
           'The Legend of Zelda: Breath of the Wild',
           '20/poster.jpg',
           '2017-03-03',
           'Nachdem die Verheerung Ganon in ihren Reinkarnationen immer wieder das Land Hyrule angreift, kann 10.000 Jahre vor Einsetzen der Handlung von Breath of the Wild der Zyklus durch die Konstruktion von vier Maschinen, den sogenannte Titanen, und zahlreichen kleineren Maschinen, den Wächtern, unterbrochen werden.'
       ),
       (
           21,
           '2021-07-04 14:21:13',
           '2021-07-04 14:21:13',
           'Dante die göttliche Komödie',
           '21/poster.jpg',
           '2005-04-30',
           'Dantes Versepos erzählt die Läuterung eines empfindsamen, oft schwermütigen Icherzählers durch die drei Reiche der jenseitigen Welt - durch die Hölle (Inferno), das Fegefeuer (Purgatorio) und das Paradies (Paradiso). Der römische Dichter Vergil führt den Helden sicher durch die Reiche des Jenseits.'
       ),
       (
           22,
           '2021-07-04 14:22:36',
           '2021-07-04 14:22:36',
           'Isaac Asimov Die Foundation-Trilogie',
           '22/poster.jpg',
           '1963-01-01',
           'Der Foundation-Zyklus ist Teil des Werkes des Science-Fiction-Schriftstellers Isaac Asimov. Den bekanntesten und ursprünglich zentralen Teil bildet die Foundation-Trilogie, die den Untergang eines galaktischen Imperiums und den darauffolgenden Neuaufbau einer interstellaren menschlichen Zivilisation beschreibt.'
       ),
       (
           23,
           '2021-07-04 14:29:24',
           '2021-07-04 14:29:24',
           '1984',
           '23/poster.jpg',
           '1949-06-01',
           'Im totalitären Staat Ozeanien lebt der kleine Angestellte Winston Smith ein erbärmliches Leben unter den alles überwachenden Augen des regierenden Großen Bruders, dessen Kameras und Mikrofone jede Bewegung seiner Untertanen verfolgen. Tagsüber ist Winstons Smiths Aufgabe, im Ministerium für Wahrheit die Geschichte zu fälschen, indem er alte Nachrichtenmeldungen und Dokumente rückwirkend so ändert, dass sie der geänderten offiziellen Sichtweise des Staates entsprechen.'
       ),
       (
           24,
           '2021-07-04 14:31:32',
           '2021-07-04 14:31:32',
           'Avatar der Herr der Elemente',
           '24/poster.jpg',
           '2005-02-21',
           'In dieser Serie muss der zwölfjährige Aang der Welt Frieden und Gleichgewicht bringen, indem er den Krieg der Feuernation mit dem Rest der Welt beendet. Im Laufe der Handlung trifft er auf Freunde, Gefährten und Verbündete, die ihn unterstützen, wird aber ständig von der Feuernation gejagt.'
       ),
       (
           25,
           '2021-07-04 14:32:38',
           '2021-07-04 14:32:38',
           'Enthüllungen zu Mitternacht ',
           '25/poster.jpg',
           '2020-04-20',
           'Die Serie dreht sich um den Spacecaster Clancy, der Interviews mit anderen Wesen aus verschiedenen Welten des Multiversums führt.'
       ),
       (
           26,
           '2021-07-04 14:34:28',
           '2021-07-04 14:34:28',
           'Love Death & Robots',
           '26/poster.jpg',
           '2019-03-15',
           'Sie besteht aus animierten Kurzfilmen verschiedener Künstler aus der ganzen Welt. Jeder Kurzfilm hat eine in sich abgeschlossene Handlung.'
       ),
       (
           27,
           '2021-07-04 14:38:25',
           '2021-07-04 14:38:25',
           'Rick and Morty',
           '27/poster.jpg',
           '2013-12-02',
           'Rick and Morty erzählt von einem genialen Wissenschaftler und Erfinder und seinem weniger genialen Enkelsohn. Gemeinsam bestreiten die beiden täglich neue Abenteuer – ganz zum Missfallen von Mortys Eltern, die in Rick eher einen potentiellen Störenfried sehen.'
       ),
       (
           28,
           '2021-07-04 14:40:43',
           '2021-07-04 14:40:43',
           'Mein Rundumschlag',
           '28/poster.jpg',
           '2002-09-26',
           'Nach Jordans Enthüllungen stehen sich die Kollegen distanziert gegenüber.'
       ),
       (
           29,
           '2021-07-04 14:42:37',
           '2021-07-04 14:42:37',
           'Mein Nachtdienst',
           '29/poster.jpg',
           '2002-10-03',
           'J.D., Turk und Elliot absolvieren zum ersten Mal alleine eine Nachschicht.'
       ),
       (
           30,
           '2021-07-04 14:44:42',
           '2021-07-04 14:44:42',
           'Mein drittes Jahr',
           '30/poster.jpg',
           '2003-10-02',
           'Elliot entscheidet sich für eine radikale Persöhnlichkeitsänderung.'
       ),
       (
           31,
           '2021-08-14 14:07:12',
           '2021-08-14 14:07:12',
           'Dark Souls II',
           '31/poster.jpg',
           '2014-03-11',
           'Dark Souls II ist inhaltlich eine direkte Fortsetzung von Dark Souls, spielt aber viele tausend Jahre nach dessen Ereignissen.[4] Der Spieler ist zu Beginn der Geschichte erneut ein Untoter, weshalb er das zerstörte Königreich Drangleic aufsucht, wo es eine Heilung für den Fluch der Untoten geben soll. Er erhält vom Emerald Herald die Aufgabe, die vier großen Seelen (Seele der verlorenen Sünderin, Seele des Verkommenen, Seele des Alten Eisenkönigs und Seele von Freja, der Liebsten) zu sammeln.[5] Wenn er dies getan hat, fordert sie ihn auf, den König von Drangleic zu suchen. Nachdem der Spieler die übrigen Wachen des Königs besiegt hat, begegnet er der Königin Nashandra, die sagt, dass der König seinen Pflichten nicht nachgekommen sei und seinen Thron, durch seinen Ring verschlossen, zurückgelassen habe. Sie bittet den Spieler, ihn zu töten und ihr dessen Ring zu überreichen, damit der Spieler den Platz des Königs einnehmen kann.[6] Zum Ende dieser Aufgabe erfährt der Spieler, dass der Untergang des Reiches nicht die Schuld des Königs, sondern die der Königin war (welche eine Nachfahrin des Verstohlenen Zwerges aus Dark Souls ist) und den Thron selbst besteigen will. Später bittet Emerald Herald den Spieler erneut, Nashandra zu töten und den Thron von Drangleic für sich selbst in Anspruch zu nehmen. '
       ),
       (
           32,
           '2021-08-14 14:09:35',
           '2021-08-14 14:09:35',
           'Dark Souls III',
           '32/poster.jpg',
           '2016-03-24',
           'Die Spielwelt befindet sich immer noch im Zyklus aus Zeitalter des Feuers, gefolgt vom Fluch der Untoten (Dark Souls II), jedoch weigert sich Prinz Lothric, seinen Platz als König einzunehmen und so das Zeitalter des Feuers neu zu entfachen. Da auch sonst kein Untoter bereit zu sein scheint, Prinz Lothrics Platz als König einzunehmen, steckt der Zyklus der Welt fest; mit teils gravierenden Konsequenzen. In einem dem Big Crunch ähnelnden Ende ziehen sich längst vergangene Welten und Königreiche um das Königreich Lothric zusammen. Zusätzlich erweckt eine Glocke des Feuerband-Schreins ehemalige Könige längst vergangener Tage als Wesen, die als Asche bezeichnet werden, wieder. Doch auch diese weigern sich, Prinz Lothrics Platz einzunehmen. '
       ),
       (
           33,
           '2021-08-14 14:12:06',
           '2021-08-14 14:12:06',
           'Demon\'s Souls',
        '33/poster.jpg',
        '2009-02-05',
        'Das Spiel handelt von König Allant XII und seinem Versuch die Welt mitsamt der Menschheit auszulöschen. Mit Hilfe der Seelenkünste brachte er seinem Königreich Boletaria noch nie dagewesenen Wohlstand. Die Seelenkünste waren jedoch nicht menschlichen, sondern dämonischen Ursprungs. Sie waren deshalb über Jahrhunderte lang verboten. Die Natur der Seelenkünste geriet aber über die vielen Generationen in Vergessenheit und der König führte die Seelenkünste wieder ein. Boletaria entwickelte sich so unter seiner Regentschaft zu einem Paradies. Trotz seiner Leistung war der König aber unzufrieden, denn in seinen Augen war die Welt voller Schmerz und Leid. Durch die Seelenkünste allein konnte er nicht die gesamte Welt verändern. Alt und desillusioniert verfiel der verbitterte König einer wahnsinnigen Idee. Um das Elend zu beenden, wollte er die Menschheit vernichten. Dazu reiste er zum Nexus, um das dort gefangene Uralte („The Old One“), ein mächtiges Wesen und die Quelle der Seelenkünste, aus seinem Schlaf zu wecken. Das Uralte war eine körperlose Bestie mit einem unendlichen Appetit auf Seelen und sollte, so der Plan des Königs, sich von den Seelen aller Menschen ernähren. Jedoch konnte es sein Gefängnis nicht verlassen. An den Grenzen Boletarias beobachteten die Menschen nach der vermeintlichen Rückkehr des Königs, der ein Dämon in seiner Gestalt war, einen dichten farblosen Nebel. Der Nebel begann das Königreich zu umschließen und es von der Außenwelt abzutrennen. Zusätzlich zum Nebel schickte das Uralte seine seelenfressenden Dämonen ins Land. Wenn alle Bewohner eines Ortes ihrer Seele beraubt wären, dann würde der gesamte Ort und jeder dort vom Nebel vollständig verschluckt und in die unendliche Finsternis, dem eigentlichen Wesen des Uralten, verbannt werden. Jene, die ihre Seelen verloren, verloren gleichzeitig den Verstand und kämpften für die Dämonen gegen alle die noch ihre Seele hatten. Die Dämonen und ihre Diener waren sehr erfolgreich und das Königreich fiel ins Chaos. Die wenigen Überlebenden, die noch bei Verstand waren, hielten sich versteckt, denn die Konfrontation mit der Armee der Dämonen führte in der Regel zur Gefangennahme, dem Tod oder noch Schlimmerem. Lediglich der Ritter Vallarfax konnte den Nebel durchdringen und die Außenwelt erreichen um von Boletarias Notlage zu berichten. Er sprach auch von der verführerischen Macht der Dämonenseelen. Wenn ein Dämon eine Seele raubte, wurde die Dämonenseele gestärkt. Die Macht, die einer solchen Dämonenseele innewohnt, übersteigt die menschliche Vorstellungskraft. Diese Legende verbreitete sich von Mann zu Mann über die ganze Welt und seitdem betreten tapfere Krieger den farblosen Nebel um nach den Dämonenseelen zu suchen, doch bisher kehrte kein Einziger zurück. '
    ),
    (
        34,
        '2021-08-14 14:14:44',
        '2021-08-14 14:14:44',
        'Demon\'s Souls',
           '34/poster.jpg',
           '2020-11-12',
           'Das Spiel handelt von König Allant XII und seinem Versuch die Welt mitsamt der Menschheit auszulöschen. Mit Hilfe der Seelenkünste brachte er seinem Königreich Boletaria noch nie dagewesenen Wohlstand. Die Seelenkünste waren jedoch nicht menschlichen, sondern dämonischen Ursprungs. Sie waren deshalb über Jahrhunderte lang verboten. Die Natur der Seelenkünste geriet aber über die vielen Generationen in Vergessenheit und der König führte die Seelenkünste wieder ein. Boletaria entwickelte sich so unter seiner Regentschaft zu einem Paradies. Trotz seiner Leistung war der König aber unzufrieden, denn in seinen Augen war die Welt voller Schmerz und Leid. Durch die Seelenkünste allein konnte er nicht die gesamte Welt verändern. Alt und desillusioniert verfiel der verbitterte König einer wahnsinnigen Idee. Um das Elend zu beenden, wollte er die Menschheit vernichten. Dazu reiste er zum Nexus, um das dort gefangene Uralte („The Old One“), ein mächtiges Wesen und die Quelle der Seelenkünste, aus seinem Schlaf zu wecken. Das Uralte war eine körperlose Bestie mit einem unendlichen Appetit auf Seelen und sollte, so der Plan des Königs, sich von den Seelen aller Menschen ernähren. Jedoch konnte es sein Gefängnis nicht verlassen. An den Grenzen Boletarias beobachteten die Menschen nach der vermeintlichen Rückkehr des Königs, der ein Dämon in seiner Gestalt war, einen dichten farblosen Nebel. Der Nebel begann das Königreich zu umschließen und es von der Außenwelt abzutrennen. Zusätzlich zum Nebel schickte das Uralte seine seelenfressenden Dämonen ins Land. Wenn alle Bewohner eines Ortes ihrer Seele beraubt wären, dann würde der gesamte Ort und jeder dort vom Nebel vollständig verschluckt und in die unendliche Finsternis, dem eigentlichen Wesen des Uralten, verbannt werden. Jene, die ihre Seelen verloren, verloren gleichzeitig den Verstand und kämpften für die Dämonen gegen alle die noch ihre Seele hatten. Die Dämonen und ihre Diener waren sehr erfolgreich und das Königreich fiel ins Chaos. Die wenigen Überlebenden, die noch bei Verstand waren, hielten sich versteckt, denn die Konfrontation mit der Armee der Dämonen führte in der Regel zur Gefangennahme, dem Tod oder noch Schlimmerem. Lediglich der Ritter Vallarfax konnte den Nebel durchdringen und die Außenwelt erreichen um von Boletarias Notlage zu berichten. Er sprach auch von der verführerischen Macht der Dämonenseelen. Wenn ein Dämon eine Seele raubte, wurde die Dämonenseele gestärkt. Die Macht, die einer solchen Dämonenseele innewohnt, übersteigt die menschliche Vorstellungskraft. Diese Legende verbreitete sich von Mann zu Mann über die ganze Welt und seitdem betreten tapfere Krieger den farblosen Nebel um nach den Dämonenseelen zu suchen, doch bisher kehrte kein Einziger zurück. '
       ),
       (
           35,
           '2021-08-14 14:16:57',
           '2021-08-14 14:16:57',
           'Bloodborne',
           '35/poster.jpg',
           '2015-03-24',
           'Der Schauplatz des Spiels ist die mystische Stadt Yharnam, in der sich ein mächtiges Allheilmittel befinden soll. Der Spieler übernimmt die Rolle eines kranken Pilgers, der sich aufgemacht hat, um eine Heilung für sein Leiden zu finden. Der Spieler wird in eine Traumwelt, Traum des Jägers, entführt. Aus dieser Traumwelt heraus erkundet der Spieler als Bestienjäger die Stadt mit dem losen Ziel fahles Blut zu jagen.[1] In Yharnam wird er durch eine Infusion mit heiligem Blut geheilt, welches jedoch die vom Bestien-Fluch Befallenen anlockt, da jenes Blut sie von dem Fluch heilen kann. Der Fluch kam durch die heilende Kirche zustande, welche durch das „Alte Blut“ die Menschheit auf eine neue Entwicklungsstufe bringen wollte, aber eben dieses befleckte Blut hat sich wie eine Epidemie in Yharnam ausgebreitet und die Bevölkerung größtenteils in aggressive Bestien verwandelt, die in der ewig währenden Nacht nach einer Heilung suchen. '
       ),
       (
           36,
           '2021-08-14 14:19:56',
           '2021-08-14 14:19:56',
           'M.A.S.H',
           '36/poster.jpg',
           '1970-01-25',
           'M*A*S*H (Mobile Army Surgical Hospital) – ein mobiles Feldlazarett, wenige Kilometer von der koreanischen Front entfernt. Das Hospital gleicht einem Irrenhaus, denn die Ärzte Hawkeye, Duke und Trapper sorgen mit ungewöhnlichen Methoden für die Truppenunterhaltung. Gnadenlos verführen sie Krankenschwestern auf dem OP-Tisch, ziehen mittels Narkose unliebsame Vorgesetzte aus dem Verkehr oder therapieren Potenzprobleme. Hier überleben wirklich nur die Stärksten.'
       ),
       (
           37,
           '2021-08-14 14:22:29',
           '2021-08-14 14:22:29',
           'M.A.S.H',
           '37/poster.jpg',
           '1968-01-01',
           'Ein Roman über drei Ärzte der Armee ist ein Roman von Richard Hooker aus dem Jahr 1968, der als Inspiration für den Spielfilm M * A * S * H ​​und die gleichnamige Fernsehserie dient. Der Roman handelt von einem fiktiven US Mobile Army Surgical Hospital in Korea während des Koreakrieges.'
       ),
       (
           38,
           '2021-08-14 14:24:40',
           '2021-08-14 14:24:40',
           'M.A.S.H',
           '38/poster.jpg',
           '1972-09-17',
           'M*A*S*H (Mobile Army Surgical Hospital) – ein mobiles Feldlazarett, wenige Kilometer von der koreanischen Front entfernt. Das Hospital gleicht einem Irrenhaus, denn die Ärzte Hawkeye, Duke und Trapper sorgen mit ungewöhnlichen Methoden für die Truppenunterhaltung. Gnadenlos verführen sie Krankenschwestern auf dem OP-Tisch, ziehen mittels Narkose unliebsame Vorgesetzte aus dem Verkehr oder therapieren Potenzprobleme. Hier überleben wirklich nur die Stärksten.'
       ),
       (
           39,
           '2021-08-14 14:27:00',
           '2021-08-14 14:27:00',
           'Ein Käfig voller Helden',
           '39/poster.jpg',
           '1965-09-17',
           'Zusammen mit einem kunterbunten Haufen Mitgefangener und unter vollem Einsatz von versteckten Tunneln, konfiszierten Vorräten und geheimen Radios ist Hogans Mission in erster Linie nicht die Flucht. Es gilt vielmehr, so viel Sand wie möglich in die Kriegsmaschinerie der Nazis zu streuen. Sehr schnell entdecken die \"Helden\", dass der unfähige Lagerkommandant von Stalag 13, Oberst Wilhelm Klink und der unbeholfene Feldwebel Hans Schultz ihre zwerchfellerschütternden Bemühungen um Sabotage, Aufklärung und Subversion enorm erleichtern!'
       ),
       (
           40,
           '2021-08-14 14:28:52',
           '2021-08-14 14:28:53',
           'Chuck',
           '40/poster.jpg',
           '2007-09-24',
           'Chuck ist ein Computerfachmann. Eines Tages erhält er eine E-Mail von einem ehemaligen College-Freund. Diese enthält eine in Bildern verschlüsselte Datenbank, die von der CIA und NSA betrieben wurde. Chuck speichert die gesamten Daten unfreiwillig in seinem Gehirn und ist fortan die einzige Quelle dieser streng geheimen Informationen. Die Serie vereint typische Elemente aus dem Action- und Komödien-Genre.'
       ),
       (
           41,
           '2021-08-14 14:33:57',
           '2021-08-14 14:33:57',
           'Zwei Asse trumpfen auf',
           '41/poster.jpg',
           '1981-12-10',
           'Alan hat Wettschulden und muss vor seinen Gläubigern fliehen. Mit einer Schatzkarte, die er von seinem Onkel Brady bekommen hat, versteckt er sich auf dem Schiff des Skippers Charlie, der zu einer Werbefahrt aufbricht. Er erwischt den blinden Passagier und zwingt ihn zu Arbeit an Bord, nachdem er ihn erst im Ozean aussetzen wollte. Alan nutzt dies, um heimlich die Kompasse zu manipulieren und den Kurs zu verändern, so dass sie die Insel ansteuern, auf der sich der Schatz befinden soll. Nach einem Handgemenge fallen beide ins Meer und müssen sich auf die Insel retten.'
       ),
       (
           42,
           '2021-08-14 14:35:00',
           '2021-08-14 14:35:00',
           'Hector, der Ritter ohne Furcht und Tadel ',
           '42/poster.jpg',
           '1976-05-19',
           'Auf italienischem Boden tobt der spanisch-französische Krieg. Als fünf Italiener, unter ihnen auch Hector Fieramosca von französischen Soldaten schlecht behandelt werden, schließen sie sich kurzerhand den Spaniern an. Von nun an haben die französischen Besatzer nichts mehr zu lachen.'
       ),
       (
           43,
           '2021-08-14 14:36:07',
           '2021-08-14 14:36:07',
           'Mein Name ist Nobody',
           '43/poster.jpg',
           '1973-12-13',
           'Der Wilde Westen neigt sich dem Ende zu, und ein quirliger Jungspund, der sich Nobody nennt, eifert seinem großem Vorbild, dem berühmten Revolverhelden Jack Beauregard nach. Er treibt sein inzwischen müde gewordenes Idol zu einem ungleichen Duell mit der “Wilden Horde”, einer Bande, die Angst und Schrecken verbreitet. Doch Beauregard kann stets mit dem Ideenreichtum seines Verehrers rechnen…'
       ),
       (
           44,
           '2021-08-14 14:38:35',
           '2021-08-14 14:38:36',
           'Bud Spencer & Terence Hill - Slaps And Beans',
           '44/poster.jpg',
           '2017-12-15',
           'Bud Spencer & Terence Hill: Slaps and Beans (deutsch: Schellen und Bohnen) ist ein im Dezember 2017 erschienenes Computerspiel, entwickelt vom in Bologna ansässigen Trinity Team und verlegt vom Augsburger Unternehmen Buddy Productions. Das über Kickstarter komplett per Crowdfunding finanzierte Spiel soll eine Hommage an die beiden titelgebenden Schauspieler Bud Spencer und Terence Hill darstellen. Auch als Geschenk an Fans der Schauspieler gedacht, stellt Slaps and Beans das erste offizielle Computerspiel zu beiden dar.\n\nDas Spiel, ein 2D-Sidescroll Beat ’em up in Retro-Pixeloptik, wurde am 15. Dezember 2017 erstmals für Windows, MacOS und Linux veröffentlicht. Im Juli 2018 folgten Umsetzungen für aktuelle Spielkonsolen. Ende 2019 erfolgte, zum 90. Geburtstag von Bud Spencer, die 90th Anniversary Edition als physische Version für PC, PS4 und Switch, welche zusätzlich zwei Autogrammkarten und ein Poster enthält. '
       ),
       (
           45,
           '2021-08-14 14:44:31',
           '2021-08-14 14:44:31',
           'Metal Gear Solid',
           '45/poster.jpg',
           '1998-09-20',
           'Die Handlung spielt zwischen dem 21. und 27. Februar 2005, sechs Jahre nach den Ereignissen von Metal Gear 2: Solid Snake und somit zehn Jahre nach dem ersten Teil, Metal Gear. FOXHOUND, ein abtrünniges, genetisch verbessertes Spezialkommando, unternimmt einen Putsch auf einem abgelegenen Archipel in Alaska, bei dem es sich in Wahrheit um „Shadow Moses“, eine geheime Abrüstungseinrichtung der Regierung, handelt. Die Einheit unter der Leitung des Söldners Liquid Snake haben mehrere atomare Sprengköpfe in ihre Gewalt gebracht sowie den Mecha Metal Gear REX, der die Atomraketen abfeuern kann. Mit diesem Trumpf erpressen die Terroristen die US-Regierung, um die sterblichen Überreste des legendären Söldners Big Boss zu erhalten. '
       ),
       (
           46,
           '2021-08-14 14:46:12',
           '2021-08-14 14:46:12',
           'Metal Gear Solid 2: Sons of Liberty',
           '46/poster.jpg',
           '2001-11-13',
           'Die Handlung beginnt im Jahr 2007. Solid Snake soll für seinen Freund Otacon im Auftrag der gemeinnützigen Organisation Philantropy den Kampfroboter Metal Gear RAY des USMC fotografieren. Dieser befindet sich geheim auf einem Tanker, der von einer bewaffneten russischen Spezialeinheit unter der Führung von Oberst Gurlukovich und Revolver Ocelot, genannt Shalashaska, übernommen wurde. Nach einem siegreichen Kampf gegen Gurlukovichs Tochter Olga sowie einer Gruppe Elitesoldaten kann Snake schließlich in den Frachtraum eindringen, in dem Metal Gear RAY gelagert wird. Dort hält der Marine-Kommandant Col. Dolph gerade eine Ansprache an seine Mannschaft, die noch nichts von der feindlichen Übernahme des Frachters bemerkt hat. Der ebenfalls anwesende Ocelot enthüllt nun seine wahre Absicht, Metal Gear RAY für eine unbekannte Organisation namens The Patriots zu stehlen. Er tötet Gurlukovich und den Kommandanten und zündet überall auf dem Schiff angebrachte Sprengladungen. Als Snake sich ihm jedoch zu erkennen gibt, erleidet er einen Anfall. Ocelot, der in Metal Gear Solid seinen rechten Arm nach einem Angriff von Gray Fox verlor, besitzt nun einen \"neuen\", der ihm von Liquid Snakes Leiche transplantiert wurde. Die Persönlichkeit von Liquid, die im Arm eingeschlossen ist, ergreift vorübergehend Besitz von Ocelot. Schließlich stiehlt Ocelot, beziehungsweise Liquid Snake den Metal Gear RAY und versenkt den Tanker mitsamt allen an Bord befindlichen russischen und amerikanischen Soldaten. Solid Snake kommt bei der Aktion scheinbar ums Leben und wird später für die Explosion des Tankers verantwortlich gemacht. Nachdem man noch ein Funkgespräch von Ocelot zu hören bekommen hat, in dem er die erfolgreiche Erbeutung von Metal Gear RAY meldet und das Gespräch bedeutungsschwanger mit \"Yes, Mr. President!\" beendet, bricht dieser Handlungsstrang an der Stelle ab. '
       ),
       (
           47,
           '2021-08-14 14:48:15',
           '2021-08-14 14:48:15',
           'Metal Gear Solid 3: Snake Eater',
           '47/poster.jpg',
           '2004-11-17',
           'In diesem MGS-Titel steuert man nicht den Protagonisten der früheren Spiele, Solid Snake, sondern seinen genetischen Vater, der unter dem Codenamen Naked Snake in den 1960er Jahren während des Kalten Krieges in die Sowjetunion geschickt wird. Snake soll dort einen sowjetischen Wissenschaftler treffen, welcher einen Panzer, den Shagohod, als mobile, nukleare Raketen-Abschussplattform entwickelt, und ihm zum Überlauf verhelfen. Diese Mission wird schlussendlich durch Snakes frühere Mentorin, The Boss, sabotiert, welche auf Seite des GRU-Oberst Volgin übergelaufen ist. Dieser ist im alleinigen Besitz von Informationen zu einer enormen, auf mehrere Banken verteilten Geldsumme, die von einer internationalen, kriegstreibenden Verschwörung während des Zweiten Weltkrieges angesammelt wurde; The Boss selbst hat sich ihm angeschlossen, um damit dieser Verschwörung zu schaden. Volgin nimmt den Wissenschaftler und den unfertigen Shagohod in Gewahrsam und sprengt mit einer Davy-Crockett-Kernwaffe, die The Boss ihm zuvor als Beweis für ihren Überlauf übergab, die betreffende, sowjetische Forschungsanlage. '
       ),
       (
           48,
           '2021-08-14 14:50:12',
           '2021-08-14 14:50:12',
           'Metal Gear Solid 4: Guns of the Patriots',
           '48/poster.jpg',
           '2008-05-12',
           'Metal Gear Solid 4 spielt im Jahre 2014, neun Jahre nach der Handlung von Metal Gear Solid und fünf Jahre nach Metal Gear Solid 2: Sons of Liberty. Es wurde weltweit eine „Kriegswirtschaft“ erschaffen, mit sogenannten PMCs (Private Military Companies), die für Geld Soldaten aussenden. PMC-Soldaten sind mit Nanomaschinen ausgerüstet, womit sich ihre Fähigkeiten auf dem Schlachtfeld verbessern. Das Kontroll-Netzwerk dieser Nanomaschinen wird Sons of the Patriots (SOP) genannt. Liquid Ocelot bereitet sich darauf vor, diesem System zu schaden. Snake spürt inzwischen seine beschleunigte Alterung durch seinen Status als Klon und hat weniger als ein Jahr zu leben. Er lebt jetzt mit Dr. Hal „Otacon“ Emmerich und Olga Gurlukovichs Tochter Sunny. Als sein ehemaliger Kommandeur Roy Campbell ihn bittet, Liquid mit einer letzten Mission zu stoppen, akzeptiert Snake dies und zieht in den Krieg im Nahen Osten, wo Liquid sich wahrscheinlich versteckt hält. '
       ),
       (
           49,
           '2021-08-14 14:52:20',
           '2021-08-14 14:52:20',
           'Metal Gear Solid V: The Phantom Pain',
           '49/poster.jpg',
           '2015-09-01',
           'The Phantom Pain schließt an die Erzählung des Prologs Ground Zeroes an. Die Spielerfigur, bei der es sich dem Anschein nach um Big Boss handelt und als „Punished Snake“ bzw. „Venom Snake“ bezeichnet wird, erwacht nach neun Jahren in einem Krankenhaus auf Zypern aus dem Koma. Beim Angriff der Geheimorganisation Cipher auf die Mother Base und dem anschließenden Absturz des Hubschraubers (s. Handlung Ground Zeroes) hatte er schwere Verletzungen davongetragen. Unter anderem musste sein linker Arm durch eine technisch hochwertige Armprothese ersetzt werden, außerdem stecken noch verschiedene Splitter in seinem Kopf und Körper. Wegen eines Angriffs auf das Krankenhaus muss sich Venom Snake in Sicherheit bringen, wobei er Unterstützung von Revolver Ocelot erhält. Im Kampf gegen Cipher übernimmt er das Kommando über die von Kazuhira „Kaz“ Miller als Nachfolgeorganisation der ehemaligen Militaires Sans Frontières (MSF, zu deutsch: Soldaten ohne Grenzen) einst gegründete Söldnereinheit Diamond Dogs, um den Angriff der Cipher Kampfeinheit „XOF“ auf die Mother Base zu rächen. Hierfür muss er jedoch erst alte Mitstreiter wie Kazuhira Miller befreien und neue Söldner rekrutieren. Snake operiert unter anderem in Afghanistan und in Afrika (Angola/Zaire). '
       );
INSERT INTO `series` (
    `age_restriction`,
    `average_length`,
    `is_completed`,
    `id`,
    `network_id`
)
VALUES (12, 22, b'1', 7, 10),
       (6, 23, b'1', 24, 7),
       (16, 23, b'0', 25, 8),
       (17, 12, b'0', 26, 8),
       (16, 22, b'0', 27, 9),
       (12, 25, b'1', 38, 2),
       (12, 25, b'1', 39, 2),
       (12, 60, b'1', 40, 10);
INSERT INTO `seasons` (
    `id`,
    `created_at`,
    `updated_at`,
    `season_number`,
    `season_title`,
    `series_id`
)
VALUES (
           1,
           '2021-06-21 20:25:06',
           '2021-06-21 20:25:06',
           1,
           'Staffel 1',
           7
       ),
       (
           2,
           '2021-07-04 16:22:34',
           '2021-07-04 16:22:34',
           2,
           'Staffel 2',
           7
       ),
       (
           3,
           '2021-07-04 16:23:22',
           '2021-07-04 16:23:22',
           3,
           'Staffel 3',
           7
       ),
       (
           4,
           '2021-07-04 16:23:38',
           '2021-07-04 16:23:38',
           4,
           'Staffel 4',
           7
       ),
       (
           5,
           '2021-07-04 16:23:52',
           '2021-07-04 16:23:52',
           5,
           'Staffel 5',
           7
       ),
       (
           6,
           '2021-07-04 16:24:02',
           '2021-07-04 16:24:02',
           6,
           'Staffel 6',
           7
       ),
       (
           7,
           '2021-07-04 16:24:12',
           '2021-07-04 16:24:12',
           7,
           'Staffel 7',
           7
       ),
       (
           8,
           '2021-07-04 16:24:21',
           '2021-07-04 16:24:21',
           8,
           'Staffel 8',
           7
       ),
       (
           9,
           '2021-07-04 16:24:35',
           '2021-07-04 16:24:35',
           9,
           'Staffel 9',
           7
       ),
       (
           10,
           '2021-07-04 16:25:00',
           '2021-07-04 16:25:00',
           1,
           'Wasser',
           24
       ),
       (
           11,
           '2021-07-04 16:26:14',
           '2021-07-04 16:26:14',
           2,
           'Erde',
           24
       ),
       (
           12,
           '2021-07-04 16:26:23',
           '2021-07-04 16:26:23',
           3,
           'Feuer',
           24
       ),
       (
           13,
           '2021-07-04 16:28:00',
           '2021-07-04 16:28:00',
           1,
           'Staffel 1',
           25
       ),
       (
           14,
           '2021-07-04 16:28:17',
           '2021-07-04 16:28:17',
           1,
           'Staffel 1',
           26
       ),
       (
           15,
           '2021-07-04 16:28:34',
           '2021-07-04 16:28:34',
           2,
           'Staffel 2',
           26
       ),
       (
           16,
           '2021-07-04 16:29:00',
           '2021-07-04 16:29:00',
           1,
           'Staffel 1',
           27
       ),
       (
           17,
           '2021-07-04 16:29:13',
           '2021-07-04 16:29:13',
           2,
           'Staffel 2',
           27
       ),
       (
           18,
           '2021-07-04 16:29:22',
           '2021-07-04 16:29:22',
           3,
           'Staffel 3',
           27
       ),
       (
           19,
           '2021-07-04 16:29:32',
           '2021-07-04 16:29:32',
           4,
           'Staffel 4',
           27
       ),
       (
           20,
           '2021-07-04 16:29:42',
           '2021-07-04 16:29:42',
           5,
           'Staffel 5',
           27
       );
INSERT INTO `books` (
    `isebook`,
    `is_print`,
    `isbn`,
    `number_of_pages`,
    `id`,
    `book_publisher_id`
)
VALUES (b'1', b'1', '978-3-426-51861-8', 600, 1, 1),
       (b'1', b'1', '978-3-641-06578-2', 528, 2, 2),
       (b'1', b'1', '978-3-446-45793-5', 435, 3, 3),
       (b'1', b'1', '978-3-938484-11-1', 544, 21, 4),
       (b'1', b'1', '978-3-453-31867', 879, 22, 2),
       (b'1', b'1', '978-3-548-23410-6', 383, 23, 5),
       (b'0', b'1', '978-3-453-00207-4', 204, 37, 2);
INSERT INTO `episodes` (`episode_number`, `length`, `id`, `season_id`)
VALUES (1, 22, 8, 1),
       (2, 21, 9, 1),
       (3, 21, 10, 1),
       (1, 20, 28, 2),
       (2, 20, 29, 2),
       (1, 20, 30, 3);
INSERT INTO `games` (
    `age_restriction`,
    `average_playtime`,
    `max_number_of_gamers`,
    `min_number_of_gamers`,
    `id`,
    `game_publisher_id`
)
VALUES (12, NULL, NULL, 1, 4, 3),
       (12, 17, 2, 2, 5, 2),
       (12, 40, 4, 1, 6, 1),
       (12, 80, NULL, 1, 18, 1),
       (16, 60, NULL, 1, 19, 4),
       (12, 50, NULL, 1, 20, 5),
       (16, 42, 4, 1, 31, 4),
       (16, 32, 4, 1, 32, 4),
       (16, 32, 4, 1, 33, 4),
       (16, 25, 1, 1, 34, 6),
       (16, 34, 3, 1, 35, 6),
       (12, 4, 2, 1, 44, 7),
       (12, 12, 1, 1, 45, 8),
       (12, 15, 1, 1, 46, 8),
       (16, 16, 1, 1, 47, 8),
       (18, 19, 1, 1, 48, 8),
       (18, 45, 1, 1, 49, 8);
INSERT INTO `games_platforms` (`games_id`, `platforms_id`)
VALUES (31, 3),
       (31, 4),
       (31, 7),
       (31, 22),
       (32, 4),
       (32, 22),
       (33, 3),
       (34, 5),
       (35, 4),
       (44, 4),
       (44, 16),
       (44, 22),
       (45, 1),
       (45, 22),
       (46, 2),
       (46, 22),
       (47, 2),
       (47, 3),
       (47, 7),
       (47, 21),
       (48, 3),
       (49, 3),
       (49, 4),
       (49, 22);
INSERT INTO `genres` (`id`, `created_at`, `updated_at`, `genre_name`)
VALUES (
           1,
           '2021-06-21 20:45:25',
           '2021-06-21 20:45:25',
           'Komödie'
       ),
       (
           2,
           '2021-06-21 20:45:25',
           '2021-06-21 20:45:25',
           'Action'
       ),
       (
           3,
           '2021-06-21 20:45:25',
           '2021-06-21 20:45:25',
           'Thriller'
       ),
       (
           4,
           '2021-06-21 20:45:25',
           '2021-06-21 20:45:25',
           'Horror'
       ),
       (
           5,
           '2021-06-21 20:45:25',
           '2021-06-21 20:45:25',
           'Drama'
       ),
       (
           6,
           '2021-06-21 20:45:25',
           '2021-06-21 20:45:25',
           'Romantik'
       ),
       (
           7,
           '2021-06-21 20:45:25',
           '2021-06-21 20:45:25',
           'RPG'
       ),
       (
           8,
           '2021-06-21 20:45:25',
           '2021-06-21 20:45:25',
           'MMORPG'
       ),
       (
           9,
           '2021-06-21 20:45:25',
           '2021-06-21 20:45:25',
           'Sci-Fi'
       ),
       (
           10,
           '2021-06-21 20:45:25',
           '2021-06-21 20:45:25',
           'Adventure'
       );
INSERT INTO `media_genres` (`media_id`, `genres_id`)
VALUES (4, 8),
       (5, 10),
       (6, 7),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (31, 2),
       (31, 7),
       (32, 2),
       (32, 7),
       (33, 2),
       (33, 7),
       (34, 2),
       (34, 7),
       (35, 2),
       (35, 7),
       (36, 1),
       (36, 5),
       (37, 1),
       (37, 5),
       (38, 1),
       (38, 5),
       (39, 1),
       (39, 2),
       (40, 1),
       (40, 2),
       (40, 3),
       (41, 1),
       (41, 2),
       (42, 1),
       (42, 2),
       (43, 1),
       (43, 2),
       (44, 1),
       (44, 2),
       (44, 10),
       (45, 2),
       (45, 10),
       (46, 2),
       (46, 10),
       (47, 2),
       (47, 10),
       (48, 2),
       (48, 10),
       (49, 2),
       (49, 10);
INSERT INTO `languages` (`id`, `created_at`, `updated_at`, `language`)
VALUES (
           1,
           '2021-06-21 20:43:48',
           '2021-06-21 20:43:48',
           'Deutsch'
       ),
       (
           2,
           '2021-06-21 20:43:48',
           '2021-06-21 20:43:48',
           'Englisch'
       ),
       (
           3,
           '2021-06-21 20:43:48',
           '2021-06-21 20:43:48',
           'Französisch'
       ),
       (
           4,
           '2021-06-21 20:43:48',
           '2021-06-21 20:43:48',
           'Spanisch'
       ),
       (
           5,
           '2021-06-21 20:43:48',
           '2021-06-21 20:43:48',
           'Portugisisch '
       ),
       (
           6,
           '2021-06-21 20:43:48',
           '2021-06-21 20:43:48',
           'Polnisch'
       ),
       (
           7,
           '2021-06-21 20:43:48',
           '2021-06-21 20:43:48',
           'Schwedisch'
       ),
       (
           8,
           '2021-06-21 20:43:48',
           '2021-06-21 20:43:48',
           'Norwegisch'
       ),
       (
           9,
           '2021-06-21 20:43:48',
           '2021-06-21 20:43:48',
           'Dänisch'
       ),
       (
           10,
           '2021-06-21 20:43:48',
           '2021-06-21 20:43:48',
           'Türkisch'
       );
INSERT INTO `media_languages` (`media_id`, `languages_id`)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (12, 1),
       (12, 2),
       (31, 1),
       (31, 2),
       (32, 1),
       (32, 2),
       (33, 1),
       (33, 2),
       (34, 1),
       (34, 2),
       (35, 1),
       (35, 2),
       (36, 1),
       (36, 2),
       (37, 1),
       (37, 2),
       (38, 1),
       (38, 2),
       (38, 3),
       (39, 1),
       (39, 2),
       (40, 1),
       (40, 2),
       (40, 3),
       (40, 4),
       (41, 1),
       (41, 2),
       (41, 3),
       (41, 4),
       (42, 1),
       (42, 2),
       (42, 3),
       (42, 4),
       (43, 1),
       (43, 2),
       (43, 3),
       (43, 4),
       (44, 1),
       (44, 2),
       (44, 3),
       (44, 4),
       (45, 1),
       (45, 2),
       (46, 1),
       (46, 2),
       (47, 1),
       (47, 2),
       (48, 1),
       (48, 2),
       (49, 1),
       (49, 2);
INSERT INTO `movies` (`age_restriction`, `length`, `id`, `network_id`)
VALUES (16, 89, 11, NULL),
       (16, 177, 12, NULL),
       (12, 98, 13, NULL),
       (12, 143, 14, NULL),
       (0, 89, 15, 4),
       (12, 169, 16, 5),
       (12, 84, 17, 6),
       (16, 114, 36, 11),
       (6, 108, 41, 12),
       (6, 95, 42, 12),
       (6, 117, 43, 12);
INSERT INTO `roles` (`id`, `created_at`, `updated_at`, `role_name`)
VALUES (
           1,
           '2021-06-21 19:17:34',
           '2021-06-21 19:17:34',
           'User'
       ),
       (
           2,
           '2021-06-21 19:17:34',
           '2021-06-21 19:17:34',
           'Admin'
       ),
       (
           3,
           '2021-06-21 19:17:34',
           '2021-06-21 19:17:34',
           'Moderator'
       ),
       (
           4,
           '2021-06-21 19:17:34',
           '2021-06-21 19:17:34',
           'Customer'
       );
INSERT INTO `users` (
    `id`,
    `created_at`,
    `updated_at`,
    `first_name`,
    `gender`,
    `last_name`,
    `profile_picture_path`,
    `second_name`,
    `user_name`,
    `login_id`
)
VALUES (
           1,
           '2021-06-21 21:17:56',
           '2021-06-21 21:17:56',
           'Super',
           'männlich',
           'Typ',
           NULL,
           NULL,
           'SuperTyp',
           NULL
       ),
       (
           2,
           '2021-06-21 21:17:56',
           '2021-06-21 21:17:56',
           'Maxima',
           'weiblich',
           'Musterfrau',
           NULL,
           NULL,
           'BadWomand78',
           NULL
       ),
       (
           3,
           '2021-06-21 21:17:56',
           '2021-06-21 21:17:56',
           'Kevin',
           'divers',
           'Kevinson',
           NULL,
           NULL,
           'DoedelDoedler',
           NULL
       );
INSERT INTO `collections` (
    `id`,
    `created_at`,
    `updated_at`,
    `title`,
    `user_id`
)
VALUES (
           1,
           '2021-08-14 14:29:12',
           '2021-08-14 14:29:12',
           'SoulsBorne Spiele',
           1
       ),
       (
           2,
           '2021-08-14 14:29:22',
           '2021-08-14 14:29:22',
           'M.A.S.H',
           1
       ),
       (
           3,
           '2021-08-14 14:39:00',
           '2021-08-14 14:39:00',
           'Bud Spencer & Terence Hill',
           2
       ),
       (
           4,
           '2021-08-14 14:40:02',
           '2021-08-14 14:40:02',
           'Gute Serien',
           2
       ),
       (
           5,
           '2021-08-14 14:40:57',
           '2021-08-14 14:40:57',
           'Fun Games',
           2
       ),
       (
           6,
           '2021-08-14 14:52:32',
           '2021-08-14 14:52:32',
           'Metal Gear Solid',
           2
       );
INSERT INTO `collections_media` (`collections_id`, `media_id`)
VALUES (1, 19),
       (1, 31),
       (1, 32),
       (1, 33),
       (1, 34),
       (1, 35),
       (2, 36),
       (2, 37),
       (2, 38),
       (3, 41),
       (3, 42),
       (3, 43),
       (3, 44),
       (4, 7),
       (4, 38),
       (4, 39),
       (4, 40),
       (5, 19),
       (5, 20),
       (5, 33),
       (6, 45),
       (6, 46),
       (6, 47),
       (6, 48),
       (6, 49);
COMMIT;