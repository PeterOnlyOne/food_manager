package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyFood {

	public Integer getFoodListCount(String name){
		if (name == null) {
			name = "";
		}
		String sql = "select count(rowid) as cr from FOOD where FOODNAME like '%"+name+"%'";
		List<Map> list = DbUtils.query(sql);
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	public PageTools getFoodListPage(String name,Integer curPage){
		if (name == null) {
			name = "";
		}
		Integer totalCount = getFoodListCount(name);
		PageTools pt = new PageTools(curPage, totalCount, null);
		List<Map> list = DbUtils.query("select * from (select t.*,ft.TYPENAME,rownum rn from FOOD t inner join FOODTYPE ft on t.TYPEID=ft.TYPEID where t.FOODNAME like '%"+name+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex());
		pt.setData(list);
		return pt;
	}
	
	public void saveFood(String typeId,String foodName,String price,String imagePath,String introduce) throws Exception{
		String sql = "insert into FOOD values((select nvl(max(FOODID),0)+1 from FOOD),'"+typeId+"','"+foodName+"','"+price+"','"+imagePath+"','"+introduce+"')";
		DbUtils.excute(sql);
	}
	/**
	 * 删除菜品
	 * @param foodId 菜品编号
	 * @throws Exception
	 */
	public void deleteFood(String foodId) throws Exception{
		String sql = "delete from FOOD where FOODID="+foodId;
		DbUtils.excute(sql);
	}
}
