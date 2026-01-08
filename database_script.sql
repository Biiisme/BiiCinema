-- =============================================
-- Script tạo Database cho Hệ Thống Rạp Chiếu Phim (BiiCinema)
-- Database: bi_cinema
-- =============================================

-- Tạo Database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'bi_cinema')
BEGIN
    CREATE DATABASE bi_cinema;
END
GO

USE bi_cinema;
GO

-- =============================================
-- 1. Bảng KhachHang (User/Customer)
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhachHang]') AND type in (N'U'))
BEGIN
    CREATE TABLE KhachHang (
        MAKH INT IDENTITY(1,1) PRIMARY KEY,
        Hoten NVARCHAR(100) NOT NULL,
        SDT NVARCHAR(20),
        Email NVARCHAR(100),
        TenDN NVARCHAR(50) NOT NULL UNIQUE,
        Role NVARCHAR(20) NOT NULL DEFAULT 'customer', -- 'customer' hoặc 'admin'
        MatKhau NVARCHAR(100) NOT NULL
    );
END
GO

-- =============================================
-- 2. Bảng Movie (Phim)
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Movie]') AND type in (N'U'))
BEGIN
    CREATE TABLE Movie (
        movie_id INT IDENTITY(1,1) PRIMARY KEY,
        TenPhim NVARCHAR(200) NOT NULL,
        poster_url NVARCHAR(500),
        MoTaNgan NVARCHAR(500),
        MoTaDai NVARCHAR(MAX),
        DaoDien NVARCHAR(100),
        DienVien NVARCHAR(200),
        trailer_url NVARCHAR(500),
        Loai NVARCHAR(50), -- Thể loại phim
        DoiTuong NVARCHAR(50), -- Đối tượng xem (P, C13, C16, C18)
        NgayKhoiChieu NVARCHAR(50), -- Ngày khởi chiếu
        ThoiLuong INT -- Thời lượng phim (phút)
    );
END
GO

-- =============================================
-- 3. Bảng MovieNew (Phim Mới - Banner)
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[MovieNew]') AND type in (N'U'))
BEGIN
    CREATE TABLE MovieNew (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        movie_id INT,
        banner_url NVARCHAR(500) NOT NULL,
        FOREIGN KEY (movie_id) REFERENCES Movie(movie_id) ON DELETE CASCADE
    );
END
GO

-- =============================================
-- 4. Bảng Phong (Room)
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Phong]') AND type in (N'U'))
BEGIN
    CREATE TABLE Phong (
        Phong_ID INT IDENTITY(1,1) PRIMARY KEY,
        TenPhong NVARCHAR(100) NOT NULL
    );
END
GO

-- =============================================
-- 5. Bảng LichChieu (Schedule)
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LichChieu]') AND type in (N'U'))
BEGIN
    CREATE TABLE LichChieu (
        LichChieu_ID INT IDENTITY(1,1) PRIMARY KEY,
        Phong_ID INT NOT NULL,
        movie_id INT NOT NULL,
        NgayChieu DATE NOT NULL,
        GioChieu NVARCHAR(10) NOT NULL, -- Format: HH:mm
        FOREIGN KEY (Phong_ID) REFERENCES Phong(Phong_ID) ON DELETE CASCADE,
        FOREIGN KEY (movie_id) REFERENCES Movie(movie_id) ON DELETE CASCADE
    );
END
GO

-- =============================================
-- 6. Bảng Ghe (Seat)
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ghe]') AND type in (N'U'))
BEGIN
    CREATE TABLE Ghe (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Phong_ID INT NOT NULL,
        TenGhe NVARCHAR(10) NOT NULL, -- Ví dụ: A1, A2, B1, B2...
        Gia INT NOT NULL DEFAULT 0, -- Giá vé
        TrangThai INT NOT NULL DEFAULT 0, -- 0: Trống, 1: Đã đặt
        FOREIGN KEY (Phong_ID) REFERENCES Phong(Phong_ID) ON DELETE CASCADE
    );
END
GO

-- =============================================
-- 7. Bảng HoaDon (Bill/Invoice)
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[HoaDon]') AND type in (N'U'))
BEGIN
    CREATE TABLE HoaDon (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        KhachHang_ID INT NOT NULL,
        NgayMua DATETIME NOT NULL DEFAULT GETDATE(),
        TrangThai INT NOT NULL DEFAULT 1, -- 1: Đã thanh toán, 0: Chưa thanh toán
        SoLuongGhe INT NOT NULL,
        TongTien INT NOT NULL,
        FOREIGN KEY (KhachHang_ID) REFERENCES KhachHang(MAKH) ON DELETE CASCADE
    );
END
GO

