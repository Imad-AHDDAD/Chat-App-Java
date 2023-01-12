-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 12, 2023 at 08:02 PM
-- Server version: 5.7.24
-- PHP Version: 8.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chatapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `friendship`
--

CREATE TABLE `friendship` (
  `id` int(11) NOT NULL,
  `username1` varchar(250) NOT NULL,
  `username2` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `friendship`
--

INSERT INTO `friendship` (`id`, `username1`, `username2`) VALUES
(1, 'imadahddad2', 'imadahddad');

-- --------------------------------------------------------

--
-- Table structure for table `invitation`
--

CREATE TABLE `invitation` (
  `id` int(11) NOT NULL,
  `invSender` varchar(250) NOT NULL,
  `invReciever` varchar(250) NOT NULL,
  `accepted` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `invitation`
--

INSERT INTO `invitation` (`id`, `invSender`, `invReciever`, `accepted`) VALUES
(5, 'imadahddad', 'imadahddad2', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `msg`
--

CREATE TABLE `msg` (
  `id` int(11) NOT NULL,
  `sender` varchar(250) NOT NULL,
  `reciever` varchar(250) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `dateSent` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `msg`
--

INSERT INTO `msg` (`id`, `sender`, `reciever`, `content`, `dateSent`) VALUES
(89, 'imadahddad4', 'imadahddad', 'Hi imad !', 'Wed Jan 04 15:44:43 CET 2023'),
(90, 'imadahddad4', 'imadahddad', 'How are you ?', 'Wed Jan 04 15:45:12 CET 2023'),
(91, 'imadahddad', 'imadahddad4', 'Fine thanks', 'Wed Jan 04 15:45:26 CET 2023'),
(92, 'imadahddad', 'imadahddad4', 'you ?', 'Wed Jan 04 15:45:31 CET 2023'),
(93, 'imadahddad', 'imadahddad2', 'Hi', 'Fri Jan 06 15:25:02 CET 2023'),
(94, 'imadahddad2', 'imadahddad', 'hello', 'Fri Jan 06 15:25:12 CET 2023'),
(95, 'imadahddad2', 'imadahddad', 'how are you ?', 'Fri Jan 06 15:25:27 CET 2023'),
(96, 'imadahddad', 'imadahddad2', 'fine thanks', 'Fri Jan 06 15:31:06 CET 2023');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `ipAddress` varchar(250) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `status` varchar(250) DEFAULT 'OFFLINE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `ipAddress`, `port`, `status`) VALUES
(4, 'imadahddad', '4467d6f9af6a8fe833ad9081f87bbf5c', '192.168.26.72', 62802, 'OFFLINE'),
(6, 'imadahddad2', 'e726d485991be3a0405fbfda9c67e287', '192.168.26.72', 54325, 'OFFLINE'),
(9, 'imadahddad3', 'c0dfff6fe207f49d1a5c480b47e8d5bf', NULL, NULL, 'OFFLINE'),
(10, 'imadahddad4', '6bd64d9d1be34c920e1b5fe789317c6f', '192.168.137.1', 57567, 'OFFLINE'),
(11, 'imadahddad5', '63add9698651d43d49c3bd685eac583e', NULL, NULL, 'OFFLINE'),
(12, 'imadahddad6', '9934b355d3afd24a4e9ea2ac79557ffb', NULL, NULL, 'OFFLINE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `friendship`
--
ALTER TABLE `friendship`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invitation`
--
ALTER TABLE `invitation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `msg`
--
ALTER TABLE `msg`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `friendship`
--
ALTER TABLE `friendship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `invitation`
--
ALTER TABLE `invitation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `msg`
--
ALTER TABLE `msg`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
