package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class orderingView extends JFrame implements ActionListener{
	
	private JTable jtable;
	private JScrollPane jscrPane;
	private String[] addInfo;
	
	String columnNames[] = {
			"Espresso Beverages", "시럽", "크기", "샷추가", "휘핑크림", "잔", "총액"
	};
	Object rowData[][];
	DefaultTableModel model;
	
	JButton backBtn, orderBtn;
	
	public orderingView(String[] ordering) {	//추가 만들 경우 String배열 대신 dto형태 class를 만든 뒤 파라미터로 넘김
		super("주문 내역");
		setLayout(null);
		addInfo = ordering;
		JLabel label = new JLabel("주문내역");
		label.setBounds(200, 10, 120, 15);
		add(label);
		
		rowData = new Object[1][7];
		
			
			rowData[0][0] = ordering[0];	//Espresso Beverages
			rowData[0][1] = ordering[1];	//시럽
			rowData[0][2] = ordering[2];	//크기
			rowData[0][3] = ordering[3];	//샷추가
			rowData[0][4] = ordering[4];	//휘핑크림
			rowData[0][5] = ordering[5];	//잔
			rowData[0][6] = ordering[6];	//총액
		
		
		// Jtable관련
		// 테이블 폭 설정하기 위한 model
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);
				
		// 테이블 생성
		jtable = new JTable(model);
						
		// 각 column의 폭 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(200);	// coffee
		jtable.getColumnModel().getColumn(1).setMaxWidth(60);	// short
		jtable.getColumnModel().getColumn(2).setMaxWidth(60);	// tall
		jtable.getColumnModel().getColumn(3).setMaxWidth(60);	// grande
		jtable.getColumnModel().getColumn(4).setMaxWidth(60);
		jtable.getColumnModel().getColumn(5).setMaxWidth(60);
		jtable.getColumnModel().getColumn(6).setMaxWidth(60);
		
				
		// 테이블의 column의 글의 맞춤(왼쪽, 중간, 오른쪽)
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	// 중간
				
		// '번호'와 '작성자'의 컬럼은 글의 중간 맞춤이 된다
		jtable.getColumn("잔").setCellRenderer(celAlignCenter);
		jtable.getColumn("총액").setCellRenderer(celAlignCenter);
		
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(20, 50, 560, 300);
		add(jscrPane);
		
		//취소하기
		backBtn = new JButton("취소");
		backBtn.setBounds(20, 400, 60, 30);
		backBtn.addActionListener(this);
		add(backBtn);
		
		//주문하기
		orderBtn = new JButton("주문하기");
		orderBtn.setBounds(400, 400, 100, 30);
		orderBtn.addActionListener(this);
		add(orderBtn);
		
		setBounds(100, 100, 600, 480);
		getContentPane().setBackground(Color.lightGray);
		setVisible(true);
	}



	@Override
	// TODO 버튼
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == backBtn) {
			this.dispose();
		}else {
			Singleton s = Singleton.getInstance();
			// "Espresso Beverages", "시럽", "크기", "샷추가", "휘핑크림", "잔", "총액"
			// public OrderDto(int orderID, String coffee, String id, Date orderDate, String size, int count, int totalCost, int delnum)
			OrderDto dto = new OrderDto(0, addInfo[0], s.getLoginID(), null, addInfo[2],
										Integer.parseInt(addInfo[5]), Integer.parseInt(addInfo[6]), 0);
			boolean b = s.orderCtrl.addOrder(dto);
			if(b) {
				JOptionPane.showMessageDialog(null, "주문완료!");
				new historyView();
				
			}else {
				JOptionPane.showMessageDialog(null, "주문 실패");
			}
			this.dispose();
		}
			
	}
}
