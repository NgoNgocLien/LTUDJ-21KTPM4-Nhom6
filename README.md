# Đồ án môn học: Nhập môn lập trình ứng dụng Java - CSC13102_FIT-HCMUS
# Sản phẩm: Hệ thống chat
## 1. Mô tả sản phẩm
Hệ thống gồm 2 phân hệ: người quản trị và người sử dụng
- Người quản trị: có quyền quản lý chi tiết người dùng, bao gồm thêm, cập nhật, và xoá tài khoản, cũng như quản lý danh sách bạn bè. Họ có khả năng xem lịch sử đăng nhập, theo dõi hoạt động người dùng và xử lý báo cáo spam. Ngoài ra, người quản trị có thể tạo và quản lý nhóm chat, xem thống kê với biểu đồ số lượng người đăng ký mới và hoạt động theo năm.
- Người sử dụng: có thể đăng ký và đăng nhập, quản lý danh sách bạn bè, tham gia nhóm chat mã hóa, và thực hiện các chức năng chat như tìm kiếm trong lịch sử, báo cáo spam, và block tài khoản. Họ cũng có khả năng thăm dò và tham gia các nhóm chat, tạo nhóm mới, và tận hưởng tính năng chat thời gian thực.
## 2. Yêu cầu
### Phân hệ dành cho người quản trị
#### 1. Quản lý danh sách người dùng. Thông tin người dùng gồm: tên đăng nhập, họ tên, địa chỉ, ngày
sinh, giới tính, email
- Xem danh sách cho phép lọc theo tên/tên đăng nhập/trạng thái, sắp xếp theo tên/ngày tạo
- Thêm/cập nhật/xoá
- Khoá/mở khóa tài khoản
- Cập nhật mật khẩu
- Xem lịch sử đăng nhập
- Danh sách bạn bè.
#### 2. Xem danh sách đăng nhập theo thứ tự thời gian. Thông tin gồm: thời gian, tên đăng nhập, họ tên.
#### 3. Xem danh sách các nhóm chat
- Sắp xếp theo tên/thời gian tạo
- Lọc theo tên
- Xem danh sách thành viên 1 nhóm
- Xem danh sách admin 1 nhóm.
#### 4. Xem danh sách báo cáo spam
- Sắp xếp theo thời gian/tên đăng nhập
- Lọc theo thời gian
- Lọc theo tên đăng nhập
- Khóa tài khoản người dùng
#### 5. Xem danh sách người dùng đăng ký mới: chọn khoảng thời gian, hiện ra danh sách người dùng
đăng ký mới
- Sắp xếp theo tên/thời gian tạo
- Lọc theo tên
#### 6. Biểu đồ số lượng người đăng ký mới theo năm: chọn năm, vẽ biểu đồ với trục hoành là tháng,
trục tung là số lượng người đăng ký mới.
#### 7. Xem danh sách người dùng và số lượng bạn bè (1 cột bạn bè trực tiếp, 1 cột tính luôn số lượng
bạn của bạn)
- Sắp xếp theo tên/thời gian tạo
- Lọc theo tên
- Lọc theo số lượng bạn trực tiếp (bằng, nhỏ hơn, lớn hơn 1 số được nhập).
#### 8. Xem danh sách người dùng hoạt động: chọn khoảng thời gian, hiện ra danh sách người dùng có
hoạt đặng và các số liệu (mở ứng dụng, chat với bao nhiêu người, chat bao nhiêu nhóm)
- Sắp xếp theo tên/thời gian tạo
- Lọc theo tên
- Lọc theo số lượng hoạt động (bằng, nhỏ hơn, lớn hơn 1 số được nhập).
#### 9. Biểu đồ số lượng người hoạt động theo năm: chọn năm, vẽ biểu đồ với trục hoành là tháng, trục
tung là số lượng người có mở ứng dụng.

### Phân hệ dành cho người sử dụng
#### 1. Đăng ký tài khoản.
#### 2. Khởi tạo lại mật khẩu: mật khẩu sẽ được random và gởi tới email.
#### 3. Đăng nhập.
#### 4. Danh sách bạn bè:
- Thêm bạn bằng tên đăng nhập
- Huỷ kết bạn
#### 5. Danh sách bạn bè đang online
#### 6. Tìm người dựa vào tên đăng nhập hoặc tên. Sau đó có thể chat.
#### 7. Báo cáo spam khi người đó chat với mình và mình không thích.
#### 8. Block tài khoản 1 người nào đó.
#### 9. Chat với bạn
- Nếu bạn đang online thì phải thấy phản hồi thời gian thực
- Nếu bạn đang offline thì gởi tin nhắn bạn xem sau
- Xem được lịch sử chat
- Xoá lịch sử chat
- Tìm kiếm chuỗi trong lịch sử chat:
  - Tìm kiếm trong lịch sử chat với 1 người
  - Tìm kiếm trong lịch sử chat với tất cả mọi người
#### 10. Nhóm chat
- Tạo nhóm: khi tạo cho thêm tối thiểu 1 tên đăng nhập
- Đổi tên nhóm chat
- Thêm thành viên
- Gán quyền admin nhóm cho thành viên
- Xoá thành viên: chỉ admin mới được quyền xoá
- Chat
- Mã hóa nhóm (tham khảo tính năng nhóm chat được mã hóa của facebook)
## 3. Video demo sản phẩm
Link video demo: [link](https://drive.google.com/drive/folders/1pvV1o0DGyhAsDYisrr0sWWbE3LelbJt7?usp=drive_link)
## 4. Công nghệ
- DBMS: mySQL  <img src="https://camo.githubusercontent.com/0c6732b7d21907a793e02e157548954f2ce6202d43f85d03f633c80f288cb82c/68747470733a2f2f6564656e742e6769746875622e696f2f537570657254696e7949636f6e732f696d616765732f7376672f6d7973716c2e737667" width="40" height="40">
- API: JDBC
- IDE: IntelliJ IDEA 2023.3.2 (Community Edition)
- Java version 8 - JDK 21
- UI: Java Swing
- Communicate: Java Socket
