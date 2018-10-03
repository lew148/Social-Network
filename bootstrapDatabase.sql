DROP TABLE IF EXISTS `social_events`;
CREATE TABLE `social_events` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user` varchar(20) NOT NULL,
  `author` varchar(20) NOT NULL,
  `content` text NOT NULL
);

INSERT INTO `social_events` (`author`, `user`, `content`) VALUES
  ('Joe','Gareth','Hey Gareth it\'s Joe'),
  ('Joe','Gareth','What\'s up?'),
  ('Gareth','Joe','Help!');

CREATE TABLE `users` (
  `idusers` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `fullname` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
SELECT * FROM socialnetwork.users;