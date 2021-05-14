-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 03:44 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gadgetbadgetonlineplatform`
--

-- --------------------------------------------------------

--
-- Table structure for table `funders`
--

CREATE TABLE `funders` (
  `funderID` int(11) NOT NULL,
  `funderName` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `fundingAmount` double(8,2) NOT NULL,
  `fundStartDate` date DEFAULT NULL,
  `fundEndDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funders`
--

INSERT INTO `funders` (`funderID`, `funderName`, `category`, `description`, `fundingAmount`, `fundStartDate`, `fundEndDate`) VALUES
(7, 'Sunimal', 'Managment', 'to research', 10000.00, '2021-05-22', '2021-05-26'),
(12, 'Nimal+gf', 'IT+S', 'to+buy+laptop', 10000.00, '2021-05-22', '2021-05-25');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `funders`
--
ALTER TABLE `funders`
  ADD PRIMARY KEY (`funderID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `funders`
--
ALTER TABLE `funders`
  MODIFY `funderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
