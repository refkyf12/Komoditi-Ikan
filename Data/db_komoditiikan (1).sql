-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 14 Nov 2019 pada 05.47
-- Versi server: 10.1.36-MariaDB
-- Versi PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_komoditiikan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `daftar_harga`
--

CREATE TABLE `daftar_harga` (
  `id_daftar_harga` int(11) NOT NULL,
  `id_ikan` int(11) NOT NULL,
  `ukuran` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL,
  `satuan` varchar(255) NOT NULL,
  `ketersediaan` varchar(255) NOT NULL,
  `jumlah_stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `daftar_harga`
--

INSERT INTO `daftar_harga` (`id_daftar_harga`, `id_ikan`, `ukuran`, `harga`, `satuan`, `ketersediaan`, `jumlah_stok`) VALUES
(1, 1, '1 inch', 2000, 'liter', 'Tersedia', 10000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_ikan`
--

CREATE TABLE `data_ikan` (
  `id_ikan` int(11) NOT NULL,
  `nama_ikan` varchar(255) NOT NULL,
  `jenis_ikan` varchar(255) NOT NULL,
  `spesies_ikan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `data_ikan`
--

INSERT INTO `data_ikan` (`id_ikan`, `nama_ikan`, `jenis_ikan`, `spesies_ikan`) VALUES
(1, 'asd', 'asd', 'asd'),
(2, '231', 'fdg', 'asd');

-- --------------------------------------------------------

--
-- Struktur dari tabel `operator`
--

CREATE TABLE `operator` (
  `id_operator` int(11) NOT NULL,
  `nama_operator` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `operator`
--

INSERT INTO `operator` (`id_operator`, `nama_operator`, `username`, `password`) VALUES
(1, 'Admin 1', 'admin1', 'admin123'),
(2, 'Admin 2', 'admin2', 'admin123');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemberian_pakan`
--

CREATE TABLE `pemberian_pakan` (
  `id_pemberian_pakan` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `id_petugas` int(11) NOT NULL,
  `id_tempat` int(11) DEFAULT NULL,
  `id_ikan` int(11) DEFAULT NULL,
  `pakan_telor` int(11) DEFAULT NULL,
  `pakan_cacing` int(11) DEFAULT NULL,
  `pakan_tepung` int(11) DEFAULT NULL,
  `pakan_f99` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `keterangan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pemberian_pakan`
--

INSERT INTO `pemberian_pakan` (`id_pemberian_pakan`, `tanggal`, `id_petugas`, `id_tempat`, `id_ikan`, `pakan_telor`, `pakan_cacing`, `pakan_tepung`, `pakan_f99`, `jumlah`, `keterangan`) VALUES
(1, '2019-11-11', 1, 3, 1, 2, 2, 2, 2, 8, NULL),
(2, '2019-11-11', 2, 3, 2, 2, 2, 2, 2, 8, NULL);

--
-- Trigger `pemberian_pakan`
--
DELIMITER $$
CREATE TRIGGER `hitung_pakan` BEFORE INSERT ON `pemberian_pakan` FOR EACH ROW SET NEW.jumlah = IF(NEW.jumlah IS NULL, NEW.pakan_telor + NEW.pakan_cacing + NEW.pakan_tepung + NEW.pakan_f99, NEW.jumlah)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `hitung_pakan2` BEFORE UPDATE ON `pemberian_pakan` FOR EACH ROW SET NEW.jumlah = IF(NEW.jumlah <=> OLD.jumlah, NEW.pakan_telor + NEW.pakan_cacing + NEW.pakan_tepung + NEW.pakan_f99, NEW.jumlah)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemijahan`
--

CREATE TABLE `pemijahan` (
  `id_pemijahan` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `id_petugas` int(11) NOT NULL,
  `jumlah_indukan` int(11) NOT NULL,
  `jumlah_jenis` int(11) NOT NULL,
  `hasil_telur` int(11) NOT NULL,
  `hasil_larva` int(11) NOT NULL,
  `ukuran` varchar(30) NOT NULL,
  `id_tempat` int(11) NOT NULL,
  `keterangan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pemijahan`
--

INSERT INTO `pemijahan` (`id_pemijahan`, `tanggal`, `id_petugas`, `jumlah_indukan`, `jumlah_jenis`, `hasil_telur`, `hasil_larva`, `ukuran`, `id_tempat`, `keterangan`) VALUES
(123, '2016-03-04', 2, 23, 23, 23, 23, '1 inch', 1, '-');

-- --------------------------------------------------------

--
-- Struktur dari tabel `petugas`
--

CREATE TABLE `petugas` (
  `id_petugas` int(3) NOT NULL,
  `nama_petugas` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `petugas`
--

INSERT INTO `petugas` (`id_petugas`, `nama_petugas`) VALUES
(1, 'Asep'),
(2, 'Dadan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `populasi_ikan`
--

CREATE TABLE `populasi_ikan` (
  `id_populasi` int(11) NOT NULL,
  `id_tempat` int(11) NOT NULL,
  `id_ikan` int(11) NOT NULL,
  `ukuran` varchar(255) NOT NULL,
  `jumlah_jantan` int(11) NOT NULL,
  `jumlah_betina` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `populasi_ikan`
--

INSERT INTO `populasi_ikan` (`id_populasi`, `id_tempat`, `id_ikan`, `ukuran`, `jumlah_jantan`, `jumlah_betina`, `total`) VALUES
(1, 1, 1, '1', 22, 22, 0);

--
-- Trigger `populasi_ikan`
--
DELIMITER $$
CREATE TRIGGER `hitung_total` BEFORE INSERT ON `populasi_ikan` FOR EACH ROW SET NEW.total = IF(NEW.total IS NULL, NEW.jumlah_jantan + NEW.jumlah_betina, NEW.total)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `hitung_total_2` BEFORE UPDATE ON `populasi_ikan` FOR EACH ROW SET NEW.total = IF(NEW.total <=> OLD.total, NEW.jumlah_jantan + NEW.jumlah_betina, NEW.total)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `riwayat_pengadaan`
--

CREATE TABLE `riwayat_pengadaan` (
  `id_pengadaan` int(11) NOT NULL,
  `pengadaan_ikan` varchar(255) NOT NULL,
  `jumlah_jantan` int(11) NOT NULL,
  `jumlah_betina` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `keterangan` varchar(2555) NOT NULL,
  `nama_kpa` varchar(255) DEFAULT NULL,
  `nama_pptk` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `riwayat_pengadaan`
--

INSERT INTO `riwayat_pengadaan` (`id_pengadaan`, `pengadaan_ikan`, `jumlah_jantan`, `jumlah_betina`, `total`, `tanggal`, `keterangan`, `nama_kpa`, `nama_pptk`) VALUES
(2, 'arwana', 23, 23, 46, '2015-06-05', '-', 'dadan', 'asep');

--
-- Trigger `riwayat_pengadaan`
--
DELIMITER $$
CREATE TRIGGER `hitung_pengadaan` BEFORE INSERT ON `riwayat_pengadaan` FOR EACH ROW SET NEW.total = IF(NEW.total IS NULL, NEW.jumlah_jantan + NEW.jumlah_betina, NEW.total)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `hitung_pengadaan2` BEFORE UPDATE ON `riwayat_pengadaan` FOR EACH ROW SET NEW.total = IF(NEW.total <=> OLD.total, NEW.jumlah_jantan + NEW.jumlah_betina, NEW.total)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `stok_indukan`
--

CREATE TABLE `stok_indukan` (
  `id_stok_indukan` int(11) NOT NULL,
  `id_ikan` int(11) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `banyak_induk_betina` int(11) NOT NULL,
  `banyak_induk_jantan` int(11) NOT NULL,
  `calon_indukan_betina` int(11) NOT NULL,
  `calon_indukan_jantan` int(11) NOT NULL,
  `jumlah_kematian` int(11) NOT NULL,
  `total_keseluruhan` int(11) DEFAULT NULL,
  `id_petugas` int(3) NOT NULL,
  `keterangan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `stok_indukan`
--

INSERT INTO `stok_indukan` (`id_stok_indukan`, `id_ikan`, `tanggal`, `banyak_induk_betina`, `banyak_induk_jantan`, `calon_indukan_betina`, `calon_indukan_jantan`, `jumlah_kematian`, `total_keseluruhan`, `id_petugas`, `keterangan`) VALUES
(12, 2, '2019-11-11', 10, 12, 10, 13, 10, 0, 1, 'sad'),
(14, 1, '2012-06-06', 10, 10, 12, 15, 10, 37, 1, 'SAd'),
(23, 2, '2015-05-04', 45, 43, 34, 32, 23, 0, 2, '-'),
(34, 2, '2012-06-06', 234, 53, 45, 243, 234, 341, 1, '-');

--
-- Trigger `stok_indukan`
--
DELIMITER $$
CREATE TRIGGER `hitung_keseluruhan` BEFORE INSERT ON `stok_indukan` FOR EACH ROW SET NEW.total_keseluruhan = IF(NEW.total_keseluruhan IS NULL, NEW.banyak_induk_betina + NEW.banyak_induk_jantan + NEW.calon_indukan_betina + NEW.calon_indukan_jantan - NEW.jumlah_kematian, NEW.total_keseluruhan)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `hitung_keseluruhan2` BEFORE UPDATE ON `stok_indukan` FOR EACH ROW SET NEW.total_keseluruhan = IF(NEW.total_keseluruhan <=> OLD.total_keseluruhan, NEW.banyak_induk_betina + NEW.banyak_induk_jantan + NEW.calon_indukan_betina + NEW.calon_indukan_jantan - NEW.jumlah_kematian, NEW.total_keseluruhan)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `stok_opname`
--

CREATE TABLE `stok_opname` (
  `id_opname` varchar(255) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `nama_barang` varchar(2555) NOT NULL,
  `jenis` varchar(255) NOT NULL,
  `satuan` varchar(255) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `jumlah_pemakaian` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `stok_opname`
--

INSERT INTO `stok_opname` (`id_opname`, `tanggal`, `nama_barang`, `jenis`, `satuan`, `stok`, `jumlah_pemakaian`, `total`) VALUES
('2', '2018-06-03', 'Pakan cacing', 'Pakan', 'Kg', 23, 23, 46);

--
-- Trigger `stok_opname`
--
DELIMITER $$
CREATE TRIGGER `HitungTotalStok` BEFORE INSERT ON `stok_opname` FOR EACH ROW SET NEW.total = IF(NEW.total IS NULL, NEW.stok + NEW.jumlah_pemakaian, NEW.total)
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `HitungTotalStok2` BEFORE UPDATE ON `stok_opname` FOR EACH ROW SET NEW.total = IF(NEW.total <=> OLD.total, NEW.stok + NEW.jumlah_pemakaian, NEW.total)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tempat`
--

CREATE TABLE `tempat` (
  `id_tempat` int(11) NOT NULL,
  `nama_tempat` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tempat`
--

INSERT INTO `tempat` (`id_tempat`, `nama_tempat`) VALUES
(1, 'asd'),
(3, 'Kolam 3');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `daftar_harga`
--
ALTER TABLE `daftar_harga`
  ADD PRIMARY KEY (`id_daftar_harga`),
  ADD KEY `id_ikan` (`id_ikan`);

--
-- Indeks untuk tabel `data_ikan`
--
ALTER TABLE `data_ikan`
  ADD PRIMARY KEY (`id_ikan`);

--
-- Indeks untuk tabel `operator`
--
ALTER TABLE `operator`
  ADD PRIMARY KEY (`id_operator`);

--
-- Indeks untuk tabel `pemberian_pakan`
--
ALTER TABLE `pemberian_pakan`
  ADD PRIMARY KEY (`id_pemberian_pakan`),
  ADD KEY `id_petugas` (`id_petugas`,`id_tempat`,`id_ikan`),
  ADD KEY `id_tempat` (`id_tempat`),
  ADD KEY `id_ikan` (`id_ikan`);

--
-- Indeks untuk tabel `pemijahan`
--
ALTER TABLE `pemijahan`
  ADD PRIMARY KEY (`id_pemijahan`),
  ADD KEY `id_tempat` (`id_tempat`),
  ADD KEY `id_petugas` (`id_petugas`);

--
-- Indeks untuk tabel `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indeks untuk tabel `populasi_ikan`
--
ALTER TABLE `populasi_ikan`
  ADD PRIMARY KEY (`id_populasi`),
  ADD KEY `id_tempat` (`id_tempat`),
  ADD KEY `id_ikan` (`id_ikan`);

--
-- Indeks untuk tabel `riwayat_pengadaan`
--
ALTER TABLE `riwayat_pengadaan`
  ADD PRIMARY KEY (`id_pengadaan`);

--
-- Indeks untuk tabel `stok_indukan`
--
ALTER TABLE `stok_indukan`
  ADD PRIMARY KEY (`id_stok_indukan`),
  ADD KEY `id_ikan` (`id_ikan`),
  ADD KEY `id_petugas` (`id_petugas`);

--
-- Indeks untuk tabel `stok_opname`
--
ALTER TABLE `stok_opname`
  ADD PRIMARY KEY (`id_opname`);

--
-- Indeks untuk tabel `tempat`
--
ALTER TABLE `tempat`
  ADD PRIMARY KEY (`id_tempat`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `daftar_harga`
--
ALTER TABLE `daftar_harga`
  MODIFY `id_daftar_harga` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `data_ikan`
--
ALTER TABLE `data_ikan`
  MODIFY `id_ikan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `operator`
--
ALTER TABLE `operator`
  MODIFY `id_operator` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `pemberian_pakan`
--
ALTER TABLE `pemberian_pakan`
  MODIFY `id_pemberian_pakan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `pemijahan`
--
ALTER TABLE `pemijahan`
  MODIFY `id_pemijahan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT untuk tabel `petugas`
--
ALTER TABLE `petugas`
  MODIFY `id_petugas` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `populasi_ikan`
--
ALTER TABLE `populasi_ikan`
  MODIFY `id_populasi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `riwayat_pengadaan`
--
ALTER TABLE `riwayat_pengadaan`
  MODIFY `id_pengadaan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `stok_indukan`
--
ALTER TABLE `stok_indukan`
  MODIFY `id_stok_indukan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT untuk tabel `tempat`
--
ALTER TABLE `tempat`
  MODIFY `id_tempat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `daftar_harga`
--
ALTER TABLE `daftar_harga`
  ADD CONSTRAINT `daftar_harga_ibfk_1` FOREIGN KEY (`id_ikan`) REFERENCES `data_ikan` (`id_ikan`);

--
-- Ketidakleluasaan untuk tabel `pemberian_pakan`
--
ALTER TABLE `pemberian_pakan`
  ADD CONSTRAINT `pemberian_pakan_ibfk_1` FOREIGN KEY (`id_tempat`) REFERENCES `tempat` (`id_tempat`),
  ADD CONSTRAINT `pemberian_pakan_ibfk_2` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`),
  ADD CONSTRAINT `pemberian_pakan_ibfk_3` FOREIGN KEY (`id_ikan`) REFERENCES `data_ikan` (`id_ikan`);

--
-- Ketidakleluasaan untuk tabel `pemijahan`
--
ALTER TABLE `pemijahan`
  ADD CONSTRAINT `pemijahan_ibfk_1` FOREIGN KEY (`id_tempat`) REFERENCES `tempat` (`id_tempat`),
  ADD CONSTRAINT `pemijahan_ibfk_2` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`);

--
-- Ketidakleluasaan untuk tabel `populasi_ikan`
--
ALTER TABLE `populasi_ikan`
  ADD CONSTRAINT `populasi_ikan_ibfk_1` FOREIGN KEY (`id_tempat`) REFERENCES `tempat` (`id_tempat`),
  ADD CONSTRAINT `populasi_ikan_ibfk_2` FOREIGN KEY (`id_ikan`) REFERENCES `data_ikan` (`id_ikan`);

--
-- Ketidakleluasaan untuk tabel `stok_indukan`
--
ALTER TABLE `stok_indukan`
  ADD CONSTRAINT `stok_indukan_ibfk_1` FOREIGN KEY (`id_ikan`) REFERENCES `data_ikan` (`id_ikan`),
  ADD CONSTRAINT `stok_indukan_ibfk_2` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
