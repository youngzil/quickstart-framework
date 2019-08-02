/**
 * 
 */
package org.quickstart.reflections.example.apimapping.exception;

/**
 * @author zh
 * @date 2019-05-22 15:19
 * 
 */
public class RestfulException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String msg;

	private String code = "500";

	public RestfulException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public RestfulException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public RestfulException(RestfulApiExceptionCode code) {
		this.code = code.getCode();
		this.msg = code.getMsg();
	}

	public RestfulException(RestfulApiExceptionCode code, String msg) {
		this.code = code.getCode();
		this.msg = msg;
	}

	public RestfulException(RestfulApiExceptionCode code, String msg, Throwable e) {
		super(msg, e);
		this.code = code.getCode();
		this.msg = msg;
	}

	public RestfulException(String msg, String code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
