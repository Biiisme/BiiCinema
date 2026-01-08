package HoaDon_Modal;


public class HoaDon_Bo {
	HoaDon_Dao hddao = new HoaDon_Dao();
	public int Them(int makh , int SoVe ,int TongTien) throws Exception{
		// TODO Auto-generated method stub
		return hddao.Them(makh,SoVe,TongTien);
	}
	public int GetmaxHd() throws Exception{
		// TODO Auto-generated method stub
		return hddao.GetmaxHd();
	}
}
