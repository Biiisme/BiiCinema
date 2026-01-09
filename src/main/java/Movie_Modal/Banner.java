package Movie_Modal;

public class Banner {
    private int id;
    private int movieId;
    private String bannerUrl;
    private String tenPhim; // Thêm để hiển thị tên phim

    public Banner() {
        super();
    }

    public Banner(int id, int movieId, String bannerUrl) {
        super();
        this.id = id;
        this.movieId = movieId;
        this.bannerUrl = bannerUrl;
    }

    public Banner(int id, int movieId, String bannerUrl, String tenPhim) {
        super();
        this.id = id;
        this.movieId = movieId;
        this.bannerUrl = bannerUrl;
        this.tenPhim = tenPhim;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    @Override
    public String toString() {
        return "Banner [id=" + id + ", movieId=" + movieId + ", bannerUrl=" + bannerUrl + ", tenPhim=" + tenPhim + "]";
    }
}
