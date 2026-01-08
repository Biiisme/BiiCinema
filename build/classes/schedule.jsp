<%@page import="Schedule_Modal.Schedule"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<title>Chọn lịch xem phim</title>
</head>
<body>
	<!-- nav bar -->
	<jsp:include page="header.jsp"/>
	<!-- end of navbar -->
	
	<br><br><br>
	<div class="container">
    <% 
        ArrayList<Schedule> dsSchedule = (ArrayList<Schedule>) request.getAttribute("dsSchedule");
	 LinkedHashMap<String, ArrayList<Schedule>> scheduleMap = new LinkedHashMap<>();
	    
	    for (Schedule schedule : dsSchedule) {
	        String ngayChieu = schedule.getNgayChieu().toString(); // Chuyển Date sang chuỗi
	        if (!scheduleMap.containsKey(ngayChieu)) {
	            scheduleMap.put(ngayChieu, new ArrayList<Schedule>()); // Spécifiez le type générique ici
	        }
	        scheduleMap.get(ngayChieu).add(schedule);
    }
    %>
    <form action="SeatController" method="get">
        <h2>Chọn lịch xem phim</h2>
        <br>
        <p>Chọn ngày xem phim (yyyy-MM-dd)</p>
        <select id="listDate" class="form-control form-control-lg" name="startDate">
            <% for (String ngayChieu : scheduleMap.keySet()) { %>
                <option value="<%=ngayChieu%>"><%=ngayChieu%></option>
            <% } %>
        </select>
        <br>
        <p>Chọn giờ xem phim (HH:mm)</p>
        <select id="listTimes" class="form-control form-control-lg" name="startTime">
            <!-- Giờ chiếu sẽ được cập nhật động -->
        </select>
        <br><br>
        <input type="hidden" name="PhimID" value="<%=request.getAttribute("PhimID")%>">
        <input type="hidden" name="RoomID" value="<%=request.getAttribute("RoomID")%>">
        <input type="hidden" id="LichChieuID" name="LichChieuID" value="">
        <input type="submit" class="btn btn-outline-danger btn-block">
    </form>
</div>

	<br><br><br><br><br><br><br>
	<jsp:include page="footer.jsp"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</body>
<script>
    // Chuẩn bị dữ liệu từ server
    const scheduleData = {};
    <% for (Map.Entry<String, ArrayList<Schedule>> entry : scheduleMap.entrySet()) { %>
        scheduleData["<%=entry.getKey()%>"] = [];
        <% for (Schedule schedule : entry.getValue()) { %>
            scheduleData["<%=entry.getKey()%>"].push({ 
                time: "<%=schedule.getGioChieu()%>", 
                id: "<%=schedule.getLichChieuID()%>" // Đổi getId thành getLichChieuID
            });
        <% } %>
    <% } %>

    // Xử lý thay đổi ngày chiếu
    $('#listDate').change(function() {
        const selectedDate = $(this).val(); // Lấy ngày được chọn
        const timesDropdown = $('#listTimes');
        const hiddenInput = $('#LichChieuID');
        timesDropdown.empty(); // Xóa các option cũ

        if (scheduleData[selectedDate]) {
            // Thêm các giờ chiếu tương ứng
            scheduleData[selectedDate].forEach(function(item) {
                timesDropdown.append(new Option(item.time, item.time));
            });

            // Gán LichChieuID của giờ chiếu đầu tiên (mặc định)
            if (scheduleData[selectedDate][0]) {
                hiddenInput.val(scheduleData[selectedDate][0].id);
            }
        } else {
            hiddenInput.val(''); // Nếu không có giờ chiếu, để trống
        }
    });

    // Xử lý thay đổi giờ chiếu
    $('#listTimes').change(function() {
        const selectedDate = $('#listDate').val();
        const selectedTime = $(this).val();
        const hiddenInput = $('#LichChieuID');

        const selectedSchedule = scheduleData[selectedDate].find(item => item.time === selectedTime);
        if (selectedSchedule) {
            hiddenInput.val(selectedSchedule.id);
        }
    });

    // Khởi tạo danh sách giờ chiếu cho ngày đầu tiên
    $(document).ready(function() {
        $('#listDate').trigger('change');
    });
</script>
</html>
