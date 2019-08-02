/**
 * 
 */
package org.quickstart.reflections.example.apimapping.model;

import java.lang.annotation.Annotation;

/**
 * @author zh
 * @date 2019-05-21 19:33
 * 
 */
public class ReqParam {

	private String paramName;

	private Class<?> paramType;

	private Class<? extends Annotation> annotitaionType;

	//注解名称
	private String annotitaionValue;
	
	private Object paramValue;
	

	/**
	 * @return the paramValue
	 */
	public Object getParamValue() {
		return paramValue;
	}

	/**
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}

	/**
	 * @return the annotitaionValue
	 */
	public String getAnnotitaionValue() {
		return annotitaionValue;
	}

	/**
	 * @param annotitaionValue the annotitaionValue to set
	 */
	public void setAnnotitaionValue(String annotitaionValue) {
		this.annotitaionValue = annotitaionValue;
	}

	private boolean required;

	/**
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	
	/**
	 * @return the paramType
	 */
	public Class<?> getParamType() {
		return paramType;
	}

	/**
	 * @param paramType the paramType to set
	 */
	public void setParamType(Class<?> paramType) {
		this.paramType = paramType;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the annotitaionType
	 */
	public Class<? extends Annotation> getAnnotitaionType() {
		return annotitaionType;
	}

	/**
	 * @param annotitaionType the annotitaionType to set
	 */
	public void setAnnotitaionType(Class<? extends Annotation> annotitaionType) {
		this.annotitaionType = annotitaionType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReqParam [paramName=");
		builder.append(paramName);
		builder.append(", paramType=");
		builder.append(paramType);
		builder.append(", annotitaionType=");
		builder.append(annotitaionType);
		builder.append(", annotitaionValue=");
		builder.append(annotitaionValue);
		builder.append(", paramValue=");
		builder.append(paramValue);
		builder.append(", required=");
		builder.append(required);
		builder.append("]");
		return builder.toString();
	}

	

	
}
