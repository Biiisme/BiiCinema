package Movie_Modal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import KetNoi_Modal.KetNoi;

public class BannerDao {

    // Lấy tất cả banner với thông tin phim
    public ArrayList<Banner> getAllBanners() throws ClassNotFoundException, SQLException {
        ArrayList<Banner> ds = new ArrayList<Banner>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT mn.ID, mn.movie_id, mn.banner_url, m.TenPhim " +
                     "FROM MovieNew mn " +
                     "INNER JOIN Movie m ON mn.movie_id = m.movie_id " +
                     "ORDER BY mn.ID DESC";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();
        while(rs.next()) {
            int id = rs.getInt("ID");
            int movieId = rs.getInt("movie_id");
            String bannerUrl = rs.getString("banner_url");
            String tenPhim = rs.getString("TenPhim");

            Banner banner = new Banner(id, movieId, bannerUrl, tenPhim);
            ds.add(banner);
        }
        rs.close();
        cmd.close();
        kn.cn.close();
        return ds;
    }

    // Lấy banner theo ID
    public Banner getBannerById(int bannerId) throws ClassNotFoundException, SQLException {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT mn.ID, mn.movie_id, mn.banner_url, m.TenPhim " +
                     "FROM MovieNew mn " +
                     "INNER JOIN Movie m ON mn.movie_id = m.movie_id " +
                     "WHERE mn.ID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setInt(1, bannerId);
        ResultSet rs = cmd.executeQuery();

        Banner banner = null;
        if(rs.next()) {
            int id = rs.getInt("ID");
            int movieId = rs.getInt("movie_id");
            String bannerUrl = rs.getString("banner_url");
            String tenPhim = rs.getString("TenPhim");

            banner = new Banner(id, movieId, bannerUrl, tenPhim);
        }
        rs.close();
        cmd.close();
        kn.cn.close();
        return banner;
    }

    // Thêm banner mới
    public boolean addBanner(int movieId, String bannerUrl) throws ClassNotFoundException, SQLException {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "INSERT INTO MovieNew (movie_id, banner_url) VALUES (?, ?)";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setInt(1, movieId);
        cmd.setString(2, bannerUrl);

        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result > 0;
    }

    // Cập nhật banner
    public boolean updateBanner(int bannerId, int movieId, String bannerUrl) throws ClassNotFoundException, SQLException {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "UPDATE MovieNew SET movie_id = ?, banner_url = ? WHERE ID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setInt(1, movieId);
        cmd.setString(2, bannerUrl);
        cmd.setInt(3, bannerId);

        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result > 0;
    }

    // Xóa banner
    public boolean deleteBanner(int bannerId) throws ClassNotFoundException, SQLException {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM MovieNew WHERE ID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setInt(1, bannerId);

        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result > 0;
    }

    // Lấy danh sách phim để chọn khi thêm banner
    public ArrayList<Movie> getAvailableMovies() throws ClassNotFoundException, SQLException {
        ArrayList<Movie> ds = new ArrayList<Movie>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT movie_id, TenPhim FROM Movie ORDER BY TenPhim";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();
        while(rs.next()) {
            int movieId = rs.getInt("movie_id");
            String tenPhim = rs.getString("TenPhim");

            Movie movie = new Movie();
            movie.setMovie_id(movieId);
            movie.setTenPhim(tenPhim);
            ds.add(movie);
        }
        rs.close();
        cmd.close();
        kn.cn.close();
        return ds;
    }

    // Kiểm tra xem phim đã có banner chưa
    public boolean hasBanner(int movieId) throws ClassNotFoundException, SQLException {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT COUNT(*) as count FROM MovieNew WHERE movie_id = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setInt(1, movieId);
        ResultSet rs = cmd.executeQuery();

        boolean hasBanner = false;
        if(rs.next()) {
            hasBanner = rs.getInt("count") > 0;
        }
        rs.close();
        cmd.close();
        kn.cn.close();
        return hasBanner;
    }
}
