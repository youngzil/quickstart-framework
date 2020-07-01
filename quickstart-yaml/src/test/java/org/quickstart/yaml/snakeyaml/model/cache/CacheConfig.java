/**
 * 
 */
package org.quickstart.yaml.snakeyaml.model.cache;

/**
 * @date   2019-02-27 21:03
 * 
 */
public class CacheConfig {

	private String switc;
	
	private Integer refreshAfterWrite;
	
	private Integer expireAfterWrite;
	
    private Integer maximumSize;
    
    private String redisGroup;

	/**
	 * @return the switc
	 */
	public String getSwitc() {
		return switc;
	}

	/**
	 * @param switc the switc to set
	 */
	public void setSwitc(String switc) {
		this.switc = switc;
	}

	

	

	/**
	 * @return the refreshAfterWrite
	 */
	public Integer getRefreshAfterWrite() {
		return refreshAfterWrite;
	}

	/**
	 * @param refreshAfterWrite the refreshAfterWrite to set
	 */
	public void setRefreshAfterWrite(Integer refreshAfterWrite) {
		this.refreshAfterWrite = refreshAfterWrite;
	}

	/**
	 * @return the expireAfterWrite
	 */
	public Integer getExpireAfterWrite() {
		return expireAfterWrite;
	}

	/**
	 * @param expireAfterWrite the expireAfterWrite to set
	 */
	public void setExpireAfterWrite(Integer expireAfterWrite) {
		this.expireAfterWrite = expireAfterWrite;
	}

	/**
	 * @return the maximumSize
	 */
	public Integer getMaximumSize() {
		return maximumSize;
	}

	/**
	 * @param maximumSize the maximumSize to set
	 */
	public void setMaximumSize(Integer maximumSize) {
		this.maximumSize = maximumSize;
	}

	/**
	 * @return the redisGroup
	 */
	public String getRedisGroup() {
		return redisGroup;
	}

	/**
	 * @param redisGroup the redisGroup to set
	 */
	public void setRedisGroup(String redisGroup) {
		this.redisGroup = redisGroup;
	}

	

	
    

	
    
    
	
}
