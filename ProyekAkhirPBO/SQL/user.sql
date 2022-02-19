-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 16, 2022 at 06:46 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tendorkomputer`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(5) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phonenumber` varchar(13) NOT NULL,
  `password` varchar(20) NOT NULL,
  `total_transaction` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `fullname`, `username`, `email`, `phonenumber`, `password`, `total_transaction`) VALUES
(1, 'Admin TENDOR KOMPUTER', 'admin', 'admin@tendorkomputer.com', '081399998888', 'admin', 0),
(2, 'Aldhaf Fadlilah', 'aldhaf06', 'aldap@gmail.com', '087779304567', '123', 88810000),
(3, 'Aldhaf Fadlilah', 'aldap', 'aldhaf123@gmail.com', '087779307421', 'IiX8oBjfm', 0),
(4, 'tester', 'tester', 'tester@gmail.com', '123412341234', '1234', 0),
(5, 'Bagus Hartono', 'bagus321', 'bagus@gmail.com', '081389765678', '12345', 62885000),
(6, 'Andhieka Agrestya', 'andhie123', 'andika@gmail.com', '087779307421', '1234', 55880000),
(7, 'cobalagi', 'cobaan', 'coba@gmail.com', '123412341234', '123', 13275000),
(8, 'Bagas Priambodo', 'bagaswew', 'bagas111@gmail.com', '081387659807', '1234', 50355000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
