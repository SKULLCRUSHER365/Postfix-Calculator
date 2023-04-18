public class Calculator{
	public int evaluatePostFix(String s) throws InvalidPostfixException{
		try{//I USE TRY BECAUSE IF EVER THERE IS A STACK THAT IS EMPTY AND ASK TO POP THAT MEANS THE EXPRESSION THAT INPUT IS NOT VALID SO BASICALLY THE CODE ITSELF CHEKS FOR THE EXPRESSION TO BE CORRECT
			MyStack stack = new MyStack();

			int i=0;
			int l=0;
			int m=0;
			while(i<s.length()){
				switch(s.charAt(i)){
				case ' ':
					i++;
					break;
				case '+':
					 l = (int) stack.pop();
					 m = (int) stack.pop();
					stack.push(l+m);
					i++;
					break;
				case '-':
					 l = (int) stack.pop();
					 m = (int) stack.pop();
					stack.push(m-l);
					i++;
					break;
				case '*':
					 l = (int) stack.pop();
					 m = (int) stack.pop();
					stack.push(l*m);
					i++;
					break;
				default:
					String k="";
					while(s.charAt(i)!=' '&&i<s.length()&&s.charAt(i)!='+'&&s.charAt(i)!='-'&&s.charAt(i)!='*'){//THIS LOOP IS BASICALLY TAKING CARE OF MULTIPLE DIGIT INTEGERS
						k=k+s.charAt(i);
						i++;
						if(i==s.length()){
						break;
						}
					}
					int n = Integer.parseInt(k);
					stack.push(n);
					break;
				}
				
			}
			if(stack.t!=1){//THIS IS FOR THAT IF AFTER ALL THE CALCULATION THERE SHOULD BE ONLY ONE TOTAL OF THE EXPRESSION SHOULD BE LEFT SO THE SIZE SHOULD BE 1 ANY OTHER SIZE MEANS OUR EXPRESSION IS NOT VALID
				throw new InvalidPostfixException("The expression is invalid");
				}
				return (int) stack.pop();
		}
			catch(EmptyStackException e){
				throw new InvalidPostfixException("The expression is invalid");
			}
	}


	static int k;
	public int basicconvert(String[] arr,int start,int end){//THIS FUNCTION CONVERT ANY EXPRESSION INTO POSTFIX IF IT DONT HAVE ANY BRACKETS IN THAT
		int i = start+1;
		int j;
		String helpstring1="";
		while(i<end){//THESE TWO WHILE LOOP IS TO CONSIDER THE PREFERENCE OF MULTIPLY OVER ADDITION AND SUBTRACTION
			
			if(arr[i]=="*"){
				helpstring1=arr[i-1]+" " + arr[i+1]+" "+ "*";
				arr[i-1]=helpstring1;
				for(j =i;j<k-2;j++){
					arr[j]=arr[j+2];

				}
				k=k-2;
				end=end-2;
				i=start+1;
			}
			else{
				i++;
			}
		}
		i=start+1;
		while(i<end){
			 if(arr[i]=="+"||arr[i]=="-"){
				helpstring1=arr[i-1]+" "+arr[i+1]+" "+arr[i];
				arr[i-1]=helpstring1;
				for(j=i;j<k-2;j++){
					arr[j]=arr[j+2];
				}
				k=k-2;
				end=end-2;
				i=start+1;

			}
			else{
				i++;
			}
		}
		return end;//AS THE END IS UPDATED THAT WHY I AM RETURNING THE END HERE AND UPDATE IT LATER IN THE FUNCTION


		}



	

	public String convertExpression(String s) throws InvalidExprException{
		try{
		String arr[]= new String[s.length()];
		arr=isexpressionvalid(s);//THIS FUNCTION IS CHECKING THE VALIDITY OF THE EXPRESSION AND RETURNING A ARRAY WITH PROPER SEPERATION BETWEEN THE NUMERS AND ALL SPACES REMOVED BASICALLY THE ARRAY IN THIS IS CONTAIN THE NUMBERS AND ALLOPERATIORS AND BRACKETS IN SAME SEQUENCE AS OF OUR EXPRESSION
		int start=0;
		int end=0;
		int i=0;
		int j=0;
		while(k!=1){
			if(arr[i]=="("){
				start=i;
				i++;
			
			}
			else if(arr[i]==")"){//AS THE BRACKET HAVE THE PREFERENCE OVER ALL THE EXPRESSION SO WE HAVE TO CONVERT THAT FIRST INTO THE POSTFIX SO THAT WHAT THIS ELSEIF IS DOING
				end=i;
				end = basicconvert(arr,start,end);//THIS CONVERTING THE EXPRESSION BETWEEN THE TWO BRACKETS INTO THE POSTFIXEXPRESSION AND AFTER TAHT THESE TWO FOR LOOP DELETING THAT TWO BRAKETS
				for(j=start;j<k;j++){
					arr[j]=arr[j+1];
				}
				k=k-1;
				for(j=end-1;j<k;j++){
					arr[j]=arr[j+1];
				}
				i=0;
				k=k-1;
			}
			else{
				boolean a=true;
				for(int test=0;test<k;test++){
				if(arr[test]=="("||arr[test]==")"){
				a=false;
				
				break;}
				}
				
				
				if(a){
					
					start=-1;
					end=k;
					basicconvert(arr,start,end);//THESE IS WHEN THERE IS NO BRAKETS LEFT
					
				}
				else{
				i++;
				}
			}
					
			
			}
		return arr[0];
		}
		
		catch(EmptyStackException e){
			throw new InvalidExprException("The expression is invalid");

		}

	}

	
	public String[] isexpressionvalid(String s) throws EmptyStackException, InvalidExprException{
		int i=0;
		k=0;
		int j=0;
		MyStack stack1 = new MyStack();
		MyStack stack2 = new MyStack();
		String updated[] = new String[s.length()];
		MyStack stack2check = new MyStack();
		while(i<s.length()){
			char a= s.charAt(i);
			switch(a){
				case '+':
					stack1.push("x");
					updated[j]="+";
					i++;
					j++;
					break;
				case '-':
					stack1.push("x");
					updated[j]="-";
					j++;
					i++;
					break;
				case '*':
					stack1.push("x");
					updated[j]="*";
					j++;
					i++;
					break;
				case ' ':
					i++;
					break;
				case '(':
					stack2.push("(");
					updated[j]="(";
					j++;
					i++;
					break;
				case ')':
					stack2.push(")");
					updated[j]=")";
					j++;
					i++;
					break;
				default:
					String k="";
					while(a!=' '&&a!='('&&a!=')'&&a!='+'&&a!='-'&&a!='*'){//THIS IS CONVERTING DOUBLE DIGIT NO IN SINGLE STRINGS
						k=k+s.charAt(i);
						i++;
						
						if(i==s.length()){
						break;
						}
						a=s.charAt(i);
					}
					stack1.push("y");
					updated[j]=k;
					j++;
					break;
				}


			}
		Object stackhelp;
		while(stack2.isEmpty()==false){
			stackhelp=stack2.pop();
			if(stackhelp==")"){
				stack2check.push('(');}
			else if(stackhelp=="("){
				stack2check.pop();}
			}

		boolean check =true;
		while(stack1.isEmpty()==false){
			stackhelp=stack1.pop();
			if((check == true&&stackhelp=="y")||(check==false&&stackhelp=="x")){
				check = !check;
			}
			else if((check == false&&stackhelp=="y")||(check == true&&stackhelp=="x")){
				throw new InvalidExprException("The expression is invalid");
				
		}
		}
		if(check==true){
			throw new InvalidExprException("The expression is invalid");
		
		}
		k=j;
		return updated;

				
		}

}




