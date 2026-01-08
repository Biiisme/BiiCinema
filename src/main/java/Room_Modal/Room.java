package Room_Modal;

public class Room {
	private int Room_id;
	private String Tenphong;
	public int getRoom_id() {
		return Room_id;
	}
	public void setRoom_id(int room_id) {
		Room_id = room_id;
	}
	public String getTenphong() {
		return Tenphong;
	}
	public void setTenphong(String tenphong) {
		Tenphong = tenphong;
	}
	public Room(int room_id, String tenphong) {
		super();
		Room_id = room_id;
		Tenphong = tenphong;
	}
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
