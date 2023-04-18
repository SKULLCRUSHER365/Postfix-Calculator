public class MyStack implements StackInterface{//THERE IS VERY BASIC APPROCH WE ARE MAKED A ARRAY AND TAKING CARE OF THE LAST ENTRY INDEX AND RETURNING THING ACCORDINGLY
	public int t=0;//THIS IS TAKING TRACK OF THE LAST INDEX OF 
	public Object A[];	


	public MyStack(){
	 A = new Object[1];
	}




	public void push(Object o){
	if(o!=null){
		if(t==A.length){
			int la =0;
			Object S[]= new Object[2*A.length];
			while(la<A.length){
				S[la]=A[la];
				la++;
			}
			A = S;
		}
		A[t]=o;
		t++;
		}
	}



	public Object pop() throws EmptyStackException{
			if(t==0){
				throw new EmptyStackException("The Stack is Empty");
			}
				t--;
				return A[t];
			
	}





	public Object top()throws EmptyStackException{
			if(t==0){
				throw new EmptyStackException("The Stack is Empty");
			}
			
				return A[t-1];
	}





	public boolean isEmpty(){
		if(t==0){
			return true;
		}
		else{
			return false;
		}
	}




	public String toString(){
		String s="";
		s="[";
		if(t!=0){
		for(int i=t-1;i>=0;i--){
			s=s+A[i];
			if(i!=0){
				s=s+", ";
			}
	}}
	s=s+"]";
	return s;
	}




}
