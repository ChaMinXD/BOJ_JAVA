package test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class A0015_PARKDONGHWAN_CHAMINHYUK_V2 {

	// 닉네임을 사용자에 맞게 변경해 주세요.
	static final String NICKNAME = "A0015_1142820_V2";

	// 일타싸피 프로그램을 로컬에서 실행할 경우 변경하지 않습니다.
	static final String HOST = "127.0.0.1";
	// static final String HOST = "70.12.60.53";

	// static final String HOST = "70.12.60.48";

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
	static final int[][] padHOLES = {{-254, 0}, {-254, 127}, {-127, 0},
			{-127, 127}, {0, 254}, {127, 254}, {254, 254}, {381, 0}, {381, 127},
			{508, 0}, {508, 127}, {0, -127}, {127, -127}, {254, -127}, {0, 0},
			{127, 0}, {254, 0}, {0, 127}, {127, 127}, {254, 127}};
	static final float r = 5.73f;
	static int curTarget[];
	static int oddTarget[] = {1, 3};
	static int evenTarget[] = {2, 4};
	static int holeIdx;
	static ArrayList<double[]> getCandVectors(double[] white,
			ArrayList<float[]> toHit, float[][] balls) {
		ArrayList<double[]> candVecList = new ArrayList<>();
		for (int i = 0; i < toHit.size(); i++) {
			float[] curTarg = toHit.get(i);
			double x = curTarg[0]; // x y 1목적구
			double y = curTarg[1]; // wx wy는 내가 치는거
			double wx = white[0];
			double wy = white[1];
			int holeIdx = 0;
			for (int hole[] : HOLES) {
				double hx = hole[0];
				double hy = hole[1];
				// hole에 대해 offset 주는 부분 start
				if (hx == 0) {
					hx += 1;
					if (hy == 0) {
						hy += 3;
					} else if (hy == 127) {
						hy -= 3;
					}
				} else if (hx == 254) {
					hx -= 1;
					if (hy == 0) {
						hy += 3;
					} else if (hy == 127) {
						hy -= 3;
					}

				} else if (hx == 127) {
					if (hy == 0) {
						hy += 1;
					} else if (hy == 127) {
						hy -= 1;
					}
				} // offset 부분 end
				if ((wx <= hx) == (wx <= x) && (wy <= hy) == (wy <= y)) {
					double a = getDist(new double[]{hx, hy},
							new double[]{x, y});
					double tmpX = hx + ((a + r) * (x - hx) / a);
					double tmpY = hy + ((a + r) * (y - hy) / a);
					if (inRange(tmpX, tmpY)
							&& findCosTheta(new double[]{x - wx, y - wy},
									new double[]{tmpX - wx, tmpY - wy}) > 0) {
						boolean isAble = true;
						for (int other = 0; other < balls.length; other++) {
							double othX = balls[other][0];
							double othY = balls[other][1];
							if (othX == x && othY == y) {
								continue;
							} else {
								double cosTheta = findCosTheta(
										new double[]{othX - wx, othY - wy},
										new double[]{tmpX - wx, tmpY - wy});
								double sinTheta = cosToSin(cosTheta);
								if (cosTheta > 0 && sinTheta < 7 && dotProduct(
										new double[]{othX - wx, othY - wy},
										new double[]{tmpX - wx,
												tmpY - wy}) <= dotProduct(
														new double[]{tmpX - wx,
																tmpY - wy},
														new double[]{tmpX - wx,
																tmpY - wy})) {
									isAble = false;
									break;
								}
							}
						}
						if (isAble) {
							candVecList.add(new double[]{tmpX, tmpY,
									findCosTheta(new double[]{x - wx, y - wy},
											new double[]{tmpX - wx, tmpY - wy}),
									holeIdx});
						}

					}
				}
				holeIdx++;
			}
		}
		return candVecList;
	}
	static ArrayList<double[]> getPadCandVectors(double[] white,
			ArrayList<float[]> padTarget, ArrayList<float[]> padEnemy) {
		System.out.println("padfunc:" + padEnemy.size());
		ArrayList<double[]> candVecList = new ArrayList<>();
		for (int i = 0; i < padTarget.size(); i++) {
			float[] curTarg = padTarget.get(i);
			double x = curTarg[0]; // x y 1목적구
			double y = curTarg[1]; // wx wy는 내가 치는거
			double wx = white[0];
			double wy = white[1];
			int holeIdx = 0;
			for (int hole[] : padHOLES) {
				double hx = hole[0];
				double hy = hole[1];

				if ((wx <= hx) == (wx <= x) && (wy <= hy) == (wy <= y)) {
					System.out.println("------");
					System.out.println(wx + " " + hx + " " + x);
					System.out.println(wy + " " + hy + " " + y);
					System.out.println("------");
					double a = getDist(new double[]{hx, hy},
							new double[]{x, y});
					double tmpX = hx + ((a + r) * (x - hx) / a);
					double tmpY = hy + ((a + r) * (y - hy) / a);
					if (inPadRange(tmpX, tmpY)
							&& findCosTheta(new double[]{x - wx, y - wy},
									new double[]{tmpX - wx, tmpY - wy}) > 0) {
						boolean isAble = true;
						for (int other = 0; other < padEnemy.size(); other++) {
							double othX = padEnemy.get(other)[0];
							double othY = padEnemy.get(other)[1];
							if (othX == x && othY == y) {
								continue;
							} else {
								double cosTheta = findCosTheta(
										new double[]{othX - wx, othY - wy},
										new double[]{tmpX - wx, tmpY - wy});
								double sinTheta = cosToSin(cosTheta);
								if (cosTheta > 0 && sinTheta <= r && dotProduct(
										new double[]{othX - wx, othY - wy},
										new double[]{tmpX - wx,
												tmpY - wy}) <= dotProduct(
														new double[]{tmpX - wx,
																tmpY - wy},
														new double[]{tmpX - wx,
																tmpY - wy})) {
									isAble = false;
									break;
								}
							}
						}
						if (isAble) {
							candVecList.add(new double[]{tmpX, tmpY,
									findCosTheta(new double[]{x - wx, y - wy},
											new double[]{tmpX - wx, tmpY - wy}),
									holeIdx});
						}
					}
				}
				holeIdx++;
			}
		}
		return candVecList;
	}

	static boolean inRange(double a, double b) {
		return a >= 0 && a <= 254 && b >= 0 && b <= 127;
	}
	static boolean inPadRange(double a, double b) {
		return a >= -127 && a <= 508 && b >= -127 && b <= 254;
	}
	private static double getDist(double[] a, double[] b) {
		return Math.sqrt(
				(a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]));
	}

	static double findCosTheta(double[] v1, double[] v2) {
		double[] normV1 = normVec(v1);
		double[] normV2 = normVec(v2);
		return dotProduct(normV1, normV2);
	}
	static double cosToSin(double cos) {
		return Math.sqrt(1 - Math.pow(cos, 2));
	}
	static double[] normVec(double[] vec) {
		double len = vecLen(vec);
		return new double[]{vec[0] / len, vec[1] / len};
	}
	static double vecLen(double[] vec) {
		return Math.sqrt(vec[0] * vec[0] + vec[1] * vec[1]);
	}
	static double dotProduct(double[] v1, double[] v2) {
		return v1[0] * v2[0] + v1[1] + v2[1];
	}
	static double getHoleDist(double[] v) {
		double dist = 999999;
		for (int[] hole : HOLES) {
			double hx = hole[0];
			double hy = hole[1];
			dist = Math.min(getDist(v, new double[]{hx, hy}), dist);
		}
		return dist;
	}
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
				// 모든 수신값은 변수, 배열에서 확인할 수 있습니다.
				// - order: 1인 경우 선공, 2인 경우 후공을 의미
				// - balls[][]: 일타싸피 정보를 수신해서 각 공의 좌표를 배열로 저장
				// 예) balls[0][0]: 흰 공의 X좌표
				// balls[0][1]: 흰 공의 Y좌표
				// balls[1][0]: 1번 공의 X좌표
				// balls[4][0]: 4번 공의 X좌표
				// balls[5][0]: 마지막 번호(8번) 공의 X좌표

				// 여기서부터 코드를 작성하세요.
				// 아래에 있는 것은 샘플로 작성된 코드이므로 자유롭게 변경할 수 있습니다.

				// whiteBall_x, whiteBall_y: 흰 공의 X, Y좌표를 나타내기 위해 사용한 변수
				float whiteBall_x = balls[0][0];
				float whiteBall_y = balls[0][1];

				// targetBall_x, targetBall_y: 목적구의 X, Y좌표를 나타내기 위해 사용한 변수
				float targetBall_x = -1;
				float targetBall_y = -1;
				ArrayList<float[]> targetSet = new ArrayList<>();
				curTarget = order == 1 ? oddTarget : evenTarget;
				targetSet.clear();
				for (int i = 0; i < curTarget.length; i++) {
					if (balls[curTarget[i]][0] == -1
							&& balls[curTarget[i]][1] == -1) {
						continue;
					} else {
						targetBall_x = balls[curTarget[i]][0];
						targetBall_y = balls[curTarget[i]][1];
						targetSet.add(balls[curTarget[i]]);
					}
				}
				for (float[] ball : targetSet) {
					System.out.println(ball[0] + " " + ball[1]);
				}
				if (targetSet.size() == 0) {
					targetSet.add(balls[5]);
					targetBall_x = balls[5][0];
					targetBall_y = balls[5][1];
				}
				System.out.println(
						"targetBall: " + targetBall_x + " " + targetBall_y);
				// width, height: 목적구와 흰 공의 X좌표 간의 거리, Y좌표 간의 거리
				ArrayList<double[]> canVecs = getCandVectors(
						new double[]{whiteBall_x, whiteBall_y}, targetSet,
						balls);
				double power_offset = 0;
				if (canVecs.isEmpty()) {
					ArrayList<float[]> padTarget = new ArrayList<>();
					ArrayList<float[]> padEnemy = new ArrayList<>();
					for (float[] ball : targetSet) {
						padTarget.add(new float[]{-ball[0], ball[1]}); // 좌
																		// 패딩
						padTarget.add(new float[]{508 - ball[0], ball[1]}); // 우
																			// 패딩
						padTarget.add(new float[]{ball[0], 254 - ball[1]}); // 상
																			// 패딩
						padTarget.add(new float[]{ball[0], -ball[1]}); // 하
																		// 패딩
					}
					for (float[] enemy : balls) {
						if (enemy[0] == whiteBall_x
								&& enemy[1] == whiteBall_y) {
							continue;
						}
						padEnemy.add(new float[]{-enemy[0], enemy[1]}); // 좌
																		// 패딩
						padEnemy.add(new float[]{508 - enemy[0], enemy[1]}); // 우
																				// 패딩
						padEnemy.add(new float[]{enemy[0], 254 - enemy[1]}); // 상
																				// 패딩
						padEnemy.add(new float[]{enemy[0], -enemy[1]}); // 하
																		// 패딩
						padEnemy.add(new float[]{enemy[0], enemy[1]});// 원본
					}
					ArrayList<double[]> padVecs = getPadCandVectors(
							new double[]{whiteBall_x, whiteBall_y}, padTarget,
							padEnemy);
					for (double[] padVec : padVecs) {
						canVecs.add(padVec);
					}
					power_offset += 40;
				}

				double width = 0;
				double height = 0;
				double maxCos = 0;
				width = Math.abs(targetBall_x - whiteBall_x);
				height = Math.abs(targetBall_y - whiteBall_y);
				double hidx = -1.0;
				for (double[] vec : canVecs) {
					double destX = vec[0];
					double destY = vec[1];
					double cos = vec[2];
					if (maxCos < cos) {
						maxCos = cos;
						targetBall_x = (float) destX;
						targetBall_y = (float) destY;
						width = Math.abs(destX - whiteBall_x);
						height = Math.abs(destY - whiteBall_y);
						hidx = vec[3];
					}
				}

				System.out.println("width: " + width);
				System.out.println("height: " + height);
				System.out.println(
						"targetBall: " + targetBall_x + " " + targetBall_y);
				// System.out.println("Hole: idx : " + hidx + " holeX: "
				// + padHOLES[(int) hidx][0] + " holeY: "
				// + padHOLES[(int) hidx][1]);
				// for (float[] d : tPoints) {
				// if (d[0] != -999 && d[1] != -999) {
				//
				// width = Math.abs(d[0] - whiteBall_x);
				// width = Math.abs(d[1] - whiteBall_y);
				// System.out.println(width + " " + height);
				// break;
				// }
				// }
				// radian: width와 height를 두 변으로 하는 직각삼각형의 각도를 구한 결과
				// - 1radian = 180 / PI (도)
				// - 1도 = PI / 180 (radian)
				// angle : 아크탄젠트로 얻은 각도 radian을 degree로 환산한 결과
				float radian = height > 0
						? (float) Math.atan(width / height)
						: 0.0f;
				//
				angle = (float) ((180.0 / Math.PI) * radian);

				// 목적구가 상하좌우로 일직선상에 위치했을 때 각도 입력

				// 목적구가 흰 공을 중심으로 3사분면에 위치했을 때 각도를 재계산
				if (whiteBall_x > targetBall_x && whiteBall_y > targetBall_y) {
					radian = (float) Math.atan(width / height);
					angle = 180 + angle;
					System.out.println("Case 1");

				}

				// 목적구가 흰 공을 중심으로 4사분면에 위치했을 때 각도를 재계산
				else if (whiteBall_x < targetBall_x
						&& whiteBall_y > targetBall_y) {
					radian = (float) Math.atan(width / height);
					angle = 180 - angle;
					System.out.println("Case 2");
				} else if (whiteBall_x > targetBall_x
						&& whiteBall_y < targetBall_y) {
					angle = 360 - angle;
					System.out.println("Case 3");
				} else {
					System.out.println(radian);
					System.out.println(angle);
				}

				// distance: 두 점(좌표) 사이의 거리를 계산
				double distance = Math
						.sqrt((width * width) + (height * height));

				// power: 거리 distance에 따른 힘의 세기를 계산

				power = (float) distance / 4
						+ (float) getHoleDist(
								new double[]{targetBall_x, targetBall_y}) / 3
						+ (float) power_offset;

				// power = 40;
				// 주어진 데이터(공의 좌표)를 활용하여 두 개의 값을 최종 결정하고 나면,
				// 나머지 코드에서 일타싸피로 값을 보내 자동으로 플레이를 진행하게 합니다.
				// - angle: 흰 공을 때려서 보낼 방향(각도)
				// - power: 흰 공을 때릴 힘의 세기
				//
				// 이 때 주의할 점은 power는 100을 초과할 수 없으며,
				// power = 0인 경우 힘이 제로(0)이므로 아무런 반응이 나타나지 않습니다.
				//
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
