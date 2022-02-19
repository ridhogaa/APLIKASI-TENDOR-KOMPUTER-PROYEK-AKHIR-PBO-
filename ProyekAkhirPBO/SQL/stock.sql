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
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `ID` int(100) NOT NULL,
  `Component` varchar(30) NOT NULL,
  `TipeProduct` varchar(100) NOT NULL,
  `Stock` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`ID`, `Component`, `TipeProduct`, `Stock`) VALUES
(1, 'Processor', 'AMD Ryzen 9 5900X 3.7Ghz Up To 4.8Ghz Cache 64MB 105W AM4 [Box] - 12 Core', 51),
(2, 'Processor', 'AMD Ryzen 5 3500 3.6Ghz Up To 4.1Ghz Cache 16MB 65W AM4 [Box] - 6 Core', 52),
(3, 'Processor', 'AMD Ryzen 7 5800X 3.8Ghz Up To 4.7Ghz Cache 32MB 105W AM4 [Box] - 8 Core', 51),
(4, 'Processor', 'AMD Athlon 3000G (Radeon Vega 3) 3.5Ghz Cache 4MB 35W Socket AM4 [BOX] - 2 Core', 51),
(5, 'Processor', 'AMD Ryzen 5 5600X 3.7Ghz Up To 4.6Ghz Cache 32MB 65W AM4 [Box] - 6 Core', 51),
(6, 'Processor', 'Intel Core i5-12600K 3.7GHz Up to 4.9GHz - Cache 20Mb - LGA 1700 - Alder Lake Series', 51),
(7, 'Processor', 'Intel Core i5-12700KF 3.6GHz Up to 5.0GHz - Cache 25Mb - LGA 1700 - Alder Lake Series', 49),
(8, 'Processor', 'Intel Core i5-12500 3.0GHz Up to 4.6GHz - Cache 18Mb - LGA 1700 - Alder Lake Series', 51),
(9, 'Processor', 'Intel Core i7-12700K 3.6GHz Up to 5.0GHz - Cache 25Mb - LGA 1700 - Alder Lake Series', 51),
(10, 'Processor', 'Intel Core i9-12900 2.4GHz Up to 5.1GHz - Cache 30 Mb - LGA 1700 - Alder Lake Series', 51),
(11, 'Motherboard', 'MSI PRO B660M-A Wifi DDR4 (LGA1700 B660 DDR4 USB3.2 SATA3)', 51),
(12, 'Motherboard', 'Gigabyte Z690 Aorus Elite AX DDR4 (LGA1700 Z690DDR4 USB3.2 SATA3)', 51),
(13, 'Motherboard', 'Asus ROG Strix Z690-F Gaming WIFI (LGA1700 Z690 DDR5 USB3.2 SATA3)', 52),
(14, 'Motherboard', 'Asus ROG Strix B660-A Gaming WIFI D4 (LGA1700 B660 DDR4 USB3.2SATA3)', 51),
(15, 'Motherboard', 'MSI MAG Z690 Tomahawk WiFi DDR4 (LGA1700 Z690 DDR4USB3.2 SATA3)', 52),
(16, 'Motherboard', 'Biostar A320MH (AM4, AMD Promontory A320, DDR4, USB 3.1, SATA3)', 51),
(17, 'Motherboard', 'ASRock A320M-HDV (AM4, AMD Promontory A320, DDR4, USB 3.1, SATA3)', 51),
(18, 'Motherboard', 'Asus TUF B450M-PLUS Gaming (AM4, AMD Promontory B450, DDR4, USB3.1, SATA3)', 51),
(19, 'Motherboard', 'Biostar Racing B450GT3 (AM4, AMD Promontory B450, DDR4, USB3.1, SATA3)', 51),
(20, 'Motherboard', 'Gigabyte B450 Aorus Elite (AM4, AMD Promontory B450, DDR4, USB3.1, SATA3)', 51),
(21, 'RAM', 'Corsair DDR4 Vengeance LPX PC19200 4GB (1X4GB)', 49),
(22, 'RAM', 'V-GeN Platinum DDR4 8GB PC17000', 10),
(23, 'RAM', 'Team Elite Plus Black DDR4 PC19200 2400Mhz Dual Channel 8GB (2X4GB)', 9),
(24, 'RAM', 'Team Elite Plus Black DDR4 PC21000 2666Mhz 8GB', 51),
(25, 'RAM', 'Team EXtreme DDR4 PC28800 3600Mhz Dual Channel 16GB (2x8GB)', 51),
(26, 'Storage 1', 'WDC 1TB SATA3 64MB - Blue - WD10EZEX - Garansi 2 Th', 51),
(27, 'Storage 1', 'Seagate 3TB SATA3 256MB - BarraCuda Series', 51),
(28, 'Storage 1', 'Seagate 2TB SATA3 - BarraCuda Series', 51),
(29, 'Storage 1', 'Kingston SSD Now SA400 SA400S37/480G SATA3', 51),
(30, 'Storage 1', 'ADATA SSD SU650 120GB SATA III ( R/W Up to 520 / 450MB/s ) ASU650SS-120GT-R', 51),
(31, 'Storage 1', 'Transcend TS512GMTE220S NVMe PCIe Gen3 x4 M.2 512GB', 51),
(32, 'Storage 2', 'WDC Purple 6TB For CCTV 24 Hours - WD62PURZ - Garansi 3 Th', 51),
(33, 'Storage 2', 'V-GeN SSD M.2 1TB', 51),
(34, 'Storage 2', 'Seagate Firecuda Gaming SSD 2TB', 51),
(35, 'Storage 2', 'Seagate 10TB For NAS - IronWolf Series', 51),
(36, 'Storage 2', 'Seagate 12TB For NAS - IronWolf Series', 51),
(37, 'Power Supply', 'Corsair CV Series 550W - 80 Plus Bronze', 51),
(38, 'Power Supply', 'Simbadda 500W', 51),
(39, 'Power Supply', 'Thermaltake TR2 S 600W', 51),
(40, 'Power Supply', 'Corsair HX Series 1200W Full Modular - Platinum', 51),
(41, 'Power Supply', 'Digital Alliance Gaming PSU 1300W 80+ Gold BTC', 51),
(42, 'VGA Card', 'Zotac GeForce RTX 2060 12GB GDDR6', 0),
(43, 'VGA Card', 'Gigabyte Quadro RTX5000 16GB DDR6 256 Bit', 49),
(44, 'VGA Card', 'MSI GeForce GTX 1650 SUPER 4GB DDR6', 51),
(45, 'VGA Card', 'Colorful GeForce GT 1030 2GB DDR4', 52),
(46, 'VGA Card', 'PALIT GeForce GTX 1050 Ti 4GB DDR5 StormX Series', 51),
(47, 'VGA Card', 'MSI GeForce RTX 3080 Ti 12GB GDDR6X - SUPRIM X', 56),
(48, 'Casing', 'PRIME T-[Q] - ALUMUNIUM GAMING CASE - DUAL SIDE TEMPERED GLASS', 51),
(49, 'Casing', 'CUBE GAMING EXORA - ATX - LEFT SIDE GLASS DOOR - FRONT RGB BAR', 51),
(50, 'Casing', 'PRIME D-[K] V2.0 - ALUMUNIUM GAMING CASE - DUAL SIDE TEMPERED GLASS', 51),
(51, 'Casing', 'CUBE GAMING NOCTURNE - SIDE TEMPERED GLASS - FRONT GLASS PANEL', 51),
(52, 'Casing', 'Corsair 4000D Airflow Tempered Glass ATX Case - Black', 56);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
