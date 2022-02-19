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
-- Table structure for table `costumers_things`
--

CREATE TABLE `costumers_things` (
  `Code` int(50) NOT NULL,
  `Processor` varchar(100) NOT NULL,
  `Motherboard` varchar(100) NOT NULL,
  `RAM` varchar(100) NOT NULL,
  `Storage1` varchar(100) NOT NULL,
  `Storage2` varchar(100) NOT NULL,
  `PowerSupply` varchar(100) NOT NULL,
  `VGA` varchar(100) NOT NULL,
  `Casing` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `costumers_things`
--

INSERT INTO `costumers_things` (`Code`, `Processor`, `Motherboard`, `RAM`, `Storage1`, `Storage2`, `PowerSupply`, `VGA`, `Casing`) VALUES
(1, 'AMD Ryzen 5 3500 3.6Ghz Up To 4.1Ghz Cache 16MB 65W AM4 [Box] - 6 Core', 'ASRock A320M-HDV (AM4, AMD Promontory A320, DDR4, USB 3.1, SATA3)', '', '', '', '', '', ''),
(2, 'Intel Core i5-12600K 3.7GHz Up to 4.9GHz - Cache 20Mb - LGA 1700 - Alder Lake Series', 'Gigabyte Z690 Aorus Elite AX DDR4 (LGA1700 Z690DDR4 USB3.2 SATA3)', 'V-GeN Platinum DDR4 8GB PC17000', 'WDC 1TB SATA3 64MB - Blue - WD10EZEX - Garansi 2 Th', 'WDC Purple 6TB For CCTV 24 Hours - WD62PURZ - Garansi 3 Th', 'Simbadda 500W', 'Zotac GeForce RTX 2060 12GB GDDR6', 'PRIME T-[Q] - ALUMUNIUM GAMING CASE - DUAL SIDE TEMPERED GLASS'),
(3, '', '', '', '', '', '', 'Gigabyte Quadro RTX5000 16GB DDR6 256 Bit', ''),
(4, 'Intel Core i5-12700KF 3.6GHz Up to 5.0GHz - Cache 25Mb - LGA 1700 - Alder Lake Series', 'Gigabyte Z690 Aorus Elite AX DDR4 (LGA1700 Z690DDR4 USB3.2 SATA3)', 'V-GeN Platinum DDR4 8GB PC17000', 'WDC 1TB SATA3 64MB - Blue - WD10EZEX - Garansi 2 Th', 'V-GeN SSD M.2 1TB', 'Corsair CV Series 550W - 80 Plus Bronze', 'MSI GeForce GTX 1650 SUPER 4GB DDR6', 'CUBE GAMING EXORA - ATX - LEFT SIDE GLASS DOOR - FRONT RGB BAR'),
(5, '', '', '', '', '', '', 'Gigabyte Quadro RTX5000 16GB DDR6 256 Bit', ''),
(6, 'Intel Core i5-12700KF 3.6GHz Up to 5.0GHz - Cache 25Mb - LGA 1700 - Alder Lake Series', 'Gigabyte Z690 Aorus Elite AX DDR4 (LGA1700 Z690DDR4 USB3.2 SATA3)', 'Team Elite Plus Black DDR4 PC19200 2400Mhz Dual Channel 8GB (2X4GB)', 'Seagate 3TB SATA3 256MB - BarraCuda Series', '', '', '', ''),
(7, 'Intel Core i7-12700K 3.6GHz Up to 5.0GHz - Cache 25Mb - LGA 1700 - Alder Lake Series', 'Gigabyte Z690 Aorus Elite AX DDR4 (LGA1700 Z690DDR4 USB3.2 SATA3)', 'V-GeN Platinum DDR4 8GB PC17000', 'Kingston SSD Now SA400 SA400S37/480G SATA3', 'V-GeN SSD M.2 1TB', 'Simbadda 500W', 'Zotac GeForce RTX 2060 12GB GDDR6', 'PRIME T-[Q] - ALUMUNIUM GAMING CASE - DUAL SIDE TEMPERED GLASS'),
(8, 'AMD Ryzen 5 3500 3.6Ghz Up To 4.1Ghz Cache 16MB 65W AM4 [Box] - 6 Core', 'Asus TUF B450M-PLUS Gaming (AM4, AMD Promontory B450, DDR4, USB3.1, SATA3)', 'Team Elite Plus Black DDR4 PC19200 2400Mhz Dual Channel 8GB (2X4GB)', 'Seagate 3TB SATA3 256MB - BarraCuda Series', 'Seagate 10TB For NAS - IronWolf Series', 'Digital Alliance Gaming PSU 1300W 80+ Gold BTC', 'MSI GeForce GTX 1650 SUPER 4GB DDR6', 'PRIME T-[Q] - ALUMUNIUM GAMING CASE - DUAL SIDE TEMPERED GLASS');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `costumers_things`
--
ALTER TABLE `costumers_things`
  ADD PRIMARY KEY (`Code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `costumers_things`
--
ALTER TABLE `costumers_things`
  MODIFY `Code` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
