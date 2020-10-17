package name.soy.dc

/**
 *
 * @author soy
 * 私人信息接口
 */
interface IPrivateData {
    /**
     * 数据库地址
     * @return
     */
    fun SQLAddress(): String
    /**
     * 数据库用户
     * @return
     */
    fun SQLUser(): String
    /**
     * 数据库密码
     * @return
     */
    fun SQLPwd(): String
    /**
     * 数据库的数据库
     * @return
     */
    fun SQLDatabase(): String
    /**
     * SQL2是备用的
     */
    fun SQL2Address(): String
    fun SQL2User(): String
    fun SQL2Pwd(): String
    fun SQL2Database(): String
    /**
     * 设备连接的验证字节
     * 避免开源了，被™乱连
     */
    fun deviceVerifyData(): ByteArray
    /**
     * 开启服务的密码
     * @return
     */
    fun startupCode(): String
}