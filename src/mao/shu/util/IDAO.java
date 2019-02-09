package mao.shu.util;

import java.util.List;
import java.util.Set;
import java.sql.SQLException;

/**
 * 定义member数据表的数据层操作标准
 * @author mldn 
 */
public interface IDAO<K,V> {
	/**
	 * 保存member数据表中的数据
	 * @param vo 要保存数据的VO对象
	 * @return 保存成功返回true，否则返回false
	 * @throws SQLException 数据库未连接，或者数据库操作错误
	 */
	public boolean doCreate(V vo) throws SQLException ;
	/**
	 * 进行member表数据的修改，本次修改是基于id的修改处理
	 * @param vo 要修改的全部数据
	 * @return 修改成功返回true，否则返回false
	 * @throws SQLException 数据库未连接，或者数据库操作错误
	 */
	public boolean doUpdate(V vo) throws SQLException ;
	/**
	 * 数据的删除处理，会将所有要删除的数据保存在Set集合之中
	 * @param ids 所有要删除的ID编号
	 * @return 如果要删除的数量符合于最终的执行标准返回true，否则返回false
	 * @throws SQLException  数据库未连接，或者数据库操作错误
	 */
	public boolean doRemoveBatch(Set<K> ids) throws SQLException ;
	/**
	 * 根据id查询出一个用户的完整信息
	 * @param id 要查询的用户id
	 * @return 如果数据找到则以VO类对象的形式返回，返回null
	 * @throws SQLException 数据库未连接，或者数据库操作错误
	 */
	public V findById(K id) throws SQLException ;
	/**
	 * 查询数据表中的全部数据
	 * @return 会返回多个Member对象，将以List的集合形式返回。
	 * 如果表中没有数据则返回的List集合长度为0（size() == 0）
	 * @throws SQLException 数据库未连接，或者数据库操作错误
	 */
	public List<V> findAll() throws SQLException ;
	/**
	 * 数据的分页显示处理
	 * @param currentPage 当前所在页
	 * @param lineSize 每页显示的数据行数
	 * @return 会返回多个Member对象，将以List的集合形式返回。
	 * 如果表中没有数据则返回的List集合长度为0（size() == 0）
	 * @throws SQLException 数据库未连接，或者数据库操作错误
	 */
	public List<V> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException ;
	/**
	 * 数据的分页查询显示处理
	 * @param column 需要进行查询的数据列
	 * @param keyWord 要查询的关键字，如果关键字为空字符串表示查询全部
	 * @param currentPage 当前所在页
	 * @param lineSize 每页显示的数据行数
	 * @return 会返回多个Member对象，将以List的集合形式返回。
	 * 如果表中没有数据则返回的List集合长度为0（size() == 0）
	 * @throws SQLException 数据库未连接，或者数据库操作错误
	 */
	public List<V> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws SQLException;
	/**
	 * 统计数据表中的全部数据量
	 * @return 使用COUNT()函数的统计结果
	 * @throws SQLException 数据库未连接，或者数据库操作错误
	 */
	public Integer getAllCount() throws SQLException ;
	/**
	 * 统计模糊查询的数据量
	 * @param column 需要进行查询的数据列
	 * @param keyWord 要查询的关键字，如果关键字为空字符串表示查询全部
	 * @return 使用COUNT()函数的统计结果
	 * @throws SQLException 数据库未连接，或者数据库操作错误
	 */
	public Integer getAllCount(String column, String keyWord) throws SQLException ;

	/**
	 * 取得自动增加列的最后一个添加的id信息,MySQL数据库使用的是 LASG_INSERT_ID() 函数取得
	 * @return
	 * @throws SQLException
	 */
	public Integer getLastId()throws SQLException;
}
