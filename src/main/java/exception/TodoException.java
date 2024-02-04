package exception;

public class TodoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public TodoException(String message) {
		super(message); 
	}
	public String NotFoundException(String id) {
		return "Todo with given id:"+id+"Not found";
	}
	
	public static String TodoAlreadyExists() {
		return "Todo with given name already exists!!!";
	}
 
}
