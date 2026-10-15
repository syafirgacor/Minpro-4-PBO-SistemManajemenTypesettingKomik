-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 15, 2026 at 12:12 PM
-- Server version: 8.0.30
-- PHP Version: 8.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_typesetting_komik`
--

-- --------------------------------------------------------

--
-- Table structure for table `komik`
--

CREATE TABLE `komik` (
  `id_komik` varchar(20) NOT NULL,
  `judul` varchar(150) NOT NULL,
  `genre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `komik`
--

INSERT INTO `komik` (`id_komik`, `judul`, `genre`) VALUES
('K01', 'Solo Leveling', 'Action'),
('K02', 'Omniscient Reader', 'Fantasy');

-- --------------------------------------------------------

--
-- Table structure for table `proyek_typeset`
--

CREATE TABLE `proyek_typeset` (
  `id_proyek` varchar(20) NOT NULL,
  `id_komik` varchar(20) NOT NULL,
  `id_typesetter` varchar(20) NOT NULL,
  `chapter` int NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `proyek_typeset`
--

INSERT INTO `proyek_typeset` (`id_proyek`, `id_komik`, `id_typesetter`, `chapter`, `status`) VALUES
('PRJ01', 'K01', 'TS01', 100, 'Dalam Pengerjaan'),
('PRJ02', 'K02', 'TS02', 25, 'Revisi');

-- --------------------------------------------------------

--
-- Table structure for table `typesetter`
--

CREATE TABLE `typesetter` (
  `id_typesetter` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis` enum('TETAP','MAGANG') NOT NULL,
  `gaji_pokok` double DEFAULT '0',
  `durasi_magang_bulan` int DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `typesetter`
--

INSERT INTO `typesetter` (`id_typesetter`, `nama`, `jenis`, `gaji_pokok`, `durasi_magang_bulan`) VALUES
('TS01', 'Ahzami', 'TETAP', 3500000, 0),
('TS02', 'Budi Santoso', 'MAGANG', 0, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `komik`
--
ALTER TABLE `komik`
  ADD PRIMARY KEY (`id_komik`);

--
-- Indexes for table `proyek_typeset`
--
ALTER TABLE `proyek_typeset`
  ADD PRIMARY KEY (`id_proyek`),
  ADD KEY `id_komik` (`id_komik`),
  ADD KEY `id_typesetter` (`id_typesetter`);

--
-- Indexes for table `typesetter`
--
ALTER TABLE `typesetter`
  ADD PRIMARY KEY (`id_typesetter`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `proyek_typeset`
--
ALTER TABLE `proyek_typeset`
  ADD CONSTRAINT `proyek_typeset_ibfk_1` FOREIGN KEY (`id_komik`) REFERENCES `komik` (`id_komik`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `proyek_typeset_ibfk_2` FOREIGN KEY (`id_typesetter`) REFERENCES `typesetter` (`id_typesetter`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
