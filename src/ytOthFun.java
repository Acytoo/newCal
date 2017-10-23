import javax.swing.JOptionPane;

class NumberStack{
	final int STACK_INIT_SIZE = 10;
	final int STACKINCREASE = 5;
	
	private double[] Array; // ����ջ
	private int ptr = 0;    //ָ��ջ��Ԫ�ص���һ��Ԫ�ص������±�
	
	public NumberStack(){
		Array = new double[STACK_INIT_SIZE];
		for(int i = 0;i < STACK_INIT_SIZE;i++ )
		{
			Array[i] = 0;
		}
	}
	
	public void Push(double e){
		Array[ptr++] = e;
	}
	public double Pop(){
		if(ptr < 1)
		{
			JOptionPane.showMessageDialog(null, "ջΪ�գ�", "Error",JOptionPane.WARNING_MESSAGE);
			return 0;
		}
		else return Array[--ptr];
	}
	public double GetTop(){
		if(ptr < 1)
		{
			JOptionPane.showMessageDialog(null, "ջΪ�գ�", "Error",JOptionPane.WARNING_MESSAGE); 
			return 0;
		}
		else return Array[ptr-1];
	}
	public boolean EmptyStack(){
		if(ptr < 1) return true;
		else return false;
	}
}

class CharStack{
	final int STACK_INIT_SIZE = 40;
	final int STACKINCREASE = 10;
	
	private char[] Array; // �ַ�ջ
	private int ptr = 0;    //ָ��ջ��Ԫ�ص���һ��Ԫ�ص������±�
	
	public CharStack(){
		Array = new char[STACK_INIT_SIZE];
		for(int i = 0;i < STACK_INIT_SIZE;i++ )
		{
			Array[i] = '\0';
		}
	}
	
	public void Push(char e){
		Array[ptr++] = e;
	}
	public char Pop(){
		if(ptr < 1)
		{
			JOptionPane.showMessageDialog(null, "ջΪ�գ�", "Error",JOptionPane.WARNING_MESSAGE); 
			return '\0';
		}
		else return Array[--ptr];
	}
	public char GetTop(){
		if(ptr < 1)
		{
			JOptionPane.showMessageDialog(null, "ջΪ�գ�", "Error",JOptionPane.WARNING_MESSAGE); 
			return '\0';
		}
		else return Array[ptr-1];
	}
	public boolean EmptyStack(){
		if(ptr < 1) return true;//Ϊ��
		else return false;
	}
}


class Expression{
	String s = "";
	public Expression(String s){
		this.s = s;
	}
	
	public boolean Precede(char a,char b)//aΪ����ջջ��Ԫ��,bΪ�������Ԫ��
	{
	    boolean i = true;//i=true��ջ,i=false�����������Լ����������м���
	    if(( a == '+' || a == '-' ) && ( b == '*' || b == '/' )) i = true;
	    if(( a == '+' || a == '-' ) && ( b == '+' || b == '-' )) i = false;
	    if(( a == '*' || a == '/' ) && ( b == '*' || b == '/' )) i = false;
	    if(( a == '*' || a == '/' ) && ( b == '+' || b == '-' )) i = false;
	    if(( a == '+' || a == '-' || a == '*' || a == '/') && b == '^' ) i=true;
	    if(a == '^' && ( b == '+' || b == '-' || b == '*' || b == '/' )) i=false;
	    if( a == '^' && b == '^' ) i = false;
	    if( a == '(' ) i = true;
	    return i;
	}
	
	public double Decimal(int n)//����10��n�η�
	{
		double s = 1.0;
		if( n >= 0)
		{
			for(int i = 0; i < n; i++)
			{
				//s *= 10; ע��ֱ����˻���ɾ�����ʧ�����������ַ���,���������漰����������Ķ�����ֱ�Ӽ���
				s = Arith.mul(s, 10);
			}
		}
		else
		{
			for(int i = 0; i < -n; i++)
			{
				s = Arith.mul(s, 0.1);
			}
		}
		return s;
	}
	
