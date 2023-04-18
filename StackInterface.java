public interface StackInterface{    //this is the interface of a stack file
	public void push(Object o);
	public Object pop() throws EmptyStackException;
	public Object top() throws EmptyStackException;
	public boolean isEmpty();
	public String toString();
}
class EmptyStackException extends Exception{   // these are the class files of exceptions with their constructors
	public EmptyStackException(String str){
		super(str);
	}

}

class InvalidPostfixException extends Exception{
	public InvalidPostfixException(String str){
		super(str);
	}
}

class InvalidExprException extends Exception{
	public InvalidExprException(String str){
		super(str);
	}
}
