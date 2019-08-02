/**
 * 
 */
package org.quickstart.reflections.example.apimapping.exception;

/**
 * @author zh
 * @date 2019-06-05 10:09
 * 
 */
public enum RestfulApiExceptionCode {

	SUCCESS("0","成功"),
	FIAL("1","失败"),
	DATA_IS_NULL("30000", "数据为空！"),
	KEY_IS_ERROR("30001","key 错误!"),
	DATA_EXIST("30002","数据重复添加!"),
	DATA_NOT_EXST("30003","数据不存在!"),
	DATA_FORMAT_ERROR("30004","数据格式错误!"),
	KEY_NOT_EXST("30005","key不存在!");

	private final String code;

	private final String msg;

	private RestfulApiExceptionCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}


}
