package Movie_Modal;

import java.sql.SQLException;
import java.util.ArrayList;

public class BannerBo {
    BannerDao bannerDao = new BannerDao();

    // Lấy tất cả banner
    public ArrayList<Banner> getAllBanners() throws ClassNotFoundException, SQLException {
        return bannerDao.getAllBanners();
    }

    // Lấy banner theo ID
    public Banner getBannerById(int bannerId) throws ClassNotFoundException, SQLException {
        return bannerDao.getBannerById(bannerId);
    }

    // Thêm banner mới
    public boolean addBanner(int movieId, String bannerUrl) throws ClassNotFoundException, SQLException {
        // Kiểm tra xem phim đã có banner chưa
        if (bannerDao.hasBanner(movieId)) {
            throw new SQLException("Phim này đã có banner!");
        }
        return bannerDao.addBanner(movieId, bannerUrl);
    }

    // Cập nhật banner
    public boolean updateBanner(int bannerId, int movieId, String bannerUrl) throws ClassNotFoundException, SQLException {
        return bannerDao.updateBanner(bannerId, movieId, bannerUrl);
    }

    // Xóa banner
    public boolean deleteBanner(int bannerId) throws ClassNotFoundException, SQLException {
        return bannerDao.deleteBanner(bannerId);
    }

    // Lấy danh sách phim có sẵn
    public ArrayList<Movie> getAvailableMovies() throws ClassNotFoundException, SQLException {
        return bannerDao.getAvailableMovies();
    }
}
