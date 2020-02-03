package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.OrderDto;
import singleton.Singleton;

public class historyView extends JFrame{
	
	JTable jtable;
	JScrollPane jscrPane;
	JButton allDeleteBtn, logoutBtn;
	
	String columnNames[] = {
			"주문한 커피", "주문 일", "사이즈", "잔 수", "가격"
	};
	Object rowData[][];
	DefaultTableModel model;
	List<OrderDto> list;
	
	public historyView() {
		super("주문 내역");
		setLayout(null);
		Singleton s = Singleton.getInstance();
		String id = s.getLoginID();
		
		JLabel label = new JLabel(id+"님의 주문 내역");
		label.setBounds(200, 10, 200, 15);
		add(label);
		
		this.list = s.orderCtrl.showHistory(id);
		rowData = new Object[list.size()][5];
		
		for (int i = 0; i < list.size(); i++) {
			OrderDto dto = list.get(i);
			rowData[i][0] = dto.getCoffee();	 
			rowData[i][1] = dto.getOrderDate();	
			rowData[i][2] = dto.getSize();		
			rowData[i][3] = dto.getCount();		
			rowData[i][4] = dto.getTotalCost();
		}
		
		// Jtable관련
		// 테이블 폭 설정하기 위한 model
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);
				
		// 테이블 생성
		jtable = new JTable(model);
				
		// 각 column의 폭 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(180);	// coffee
		jtable.getColumnModel().getColumn(1).setMaxWidth(100);	// short
		jtable.getColumnModel().getColumn(2).setMaxWidth(100);	// tall
		jtable.getColumnModel().getColumn(3).setMaxWidth(100);	// grande
		jtable.getColumnModel().getColumn(4).setMaxWidth(100);
				
		// 테이블의 column의 글의 맞춤(왼쪽, 중간, 오른쪽)
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	// 중간
				
		// '번호'와 '작성자'의 컬럼은 글의 중간 맞춤이 된다
		jtable.getColumn("주문한 커피").setCellRenderer(celAlignCenter);
		jtable.getColumn("주문 일").setCellRenderer(celAlignCenter);
		jtable.getColumn("사이즈").setCellRenderer(celAlignCenter);
		jtable.getColumn("잔 수").setCellRenderer(celAlignCenter);
		jtable.getColumn("가격").setCellRenderer(celAlignCenter);
		
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(20, 50, 560, 300);
		add(jscrPane);
		
		
		JLabel totalcost = new JLabel("합계:");
		totalcost.setBounds(350, 350, 30, 30);
		add(totalcost);
		
		int cost = 0;
		for (int i = 0; i < list.size(); i++) {
			
			cost += list.get(i).getTotalCost(); 
		}
		JLabel lb1 = new JLabel(cost+""+" 원");
		lb1.setBounds(390, 350, 150, 30);
		add(lb1);
		
		allDeleteBtn = new JButton("모든데이터삭제");
		allDeleteBtn.setBounds(50, 350, 150, 30);
		add(allDeleteBtn);
		
		logoutBtn = new JButton("로그아웃");
		logoutBtn.setBounds(400, 20, 100, 20);
		add(logoutBtn);
		
		setBounds(200, 200, 600, 480);
		getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		logoutBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				s.setLoginID("");
				new loginView();
				dispose();
				s.orderView.dispose();
			}
		});

		// 모든 데이터 삭제 (DB데이터는 삭제 안됨)
		allDeleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = s.getLoginID();
				boolean b = s.orderCtrl.deleteHistory(id);
				if(b) {
					JOptionPane.showMessageDialog(null, "삭제 완료");
					new historyView();
				} else {
					JOptionPane.showMessageDialog(null, "삭제 실패");
					new historyView();
				}
			}
		});
	}
}
