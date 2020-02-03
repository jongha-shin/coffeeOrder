package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.CoffeeDto;



public class menuView extends JFrame{
	
	private JTable jtable;
	private JScrollPane jscrPane;
	
	String columnNames[] = {
			"Espresso Beverages", "Short", "Tall", "Grande"
	};
	Object rowData[][];
	DefaultTableModel model;
	List<CoffeeDto> list;
	
	public menuView(List<CoffeeDto> list) {
		super("메뉴");
		setLayout(null);
		
		JLabel label = new JLabel("가격표");
		label.setBounds(200, 10, 120, 15);
		add(label);
		
		this.list = list;
		rowData = new Object[list.size()][4];
		
		for (int i = 0; i < list.size(); i++) {
			CoffeeDto dto = list.get(i);
			rowData[i][0] = dto.getCoffeeName();	// esproesso beverages
			rowData[i][1] = dto.getSize_short();	// short
			rowData[i][2] = dto.getSize_Tall();		// tall
			rowData[i][3] = dto.getSize_Grande();	// Grande
			
		}
		
		// Jtable관련
		// 테이블 폭 설정하기 위한 model
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames);
				
		// 테이블 생성
		jtable = new JTable(model);
				
		// 각 column의 폭 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(200);	// coffee
		jtable.getColumnModel().getColumn(1).setMaxWidth(120);	// short
		jtable.getColumnModel().getColumn(2).setMaxWidth(120);	// tall
		jtable.getColumnModel().getColumn(3).setMaxWidth(120);	// grande
				
		// 테이블의 column의 글의 맞춤(왼쪽, 중간, 오른쪽)
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	// 중간
				
		// '번호'와 '작성자'의 컬럼은 글의 중간 맞춤이 된다
		jtable.getColumn("Short").setCellRenderer(celAlignCenter);
		jtable.getColumn("Tall").setCellRenderer(celAlignCenter);
		jtable.getColumn("Grande").setCellRenderer(celAlignCenter);
		
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(20, 50, 560, 300);
		add(jscrPane);
		
		setBounds(120, 120, 600, 480);
		getContentPane().setBackground(Color.lightGray);
		setVisible(true);
	}
}
