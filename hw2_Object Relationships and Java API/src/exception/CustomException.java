/*
 * Hsuan Chen (hsuanc)
 */

package exception;

public class CustomException extends Exception {
	private int errorno;
	private String errormsg;
	
	/* Constructor */
	public CustomException(int errorno, String errormsg) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
	}
	
	/* Getters */
	public String getErrormsg() {
		return errormsg;
	}
	public int getErrorno() {
		return errorno;
	}
	
	/* Setters */
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}
	
	/* Method printInfo */
	@Override
	public String getLocalizedMessage() {
		return errormsg;
	}
}
