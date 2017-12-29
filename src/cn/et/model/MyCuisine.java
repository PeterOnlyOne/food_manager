package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyCuisine {

	/**
	 * 获取菜系的个数
	 * @param name 菜系名称
	 * @return
	 */
	public Integer getCuisineListCount(String name){
		if (name == null) {
			name = "";
		}
		String sql="select count(rowid) as cr from FOODTYPE where TYPENAME like '%"+name+"%'";
		List<Map> list = DbUtils.query(sql);
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	/**
	 * 获取当前页的菜系名称
	 * @param name 菜系名称
	 * @param curPage 当前页
	 * @return
	 */
	public PageTools getCuisineListPage(String name,Integer curPage){
		if (name == null) {
			name = "";
		}
		Integer totalCount = getCuisineListCount(name);
		PageTools pt = new PageTools(curPage, totalCount, null);
		List<Map> list = DbUtils.query("select * from (select t.*,rownum rn from FOODTYPE t where t.TYPENAME like '%"+name+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex());
		pt.setData(list);
		return pt;
	}
	/**
	 * 添加菜系
	 * @param typeName 菜系名称
	 * @throws Exception
	 */
	public void saveCuisine(String typeName) throws Exception{
		String sql = "insert into FOODTYPE values((select nvl(max(TYPEID),0)+1 from FOODTYPE),'"+typeName+"')";
		DbUtils.excute(sql);
	}
	/**
	 * 删除菜系
	 * @param typeId 菜系编号
	 * @throws Exception
	 */
	public void deleteCuisine(String typeId) throws Exception{
		String sql = "delete from FOODTYPE where TYPEID="+typeId;
		DbUtils.excute(sql);
	}
	/**
	 * 更新菜系名称
	 * @param typeName 菜系名称
	 * @throws Exception
	 */
	public void updateCuisine(String typeId,String typeName) throws Exception{
		String sql = "update FOODTYPE set TYPENAME='"+typeName+"' where TYPEID='"+typeId+"'";
		DbUtils.excute(sql);
	}
	public List<Map> getAllCuisine(){
		String sql = "select * from FOODTYPE";
		return DbUtils.query(sql);
	}
}
