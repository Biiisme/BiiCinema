package ChiTietHD;



public class ChiTietHdBO {
	ChiTietHdDAO cthddao = new ChiTietHdDAO();
	public int ThemCTHD(int phongID,int PhimID,int GheID,int LichChieuID,int HoaDonID) throws Exception{
		return cthddao.ThemCTHD(phongID,PhimID,GheID ,LichChieuID, HoaDonID);
	}
	
}