-- =============================================
-- 8. Bảng CTHD (Chi Tiết Hóa Đơn - Bill Detail)
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CTHD]') AND type in (N'U'))
BEGIN
    CREATE TABLE CTHD (
        ID INT IDENTITY(1,1) PRIMARY KEY,
        Phong_ID INT NOT NULL,
        Phim_ID INT NOT NULL,
        Ghe_ID INT NOT NULL,
        LichChieu_ID INT NOT NULL,
        HoaDon_ID INT NOT NULL,
        FOREIGN KEY (Phong_ID) REFERENCES Phong(Phong_ID) ON DELETE NO ACTION,
        FOREIGN KEY (Phim_ID) REFERENCES Movie(movie_id) ON DELETE NO ACTION,
        FOREIGN KEY (Ghe_ID) REFERENCES Ghe(ID) ON DELETE NO ACTION,
        FOREIGN KEY (LichChieu_ID) REFERENCES LichChieu(LichChieu_ID) ON DELETE NO ACTION,
        FOREIGN KEY (HoaDon_ID) REFERENCES HoaDon(ID) ON DELETE CASCADE
    );
END
GO

-- =============================================
-- Tạo Index để tối ưu hiệu suất
-- =============================================

-- Index cho bảng KhachHang
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'IX_KhachHang_TenDN')
    CREATE INDEX IX_KhachHang_TenDN ON KhachHang(TenDN);
GO

-- Index cho bảng LichChieu
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'IX_LichChieu_PhongID')
    CREATE INDEX IX_LichChieu_PhongID ON LichChieu(Phong_ID);
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'IX_LichChieu_MovieID')
    CREATE INDEX IX_LichChieu_MovieID ON LichChieu(movie_id);
GO

-- Index cho bảng Ghe
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'IX_Ghe_PhongID')
    CREATE INDEX IX_Ghe_PhongID ON Ghe(Phong_ID);
GO

-- Index cho bảng HoaDon
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'IX_HoaDon_KhachHangID')
    CREATE INDEX IX_HoaDon_KhachHangID ON HoaDon(KhachHang_ID);
GO

-- Index cho bảng CTHD
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name = 'IX_CTHD_HoaDonID')
    CREATE INDEX IX_CTHD_HoaDonID ON CTHD(HoaDon_ID);
GO

-- =============================================
-- INSERT DỮ LIỆU MẪU
-- =============================================

-- Xóa dữ liệu cũ (nếu có) - Xóa theo thứ tự ngược lại foreign key
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CTHD]') AND type in (N'U'))
    DELETE FROM CTHD;
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[HoaDon]') AND type in (N'U'))
    DELETE FROM HoaDon;
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ghe]') AND type in (N'U'))
    DELETE FROM Ghe;
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LichChieu]') AND type in (N'U'))
    DELETE FROM LichChieu;
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[MovieNew]') AND type in (N'U'))
    DELETE FROM MovieNew;
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Movie]') AND type in (N'U'))
    DELETE FROM Movie;
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Phong]') AND type in (N'U'))
    DELETE FROM Phong;
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhachHang]') AND type in (N'U'))
    DELETE FROM KhachHang;
GO

-- Reset Identity (chỉ reset nếu bảng tồn tại)
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CTHD]') AND type in (N'U'))
    DBCC CHECKIDENT ('CTHD', RESEED, 0);
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[HoaDon]') AND type in (N'U'))
    DBCC CHECKIDENT ('HoaDon', RESEED, 0);
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ghe]') AND type in (N'U'))
    DBCC CHECKIDENT ('Ghe', RESEED, 0);
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LichChieu]') AND type in (N'U'))
    DBCC CHECKIDENT ('LichChieu', RESEED, 0);
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[MovieNew]') AND type in (N'U'))
    DBCC CHECKIDENT ('MovieNew', RESEED, 0);
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Movie]') AND type in (N'U'))
    DBCC CHECKIDENT ('Movie', RESEED, 0);
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Phong]') AND type in (N'U'))
    DBCC CHECKIDENT ('Phong', RESEED, 0);
IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhachHang]') AND type in (N'U'))
    DBCC CHECKIDENT ('KhachHang', RESEED, 0);
GO

-- 1. Thêm Admin và Customer mẫu
INSERT INTO KhachHang (Hoten, SDT, Email, TenDN, Role, MatKhau) VALUES
(N'Admin System', '0123456789', 'admin@cinema.com', 'admin', 'admin', 'admin123'),
(N'Nguyễn Văn A', '0987654321', 'nguyenvana@gmail.com', 'user1', 'customer', '123456'),
(N'Trần Thị B', '0912345678', 'tranthib@gmail.com', 'user2', 'customer', '123456');
GO

