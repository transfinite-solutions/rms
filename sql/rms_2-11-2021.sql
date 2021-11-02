-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 02, 2021 at 06:47 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.3.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rms`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `address_id` bigint(20) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `landmark` varchar(255) DEFAULT NULL,
  `line1` varchar(255) NOT NULL,
  `pincode` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `user_user_id` bigint(20) DEFAULT NULL,
  `rent_rent_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_id`, `city`, `country`, `landmark`, `line1`, `pincode`, `state`, `tag`, `user_user_id`, `rent_rent_id`) VALUES
(1, 'mumbai', 'india', 'test', 'test', '400001', 'maharashtra', NULL, NULL, NULL),
(2, 'city', 'country', 'landmark', 'line1', 'pincode', 'state', NULL, 2, NULL),
(3, 'city', 'country', 'landmark', 'line1', 'pincode', 'state', NULL, 2, NULL),
(4, 'city', 'country', 'landmark', 'line1', 'pincode', 'state', NULL, 2, NULL),
(5, 'city', 'country', 'landmark', 'line1', 'pincode', 'state', NULL, 2, NULL),
(6, 'city', 'country', 'landmark', 'line1', 'pincode', 'state', NULL, 2, NULL),
(7, 'karnataka', 'india', 'land', 'line1', '300042', 'bangalore', NULL, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryId` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryId`, `image`, `name`) VALUES
(1, 'image', 'transport'),
(2, 'image', 'rent'),
(3, 'image', 'resources'),
(4, 'image', 'masonry');

-- --------------------------------------------------------

--
-- Table structure for table `contract`
--

CREATE TABLE `contract` (
  `contract_id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endDate` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `startDate` datetime(6) DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `customer_user_id` bigint(20) DEFAULT NULL,
  `vendor_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `contract_product`
--

CREATE TABLE `contract_product` (
  `contract_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `orderstock`
--

CREATE TABLE `orderstock` (
  `id` bigint(20) NOT NULL,
  `order_order_id` bigint(20) DEFAULT NULL,
  `stock_stock_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order_stock`
--

CREATE TABLE `order_stock` (
  `order_id` bigint(20) NOT NULL,
  `stock_id` bigint(20) NOT NULL,
  `rent_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `registrationNumber` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `category_categoryId` bigint(20) DEFAULT NULL,
  `user_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_id`, `description`, `imageUrl`, `name`, `registrationNumber`, `type`, `category_categoryId`, `user_user_id`) VALUES
