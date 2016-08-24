package  com.heetian.spider.mysql.model;
import java.util.Date;
/**
 * <p>
 * Title: Proxy
 * </p>
 * <p>
 * Description: 代理表Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class Proxy {

    private int id;  //id
    private String ip;  //ip
    private int port;  //端口号
    private String country;  //国家
    private String province;  //省份
    private String city;  //地区
    private String ips;  //供应商
    private String proxyType;  //类型
    private String anonymity;  //匿名度
    private int connectTimeMs;  //响应时间，单位毫秒，购买的代理有此选 项
    private int source;  //来源,0自采，1购买
    private int valid;  //是否有效，0,无效，1，有效，2，有效，但是一天使用超过了100次，目前不可再用，24小时后可再用
    private Date validTime;  //有效或无效时间


    public Proxy() {
		super();
	}
    
	public Proxy(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	/**  
	 * 返回 id 的值   
	 * @return id  
	 */
	public int getId() {
		return  id;
	}
	/**  
	 * 设置  id 的值  
	 * @param  id
	 */
	public void setId(int  id) {
		this. id =  id;
	}

    /**  
	 * 返回 ip 的值   
	 * @return ip  
	 */
	public String getIp() {
		return  ip;
	}
	/**  
	 * 设置  ip 的值  
	 * @param  ip
	 */
	public void setIp(String  ip) {
		this. ip =  ip;
	}

    /**  
	 * 返回 port 的值   
	 * @return port  
	 */
	public int getPort() {
		return  port;
	}
	/**  
	 * 设置  port 的值  
	 * @param  port
	 */
	public void setPort(int  port) {
		this. port =  port;
	}

    /**  
	 * 返回 country 的值   
	 * @return country  
	 */
	public String getCountry() {
		return  country;
	}
	/**  
	 * 设置  country 的值  
	 * @param  country
	 */
	public void setCountry(String  country) {
		this. country =  country;
	}

    /**  
	 * 返回 province 的值   
	 * @return province  
	 */
	public String getProvince() {
		return  province;
	}
	/**  
	 * 设置  province 的值  
	 * @param  province
	 */
	public void setProvince(String  province) {
		this. province =  province;
	}

    /**  
	 * 返回 city 的值   
	 * @return city  
	 */
	public String getCity() {
		return  city;
	}
	/**  
	 * 设置  city 的值  
	 * @param  city
	 */
	public void setCity(String  city) {
		this. city =  city;
	}

    /**  
	 * 返回 ips 的值   
	 * @return ips  
	 */
	public String getIps() {
		return  ips;
	}
	/**  
	 * 设置  ips 的值  
	 * @param  ips
	 */
	public void setIps(String  ips) {
		this. ips =  ips;
	}

    /**  
	 * 返回 proxyType 的值   
	 * @return proxyType  
	 */
	public String getProxyType() {
		return  proxyType;
	}
	/**  
	 * 设置  proxyType 的值  
	 * @param  proxyType
	 */
	public void setProxyType(String  proxyType) {
		this. proxyType =  proxyType;
	}

    /**  
	 * 返回 anonymity 的值   
	 * @return anonymity  
	 */
	public String getAnonymity() {
		return  anonymity;
	}
	/**  
	 * 设置  anonymity 的值  
	 * @param  anonymity
	 */
	public void setAnonymity(String  anonymity) {
		this. anonymity =  anonymity;
	}

    /**  
	 * 返回 connectTimeMs 的值   
	 * @return connectTimeMs  
	 */
	public int getConnectTimeMs() {
		return  connectTimeMs;
	}
	/**  
	 * 设置  connectTimeMs 的值  
	 * @param  connectTimeMs
	 */
	public void setConnectTimeMs(int  connectTimeMs) {
		this. connectTimeMs =  connectTimeMs;
	}

    /**  
	 * 返回 source 的值   
	 * @return source  
	 */
	public int getSource() {
		return  source;
	}
	/**  
	 * 设置  source 的值  
	 * @param  source
	 */
	public void setSource(int  source) {
		this. source =  source;
	}

    /**  
	 * 返回 valid 的值   
	 * @return valid  
	 */
	public int getValid() {
		return  valid;
	}
	/**  
	 * 设置  valid 的值  
	 * @param  valid
	 */
	public void setValid(int  valid) {
		this. valid =  valid;
	}

    /**  
	 * 返回 validTime 的值   
	 * @return validTime  
	 */
	public Date getValidTime() {
		return  validTime;
	}
	/**  
	 * 设置  validTime 的值  
	 * @param  validTime
	 */
	public void setValidTime(Date  validTime) {
		this. validTime =  validTime;
	}
	
}