-- 2. Thêm Phim mẫu
INSERT INTO Movie (TenPhim, poster_url, MoTaNgan, MoTaDai, DaoDien, DienVien, trailer_url, Loai, DoiTuong, NgayKhoiChieu, ThoiLuong) VALUES
(N'Avengers: Endgame', '/images/avengers.jpg', N'Phim siêu anh hùng hay nhất', N'Phim kể về cuộc chiến cuối cùng của các siêu anh hùng', N'Russo Brothers', N'Robert Downey Jr, Chris Evans', '/trailer/avengers.mp4', N'Hành động', N'C13', '2024-01-15', 180),
(N'Spider-Man: No Way Home', '/images/spiderman.jpg', N'Spider-Man đa vũ trụ', N'Peter Parker phải đối mặt với các phiên bản Spider-Man khác', N'Jon Watts', N'Tom Holland, Zendaya', '/trailer/spiderman.mp4', N'Hành động', N'C13', '2024-02-01', 148),
(N'Dune', '/images/dune.jpg', N'Khoa học viễn tưởng', N'Cuộc phiêu lưu trên hành tinh sa mạc Arrakis', N'Denis Villeneuve', N'Timothée Chalamet, Zendaya', '/trailer/dune.mp4', N'Khoa học viễn tưởng', N'C16', '2024-01-20', 155);
GO

-- 4. Thêm Phòng chiếu
INSERT INTO Phong (TenPhong) VALUES
(N'Phòng 1'),
(N'Phòng 2'),
(N'Phòng 3'),
(N'Phòng VIP');
GO

-- 5. Thêm Lịch chiếu (sử dụng movie_id từ bảng Movie)
DECLARE @Movie1 INT, @Movie2 INT, @Movie3 INT;

-- Lấy movie_id từ bảng Movie
SELECT @Movie1 = movie_id FROM Movie WHERE TenPhim = N'Avengers: Endgame';
SELECT @Movie2 = movie_id FROM Movie WHERE TenPhim = N'Spider-Man: No Way Home';
SELECT @Movie3 = movie_id FROM Movie WHERE TenPhim = N'Dune';

-- Insert lịch chiếu
INSERT INTO LichChieu (Phong_ID, movie_id, NgayChieu, GioChieu) VALUES
(1, @Movie1, '2024-12-25', '09:00'),
(1, @Movie1, '2024-12-25', '12:00'),
(1, @Movie1, '2024-12-25', '15:00'),
(1, @Movie1, '2024-12-25', '18:00'),
(2, @Movie2, '2024-12-25', '10:00'),
(2, @Movie2, '2024-12-25', '13:00'),
(2, @Movie2, '2024-12-25', '16:00'),
(3, @Movie3, '2024-12-25', '11:00'),
(3, @Movie3, '2024-12-25', '14:00'),
(3, @Movie3, '2024-12-25', '17:00');
GO

-- 3. Thêm Banner phim mới (sau khi đã có movie_id)
INSERT INTO MovieNew (movie_id, banner_url)
SELECT movie_id, '/images/banner1.jpg' FROM Movie WHERE TenPhim = N'Avengers: Endgame'
UNION ALL
SELECT movie_id, '/images/banner2.jpg' FROM Movie WHERE TenPhim = N'Spider-Man: No Way Home';
GO

-- 6. Thêm Ghế cho các Phòng (lấy Phong_ID từ bảng Phong)
DECLARE @Phong1 INT, @Phong2 INT, @Phong3 INT, @PhongVIP INT;
DECLARE @Row INT, @Col INT;
DECLARE @TenGhe NVARCHAR(10);

-- Lấy Phong_ID từ bảng Phong
SELECT @Phong1 = Phong_ID FROM Phong WHERE TenPhong = N'Phòng 1';
SELECT @Phong2 = Phong_ID FROM Phong WHERE TenPhong = N'Phòng 2';
SELECT @Phong3 = Phong_ID FROM Phong WHERE TenPhong = N'Phòng 3';
SELECT @PhongVIP = Phong_ID FROM Phong WHERE TenPhong = N'Phòng VIP';

-- Thêm Ghế cho Phòng 1 (10 hàng x 8 cột = 80 ghế)
SET @Row = 1;
WHILE @Row <= 10
BEGIN
    SET @Col = 1;
    WHILE @Col <= 8
    BEGIN
        SET @TenGhe = CHAR(64 + @Row) + CAST(@Col AS NVARCHAR(2)); -- A1, A2, ..., J8
        INSERT INTO Ghe (Phong_ID, TenGhe, Gia, TrangThai) VALUES (@Phong1, @TenGhe, 50000, 0);
        SET @Col = @Col + 1;
    END
    SET @Row = @Row + 1;
END

-- Thêm Ghế cho Phòng 2
SET @Row = 1;
WHILE @Row <= 10
BEGIN
    SET @Col = 1;
    WHILE @Col <= 8
    BEGIN
        SET @TenGhe = CHAR(64 + @Row) + CAST(@Col AS NVARCHAR(2));
        INSERT INTO Ghe (Phong_ID, TenGhe, Gia, TrangThai) VALUES (@Phong2, @TenGhe, 50000, 0);
        SET @Col = @Col + 1;
    END
    SET @Row = @Row + 1;
