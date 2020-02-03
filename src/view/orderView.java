package view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dto.CoffeeDto;
import singleton.Singleton;

public class orderView extends JFrame implements ActionListener, ItemListener{
	
	JCheckBox cb1, cb2;
	JRadioButton rb1, rb2, rb3, rb4, rb5, rb6;
	JButton orderBtn, menuBtn, logoutBtn;
	ButtonGroup bg1, bg2, cbg1;
	Container container = getContentPane();
	Container container2 = getContentPane();
	Choice choice;
	JTextField count;
	
	String addShot, addWhip = "";
	
	public orderView() {
		super("주문");
		setLayout(null);
		
		Panel panel_size = new Panel();
		panel_size.setBackground(Color.white);
		panel_size.setLayout(new GridLayout(4, 1));
		panel_size.setBounds(50, 150, 150, 150);
		add(panel_size);
		JLabel label_size = new JLabel("크기");
		panel_size.add(label_size);
		rb1 = new JRadioButton("Short");
		rb2 = new JRadioButton("Tall");
		rb3 = new JRadioButton("Grande");
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rb1);
		bg1.add(rb2);
		bg1.add(rb3);
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		container.add(rb1);
		container.add(rb2);
		container.add(rb3);
		panel_size.add(rb1);
		panel_size.add(rb2);
		panel_size.add(rb3);

		// 커피선택 리스트
		Singleton s = Singleton.getInstance();
		List<CoffeeDto> list = s.orderCtrl.getMenuName();
		choice = new Choice();
		for (int i = 0; i < list.size(); i++) {
			choice.add(list.get(i).getCoffeeName());
		}
		choice.setBounds(50, 50, 200, 30);
		add(choice);
		
		
		Panel panel_syrup = new Panel();
		panel_syrup.setBackground(Color.white);
		panel_syrup.setLayout(new GridLayout(4, 1));
		panel_syrup.setBounds(220, 150, 150, 150);
		add(panel_syrup);
		JLabel label_syrup = new JLabel("시럽");
		panel_syrup.add(label_syrup);
		rb4 = new JRadioButton("바닐라");
		rb5 = new JRadioButton("카라멜");
		rb6 = new JRadioButton("헤이즐럿");
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(rb4);
		bg2.add(rb5);
		bg2.add(rb6);
		container2.add(rb4);
		container2.add(rb5);
		container2.add(rb6);
		panel_syrup.add(rb4);
		panel_syrup.add(rb5);
		panel_syrup.add(rb6);
		
		Panel panel_etc = new Panel();
		panel_etc.setBackground(Color.white);
		panel_etc.setLayout(new GridLayout(4, 1));
		panel_etc.setBounds(390, 150, 150, 150);
		add(panel_etc);
		JLabel label_etc = new JLabel("기타");
		panel_etc.add(label_etc);
		cb1 = new JCheckBox("샷추가");
		cb2 = new JCheckBox("휘핑크림");
		
		
		panel_etc.add(cb1);
		panel_etc.add(cb2);
		
		count = new JTextField();
		count.setBounds(180, 350, 50, 30);
		JLabel lb1 = new JLabel("잔");
		lb1.setBounds(235, 350, 30, 30);
		add(count);
		add(lb1);
		
		menuBtn = new JButton("Menu");
		menuBtn.setBounds(450, 30, 100, 30);
		menuBtn.addActionListener(this);
		add(menuBtn);
		
		orderBtn = new JButton("주문 하기");
		orderBtn.setBounds(300, 350, 100, 30);
		orderBtn.addActionListener(this);
		add(orderBtn);
		
		logoutBtn = new JButton("로그 아웃");
		logoutBtn.setBounds(50, 350, 100, 30);
		logoutBtn.addActionListener(this);
		add(logoutBtn);
		
		setBounds(100, 100, 600, 480);
		getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	// TODO 버튼
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton s = Singleton.getInstance();
		
		if(obj == menuBtn) {
			//JOptionPane.showMessageDialog(null, "메뉴 버튼 동작");
			s.orderCtrl.showMenu();
		}else if(obj == logoutBtn) {
			s.setLoginID("");
			new loginView();
			this.dispose();
		}else if(obj == orderBtn) {
			// 스트링 배열로 담아서 전송
			/*
			String columnNames[] = {	  rb456			addShot			count 
					"Espresso Beverages", "시럽", "크기", "샷추가", "휘핑크림", "잔", "총액"
			};                                   rb123			addWhip
			*/
			String size ="";
			if(rb1.isSelected()) {	//nullpointerException
				size = rb1.getText();
			} else if( rb2.isSelected()) {
				size= rb2.getText();
			} else if( rb3.isSelected()) {
				size= rb3.getText();
			}
			
			String syrup = "";
			if(rb4.isSelected()) {
				syrup = "바닐라";
			} else if( rb5.isSelected()) {
				syrup= "카라멜";
			} else if( rb6.isSelected()) {
				syrup= "헤이즐넛";
			}
			
			String addShot = "";
			if(cb1.isSelected()) {
				addShot = "추가";
			}else {
				addShot = "추가 안함";
			}
			String addWhip = "";
			if(cb2.isSelected()) {
				addWhip = "추가";
			}else {
				addWhip = "추가 안함";
			}
			
			
			int cost = s.orderCtrl.coffee_cost(choice.getSelectedItem(), size);
			int jan = Integer.parseInt(count.getText());
			String[] ordering = {choice.getSelectedItem(), syrup, size, addShot, addWhip, count.getText(), (cost * jan)+""};
			
			JOptionPane.showMessageDialog(null, ordering);
			new orderingView(ordering);
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}
}