	public String ytOthFun()
	{
	    String temp = s , res = "";
	    String Numbuff = "";//��������������ַ���С���㣬���԰Ѷ����������ַ�ת������Ӧ������
	    char c,d,e = '\0';
	    int ptr = 0,ptrtemp = 0;
	    int flag = 0;
	    double a,b,j = 0;
	    double Numtemp = 0;
	    boolean l;
	    CharStack S1 = new CharStack();	//S1Ϊ������ջ��S2Ϊ������ջ
	    NumberStack S2 = new NumberStack();
	    
	    if(temp == "") 
	    {
	    	JOptionPane.showMessageDialog(null, "���ʽΪ�գ�", "Error",JOptionPane.WARNING_MESSAGE);
	    	return "ERROR!";
	    }
	    c=temp.charAt(ptr++);
	    while(c != '=')//��������String s ��'='����
	    {
	         if(c>=48&&c<=57) //��������
	         {
	        	 ptrtemp = ptr;
	        	 Numbuff += c;
	        	 //�ж������ַ�����һ���ַ��Ƿ�Ϊ�����ַ�����С����
	        	 while( ( temp.charAt(ptrtemp) >= 48 && temp.charAt(ptrtemp ) <= 57) || temp.charAt(ptrtemp) == '.')
	        	 {
	        		 Numbuff += temp.charAt(ptrtemp);
	        		 if(temp.charAt(ptrtemp) == '.')
	        		 {
	        			 flag = Numbuff.length()-1;//��¼С������Numbuff�е�λ��
	        		 }
	        		 ptrtemp++;
	        	 }
	        	 if(Numbuff.length() > 1)//temp�в��ǵ��������ַ�
	        	 {
	        		 if(flag != 0) //����С����
	        		 {
	        			 for(int i = 0; i < flag ;i++)//������������
	        			 {
	        				 //Numtemp += ( (int)Numbuff.charAt(i) - 48 ) * Decimal(flag-1-i);//�˴��������޸�,������������
	        				 Numtemp = Arith.add(Numtemp, Arith.mul( ( (int)Numbuff.charAt(i) - 48 ) ,Decimal(flag-1-i) ) );
	        			 }
	        			 for(int i = flag+1;i < Numbuff.length();i++)//����С������
	        			 {
	        				 //Numtemp += ( (int)Numbuff.charAt(i) - 48 ) * Decimal(flag-i);
	        				 Numtemp = Arith.add(Numtemp, Arith.mul( ( (int)Numbuff.charAt(i) - 48 ) ,Decimal(flag-i) ) );
	        			 }
	        		 }
	        		 else
	        		 {
	        			 for(int i = 0;i < Numbuff.length(); i++)
	        			 {
	        				 //Numtemp += ( (int)Numbuff.charAt(i) - 48 ) * Decimal(Numbuff.length()-1-i);
	        				 Numtemp = Arith.add(Numtemp, Arith.mul( ( (int)Numbuff.charAt(i) - 48 ) ,Decimal(Numbuff.length()-1-i) ) );
	        			 }
	        		 }
	        	 }
	        	 else
	        	 {
	        		 //Numtemp = ( (int)Numbuff.charAt(0) - 48 );
	        		 Numtemp = Arith.sub((int)Numbuff.charAt(0) ,48 );
	        	 }
	        	 S2.Push(Numtemp);//�ѵõ��ĸ�����ѹ�������ջ
	        	 //ע��ÿ���жϴ���������,������Щ����Ҫ����Ϊ��ʼֵ
	        	 Numtemp = 0;
	        	 flag = 0;
	        	 Numbuff = "";
	        	 ptr = ptrtemp;//������һ�������ַ����ǵð�ptr�Ƶ������ַ�������һ��λ��
	         }
	         if(c=='(') //����Ϊ������
	         {
	        	 ptrtemp = ptr;
	        	 if(temp.charAt(ptrtemp) == '-')//��������ź���һ������Ϊ'-'����ô�������һ���Ǹ��ţ�������Ҫ��ȡ�����еĸ���
	        	 {
	        		 ptrtemp++;
	        		 while(temp.charAt(ptrtemp) != ')')
	        		 {
	        			 Numbuff += temp.charAt(ptrtemp);
	        			 if(temp.charAt(ptrtemp) == '.')
	        			 {
	        				 flag = Numbuff.length()-1;//��¼С������Numbuff�е�λ��
	        			 }
	        			 ptrtemp++;
	        		 }
	        		 if(Numbuff.length() > 1)//temp�в��ǵ��������ַ�
		        	 {
		        		 if(flag != 0) //����С����
		        		 {
		        			 for(int i = 0; i < flag ;i++)//������������
		        			 {
		        				 //Numtemp += ( (int)Numbuff.charAt(i) - 48 ) * Decimal(flag-1-i);
		        				 Numtemp = Arith.add(Numtemp, Arith.mul(( (int)Numbuff.charAt(i) - 48 ) , Decimal(flag-1-i)));
		        			 }
		        			 for(int i = flag+1;i < Numbuff.length();i++)//����С������
		        			 {
		        				 //Numtemp += ( (int)Numbuff.charAt(i) - 48 ) * Decimal(flag-i);
		        				 Numtemp = Arith.add(Numtemp, Arith.mul(( (int)Numbuff.charAt(i) - 48 ) , Decimal(flag-i)));
		        			 }
		        		 }
		        		 else
		        		 {
		        			 for(int i = 0;i < Numbuff.length(); i++)
		        			 {
		        				 //Numtemp += ( (int)Numbuff.charAt(i) - 48 ) * Decimal(Numbuff.length()-1-i);
		        				 Numtemp = Arith.add(Numtemp, Arith.mul(( (int)Numbuff.charAt(i) - 48 ) , Decimal(Numbuff.length()-1-i)));
		        			 }
		        		 }
		        	 }
		        	 else
		        	 {
		        		 Numtemp = ( (int)Numbuff.charAt(0) - 48 );
		        	 }
	        		 Numtemp = 0 - Numtemp;//ȡ�෴��
		        	 S2.Push(Numtemp);//�ѵõ��ĸ�����ѹ�������ջ
		        	 ptr += Numbuff.length() + 2;//������һ�������ַ����ǵð�ptr�Ƶ������ַ�������һ��λ��
		        	 //ע��ÿ���жϴ���������,������Щ����Ҫ����Ϊ��ʼֵ
		        	 Numtemp = 0;
		        	 flag = 0;
		        	 Numbuff = "";
	        	 }
	        	 else
	        	 {
	        		 S1.Push(c);
	        	 }
	         }
	         if(c==')')//����Ϊ������
	         {
	             if(!S1.EmptyStack()) e = S1.GetTop();
	             if(e == '(') { e = S1.Pop(); }
	             while(e!='(')
	             {
	                 a = S2.Pop();
	                 b = S2.Pop();
	                 d = S1.Pop();
	                 if(d=='+') j= Arith.add(b, a);
	                 if(d=='-') j= Arith.sub(b, a);
	                 if(d=='*') j= Arith.mul(b, a);
	                 if(d=='/')
	                 {
	                     if(a != 0)  j = b/a;//j= Arith.div(b, a, 30);//����ȡ30
	                     else 
	                     {
	                    	 JOptionPane.showMessageDialog(null, "������̳��ֳ���Ϊ��Ĵ���", "Error",JOptionPane.WARNING_MESSAGE);
	                    	 return "ERROR!";
	                     }
	                 }
	                 if(d== '^') j = Math.pow(b, a);
	                 S2.Push(j);
	                 if(!S1.EmptyStack()) e = S1.GetTop();//ֱ������������
	                 if(e=='(') e = S1.Pop();
	             }
	         }
	         if(c=='+'||c=='-'||c=='*'||c=='/'||c=='^')
	         {
	             if(S1.EmptyStack())  S1.Push(c);
	             else
	             {
	                 e = S1.GetTop();
	                 l=Precede(e,c);
	                 if(!l)
	                 {
	                     a = S2.Pop();
	                     b = S2.Pop();
	                     d = S1.Pop();
	                     if(d=='+') j= Arith.add(b, a);
		                 if(d=='-') j= Arith.sub(b, a);
		                 if(d=='*') j= Arith.mul(b, a);
		                 if(d=='/')
		                 {
		                     if(a != 0) j = b/a;//j= Arith.div(b, a, 30);//����ȡ30
		                     else 
		                     {
		                    	 JOptionPane.showMessageDialog(null, "������̳��ֳ���Ϊ��Ĵ���", "Error",JOptionPane.WARNING_MESSAGE);
		                    	 return "ERROR!";
		                     }
		                 }
		                 if(d== '^') j = Math.pow(b, a);
	                     S1.Push(c);
	                     S2.Push(j);
	                 }
	                 else S1.Push(c);
	             }
	         }
	         if(c == '��')
	         {
	        	 ptrtemp = ptr;
	        	 ptrtemp++;
        		 while(temp.charAt(ptrtemp) != ')')
        		 {
        			 Numbuff += temp.charAt(ptrtemp);
        			 if(temp.charAt(ptrtemp) == '.')
        			 {
        				 flag = Numbuff.length()-1;//��¼С������Numbuff�е�λ��
        			 }
        			 ptrtemp++;
        		 }
        		 if(Numbuff.length() > 1)//temp�в��ǵ��������ַ�
	        	 {
	        		 if(flag != 0) //����С����
	        		 {
	        			 for(int i = 0; i < flag ;i++)//������������
	        			 {
	        				 //Numtemp += ( (int)Numbuff.charAt(i) - 48 ) * Decimal(flag-1-i);
	        				 Numtemp = Arith.add(Numtemp, Arith.mul(( (int)Numbuff.charAt(i) - 48 ) , Decimal(flag-1-i)));
	        			 }
	        			 for(int i = flag+1;i < Numbuff.length();i++)//����С������
	        			 {
	        				 //Numtemp += ( (int)Numbuff.charAt(i) - 48 ) * Decimal(flag-i);
	        				 Numtemp = Arith.add(Numtemp, Arith.mul(( (int)Numbuff.charAt(i) - 48 ) , Decimal(flag-i)));
	        			 }
	        		 }
	        		 else
	        		 {
	        			 for(int i = 0;i < Numbuff.length(); i++)
	        			 {
	        				 //Numtemp += ( (int)Numbuff.charAt(i) - 48 ) * Decimal(Numbuff.length()-1-i);
	        				 Numtemp = Arith.add(Numtemp, Arith.mul(( (int)Numbuff.charAt(i) - 48 ) , Decimal(Numbuff.length()-1-i)));
	        			 }
	        		 }
	        	 }
	        	 else
	        	 {
	        		 Numtemp = ( (int)Numbuff.charAt(0) - 48 );
	        	 }
        		 Numtemp = Math.sqrt(Numtemp);//ȡ�෴��
	        	 S2.Push(Numtemp);//�ѵõ��ĸ�����ѹ�������ջ
	        	 ptr += Numbuff.length() + 2;//������һ�������ַ����ǵð�ptr�Ƶ������ַ�������һ��λ��
	        	 //ע��ÿ���жϴ���������,������Щ����Ҫ����Ϊ��ʼֵ
	        	 Numtemp = 0;
	        	 flag = 0;
	        	 Numbuff = "";
	         }
	         c = temp.charAt(ptr++);
	    }
	    
	    if(!S1.EmptyStack())
	    {
	        while(!S1.EmptyStack())
	        {
	             a = S2.Pop();
	             b = S2.Pop();
	             d = S1.Pop();
	             if(d=='+') j= Arith.add(b, a);
                 if(d=='-') j= Arith.sub(b, a);
                 if(d=='*') j= Arith.mul(b, a);
                 if(d=='/')
                 {
                     if(a != 0) j = b/a;//j= Arith.div(b, a, 30);//����ȡ30
                     else 
                     {
                    	 JOptionPane.showMessageDialog(null, "������̳��ֳ���Ϊ��Ĵ���", "Error",JOptionPane.WARNING_MESSAGE);
                    	 return "ERROR!";
                     }
                 }
                 if(d== '^') j = Math.pow(b, a);
	             S2.Push(j);
	        }
	    }
	    //��󷵻ؽ��
	    j = S2.Pop();
	    res += j;
	    return res;
	}
}