(1, 'description', 'imageUrl', 'product1', NULL, 'rent', 1, 3),
(2, 'description', 'imageUrl', 'product2', NULL, 'rent', 1, 3),
(3, 'description', 'imageUrl', 'product3', '', 'rent', 2, 3),
(4, 'description', 'imageUrl', 'product4', NULL, 'rent', 2, 3),
(5, 'description', 'imageUrl', 'product5', NULL, 'rent', 3, 3),
(6, 'description', 'imageUrl', 'product6', NULL, 'rent', 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `rent`
--

CREATE TABLE `rent` (
  `rent_id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `dropDate` datetime(6) DEFAULT NULL,
  `paymentStatus` varchar(255) DEFAULT NULL,
  `pickupDate` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `address_address_id` bigint(20) DEFAULT NULL,
  `customer_user_id` bigint(20) DEFAULT NULL,
  `vendor_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rentstock`
--

CREATE TABLE `rentstock` (
  `id` bigint(20) NOT NULL,
  `rent_rent_id` bigint(20) DEFAULT NULL,
  `stock_stock_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `rent_stock`
--

CREATE TABLE `rent_stock` (
  `rent_id` bigint(20) NOT NULL,
  `stock_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `name`) VALUES
(1, 'CUSTOMER'),
(2, 'ADMIN'),
(3, 'VENDOR');

-- --------------------------------------------------------

--
-- Table structure for table `sell`
--

CREATE TABLE `sell` (
  `sell_id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `paymentStatus` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `address_address_id` bigint(20) DEFAULT NULL,
  `customer_user_id` bigint(20) DEFAULT NULL,
  `vendor_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `shop_order`
--

CREATE TABLE `shop_order` (
  `order_id` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `fromDate` datetime(6) DEFAULT NULL,
  `orderType` varchar(255) DEFAULT NULL,
  `paymentStatus` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `toDate` datetime(6) DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `address_address_id` bigint(20) DEFAULT NULL,
  `customer_user_id` bigint(20) DEFAULT NULL,
  `vendor_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `stock_id` bigint(20) NOT NULL,
  `availability` varchar(255) DEFAULT NULL,
  `durationTime` bigint(20) DEFAULT NULL,
  `durationType` varchar(255) DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `product_product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`stock_id`, `availability`, `durationTime`, `durationType`, `rate`, `product_product_id`) VALUES
(1, 'AVAILABLE', 1, 'HOUR', 123, 1),
(2, 'AVAILABLE', 1, 'HOUR', 453, 2),
(3, 'AVAILABLE', 1, 'HOUR', 523, 3),
(4, 'AVAILABLE', 1, 'HOUR', 412, 4),
(5, 'AVAILABLE', 1, 'HOUR', 343, 5),
(6, 'AVAILABLE', 1, 'HOUR', 752, 6),
(7, 'AVAILABLE', 1, 'HOUR', 678, 1),
(8, 'AVAILABLE', 1, 'HOUR', 317, 1);

-- --------------------------------------------------------

--
-- Table structure for table `stock_orders`
--

CREATE TABLE `stock_orders` (
  `Stock_stock_id` bigint(20) NOT NULL,
  `orders_order_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `role_role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `name`, `password`, `phone`, `role_role_id`) VALUES
(1, 'customer@gmail.com', 'customer', '$2a$10$LpontO.JIjM//qV7/agUT.Ka7Y2Oij5uflWesokLZiwgcRybdRI2a', '7485963215', 1),
(2, 'admin@gmail.com', 'admin', '$2a$10$Jw5e2XRMTXSfIKgW2M4eU.3CtRXKqg3LQQiQZviV67eOikiTMGLSu', '7485957215', 2),
(3, 'vendor@gmail.com', 'vendor', '$2a$10$xtyR7RkJo966/Gd0/cDewu.j0f6wzfLRkaWA5PHE/A2GqidAC30Tm', '7487363215', 3),
(4, 'asd@gmail.com', 'asd', '$2a$10$SePFcFiKkU1mNdptIsD3BuxpO/qqQb3O5Dt55HQJMSrUaZPgUAsGm', '7418529358', 1);

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `vehicleId` bigint(20) NOT NULL,
  `availability` varchar(255) DEFAULT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `registrationNumber` varchar(255) DEFAULT NULL,
  `user_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `warehouse`
--

CREATE TABLE `warehouse` (
  `warehouseId` bigint(20) NOT NULL,
  `createdAt` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address_address_id` bigint(20) DEFAULT NULL,
  `user_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_id`),
  ADD KEY `FKpxpw7qa7k7scwns6b57iio3xg` (`user_user_id`),
  ADD KEY `FKlopg242jbttxeqtiyv99bs8gd` (`rent_rent_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryId`);

--
-- Indexes for table `contract`
--
ALTER TABLE `contract`
  ADD PRIMARY KEY (`contract_id`),
  ADD KEY `FKtdhittboxe3t2omdfjihpbipg` (`customer_user_id`),
  ADD KEY `FKoqgk07o4a7a9jijgkxitaohb` (`vendor_user_id`);

--
-- Indexes for table `contract_product`
--
ALTER TABLE `contract_product`
  ADD KEY `FKmygg675doa79tb14x7lhgwxy8` (`product_id`),
  ADD KEY `FKcypveko4d778htqy57j8fpvqq` (`contract_id`);

--
-- Indexes for table `orderstock`
--
ALTER TABLE `orderstock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs74fsjhwqpk39h5b1lcfow5k2` (`order_order_id`),
  ADD KEY `FKm63rdkm88e9eml7fs434fc5cb` (`stock_stock_id`);

--
-- Indexes for table `order_stock`
--
ALTER TABLE `order_stock`
  ADD KEY `FKn8samf9ku5oxli478nej86aa8` (`stock_id`),
  ADD KEY `FK23abl4atgb6vih2sl5e0o7gyi` (`order_id`),
  ADD KEY `FKjjx2mofy48pajx6lsmhyycoke` (`rent_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `FKglg21kb09bpkvoyt6m8eetp7b` (`category_categoryId`),
  ADD KEY `FK3ash1pklk3e76ihhq9la4fvcs` (`user_user_id`);

--
-- Indexes for table `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`rent_id`),
  ADD KEY `FKmaufvxgpfi2rdk1uldsbmrdr4` (`address_address_id`),
  ADD KEY `FKobof0sshu4ja13b7e89cwkxcd` (`customer_user_id`),
  ADD KEY `FK7exdqteymebi0pmvrjx98ih1x` (`vendor_user_id`);

--
-- Indexes for table `rentstock`
--
ALTER TABLE `rentstock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKruf5wr4bi8x3em5vy3334a763` (`rent_rent_id`),
  ADD KEY `FKektivg6p88ylxh6fihof2882p` (`stock_stock_id`);

--
-- Indexes for table `rent_stock`
--
ALTER TABLE `rent_stock`
  ADD KEY `FK3eeax4i8cb9bwodnmbhjw5i6g` (`stock_id`),
  ADD KEY `FKg36tqv2558aa32hh2wntbsu1w` (`rent_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `sell`
--
ALTER TABLE `sell`
  ADD PRIMARY KEY (`sell_id`),
  ADD KEY `FKl1lh9wqidrar8hciejqp6ynnc` (`address_address_id`),
  ADD KEY `FKgn5fp52dt0gi24pjhllw1betb` (`customer_user_id`),
  ADD KEY `FKg7owv62g38y8g7du7g589g0wn` (`vendor_user_id`);

--
-- Indexes for table `shop_order`
--
ALTER TABLE `shop_order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FKne3yvdmfeuiqp3py0jb51ovqu` (`address_address_id`),
  ADD KEY `FKc5kmsxq10as6hfyeecgw5lnjx` (`customer_user_id`),
  ADD KEY `FK4n2fe5dnxmndp8qlj9b2adrvf` (`vendor_user_id`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`stock_id`),
  ADD KEY `FKrwdkwjf037066qtbpq0pg0h6n` (`product_product_id`);

--
-- Indexes for table `stock_orders`
--
ALTER TABLE `stock_orders`
  ADD KEY `FKh3sra3g369lsjmepiwybj3pdw` (`orders_order_id`),
  ADD KEY `FKhudsuku1ndlairx4ea5th40ag` (`Stock_stock_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UK_589idila9li6a4arw1t8ht1gx` (`phone`),
  ADD KEY `FKs2ym81xl98n65ndx09xpwxm66` (`role_role_id`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`vehicleId`),
  ADD KEY `FKtm1a0t7o7sjh0iaqea6fc0c0e` (`user_user_id`);

--
-- Indexes for table `warehouse`
--
ALTER TABLE `warehouse`
  ADD PRIMARY KEY (`warehouseId`),
  ADD KEY `FK7m3he1j466le4qxt6p6h4g65h` (`address_address_id`),
  ADD KEY `FKc4wd4u8jcid7f3gb1md0g2cxq` (`user_user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `address_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `categoryId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `contract`
--
ALTER TABLE `contract`
  MODIFY `contract_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orderstock`
--
ALTER TABLE `orderstock`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `rent`
--
ALTER TABLE `rent`
  MODIFY `rent_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `rentstock`
--
ALTER TABLE `rentstock`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sell`
--
ALTER TABLE `sell`
  MODIFY `sell_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_order`
--
ALTER TABLE `shop_order`
  MODIFY `order_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `stock_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `vehicleId` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `warehouse`
--
ALTER TABLE `warehouse`
  MODIFY `warehouseId` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FKlopg242jbttxeqtiyv99bs8gd` FOREIGN KEY (`rent_rent_id`) REFERENCES `rent` (`rent_id`),
  ADD CONSTRAINT `FKpxpw7qa7k7scwns6b57iio3xg` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `FKoqgk07o4a7a9jijgkxitaohb` FOREIGN KEY (`vendor_user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKtdhittboxe3t2omdfjihpbipg` FOREIGN KEY (`customer_user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `contract_product`
--
ALTER TABLE `contract_product`
  ADD CONSTRAINT `FKcypveko4d778htqy57j8fpvqq` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`contract_id`),
  ADD CONSTRAINT `FKmygg675doa79tb14x7lhgwxy8` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Constraints for table `orderstock`
--
ALTER TABLE `orderstock`
  ADD CONSTRAINT `FKm63rdkm88e9eml7fs434fc5cb` FOREIGN KEY (`stock_stock_id`) REFERENCES `stock` (`stock_id`),
  ADD CONSTRAINT `FKs74fsjhwqpk39h5b1lcfow5k2` FOREIGN KEY (`order_order_id`) REFERENCES `shop_order` (`order_id`);

--
-- Constraints for table `order_stock`
--
ALTER TABLE `order_stock`
  ADD CONSTRAINT `FK23abl4atgb6vih2sl5e0o7gyi` FOREIGN KEY (`order_id`) REFERENCES `shop_order` (`order_id`),
  ADD CONSTRAINT `FKjjx2mofy48pajx6lsmhyycoke` FOREIGN KEY (`rent_id`) REFERENCES `rent` (`rent_id`),
  ADD CONSTRAINT `FKn8samf9ku5oxli478nej86aa8` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK3ash1pklk3e76ihhq9la4fvcs` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKglg21kb09bpkvoyt6m8eetp7b` FOREIGN KEY (`category_categoryId`) REFERENCES `category` (`categoryId`);

--
-- Constraints for table `rent`
--
ALTER TABLE `rent`
  ADD CONSTRAINT `FK7exdqteymebi0pmvrjx98ih1x` FOREIGN KEY (`vendor_user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKmaufvxgpfi2rdk1uldsbmrdr4` FOREIGN KEY (`address_address_id`) REFERENCES `address` (`address_id`),
  ADD CONSTRAINT `FKobof0sshu4ja13b7e89cwkxcd` FOREIGN KEY (`customer_user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `rentstock`
--
ALTER TABLE `rentstock`
  ADD CONSTRAINT `FKektivg6p88ylxh6fihof2882p` FOREIGN KEY (`stock_stock_id`) REFERENCES `stock` (`stock_id`),
  ADD CONSTRAINT `FKruf5wr4bi8x3em5vy3334a763` FOREIGN KEY (`rent_rent_id`) REFERENCES `rent` (`rent_id`);

--
-- Constraints for table `rent_stock`
--
ALTER TABLE `rent_stock`
  ADD CONSTRAINT `FK3eeax4i8cb9bwodnmbhjw5i6g` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`stock_id`),
  ADD CONSTRAINT `FKg36tqv2558aa32hh2wntbsu1w` FOREIGN KEY (`rent_id`) REFERENCES `rent` (`rent_id`);

--
-- Constraints for table `sell`
--
ALTER TABLE `sell`
  ADD CONSTRAINT `FKg7owv62g38y8g7du7g589g0wn` FOREIGN KEY (`vendor_user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKgn5fp52dt0gi24pjhllw1betb` FOREIGN KEY (`customer_user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKl1lh9wqidrar8hciejqp6ynnc` FOREIGN KEY (`address_address_id`) REFERENCES `address` (`address_id`);

--
-- Constraints for table `shop_order`
--
ALTER TABLE `shop_order`
  ADD CONSTRAINT `FK4n2fe5dnxmndp8qlj9b2adrvf` FOREIGN KEY (`vendor_user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKc5kmsxq10as6hfyeecgw5lnjx` FOREIGN KEY (`customer_user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKne3yvdmfeuiqp3py0jb51ovqu` FOREIGN KEY (`address_address_id`) REFERENCES `address` (`address_id`);

--
-- Constraints for table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `FKrwdkwjf037066qtbpq0pg0h6n` FOREIGN KEY (`product_product_id`) REFERENCES `product` (`product_id`);

--
-- Constraints for table `stock_orders`
--
ALTER TABLE `stock_orders`
  ADD CONSTRAINT `FKh3sra3g369lsjmepiwybj3pdw` FOREIGN KEY (`orders_order_id`) REFERENCES `shop_order` (`order_id`),
  ADD CONSTRAINT `FKhudsuku1ndlairx4ea5th40ag` FOREIGN KEY (`Stock_stock_id`) REFERENCES `stock` (`stock_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKs2ym81xl98n65ndx09xpwxm66` FOREIGN KEY (`role_role_id`) REFERENCES `role` (`role_id`);

--
-- Constraints for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `FKtm1a0t7o7sjh0iaqea6fc0c0e` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `warehouse`
--
ALTER TABLE `warehouse`
  ADD CONSTRAINT `FK7m3he1j466le4qxt6p6h4g65h` FOREIGN KEY (`address_address_id`) REFERENCES `address` (`address_id`),
  ADD CONSTRAINT `FKc4wd4u8jcid7f3gb1md0g2cxq` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
