package name.soy.dc;
/**
 *
 * @author soy
 * 私人信息接口
 */
public interface IPrivateData {
	/**
	 * 数据库地址
	 * @return
	 */
	String SQLAddress();
	/**
	 * 数据库用户
	 * @return
	 */
	String SQLUser();
	/**
	 * 数据库密码
	 * @return
	 */
	String SQLPwd();
	/**
	 * 数据库的数据库
	 * @return
	 */
	String SQLDatabase();

	/**
	 * SQL2是备用的
	 */
	String SQL2Address();

	String SQL2User();

	String SQL2Pwd();

	String SQL2Database();
	/**
	 * 设备连接的验证字节
	 * 避免开源了，被™乱连
	 */
	byte[] deviceVerifyData();

	/**
	 * 开启服务的密码
	 * @return
	 */
	String startupCode();


}
