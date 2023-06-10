-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th12 28, 2022 lúc 01:03 PM
-- Phiên bản máy phục vụ: 5.7.36
-- Phiên bản PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlygiaovien`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giaovien`
--

DROP TABLE IF EXISTS `giaovien`;
CREATE TABLE IF NOT EXISTS `giaovien` (
  `ma_gv` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ho_ten` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nam_sinh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `danh_gia` double NOT NULL,
  `ten_khoa` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ma_gv`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `giaovien`
--

INSERT INTO `giaovien` (`ma_gv`, `ho_ten`, `nam_sinh`, `danh_gia`, `ten_khoa`) VALUES
('HQT', 'Hồ Quang Trường', '1987', 2, 'Tiếng Anh'),
('NDT', 'Nguyễn Đức Thắng', '1995', 2.4, 'Tiếng Anh'),
('NMK', 'Nguyễn Minh Khôi', '1999', 9, 'Tiếng Anh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khoa`
--

DROP TABLE IF EXISTS `khoa`;
CREATE TABLE IF NOT EXISTS `khoa` (
  `ma_khoa` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ten_khoa` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ma_khoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khoa`
--

INSERT INTO `khoa` (`ma_khoa`, `ten_khoa`) VALUES
('TA', 'Tiếng Anh'),
('TN', 'Tiếng Nhật'),
('TT', 'Công nghệ thông tin'),
('TV', 'Tiếng Việt');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lophoc`
--

DROP TABLE IF EXISTS `lophoc`;
CREATE TABLE IF NOT EXISTS `lophoc` (
  `ma_lop` varchar(50) NOT NULL,
  `ten_lop` varchar(50) NOT NULL,
  `buoi_day` varchar(50) NOT NULL,
  `ma_gv` varchar(50) NOT NULL,
  PRIMARY KEY (`ma_lop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `lophoc`
--

INSERT INTO `lophoc` (`ma_lop`, `ten_lop`, `buoi_day`, `ma_gv`) VALUES
('TN1', 'Tiếng Nhật', 'Thứ 2', 'Hồ Quang Trường'),
('TT10', 'Công nghệ thông tin', 'Thứ 3', 'Nguyễn Đức Thắng'),
('TT11', 'CNTT-Tiếng Nhật', 'Chủ nhật', 'Hồ Quang Trường'),
('TT15', 'Công nghệ thông tin', 'Thứ 3', 'Hồ Quang Trường'),
('TT7', 'Công nghệ thông tin', 'Thứ 3', 'Nguyễn Minh Khôi');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
