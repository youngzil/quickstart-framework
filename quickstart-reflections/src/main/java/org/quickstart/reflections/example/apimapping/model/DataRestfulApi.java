/**
 * 
 */
package org.quickstart.reflections.example.apimapping.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author zh
 * @date 2019-05-17 10:30
 * 
 */
public class DataRestfulApi implements Serializable {

	private static final long serialVersionUID = 1L;

	// 映射cls
	private Class<?> mappingCls;
	// 类请求路径
	private String requestUrl;

	// 请求方式
	private String requestMethod;
	// 方法名称
	private String method;

	private Class<?>[] paramsCls;

	private List<ReqParam> reqParams;

	/**
	 * @return the reqParams
	 */
	public List<ReqParam> getReqParams() {
		return reqParams;
	}

	/**
	 * @param reqParams the reqParams to set
	 */
	public void setReqParams(List<ReqParam> reqParams) {
		this.reqParams = reqParams;
	}

	/**
	 * @return the paramsCls
	 */
	public Class<?>[] getParamsCls() {
		return paramsCls;
	}

	/**
	 * @param paramsCls the paramsCls to set
	 */
	public void setParamsCls(Class<?>[] paramsCls) {
		this.paramsCls = paramsCls;
	}

	/**
	 * @return the mappingCls
	 */
	public Class<?> getMappingCls() {
		return mappingCls;
	}

	/**
	 * @param mappingCls the mappingCls to set
	 */
	public void setMappingCls(Class<?> mappingCls) {
		this.mappingCls = mappingCls;
	}

	/**
	 * @return the requestUrl
	 */
	public String getRequestUrl() {
		return requestUrl;
	}

	/**
	 * @param requestUrl the requestUrl to set
	 */
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	/**
	 * @return the requestMethod
	 */
	public String getRequestMethod() {
		return requestMethod;
	}

	/**
	 * @param requestMethod the requestMethod to set
	 */
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataRestfulApi [mappingCls=");
		builder.append(mappingCls);
		builder.append(", requestUrl=");
		builder.append(requestUrl);
		builder.append(", requestMethod=");
		builder.append(requestMethod);
		builder.append(", method=");
		builder.append(method);
		builder.append(", paramsCls=");
		builder.append(Arrays.toString(paramsCls));
		builder.append(", reqParams=");
		builder.append(reqParams);
		builder.append("]");
		return builder.toString();
	}
	
	

}
