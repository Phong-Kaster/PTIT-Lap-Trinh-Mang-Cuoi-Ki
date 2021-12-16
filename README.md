<h1 align="center"> Tóm tắt kiến thức Lập Trình Mạng <br/></h1>

<p align="center">
	<img src="./avatar/cropped-1280-640-1177397.jpg" />
</p>

# [**Table of contents**](#table-of-contents)
- [**Table of contents**](#table-of-contents)
- [**1.Giao tiếp với cơ sở dữ liệu**](#1giao-tiếp-với-cơ-sở-dữ-liệu)
	- [**1.1.Kết nối**](#11kết-nối)
	- [**1.2.Thao tác**](#12thao-tác)
- [**2.Đọc & ghi file**](#2đọc--ghi-file)
- [**3.Lập trình với giao thức TCP**](#3lập-trình-với-giao-thức-tcp)
- [**4.Lập trình với giao thức UDP**](#4lập-trình-với-giao-thức-udp)
- [**5.Lập trình với giao thức RMI**](#5lập-trình-với-giao-thức-rmi)
	- [**Interface**](#interface)
	- [**Class**](#class)
	- [**Server**](#server)
	- [**Client**](#client)


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
1. **database**: tên cơ sở dữ liệu mà ta muốn giao tiếp. Ở ví dụ này là "LTM"

2. **user và password**: tên tài khoản để đăng nhập vào Server chứa cơ sở dữ liệu. Ở đây chúng ta sử dụng tài khoản "sa" mặc định

3. **port**: cổng kết nối tới cơ sở dữ liệu. Cổng 1433 là mặc định của SQL Server

4. **server**: tên biến sử dụng trong đường dẫn kết nối chính

5. **url**: đường dẫn để JDBC kết nối tới cơ sở dữ liệu ta mong muốn

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

# [**2.Đọc & ghi file**](#doc-&-ghi-file)

Đây là một trong những nội dung chắc chắn sẽ có trong bài thi cuối kì. Lấy ví dụ nha, đọc và in ra một ma trận 3x3 từ file cho trước và lưu lại theo đường dẫn được nhập. 

		//DOC NOI DUNG FILE
        try 
        {
            File myObj = new File("Matrix.txt");
            File myObj2 = new File("SortMatrix.txt");
            int[][] myArray = new int[3][3];
            
            Scanner myReader = new Scanner(myObj);
            


            // CHEP MANG TU FILE RA 
            while (myReader.hasNextLine()) 
            {
              for( int i = 0;i < myArray.length;i++)
              {
                  String[] line = myReader.nextLine().trim().split(" ");
                  for( int j = 0 ; j < line.length; j++)
                  {
                      myArray[i][j] = Integer.parseInt( line[j] );  
                  }
              }
            }
            System.out.println("BEFORE");
            System.out.println(Arrays.deepToString(myArray));
            


           //SAP XEP TU TRAI QUA PHAI , TU TREN XUONG DUOI
            for( int i = 0 ; i < 3; i++)
            {
                for( int j = 0 ; j < 2 ;j++)
                {
                    
                    if( myArray[i][j] > myArray[i][j+1])
                    {
                        int temp = myArray[i][j];
                        myArray[i][j] = myArray[i][j+1];
                        myArray[i][j+1] = temp;
                    }
                    
                }
            }
            System.out.println("AFTER");
            System.out.println(Arrays.deepToString(myArray));
            
            
            // VIET VAO FILE result.txt
	    	FileWriter myWriter = new FileWriter(myObj2);
            
            for( int i = 0 ; i < myArray.length; i++)
            {
                for( int j = 0 ; j < myArray.length ;j++)
                {
                    myWriter.write( myArray[i][j] + "," );
                }
                myWriter.write("\n");
            }
            
            
            myWriter.close();
            myReader.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

Trong đó `Matrix.txt` là file cho trước & `SortMatrix.txt` sẽ lưu kết quả sau khi đã sắp xếp xong ❤.
# [**3.Lập trình với giao thức TCP**](#lap-trinh-voi-giao-thuc-TCP)

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
		
		
# [**4.Lập trình với giao thức UDP**](#lap-trinh-voi-giao-thuc-UDP)

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
    
# [**5.Lập trình với giao thức RMI**](#lap-trinh-voi-giao-thuc-RMI)

Để viết chương trình với giao thức RMI, chúng ta cần 4 thành phần chính gồm

1. **Interface** : chứa các khai báo về phương thức | hàm

2. **Class** : class này sẽ kế thừa **Interface** để triển khai các phương thức | hàm hoàn chỉnh

3. **Server** : nơi khai báo Registry để tạo liên kết giữa `Interface` và `Class`

4. **Client** : nơi xử lý cho phía người dùng

Giờ chúng ta sẽ thực hiện viết một chương trình đơn giản tính chu vi và diện tích hình chữ nhật bằng giao thức RMI

## [**Interface**](#interface)

Chúng ta phải khai báo một interface để khai báo các tên các hàm muốn sử dụng trong chương trình.
	
		public interface chuNhat extends Remote 
		{
				public int chuVi(int a, int b) throws RemoteException;
				public int dienTich(int a, int b) throws RemoteException;
		}

Trong interface này chúng ta cần lưu ý gọi `extends Remote` để và gọi thư viện `import java.rmi.Remote`. Ngoài ra tất cả các phương thức | hàm phải có `throws RemoteException`.
	
## [**Class**](#class)

Chúng ta sẽ tiếp tục viết một Class kế thừa `Interface` bên trên và viết cụ thể các phương thức | hàm đã khai báo ở bên trên ở bên trên
	
	public class chuNhatImplement extends UnicastRemoteObject implements chuNhat
	{

			public chuNhatImplement() throws RemoteException
			{
				super();
			}
		
		
		
			@Override
			public int chuVi(int a, int b) throws RemoteException {
				return (a+b)*2;
			}



			@Override
			public int dienTich(int a, int b) throws RemoteException {
				return a*b;
			}
	}
		
Trong class phía trên chúng ta phải chú ý bắt buộc gọi `extends UnicastRemoteObject` và `implements chuNhat`. Ngoài việc, viết chương trình cụ thể cho các hàm. Chúng ta sẽ bắt buộc phải khai báo một **Contructor** không kèm theo tham số. 
	
## [**Server**](#server)

Chúng ta sẽ sử dụng `Registry` để tạo cổng và liên kết giữa `Interface` và `Class`.
	
	public static void main(String[] args) throws RemoteException 
	{
	
	
			Registry registry = LocateRegistry.createRegistry(5555);
			System.out.println("Server is started ! ");
			
			
			
			/* khai bao class chuNhatImplement */
			chuNhatImplement chuNhatImplements = new chuNhatImplement();
			
			
			
			/* dang ky ten class chuNhatImplement voi interface chuNhat */
			registry.rebind("chuNhat", chuNhatImplements);
	}
	


Trong lệnh liên kết phía dưới, chúng ta dùng `rebind()` thay vì `bind()` bởi 

- `Rebind()` : liên kết Interface và Class và cho phép người dùng sử dụng **liên tục**

- `Bind()` : liên kết Interface và Class và cho phép người dùng sử dụng **một lần duy nhất**
	
## [**Client**](#client)

Client - chúng ta xử lý dữ liệu nhập từ phía người dùng.
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException 
	{
			Registry registry = LocateRegistry.getRegistry("localhost", 5555);
			chuNhat rmi = (chuNhat) registry.lookup("chuNhat");
			Scanner sc = new Scanner( System.in );
			
			
			
			System.out.println("Nhap chieu dai : ");
			int a = sc.nextInt();
			
			System.out.println("Nhap chieu rong : ");
			int b = sc.nextInt();
			
			
			
			int chuVi = rmi.chuVi(a, b);
			int dienTich = rmi.dienTich(a, b);
			
			System.out.println("Chu vi hinh chu nhat = " + chuVi );
			System.out.println("Dien tich = " + dienTich );
	} 

HO CHI MINH, VIETNAM <br/>
16 November, 2021
