-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 03 Août 2017 à 11:27
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `transaction`
--

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

CREATE TABLE IF NOT EXISTS `agence` (
  `numagence` int(11) NOT NULL AUTO_INCREMENT,
  `nomagence` varchar(25) NOT NULL,
  PRIMARY KEY (`numagence`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `agence`
--

INSERT INTO `agence` (`numagence`, `nomagence`) VALUES
(1, 'BDF'),
(2, 'bicis');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `numcli` int(11) NOT NULL,
  `nomcli` varchar(20) NOT NULL,
  `prenomcli` varchar(20) NOT NULL,
  `numagence` int(11) NOT NULL,
  PRIMARY KEY (`numcli`),
  KEY `numagence` (`numagence`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`numcli`, `nomcli`, `prenomcli`, `numagence`) VALUES
(30000, 'ba', 'mamadou', 2),
(32305, 'ly', 'moussa', 1);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `numcompte` int(11) NOT NULL,
  `libcompte` varchar(20) NOT NULL,
  `sens` varchar(20) NOT NULL,
  `solde` double NOT NULL,
  `numcli` int(11) NOT NULL,
  PRIMARY KEY (`numcompte`),
  KEY `numcli` (`numcli`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`numcompte`, `libcompte`, `sens`, `solde`, `numcli`) VALUES
(353500, 'compte courant', 'debit', 82000, 32305);

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE IF NOT EXISTS `operation` (
  `numoper` int(11) NOT NULL,
  `liboper` varchar(20) NOT NULL,
  `sensoper` varchar(20) NOT NULL,
  `dateoper` date NOT NULL,
  `montant` double NOT NULL,
  `numcompte` int(11) NOT NULL,
  PRIMARY KEY (`numoper`),
  KEY `numcompte` (`numcompte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `operation`
--

INSERT INTO `operation` (`numoper`, `liboper`, `sensoper`, `dateoper`, `montant`, `numcompte`) VALUES
(218, 'teste', 'debiter', '2017-08-05', 2500, 353500),
(601, 'achat ordi', 'debiter', '2017-05-01', 15000, 353500),
(2245, 'jhjhkj', 'debiter', '2017-12-07', 2000, 353500),
(2250, 'edefef', 'crediter', '2017-05-01', 5000, 353500),
(45666, 'jllvdevdev', 'crediter', '2017-04-01', 7500, 353500);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`numagence`) REFERENCES `agence` (`numagence`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `compte_ibfk_1` FOREIGN KEY (`numcli`) REFERENCES `client` (`numcli`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `operation_ibfk_1` FOREIGN KEY (`numcompte`) REFERENCES `compte` (`numcompte`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
