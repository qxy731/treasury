package com.soule.base.service.keygen;

import com.soule.base.service.ServiceException;


/**
 * 获取KEY值的生成器接口，基础平台中提供Long型和String型的主键生成，应用中也可以实现本接口后采用自己的实现类。
 */
public interface IKeyGenerator {
	/**
	 * 获取key值 注意：该方法是基础平台的默认实现，其返回值为uuid方式生成的String值
	 *
	 * @return String 获取的可用的key值
	 */
	public String getUUIDKey() ;

	/**
	 * 获取key值 注意：该方法是基础平台的默认实现，其返回值为uuid方式生成的String值
	 *
	 * @return String 获取的可用的key值
	 */
	public String getUUIDKey(Object o);

	/**
	 * 根据数据库中值，获取顺序号
	 * @param keyName
	 * @return
	 * @throws ServiceException
	 */
    public Long getSeqence(String keyName) throws ServiceException;

}
