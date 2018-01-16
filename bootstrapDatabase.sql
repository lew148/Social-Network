DROP TABLE IF EXISTS `walls`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `fullname` varchar(128) NOT NULL,
  `hashedpassword` char(128) NOT NULL,
  PRIMARY KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `walls` (
  `username` varchar(20) NOT NULL,
  `author` varchar(20) NOT NULL,
  `content` text NOT NULL,
  FOREIGN KEY (`username`) REFERENCES `users`(`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users` (`username`, `fullname`, `hashedpassword`) VALUES
  ('rick', 'Rick Sanchez', '$argon2id$v=19$m=65536,t=2,p=1$D/aqIytwOPkz3UfQvrQ0Xg$cMq1vJHbntlXOBrLUZyGTPLDDfsgmCHbb0AcTd1KiDE                               '),
  ('morty', 'Morty Smith', '$argon2id$v=19$m=65536,t=2,p=1$dyiVM0EPn6MN0cerJcdkdQ$AkCOPYN4qNov7PlQt3CeRoKCvrjboHyl6D3/tt7zXXs                               ');

INSERT INTO `walls` (`author`, `username`, `content`) VALUES
  ('rick','morty','Hey Morty it\'s rick'),
  ('rick','morty','What\'s up?'),
  ('morty','rick','Help!');
