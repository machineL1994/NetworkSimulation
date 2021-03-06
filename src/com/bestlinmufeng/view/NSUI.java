package com.bestlinmufeng.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class NSUI extends JFrame {

	public static void main(String[] args) {
		NSUI nsui = new NSUI();
	}

	private static int X1_Y = 100;
	private static int X2_Y = 1700;
	private static int X3_Y = 1900;
	private static int X4_Y = 2100;

	private static int X_Y1 = 100;
	private static int X_Y2 = 150;
	private static int X_Y3 = 250 + 100;
	private static int X_Y4 = 350 + 100;
	private static int X_Y5 = 450 + 100;
	private static int X_Y6 = 550 + 100;
	private static int X_Y7 = 700 + 100;

	private static int LEN_LBL_TXT_WIG = 200;
	private static int LEN_LBL_TXT_HIG = 50;
	private static int LEN_ICO_WIG_HIG = 50;

	private JPanel panelNU; // 底层面板
	private JPanel panelNU2; // 上层面板

	private Base base;

	private JLabel lblImgMap; // 地图
	private Vehicle lblImgVehicle; // 车图标
	private Base lblImgBase; // 基站图标

	private JLabel lblTxtVehicle; // 车
	private JLabel lblTxtVNumb; // 车数目
	private JLabel lblTxtVPriority; // 宽
	private JLabel lblTxtVRandom; // 是否随机
	private JLabel lblTxtBase; // 基站

	private JComboBox cboxVNum; // 车数目选项
	private JComboBox cboxVPriority; // 车优先级
	private JComboBox cboxVRandom; // 车优先级

	// 基站和车的图片地址
	private static String PATH_BASE = "images/base.png";
	private static String PATH_VEHICLE = "images/vehicle.png";

	private JButton btnMap; // 车辆生成按钮

	// 基站左上坐标
	private int base_x;
	private int base_y;

	// 前一个位置
	int begin_base_x = 0;
	int begin_base_y = 0;

	// 指针是否在base图片上
	boolean inTheBase = false;

	public NSUI() {
		panelNU = new JPanel();
		panelNU2 = new JPanel();

		base = new Base(PATH_BASE);

		lblImgMap = new JLabel();
		lblImgVehicle = new Vehicle();

		lblTxtVehicle = new JLabel();
		lblTxtVNumb = new JLabel();
		lblTxtVPriority = new JLabel();
		lblTxtVRandom = new JLabel();
		lblTxtBase = new JLabel();

		cboxVNum = new JComboBox();
		cboxVPriority = new JComboBox();
		cboxVRandom = new JComboBox();

		btnMap = new JButton();

		initNU();

		base_x = X3_Y;// base的初始位置横坐标
		base_y = X_Y2;// base的初始位置纵坐标
		base.setLocation(base_x, base_y);

		// 鼠标动作 监听器 注册
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// 检测 落点 是否在基站上,只有落点在基站上时 才起作用
				if (inBaseBounds(e.getX(), e.getY())) {
					begin_base_x = e.getX();
					begin_base_y = e.getY();
					inTheBase = true;
				}

			}

			// 释放
			public void mouseReleased(MouseEvent e) {
				inTheBase = false;
			}
		});

		// 鼠标移动 监听器 注册
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (inTheBase && checkBasePoint(e.getX(), e.getY())) {
					// 边界 检查
					base_x = base_x - (begin_base_x - e.getX());
					base_y = base_y - (begin_base_y - e.getY());
					// System.out.println("pic_x=" + pic_x);
					begin_base_x = e.getX();
					begin_base_y = e.getY();
					base.setLocation(base_x, base_y);
				}
			}
		});

	}

	private void initNU() {
		Container container = this.getContentPane();

		panelNU.setBounds(0, 0, 2400, 1500);
		this.panelNU.setLayout(null);
		panelNU.setBackground(Color.WHITE);

		panelNU2.setBounds(0, 0, 2100, 1000);
		panelNU2.setBackground(null);
		panelNU2.setOpaque(false);
		this.panelNU2.setLayout(null);

		// 控件内容
		lblImgMap.setIcon(new ImageIcon("images/map.png"));
		lblImgVehicle.setIcon(new ImageIcon("images/vehicle.png"));
//		lblImgBase.setIcon(new ImageIcon("images/base.png"));

		lblTxtBase.setText("基　站：");
		lblTxtVehicle.setText("汽　车：");
		lblTxtVNumb.setText("数　目：");
		lblTxtVPriority.setText("优先级：");
		lblTxtVRandom.setText("随　机：");
		btnMap.setText("生成汽车");

		cboxVNum.addItem("5");
		cboxVNum.addItem("10");
		cboxVNum.addItem("15");
		cboxVNum.addItem("20");
		cboxVNum.addItem("25");

		cboxVPriority.addItem("1");
		cboxVPriority.addItem("2");
		cboxVPriority.addItem("3");
		cboxVPriority.addItem("4");
		cboxVPriority.addItem("5");

		cboxVRandom.addItem("是");
		cboxVRandom.addItem("否");

		// 设置控件大小
		lblImgMap.setBounds(X1_Y, X_Y1, 1500, 1000);
//		lblImgBase.setBounds(X3_Y, X_Y2, LEN_ICO_WIG_HIG, LEN_ICO_WIG_HIG);
		lblImgVehicle.setBounds(X3_Y, X_Y3, LEN_ICO_WIG_HIG, LEN_ICO_WIG_HIG);

		lblTxtBase.setBounds(X2_Y, X_Y2, LEN_LBL_TXT_WIG, LEN_LBL_TXT_HIG);
		lblTxtVehicle.setBounds(X2_Y, X_Y3, LEN_LBL_TXT_WIG, LEN_LBL_TXT_HIG);
		lblTxtVNumb.setBounds(X3_Y, X_Y4, LEN_LBL_TXT_WIG, LEN_LBL_TXT_HIG);
		lblTxtVPriority.setBounds(X3_Y, X_Y5, LEN_LBL_TXT_WIG, LEN_LBL_TXT_HIG);
		lblTxtVRandom.setBounds(X3_Y, X_Y6, LEN_LBL_TXT_WIG, LEN_LBL_TXT_HIG);

		cboxVNum.setBounds(X4_Y, X_Y4, 200, 50);
		cboxVPriority.setBounds(X4_Y, X_Y5, 200, 50);
		cboxVRandom.setBounds(X4_Y, X_Y6, 200, 50);

		btnMap.setBounds(X4_Y, X_Y7, 200, 100);

		lblTxtBase.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		lblTxtVehicle.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		lblTxtVNumb.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		lblTxtVPriority.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		lblTxtVRandom.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		btnMap.setFont(new Font("微软雅黑", Font.PLAIN, 40));

		cboxVNum.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		cboxVPriority.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		cboxVRandom.setFont(new Font("微软雅黑", Font.PLAIN, 40));

		// 控件加载到面板
		this.panelNU.add(lblImgMap);

		this.panelNU.add(lblTxtBase);
		this.panelNU.add(lblTxtVehicle);
		this.panelNU.add(lblTxtVNumb);
		this.panelNU.add(lblTxtVPriority);
		this.panelNU.add(lblTxtVPriority);
		this.panelNU.add(lblTxtVRandom);

		this.panelNU.add(cboxVNum);
		this.panelNU.add(cboxVPriority);
		this.panelNU.add(cboxVRandom);

		this.panelNU.add(btnMap);

		this.panelNU2.add(base);
		this.panelNU2.add(lblImgVehicle);

		container.add(panelNU2);
		container.add(panelNU);

		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭框架的同时结束程序
		this.setSize(2400, 1500);
		this.setLocation(500, 500);
		this.setResizable(false);// 设置框架不可以改变大小
		this.setTitle("2D|3D网络仿真软件");
		this.setVisible(true);
		// Base test = new Base();
		// test.setIcon(new ImageIcon("images/base.png"));
		// test.setBounds(1900, 755, 50, 50);
		// this.panelNU.add(test);

		// JMenuBar menuBar = new JMenuBar();
		// setJMenuBar(menuBar);
		// JMenu menuFile = new JMenu("File");
		// //JMenuItem openItem = new JMenuItem("open");
		// //JMenuItem exitItem = new JMenuItem("exit");
		// JMenu menuEdit = new JMenu("File");
		// JMenu menuHelp = new JMenu("Help");
		//
		// //menu.add(exitItem);
		// menuBar.add(menuFile);
		// menuBar.add(menuEdit);
		// menuBar.add(menuHelp);

		// labelImgMap.setIcon(new ImageIcon(""));
		//// labelImgMap.setIcon(null);
		// labelTextCenter.setText("中心点坐标：");
		//// labelTextCenter.setFont();
		// labelTextLength.setText("长：");
		// labelTextWeight.setText("宽：");
		// btnMap.setText("生成地图");

		// //设置控件
		// this.labelImgMap.setBounds(30, 30, 1000, 700);
		// this.labelTextCenter.setBounds(1000, 100, 100, 100);
		// this.labelTextCenter.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		// this.labelTextLength.setBounds(1000, 120, 100, 100);
		// this.labelTextWeight.setBounds(1000, 120, 100, 100);

	}

	// -------------帮助方法-----------------//
	// 检测 点(px,py) 是否在基站上
	private boolean inBaseBounds(int px, int py) {
		int a = base.getWidth();
		int b = base.getHeight();
		if (px >= base_x && px <= base_x + base.getWidth() && (py - 50) >= base_y
				&& (py - 50) <= base_y + base.getHeight())// 暂时没搞清楚为什么（py-50）
			return true;
		else
			return false;
	}

	// 越界 检查
	private boolean checkBasePoint(int px, int py) {
		if (px < 0 || (py - 50) < 0)
			return false;
		if (px > panelNU2.getWidth() || (py - 50) > panelNU2.getHeight())
			return false;
		return true;
	}

}
