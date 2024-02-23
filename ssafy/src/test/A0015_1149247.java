package test;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class A0015_1149247 {

	// 닉네임을 사용자에 맞게 변경해 주세요.
	static final String NICKNAME = "A0015_1149247";

	// 일타싸피 프로그램을 로컬에서 실행할 경우 변경하지 않습니다.
	static final String HOST = "70.12.60.59";

	// 일타싸피 프로그램과 통신할 때 사용하는 코드값으로 변경하지 않습니다.
	static final int PORT = 1447;
	static final int CODE_SEND = 9901;
	static final int CODE_REQUEST = 9902;
	static final int SIGNAL_ORDER = 9908;
	static final int SIGNAL_CLOSE = 9909;

	// 게임 환경에 대한 상수입니다.
	static final int TABLE_WIDTH = 254;
	static final int TABLE_HEIGHT = 127;
	static final int NUMBER_OF_BALLS = 6;
	static final int[][] HOLES = {{0, 0}, {127, 0}, {254, 0}, {0, 127},
			{127, 127}, {254, 127}};

	public static void main(String[] args) {

		Socket socket = null;
		String recv_data = null;
		byte[] bytes = new byte[1024];
		float[][] balls = new float[NUMBER_OF_BALLS][2];
		int order = 0;

		try {
			socket = new Socket();
			System.out.println("Trying Connect: " + HOST + ":" + PORT);
			socket.connect(new InetSocketAddress(HOST, PORT));
			System.out.println("Connected: " + HOST + ":" + PORT);

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			String send_data = CODE_SEND + "/" + NICKNAME + "/";
			bytes = send_data.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("Ready to play!\n--------------------");

			while (socket != null) {

				// Receive Data
				bytes = new byte[1024];
				int count_byte = is.read(bytes);
				recv_data = new String(bytes, 0, count_byte, "UTF-8");
				System.out.println("Data Received: " + recv_data);

				// Read Game Data
				String[] split_data = recv_data.split("/");
				int idx = 0;
				try {
					for (int i = 0; i < NUMBER_OF_BALLS; i++) {
						for (int j = 0; j < 2; j++) {
							balls[i][j] = Float.parseFloat(split_data[idx++]);
						}
					}
				} catch (Exception e) {
					bytes = (CODE_REQUEST + "/" + CODE_REQUEST)
							.getBytes("UTF-8");
					os.write(bytes);
					os.flush();
					System.out.println(
							"Received Data has been currupted, Resend Requested.");
					continue;
				}

				// Check Signal for Player Order or Close Connection
				if (balls[0][0] == SIGNAL_ORDER) {
					order = (int) balls[0][1];
					System.out.println("\n* You will be the "
							+ (order == 1 ? "first" : "second")
							+ " player. *\n");
					continue;
				} else if (balls[0][0] == SIGNAL_CLOSE) {
					break;
				}

				// Show Balls' Position
				for (int i = 0; i < NUMBER_OF_BALLS; i++) {
					System.out.println("Ball " + i + ": " + balls[i][0] + ", "
							+ balls[i][1]);
				}

				float angle = 0.0f;
				float power = 0.0f;

				//////////////////////////////
				// 이 위는 일타싸피와 통신하여 데이터를 주고 받기 위해 작성된 부분이므로 수정하면 안됩니다.
				//

				float whiteBall_x = balls[0][0];
				float whiteBall_y = balls[0][1];
				// targetBall_x, targetBall_y: 목적구의 X, Y좌표를 나타내기 위해 사용한 변수
				float targetBall_x = -1;
				float targetBall_y = -1;
				for (int i = 1; i < NUMBER_OF_BALLS; i++) {
					if (balls[i][0] == -1 || balls[i][1] == -1) {
						continue;
					}
					if (i == 2 || i == 4) {
						continue;
					}
					targetBall_x = balls[i][0];
					targetBall_y = balls[i][1];
					break;
				}
				targetBall_x = 0;
				targetBall_y = 65;
				float targetHole_x = -1;
				float targetHole_y = -1;
				double min = Integer.MAX_VALUE / 10;
				for (int i = 0; i < 6; i++) {
					float w = Math.abs(targetBall_x - HOLES[i][0]);
					float h = Math.abs(targetBall_y - HOLES[i][1]);
					double dis = Math.sqrt((w * w) + (h * h));
					if (dis < min) {
						min = dis;
						targetHole_x = HOLES[i][0];
						targetHole_y = HOLES[i][1];
					}
				}
				System.out.println(targetHole_x);
				System.out.println(targetHole_y);
				float hole_w = Math.abs(targetBall_x - targetHole_x);
				float hole_h = Math.abs(targetBall_y - targetHole_x);
				double hole_rad = hole_h > 0 ? Math.atan(hole_w / hole_h) : 0;
				float hole_angle = (float) ((180.0 / Math.PI) * hole_rad);
				System.out.println(Math.cos(hole_angle));
				System.out.println(Math.sin(hole_angle));
				System.out.println(targetBall_x);
				System.out.println(targetBall_y);

				if (targetBall_x > whiteBall_x) {
					targetBall_x -= Math.cos(hole_angle) * 5.73 / 2;
				} else if (targetBall_x < whiteBall_x) {
					targetBall_x += Math.cos(hole_angle) * 5.73 / 2;

				}
				if (targetBall_y > whiteBall_y) {
					targetBall_y -= Math.sin(hole_angle) * 5.73 / 2;
				} else if (targetBall_y < whiteBall_y) {
					targetBall_y += Math.sin(hole_angle) * 5.73 / 2;
				}
				System.out.println(targetBall_x);
				System.out.println(targetBall_y);
				// width, height: 목적구와 흰 공의 X좌표 간의 거리, Y좌표 간의 거리
				float width = Math.abs(targetBall_x - whiteBall_x);
				float height = Math.abs(targetBall_y - whiteBall_y);

				// radian: width와 height를 두 변으로 하는 직각삼각형의 각도를 구한 결과
				// - 1radian = 180 / PI (도)
				// - 1도 = PI / 180 (radian)
				// angle : 아크탄젠트로 얻은 각도 radian을 degree로 환산한 결과
				double radian = height > 0 ? Math.atan(width / height) : 0;
				angle = (float) ((180.0 / Math.PI) * radian);
				// 목적구가 상하좌우로 일직선상에 위치했을 때 각도 입력
				if (whiteBall_x == targetBall_x) {
					if (whiteBall_y < targetBall_y) {
						angle = 0;
					} else {
						angle = 180;
					}
				} else if (whiteBall_y == targetBall_y) {
					if (whiteBall_x < targetBall_x) {
						angle = 90;
					} else {
						angle = 270;
					}
				}

				// 목적구가 흰 공을 중심으로 3사분면에 위치했을 때 각도를 재계산
				if (whiteBall_x > targetBall_x && whiteBall_y > targetBall_y) {
					radian = Math.atan(width / height);
					angle = (float) (((180.0 / Math.PI) * radian) + 180);
				}

				// 목적구가 흰 공을 중심으로 4사분면에 위치했을 때 각도를 재계산
				else if (whiteBall_x < targetBall_x
						&& whiteBall_y > targetBall_y) {
					radian = Math.atan(height / width);
					angle = (float) (((180.0 / Math.PI) * radian) + 90);
				}

				// 1
				else if (whiteBall_x > targetBall_x
						&& whiteBall_y < targetBall_y) {
					radian = Math.atan(height / width);
					angle = (float) (((180.0 / Math.PI) * radian) + 270);
				}
				
				System.out.println(whiteBall_x);
				System.out.println(whiteBall_y);


				// distance: 두 점(좌표) 사이의 거리를 계산
				double distance = Math
						.sqrt((width * width) + (height * height));
				power=(float) distance;
				// 아래는 일타싸피와 통신하는 나머지 부분이므로 수정하면 안됩니다.
				//////////////////////////////

				String merged_data = angle + "/" + power + "/";
				bytes = merged_data.getBytes("UTF-8");
				os.write(bytes);
				os.flush();
				System.out.println("Data Sent: " + merged_data);
			}

			os.close();
			is.close();
			socket.close();
			System.out.println("Connection Closed.\n--------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
