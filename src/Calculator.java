
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Calculator {
	public static void main(String[] args){
		new MyNewCal();
	}
}

class MyNewCal extends JFrame{
	
	private Point point;
	private int width,height;
	private JTextArea InputArea,OutputArea;
	private String s,InputTemp = "",OutputTemp = "";
	private boolean flag = false;

	public MyNewCal(){
		super("Java实验:计算器");
		ImageIcon icon=new ImageIcon("d:/yt_16x16.png");
        this.setIconImage(icon.getImage()); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		addKeyListener(new KeyMonitor());
		setFocusable(true);
		setLayout(new GridLayout(1,2,2,0));
		JPanel workspace = new JPanel();
		add(workspace);
		JPanel historyspace = new JPanel();
		historyspace.setBackground(Color.white);
		add(historyspace);
		JMenuBar bar = new JMenuBar();
		bar.setBackground(Color.white);
		setJMenuBar(bar);
		JMenu fileMenu = new JMenu("文件");
		fileMenu.setFont(new Font("微软雅黑",Font.PLAIN,15));
		bar.add(fileMenu);
		JMenuItem helpItem = new JMenuItem("帮助");
		JMenuItem exitItem = new JMenuItem("退出");
		fileMenu.add(exitItem);
		fileMenu.add(helpItem);
		JDialog dialog2 = new JDialog();
		dialog2.setModal(true);
		dialog2.setIconImage(icon.getImage());
		dialog2.setTitle("关于本计算器");
		dialog2.setSize(450, 250);
		JTextArea J8 = new JTextArea("本计算器是东北大学Java课程实验课成果\n\n"
				+ "除了UI上的按钮输入，本程序还支持键盘输入，但要求输入符号为半角符号\n\n"
				+ "使用C键可以清空输入区\n\n"
				+ "键盘上的等号和回车都相当于程序界面的 '=' 号\n\n"
				+ "\n\n\t\t\tAcytoo@gmail.com");
		J8.setFont(new Font("微软雅黑",Font.PLAIN,13));
		J8.setEditable(false);
		J8.setLineWrap(true);
		dialog2.add(J8);
	
		helpItem.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent e){
					point = MyNewCal.this.getLocation();
					width = MyNewCal.this.getWidth();
					height = MyNewCal.this.getHeight();
					dialog2.setLocation(
					        point.x + width/2 - dialog2.getWidth()/2, 
					        point.y + height/2 - dialog2.getHeight()/2);
					dialog2.setVisible(true);
				}
			}
		);
		
		exitItem.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent e){
					setVisible(false);
					System.exit(0);
				}
			}
		);
		
		workspace.setLayout(new BorderLayout());
		InputArea = new JTextArea(8,10);
		InputArea.addKeyListener(new KeyMonitor());
		InputArea.setFont(new Font("微软雅黑",Font.BOLD,20));
		InputArea.setVisible(true);
		InputArea.setEditable(false);
		InputArea.setLineWrap(true);
		InputArea.setText("");
		workspace.add(InputArea,BorderLayout.NORTH);
		JPanel J2 = new JPanel();
		J2.setLayout(new GridLayout(6,4));
		J2.addKeyListener(new KeyMonitor());
		
		ButtonMonitor buttonMonitor = new ButtonMonitor();
		JButton[] button = new JButton[24];
		button[0] = new JButton("√");
		button[1] = new JButton("x^"); 
		button[2] = new JButton("1/x");
		button[3] = new JButton("C");
		button[4] = new JButton("(");
		button[5] = new JButton(")");
		button[6] = new JButton("<--");
		button[7] = new JButton("/");
		button[8] = new JButton("7");
		button[9] = new JButton("8");
		button[10] = new JButton("9");
		button[11] = new JButton("*");
		button[12] = new JButton("4");
		button[13] = new JButton("5");
		button[14] = new JButton("6");
		button[15] = new JButton("-");
		button[16] = new JButton("1");
		button[17] = new JButton("2");
		button[18] = new JButton("3");
		button[19] = new JButton("+");
		button[20] = new JButton("±");
		button[21] = new JButton("0");
		button[22] = new JButton(".");
		button[23] = new JButton("=");
		for(int i = 0;i < 24;i++)
		{
			button[i].addActionListener(buttonMonitor);
			button[i].addKeyListener(new KeyMonitor());
			button[i].setFont(new Font("微软雅黑",Font.PLAIN,20));
			J2.add(button[i]);
		}
		workspace.add(J2);
		
		historyspace.setLayout(new BorderLayout());
		JLabel J3 = new JLabel("历史记录");
		J3.setFont(new Font("微软雅黑",Font.BOLD,13));
		J3.addKeyListener(new KeyMonitor());
		historyspace.add(J3,BorderLayout.NORTH);
		OutputArea = new JTextArea();
		OutputArea.addKeyListener(new KeyMonitor());
		OutputArea.setFont(new Font("宋体",Font.BOLD,20));
		OutputArea.setEditable(false);
		OutputArea.setVisible(true);
		OutputArea.setLineWrap(true);
		OutputArea.setText("");
		JScrollPane OutputAreaScroll = new JScrollPane(OutputArea);
		OutputAreaScroll.addKeyListener(new KeyMonitor());
		historyspace.add(OutputAreaScroll,BorderLayout.CENTER);
		JButton J5 = new JButton("清除历史记录");
		J5.setFont(new Font("微软雅黑",Font.BOLD,13));
		J5.addKeyListener(new KeyMonitor());
		J5.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent e){
				OutputArea.setText("");
			}
		}
		);
		historyspace.add(J5,BorderLayout.SOUTH);
		setSize(750,600);
		setMinimumSize(new Dimension(400,300));
		setLocationRelativeTo(null);//显示在屏幕中央
		setVisible(true);
		point = MyNewCal.this.getLocation();//获得主窗体在屏幕的坐标
		width = MyNewCal.this.getWidth();
		height = MyNewCal.this.getHeight();
		dialog2.setLocation(
		        point.x + width/2 - dialog2.getWidth()/2, 
		        point.y + height/2 - dialog2.getHeight()/2);
		dialog2.setVisible(true);
	}
	//括号匹配函数,后期修改版，思路来自于编译原理编译器项目
	boolean Match(String s){
		int num = 0;
		for (int i=0; i<s.length(); ++i){
			if (s.charAt(i) == '(')
				num++;
			else if (s.charAt(i) == ')')
				num--;
			if (num < 0)
				return false;
		}
		if (num > 0)
			return false;
		return true;
	}
	
	//按钮点击输入
	
	class ButtonMonitor implements ActionListener{	
		public void actionPerformed(ActionEvent e){
			JButton J = (JButton)e.getSource();
			int ptr = 0;
			boolean flag3 = false;
			double t;
			String res = "";
			if(flag) 
			{
				InputArea.setText("");
				flag = false;
			}
						
			if(J.getText() == "<--") 
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals("")){}
				else 
				{
					InputTemp = InputTemp.substring(0,InputTemp.length()-1);
					InputArea.setText(InputTemp);
				}
			}
			else if(J.getText() == "=") 
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals("")){}
				else
				{
					if(InputTemp.charAt(InputTemp.length()-1) == '/'||
					   InputTemp.charAt(InputTemp.length()-1) == '*'||
					   InputTemp.charAt(InputTemp.length()-1) == '-'||
					   InputTemp.charAt(InputTemp.length()-1) == '+'||
					   InputTemp.charAt(InputTemp.length()-1) == '.'||
					   InputTemp.charAt(InputTemp.length()-1) == '(')
					{
						JOptionPane.showMessageDialog(null, "表达式存在错误！", "Error",JOptionPane.WARNING_MESSAGE);
						OutputTemp = OutputArea.getText();
						OutputTemp = OutputTemp + "\n" + InputArea.getText() + " = " + "ERROR!";
						OutputArea.setText(OutputTemp);
					}
					else
					{
						OutputTemp = OutputArea.getText();
						OutputTemp = OutputTemp + "\n" + InputArea.getText() + " = ";
						OutputArea.setText(OutputTemp);
						s = InputArea.getText();
						s += '=';//'='为s结尾标志
						if(!Match(s))
						{
							JOptionPane.showMessageDialog(null, "表达式括号不匹配！", "Error",JOptionPane.WARNING_MESSAGE);
							OutputTemp = OutputArea.getText();
							OutputTemp += "ERROR!";
							OutputArea.setText(OutputTemp);
						}
						else
						{
							res = new Expression(s).ytOthFun();//每次按下=把计算结果显示在InputArea区域并且把添加一条历史记录到OutputArea区域
							if(res == "ERROR!")
							{
								InputTemp = InputArea.getText();
								InputTemp += " = " + res;
								InputArea.setText(InputTemp);
								OutputTemp = OutputArea.getText();
								OutputTemp += res;
								OutputArea.setText(OutputTemp);
								flag = true;
							}
							else
							{
								t = Double.valueOf(res);
								if( res.charAt(res.length()-1) == '0' && res.charAt(res.length()-2) == '.')
								{
									InputTemp = InputArea.getText();
									InputTemp += " = " + (int)t;
									InputArea.setText(InputTemp);
									OutputTemp = OutputArea.getText();
									OutputTemp += (int)t;
									OutputArea.setText(OutputTemp);
								}
								else
								{
									InputTemp = InputArea.getText();
									InputTemp += " = " + t;
									InputArea.setText(InputTemp);
									OutputTemp = OutputArea.getText();
									OutputTemp += t;
									OutputArea.setText(OutputTemp);
								}
								flag = true;
								s = "";
							}
						}
					}
				}
			}
			else if(J.getText() == "±")//按下的是负号
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals("")){}
				else
				{
					ptr = InputTemp.length()-1;
					if(InputTemp.charAt(ptr)=='('||
					   InputTemp.charAt(ptr)==')'||
					   InputTemp.charAt(ptr)=='/'||
					   InputTemp.charAt(ptr)=='*'||
					   InputTemp.charAt(ptr)=='-'||
					   InputTemp.charAt(ptr)=='+'||
					   InputTemp.charAt(ptr)=='.'){}
					else
					{
						while( (InputTemp.charAt(ptr)=='0'||
								InputTemp.charAt(ptr)=='1'||
								InputTemp.charAt(ptr)=='2'||
								InputTemp.charAt(ptr)=='3'||
								InputTemp.charAt(ptr)=='4'||
								InputTemp.charAt(ptr)=='5'||
								InputTemp.charAt(ptr)=='6'||
								InputTemp.charAt(ptr)=='7'||
								InputTemp.charAt(ptr)=='8'||
								InputTemp.charAt(ptr)=='9'||
								InputTemp.charAt(ptr)=='.') && ptr >=0 )
					{
						ptr--;
						if( ptr < 0){break;}
					}
					InputTemp = InputTemp.substring(0, ptr+1) + "(" + "-" + InputTemp.substring(ptr+1, InputTemp.length()) + ")";
					InputArea.setText(InputTemp);
					}
				}
			}
			else if(J.getText() == "/"||
					J.getText() == "*"||
					J.getText() == "-"||
					J.getText() == "+")
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals("")){}
				else
				{
					if(InputTemp.charAt(InputTemp.length()-1) == '/'||
					   InputTemp.charAt(InputTemp.length()-1) == '*'||
					   InputTemp.charAt(InputTemp.length()-1) == '-'||
					   InputTemp.charAt(InputTemp.length()-1) == '+'||
					   InputTemp.charAt(InputTemp.length()-1) == '('||
					   InputTemp.charAt(InputTemp.length()-1) == '.')
					{}
					else
					{
						InputTemp = InputArea.getText();
						InputTemp += J.getText();
						InputArea.setText(InputTemp);
					}
				}
			}
			else if(J.getText() == ".")
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals("")){}
				else
				{
					ptr = InputTemp.length()-1;
					while( (InputTemp.charAt(ptr)=='0'||
							InputTemp.charAt(ptr)=='1'||
							InputTemp.charAt(ptr)=='2'||
							InputTemp.charAt(ptr)=='3'||
							InputTemp.charAt(ptr)=='4'||
							InputTemp.charAt(ptr)=='5'||
							InputTemp.charAt(ptr)=='6'||
							InputTemp.charAt(ptr)=='7'||
							InputTemp.charAt(ptr)=='8'||
							InputTemp.charAt(ptr)=='9') && ptr >= 0)
							{
								ptr--;
								if(ptr >= 0)
								{
									if(InputTemp.charAt(ptr)=='.')
									{
										flag3 = true;
										break;
									}
								}
								else
									break;
							}	
						
					InputTemp = InputArea.getText();
					if(InputTemp.charAt(InputTemp.length()-1) == '/'||
					   InputTemp.charAt(InputTemp.length()-1) == '*'||
					   InputTemp.charAt(InputTemp.length()-1) == '-'||
					   InputTemp.charAt(InputTemp.length()-1) == '+'||
					   InputTemp.charAt(InputTemp.length()-1) == '('||
					   InputTemp.charAt(InputTemp.length()-1) == ')'||
					   InputTemp.charAt(InputTemp.length()-1) == '.'||flag3)
					{}
					else
					{
						InputTemp = InputArea.getText();
						InputTemp += J.getText();
						InputArea.setText(InputTemp);
					}
				}
			}
			else if(J.getText() == "(")
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals(""))
				{
					InputArea.setText("(");
				}
				else
				{
					if(InputTemp.charAt(InputTemp.length()-1) == '0' ||
					   InputTemp.charAt(InputTemp.length()-1) == '1' ||
					   InputTemp.charAt(InputTemp.length()-1) == '2' ||
					   InputTemp.charAt(InputTemp.length()-1) == '3' ||
					   InputTemp.charAt(InputTemp.length()-1) == '4' ||
					   InputTemp.charAt(InputTemp.length()-1) == '5' ||
					   InputTemp.charAt(InputTemp.length()-1) == '6' ||
					   InputTemp.charAt(InputTemp.length()-1) == '7' ||
					   InputTemp.charAt(InputTemp.length()-1) == '8' ||
					   InputTemp.charAt(InputTemp.length()-1) == '9' ||
					   InputTemp.charAt(InputTemp.length()-1) == '.' ||
					   InputTemp.charAt(InputTemp.length()-1) == ')' ){}
					else
					{
						InputTemp += J.getText();
						InputArea.setText(InputTemp);
					}
				}
			}
			else if(J.getText() == ")")
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals("")){}
				else
				{
					if(InputTemp.charAt(InputTemp.length()-1) == '.' ||
					   InputTemp.charAt(InputTemp.length()-1) == '/' ||
					   InputTemp.charAt(InputTemp.length()-1) == '*' ||
					   InputTemp.charAt(InputTemp.length()-1) == '-' ||
					   InputTemp.charAt(InputTemp.length()-1) == '+' ||
					   InputTemp.charAt(InputTemp.length()-1) == '(' ||
					   InputTemp == ""){}
					else
					{
						InputTemp += J.getText();
						InputArea.setText(InputTemp);
					}
				}
			}
			else if(J.getText() == "√")
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals("")){}
				else
				{
					ptr = InputTemp.length()-1;
					if(InputTemp.charAt(ptr)=='('||
					   InputTemp.charAt(ptr)==')'||
					   InputTemp.charAt(ptr)=='/'||
					   InputTemp.charAt(ptr)=='*'||
					   InputTemp.charAt(ptr)=='-'||
					   InputTemp.charAt(ptr)=='+'||
					   InputTemp.charAt(ptr)=='.'){}
					else
					{
						while( (InputTemp.charAt(ptr)=='0'||
								InputTemp.charAt(ptr)=='1'||
								InputTemp.charAt(ptr)=='2'||
								InputTemp.charAt(ptr)=='3'||
								InputTemp.charAt(ptr)=='4'||
								InputTemp.charAt(ptr)=='5'||
								InputTemp.charAt(ptr)=='6'||
								InputTemp.charAt(ptr)=='7'||
								InputTemp.charAt(ptr)=='8'||
								InputTemp.charAt(ptr)=='9'||
								InputTemp.charAt(ptr)=='.') && ptr >=0 )
					{
						ptr--;
						if( ptr < 0){break;}
					}
					InputTemp = InputTemp.substring(0, ptr+1) + "√" + "(" + InputTemp.substring(ptr+1, InputTemp.length()) + ")";
					InputArea.setText(InputTemp);
					}
				}
			}
			else if(J.getText() == "x^")
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals("")){}
				else
				{
					ptr = InputTemp.length()-1;
					if(InputTemp.charAt(ptr)=='('||
					   InputTemp.charAt(ptr)==')'||
					   InputTemp.charAt(ptr)=='/'||
					   InputTemp.charAt(ptr)=='*'||
					   InputTemp.charAt(ptr)=='-'||
					   InputTemp.charAt(ptr)=='+'||
					   InputTemp.charAt(ptr)=='.'){}
					else
					{
						while( (InputTemp.charAt(ptr)=='0'||
								InputTemp.charAt(ptr)=='1'||
								InputTemp.charAt(ptr)=='2'||
								InputTemp.charAt(ptr)=='3'||
								InputTemp.charAt(ptr)=='4'||
								InputTemp.charAt(ptr)=='5'||
								InputTemp.charAt(ptr)=='6'||
								InputTemp.charAt(ptr)=='7'||
								InputTemp.charAt(ptr)=='8'||
								InputTemp.charAt(ptr)=='9'||
								InputTemp.charAt(ptr)=='.') && ptr >=0 )
					{
						ptr--;
						if( ptr < 0){break;}
					}
					InputTemp = InputTemp + "^";
					InputArea.setText(InputTemp);
					}
				}
			}
			else if(J.getText() == "1/x")
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals("")){}
				else
				{
					ptr = InputTemp.length()-1;
					if(InputTemp.charAt(ptr)=='('||
					   InputTemp.charAt(ptr)==')'||
					   InputTemp.charAt(ptr)=='/'||
					   InputTemp.charAt(ptr)=='*'||
					   InputTemp.charAt(ptr)=='-'||
					   InputTemp.charAt(ptr)=='+'||
					   InputTemp.charAt(ptr)=='.'){}
					else
					{
						while( (InputTemp.charAt(ptr)=='0'||
								InputTemp.charAt(ptr)=='1'||
								InputTemp.charAt(ptr)=='2'||
								InputTemp.charAt(ptr)=='3'||
								InputTemp.charAt(ptr)=='4'||
								InputTemp.charAt(ptr)=='5'||
								InputTemp.charAt(ptr)=='6'||
								InputTemp.charAt(ptr)=='7'||
								InputTemp.charAt(ptr)=='8'||
								InputTemp.charAt(ptr)=='9'||
								InputTemp.charAt(ptr)=='.') && ptr >=0 )
					{
						ptr--;
						if( ptr < 0){break;}
					}
						InputTemp = InputTemp.substring(0, ptr+1) + "(1/" + InputTemp.substring(ptr+1, InputTemp.length()) + ")";
						InputArea.setText(InputTemp);
					}
				}
			}
			else if(J.getText() == "C")
			{
				InputArea.setText("");
				s = "";
			}
			else
			{
				InputTemp = InputArea.getText();
				if(InputTemp==null || InputTemp.equals(""))
				{
					InputTemp += J.getText();
					InputArea.setText(InputTemp);
				}
				else
				{
					ptr = InputTemp.length()-1;
					if(InputTemp.charAt(ptr) == ')'){}
					else
					{
						InputTemp += J.getText();
						InputArea.setText(InputTemp);
					}
				}
			}
		}
	}
	//键盘输入
	class KeyMonitor extends KeyAdapter{
		private int ptr = 0;
		private boolean flag3 = false;
		double t ;
		String res = "";
		public void keyReleased(KeyEvent e){
			int key = e.getKeyCode();
			if(flag) 
			{
				InputArea.setText("");
				flag = false;
			}
			if(!e.isShiftDown())
			{
				if(key == KeyEvent.VK_BACK_SPACE)
				{
					InputTemp = InputArea.getText();
					if(InputTemp==null || InputTemp.equals("")){}
					else 
					{
						InputTemp = InputTemp.substring(0,InputTemp.length()-1);
						InputArea.setText(InputTemp);
					}
				}
				if(key == KeyEvent.VK_EQUALS || key == KeyEvent.VK_ENTER)
				{
					InputTemp = InputArea.getText();
					if(InputTemp==null || InputTemp.equals("")){}
					else
					{
						if(InputTemp.charAt(InputTemp.length()-1) == '/'||
						   InputTemp.charAt(InputTemp.length()-1) == '*'||
						   InputTemp.charAt(InputTemp.length()-1) == '-'||
						   InputTemp.charAt(InputTemp.length()-1) == '+'||
						   InputTemp.charAt(InputTemp.length()-1) == '.'||
						   InputTemp.charAt(InputTemp.length()-1) == '(')
						{
							JOptionPane.showMessageDialog(null, "表达式存在错误！", "Error",JOptionPane.WARNING_MESSAGE);
							OutputTemp = OutputArea.getText();
							OutputTemp = OutputTemp + "\n" + InputArea.getText() + " = " + "ERROR!";
							OutputArea.setText(OutputTemp);
						}
						else
						{
							OutputTemp = OutputArea.getText();
							OutputTemp = OutputTemp + "\n" + InputArea.getText() + " = ";
							OutputArea.setText(OutputTemp);
							s = InputArea.getText();
							s += '=';//'='为s结尾标志
							if(!Match(s))
							{
								JOptionPane.showMessageDialog(null, "表达式括号不匹配！", "Error",JOptionPane.WARNING_MESSAGE);
								OutputTemp = OutputArea.getText();
								OutputTemp += "ERROR!";
								OutputArea.setText(OutputTemp);
							}
							else
							{
								res = new Expression(s).ytOthFun();//每次按下=把计算结果显示在InputArea区域并且把添加一条历史记录到OutputArea区域
								if(res == "ERROR!")
								{
									InputTemp = InputArea.getText();
									InputTemp += " = " + res;
									InputArea.setText(InputTemp);
									OutputTemp = OutputArea.getText();
									OutputTemp += res;
									OutputArea.setText(OutputTemp);
									flag = true;
								}
								else
								{
									t = Double.valueOf(res);
									if( res.charAt(res.length()-1) == '0' && res.charAt(res.length()-2) == '.')
									{
										InputTemp = InputArea.getText();
										InputTemp += " = " + (int)t;
										InputArea.setText(InputTemp);
										OutputTemp = OutputArea.getText();
										OutputTemp += (int)t;
										OutputArea.setText(OutputTemp);
									}
									else
									{
										InputTemp = InputArea.getText();
										InputTemp += " = " + t;
										InputArea.setText(InputTemp);
										OutputTemp = OutputArea.getText();
										OutputTemp += t;
										OutputArea.setText(OutputTemp);
									}
									flag = true;
									s = "";
								}
							}
						}
					}
				}
				if( key == KeyEvent.VK_0 ||
					key == KeyEvent.VK_1 ||
					key == KeyEvent.VK_2 ||
					key == KeyEvent.VK_3 ||
					key == KeyEvent.VK_4 ||
					key == KeyEvent.VK_5 ||
					key == KeyEvent.VK_6 ||
					key == KeyEvent.VK_7 ||
					key == KeyEvent.VK_8 ||
					key == KeyEvent.VK_9 ||
					(key >= 96 && key <= 105) )
				{
					if(key >= 96 && key <= 105)
					{
						InputTemp = InputArea.getText();
						InputTemp += (char)(key-48);
						InputArea.setText(InputTemp);
					}
					else
					{
						InputTemp = InputArea.getText();
						InputTemp += (char)key;
						InputArea.setText(InputTemp);
					}
				}
				if(key == KeyEvent.VK_PERIOD || key == 110)   //小数点
				{
					InputTemp = InputArea.getText();
					if(InputTemp==null || InputTemp.equals("")){}
					else
					{
						InputTemp = InputArea.getText();
						ptr = InputTemp.length()-1;
						while( (InputTemp.charAt(ptr)=='0'||
								InputTemp.charAt(ptr)=='1'||
								InputTemp.charAt(ptr)=='2'||
								InputTemp.charAt(ptr)=='3'||
								InputTemp.charAt(ptr)=='4'||
								InputTemp.charAt(ptr)=='5'||
								InputTemp.charAt(ptr)=='6'||
								InputTemp.charAt(ptr)=='7'||
								InputTemp.charAt(ptr)=='8'||
								InputTemp.charAt(ptr)=='9') && ptr >= 0)
								{
									ptr--;
									if(ptr >= 0)
									{
										if(InputTemp.charAt(ptr)=='.')
										{
											flag3 = true;
											break;
										}
									}
									else
										break;
								}	
				
						InputTemp = InputArea.getText();
						if(InputTemp.charAt(InputTemp.length()-1) == '/'||
						   InputTemp.charAt(InputTemp.length()-1) == '*'||
						   InputTemp.charAt(InputTemp.length()-1) == '-'||
						   InputTemp.charAt(InputTemp.length()-1) == '+'||
						   InputTemp.charAt(InputTemp.length()-1) == '('||
						   InputTemp.charAt(InputTemp.length()-1) == ')'||
						   InputTemp.charAt(InputTemp.length()-1) == '.'||flag3)
						{}
						else
						{
							InputTemp = InputArea.getText();
							if(key == 110)
							{
								InputTemp += (char)(key - 64);
							}
							else
							{
								InputTemp += (char)key;
							}
							InputArea.setText(InputTemp);
						}
					}
				}
				if( key == 106 || key == 107 || key == 109 || key == 111 || key == 45 || key == 47 )//小键盘上的加减乘除
				{																//以及大键盘上的减号和除号(不依赖组合键)
					InputTemp = InputArea.getText();
					if(InputTemp==null || InputTemp.equals("")){}
					else
					{
						if(InputTemp.charAt(InputTemp.length()-1) == '/'||
						   InputTemp.charAt(InputTemp.length()-1) == '*'||
						   InputTemp.charAt(InputTemp.length()-1) == '-'||
						   InputTemp.charAt(InputTemp.length()-1) == '+'||
						   InputTemp.charAt(InputTemp.length()-1) == '('||
						   InputTemp.charAt(InputTemp.length()-1) == '.')
						{}//不能在这些符号后面输入运算符
						else
						{
							InputTemp = InputArea.getText();
							if(key == 45 || key == 47 )
							{
								InputTemp += (char)key;
							}
							else
							{
								InputTemp += (char)(key-64);
							}
							InputArea.setText(InputTemp);
						}
					}
				}
				if(key == KeyEvent.VK_DELETE) //点击此按钮清空InputArea和s)
				{
					InputArea.setText("");
					s = "";
				}
				else{}
			}
			else
			{
				if(key == KeyEvent.VK_EQUALS) //组合键加号'shift' + '='
				{
					InputTemp = InputArea.getText();
					if(InputTemp==null || InputTemp.equals("")){}
					else
					{
						if(InputTemp.charAt(InputTemp.length()-1) == '/'||
						   InputTemp.charAt(InputTemp.length()-1) == '*'||
						   InputTemp.charAt(InputTemp.length()-1) == '-'||
						   InputTemp.charAt(InputTemp.length()-1) == '+'||
						   InputTemp.charAt(InputTemp.length()-1) == '('||
						   InputTemp.charAt(InputTemp.length()-1) == '.')
						{}//不能在这些符号后面输入运算符
						else
						{
							InputTemp = InputArea.getText();
							InputTemp += '+';
							InputArea.setText(InputTemp); 
						}
					}
				}
				if(key == KeyEvent.VK_9)//组合键左括号'shift' + '9'
				{
					InputTemp = InputArea.getText();
					if(InputTemp==null || InputTemp.equals(""))
					{
						InputArea.setText("(");
					}
					else
					{
						if(InputTemp.charAt(InputTemp.length()-1) == '0' ||
						   InputTemp.charAt(InputTemp.length()-1) == '1' ||
						   InputTemp.charAt(InputTemp.length()-1) == '2' ||
						   InputTemp.charAt(InputTemp.length()-1) == '3' ||
						   InputTemp.charAt(InputTemp.length()-1) == '4' ||
						   InputTemp.charAt(InputTemp.length()-1) == '5' ||
						   InputTemp.charAt(InputTemp.length()-1) == '6' ||
						   InputTemp.charAt(InputTemp.length()-1) == '7' ||
						   InputTemp.charAt(InputTemp.length()-1) == '8' ||
						   InputTemp.charAt(InputTemp.length()-1) == '9' ||
						   InputTemp.charAt(InputTemp.length()-1) == '.' ||
						   InputTemp.charAt(InputTemp.length()-1) == ')' ){}
						else
						{
							InputTemp += '(';
							InputArea.setText(InputTemp);
						}
					}
				}
				if(key == KeyEvent.VK_0)//组合键右括号'shift' + '0'
				{
					InputTemp = InputArea.getText();
					if(InputTemp==null || InputTemp.equals("")){}
					else
					{
						if(InputTemp.charAt(InputTemp.length()-1) == '.' ||
						   InputTemp.charAt(InputTemp.length()-1) == '/' ||
						   InputTemp.charAt(InputTemp.length()-1) == '*' ||
						   InputTemp.charAt(InputTemp.length()-1) == '-' ||
						   InputTemp.charAt(InputTemp.length()-1) == '+' ||
						   InputTemp.charAt(InputTemp.length()-1) == '(' ||
						   InputTemp == ""){}
						else
						{
							InputTemp += ')';
							InputArea.setText(InputTemp);
						}
					}
				}
			}
		}
	}
}	
