# Lap-Trinh-Mang-Co-Ban
Tóm tắt lại một số kiến thức cơ bản liên quan tới TCP, UDP và giao tiếp với cơ sở dữ liệu

# [**Table of contents**](#table-of-contents)
- [Lap-Trinh-Mang-Co-Ban](#lap-trinh-mang-co-ban)
- [**Table of contents**](#table-of-contents)
- [**1.Giao tiếp với cơ sở dữ liệu**](#1giao-tiếp-với-cơ-sở-dữ-liệu)
	- [**1.1.Kết nối**](#11kết-nối)
	- [**1.2.Thao tác**](#12thao-tác)
- [**2.Lập trình với giao thức TCP**](#2lập-trình-với-giao-thức-tcp)
- [**3.Lập trình với giao thức UDP**](#3lập-trình-với-giao-thức-udp)
# [**1.Giao tiếp với cơ sở dữ liệu**](#giao-tiep-voi-co-so-du-lieu)
## [**1.1.Kết nối**](#ket-noi)
Phần này xin giới thiệu sơ lược cách dùng kết nối cơ sở dữ liệu SQL Server trong một dự án lập trình mạng đơn giản. Để làm được
chúng ta cần có thư việc jdbc 4.2 , Netbean IDE và hệ quản trị cơ sở dữ liệu SQL

Trong dự án, bắt buộc phải có đoạn chương trình sau: 

		public static Connection getConnection() throws SQLException
		{
			String database = "LTM";
			String user = "sa";
			String password = "123456";
			String port = "1433";
			String server = "localhost";
			String url = "jdbc:sqlserver://"+server+":"+port+";databaseName="+database+";user="+user+";password="+password;
			return DriverManager.getConnection(url);
		}
		
Trong đoạn chương trình trên chúng ta có 
1. database: tên cơ sở dữ liệu mà ta muốn giao tiếp. Ở ví dụ này là "LTM"

2. user và password : tên tài khoản để đăng nhập vào Server chứa cơ sở dữ liệu. Ở đây chúng ta sử dụng tài khoản "sa" mặc định

3. port: cổng kết nối tới cơ sở dữ liệu. Cổng 1433 là mặc định của SQL Server

4. server: tên biến sử dụng trong đường dẫn kết nối chính

5. url: đường dẫn để JDBC kết nối tới cơ sở dữ liệu ta mong muốn

> Note: khi lập trình mạng với giao thức TCP hoặc UDP. Chúng ta cần phải đặt đoạn chương trình trên ở phía Server. Không nên đặt ở phía Client
## [**1.2.Thao tác**](#thao-tac)

Thao tác với cơ sở dữ liệu, ở đây, có nghĩa là chúng ta cần có thể thêm - sửa - xóa với các bảng trong cơ sở dữ liệu mà ta kết nối tới. 
Thông thường để giao tiếp cũng chúng ta sẽ có đoạn chương trình như sau:

	    Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String query = "";
            
           
            String query1 = "INSERT INTO person(name) VALUES ('Phong');";
         
            String query2 = "DELETE FROM person WHERE id = 007";
            
            String query3 = "UPDATE person SET name = 'Emma' WHERE id = 456;
            
			query = query1 || query2 || query3
            
            int rs = statement.executeUpdate(query);
            if( rs > -1)
            {
               return "success"; 
            }
            else
            {
                return "fail";
            }
		
Trong đoạn chương trình này chúng ta sẽ nhận thấy như sau:

1. Biến ***connection*** làm nhiệm vụ thiết lập kết nối tới cơ sở dữ liệu

2. Biến ***statement*** đảm nhiệm việc tạo câu lệnh giao tiếp với cơ sở dữ liệu

3. Câu lệnh ***statement.executeUpdate(query)*** thực hiện câu truy vấn mong muốn


# [**2.Lập trình với giao thức TCP**](#lap-trinh-voi-giao-thuc-TCP)

Để lập trình với giao thức TCP, chúng ta cần có 2 thành phần: Server và Client.

Server sẽ xử lý dữ liệu nhận được. Trong khi Client, đảm nhiệm việc gửi dữ liệu từ phía người dùng.

Chương trình có thể có nhiều tính năng bao nhiêu tùy thuộc vào người lập trình. Tuy nhiên, thành phần chính thì luôn như sau:

1. Đối với Server
	
		public static void main(String[] args) throws IOException {
			ServerSocket server = new ServerSocket(5555);
			Socket socket = server.accept();
			System.out.println("Server is running !");
			
			
			DataOutputStream dout = new DataOutputStream( socket.getOutputStream() );
			DataInputStream din  = new DataInputStream( socket.getInputStream() );
			
			
			// REQUEST
			int request = din.readInt();
			
			
			// RESPONSE 
			String response = dout.writeUTF();
		}
		
2. Đối với Client

		public static void main(String[] args) throws IOException 
		{
			   Socket socket = new Socket("localhost", 5555);
			 
			   DataInputStream din = new DataInputStream( socket.getInputStream() );
			   DataOutputStream dout = new DataOutputStream( socket.getOutputStream() );
			   
			   Scanner sc = new Scanner( System.in );
			   
			   // MENU
			   System.out.println("1. HELLO");
			   System.out.println("2. GOODBYE");
			   System.out.println("3. FAREWELL");
			   System.out.print("NOW, MAKE YOUR DECISION : ");
			   
			   
			   // REQUEST
			   int request = sc.nextInt();
			   dout.writeInt(request);
		   
				
			   // RESPONSE
			   String response = din.readUTF();
			   System.out.println("RECEIVED RESPONSE: " + response);
		}
		
		
# [**3.Lập trình với giao thức UDP**](#lap-trinh-voi-giao-thuc-UDP)

Lập trình với UDP cũng tương tự TCP. Gồm 2 phần chính là Server và Client

Tuy nhiên, cách thực hiện sẽ khác hoàn toàn. Cụ thể được trình bày dưới đây:

1. Đối với Server 


		public static void main(String[] args) throws SocketException, IOException 
		{
			DatagramSocket server = new DatagramSocket(5555);
			
			
			// NHAN DU LIEU
			byte buffer1[] = new byte[1000];
			DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length);
			server.receive(packet1);
			
			
			
			// XU LY CHUOI DU LIEU NHAN DUOC
			String request = new String( packet1.getData(), 0 , packet1.getLength() );
			String data[] = request.split("#");
			
			
			
			// TACH DU LIEU
			int result = 0;
			String require = data[0];
			int number1 = Integer.parseInt( data[1] );
			int number2 = Integer.parseInt( data[2] );
			
			
			
			// XU LY THEO YEU CAU
			if( require.contains("1"))
			{
				result = number1 + number2;
			}
			if( require.contains("2"))
			{
				result = number1 - number2;
			}
			
			
			
			// GUI DU LIEU VE
			byte buffer2[] = new byte[1000];
			buffer2 = String.valueOf(result).getBytes();
			DatagramPacket packet2 = new DatagramPacket( buffer2, buffer2.length, packet1.getAddress(), packet1.getPort());
			server.send(packet2);
		}
		
		
2. Đối với Client

		
		public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        // KHAI BAO BIEN
		DatagramSocket socket = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        
		
		
		// IN MENU
        System.out.println("1. TONG");
        System.out.println("2. HIEU");
        
		
		// XU LY YEU CAU
        String yeuCau = sc.nextLine();
		int a,b;
		System.out.print("NHAP SO 1: "); a = sc.nextInt();
		System.out.print("NHAP SO 2: "); b = sc.nextInt();
		
		
		
		// NEN DU LIEU THANH BYTE
        String request = yeuCau + "#" + a + "#" + b;
        byte[] buffer1 = new byte[1000];
        buffer1 = String.valueOf(request).getBytes();
        
        
		
        // GUI DU LIEU
        InetAddress IP = InetAddress.getByName("localhost");
        int port = 5555;
        
        DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length, IP, port);
        socket.send(packet1);
        
		
		
        // NHAN DU LIEU
        byte[] buffer2 = new byte[1000];
        DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length);
        socket.receive(packet2);
        
		
		
        //IN DU LIEU
        String result = new String( packet2.getData(),0 , packet2.getLength() );
        System.out.println(result);
    }
    
    
HO CHI MINH, VIETNAM <br/>
16 November, 2021
