-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 29, 2025 at 05:42 PM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `delivery_schedule`
--

DROP TABLE IF EXISTS `delivery_schedule`;
CREATE TABLE IF NOT EXISTS `delivery_schedule` (
  `scheduleId` int NOT NULL AUTO_INCREMENT,
  `shipmentId` int NOT NULL,
  `scheduledDeliveryTime` timestamp NOT NULL,
  PRIMARY KEY (`scheduleId`),
  KEY `fk_schedule_shipment` (`shipmentId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `delivery_schedule`
--

INSERT INTO `delivery_schedule` (`scheduleId`, `shipmentId`, `scheduledDeliveryTime`) VALUES
(1, 3, '2025-06-17 18:45:00'),
(2, 7, '2025-06-19 10:30:00'),
(3, 9, '2024-06-20 11:10:00');

-- --------------------------------------------------------

--
-- Table structure for table `drivers`
--

DROP TABLE IF EXISTS `drivers`;
CREATE TABLE IF NOT EXISTS `drivers` (
  `driverId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `assignedRoute` varchar(100) DEFAULT NULL,
  `schedule` varchar(100) DEFAULT NULL,
  `deliveryHistory` text,
  PRIMARY KEY (`driverId`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `drivers`
--

INSERT INTO `drivers` (`driverId`, `name`, `email`, `phone`, `assignedRoute`, `schedule`, `deliveryHistory`) VALUES
(1, 'Kamal', 'sahandesilva84@gmail.com', '0776822240', 'Kaduwela//Colpetty', '8-10', 'non'),
(2, 'Sarath', 'sahandesilva84@gmail.com', '0716583258', '177', '9-5', '1'),
(3, 'srimal', 'sahandesilva84@gmai.com', '0776822240', '153', '9-10', '5'),
(4, 'ssggsg', 'sahandesilva84@gmail.com', '0776843535', '177', '89', 'non'),
(5, 'kiru', 'ea@gmail.com', '09988', '101', '9-10', '3'),
(6, 'ee', 'jdk@gmail.com', '09977', '101', '9-10', 'non');

-- --------------------------------------------------------

--
-- Table structure for table `driver_assignment`
--

DROP TABLE IF EXISTS `driver_assignment`;
CREATE TABLE IF NOT EXISTS `driver_assignment` (
  `assignment_id` int NOT NULL AUTO_INCREMENT,
  `shipment_id` int NOT NULL,
  `driver_id` int NOT NULL,
  `assignment_date` timestamp NULL DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `fk_shipment` (`shipment_id`),
  KEY `fk_driver` (`driver_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `driver_assignment`
--

INSERT INTO `driver_assignment` (`assignment_id`, `shipment_id`, `driver_id`, `assignment_date`, `status`) VALUES
(1, 3, 1, '2025-06-18 01:43:30', 'Ongoing'),
(2, 8, 2, '2025-06-19 10:50:30', 'Available'),
(3, 9, 3, '2025-06-25 15:00:35', 'Available'),
(4, 3, 1, '2025-06-19 08:23:24', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `shipments`
--

DROP TABLE IF EXISTS `shipments`;
CREATE TABLE IF NOT EXISTS `shipments` (
  `shipmentId` int NOT NULL AUTO_INCREMENT,
  `senderName` varchar(100) NOT NULL,
  `receiverName` varchar(100) NOT NULL,
  `packageContents` varchar(255) NOT NULL,
  `deliveryStatus` varchar(50) NOT NULL,
  `senderEmail` varchar(255) NOT NULL,
  `shipmentDate` date NOT NULL,
  `weight` double NOT NULL,
  `deliveryAddress` varchar(255) NOT NULL,
  PRIMARY KEY (`shipmentId`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `shipments`
--

INSERT INTO `shipments` (`shipmentId`, `senderName`, `receiverName`, `packageContents`, `deliveryStatus`, `senderEmail`, `shipmentDate`, `weight`, `deliveryAddress`) VALUES
(3, 'Sahan', 'Kaveen', 'Medicine', 'Pending', 'sahandesilva84@gmail.com', '2025-06-18', 15, 'Galle'),
(4, 'Vinod', 'Senal', 'Batteries', 'Ongoing', 'sahandesilva84@gmail.com', '2025-05-05', 60, 'Negambo'),
(5, 'Senal', 'Nimesh', 'Tires', 'Ongoing', 'sahandesilva84@gmail.com', '2025-06-18', 150, 'bandarawela'),
(6, 'nimath', 'Gayan', 'Bike', 'Ongoing', 'sahandesilva84@gmail.com', '2025-06-18', 200, 'Malabe'),
(7, 'Pasindu', 'Dhanuka', 'Coconuts', 'Ongoing', 'sahandesilva84@gmail.com', '2025-06-18', 85, 'Welimada'),
(8, 'Rashmi', 'Adeesha', 'flowers', 'Ongoing', 'bamunuge2002@gmail.com', '2025-06-19', 100, 'wennappuwa'),
(13, 'vinod', 'perera', 'flowers', 'Ongoing', 'abc@gmaio.com', '2025-06-19', 100, 'negombo'),
(11, 'sdsad', 'sdsadasd', 'fafaf', 'Ongoing', 'oshadyjayasundaa859@gmail.com', '2025-09-09', 100, 'Kottawa'),
(12, 'Oshady', 'Nimath', 'sfdgsfh', 'Delivered', 'sahandesilva84@gmail.com', '2025-09-16', 151, 'kk'),
(14, 'oshady', 'abc', '2', 'Ongoing', 'sahandesilva84@gmail.com', '2025-09-09', 315, 'cmb');

-- --------------------------------------------------------

--
-- Table structure for table `shipment_progress`
--

DROP TABLE IF EXISTS `shipment_progress`;
CREATE TABLE IF NOT EXISTS `shipment_progress` (
  `progressId` int NOT NULL AUTO_INCREMENT,
  `shipmentId` int NOT NULL,
  `currentLocation` varchar(255) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `estimatedArrival` timestamp NULL DEFAULT NULL,
  `delayReason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`progressId`),
  KEY `fk_shipment_progress_shipment` (`shipmentId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `shipment_progress`
--

INSERT INTO `shipment_progress` (`progressId`, `shipmentId`, `currentLocation`, `status`, `estimatedArrival`, `delayReason`) VALUES
(1, 3, 'Colombo', 'On-route', '2025-06-18 10:30:00', 'non'),
(2, 7, 'Colombo', 'Processing', '2025-05-19 11:00:00', 'No availabale driver'),
(3, 9, 'galle', 'ongoing ', '2025-06-19 14:46:00', 'Rain');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