END

-- Thêm Ghế cho Phòng 3
SET @Row = 1;
WHILE @Row <= 10
BEGIN
    SET @Col = 1;
    WHILE @Col <= 8
    BEGIN
        SET @TenGhe = CHAR(64 + @Row) + CAST(@Col AS NVARCHAR(2));
        INSERT INTO Ghe (Phong_ID, TenGhe, Gia, TrangThai) VALUES (@Phong3, @TenGhe, 50000, 0);
        SET @Col = @Col + 1;
    END
    SET @Row = @Row + 1;
END

-- Thêm Ghế cho Phòng VIP (6 hàng x 6 cột = 36 ghế, giá cao hơn)
SET @Row = 1;
WHILE @Row <= 6
BEGIN
    SET @Col = 1;
    WHILE @Col <= 6
    BEGIN
        SET @TenGhe = CHAR(64 + @Row) + CAST(@Col AS NVARCHAR(2));
        INSERT INTO Ghe (Phong_ID, TenGhe, Gia, TrangThai) VALUES (@PhongVIP, @TenGhe, 100000, 0); -- 100,000 VNĐ
        SET @Col = @Col + 1;
    END
    SET @Row = @Row + 1;
END
GO

-- =============================================
-- Tạo View để xem lịch sử đặt vé
-- =============================================
IF EXISTS (SELECT * FROM sys.views WHERE name = 'vw_LichSuDatVe')
    DROP VIEW vw_LichSuDatVe;
GO

CREATE VIEW vw_LichSuDatVe AS
SELECT 
    kh.MAKH,
    kh.Hoten AS TenKhachHang,
    m.TenPhim,
    lc.NgayChieu,
    lc.GioChieu,
    p.TenPhong,
    g.TenGhe,
    hd.NgayMua,
    hd.TongTien
FROM CTHD cthd
INNER JOIN HoaDon hd ON cthd.HoaDon_ID = hd.ID
INNER JOIN KhachHang kh ON hd.KhachHang_ID = kh.MAKH
INNER JOIN Movie m ON cthd.Phim_ID = m.movie_id
INNER JOIN LichChieu lc ON cthd.LichChieu_ID = lc.LichChieu_ID
INNER JOIN Phong p ON cthd.Phong_ID = p.Phong_ID
INNER JOIN Ghe g ON cthd.Ghe_ID = g.ID;
GO

-- =============================================
-- Tạo Stored Procedure để lấy thông tin phim chi tiết
-- =============================================
IF EXISTS (SELECT * FROM sys.procedures WHERE name = 'sp_GetMovieDetail')
    DROP PROCEDURE sp_GetMovieDetail;
GO

CREATE PROCEDURE sp_GetMovieDetail
    @MovieID INT
AS
BEGIN
    SELECT 
        movie_id,
        TenPhim,
        poster_url,
        MoTaNgan,
        MoTaDai,
        DaoDien,
        DienVien,
        trailer_url,
        Loai,
        DoiTuong,
        NgayKhoiChieu,
        ThoiLuong
    FROM Movie
    WHERE movie_id = @MovieID;
END
GO

-- =============================================
-- Tạo Stored Procedure để lấy lịch chiếu theo phòng
-- =============================================
IF EXISTS (SELECT * FROM sys.procedures WHERE name = 'sp_GetScheduleByRoom')
    DROP PROCEDURE sp_GetScheduleByRoom;
GO

CREATE PROCEDURE sp_GetScheduleByRoom
    @PhongID INT
AS
BEGIN
    SELECT 
        lc.LichChieu_ID,
        lc.NgayChieu,
        lc.GioChieu,
        m.TenPhim,
        m.ThoiLuong
    FROM LichChieu lc
    INNER JOIN Movie m ON lc.movie_id = m.movie_id
    WHERE lc.Phong_ID = @PhongID
    ORDER BY lc.NgayChieu, lc.GioChieu;
END
GO

-- =============================================
-- Hoàn tất
-- =============================================
PRINT '========================================';
PRINT 'Database bi_cinema đã được tạo thành công!';
PRINT '========================================';
PRINT '';
PRINT 'Thông tin đăng nhập mẫu:';
PRINT 'Admin: TenDN = admin, MatKhau = admin123';
PRINT 'User: TenDN = user1, MatKhau = 123456';
PRINT '';
PRINT 'Đã tạo:';
PRINT '- 3 phim mẫu';
PRINT '- 4 phòng chiếu';
PRINT '- 10 lịch chiếu';
PRINT '- 240 ghế (80 ghế/phòng x 3 phòng + 36 ghế phòng VIP)';
PRINT '- 3 tài khoản (1 admin + 2 customer)';
GO

