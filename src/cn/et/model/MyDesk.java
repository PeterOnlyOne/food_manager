package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyDesk {

	public Integer getTableListCount(String name){
		if (name == null) {
			name = "";
		}
		String sql = "select count(rowid) as cr from DESK where dname like '%"+name+"%'";
		List<Map> list = DbUtils.query(sql);
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	public PageTools getTableListPage(String name,Integer curPage){
		if (name == null) {
			name = "";
		}
		Integer totalCount = getTableListCount(name);
		PageTools pt = new PageTools(curPage, totalCount, null);
		List<Map> list = DbUtils.query("select * from (select t.*,rownum rn from DESK t where t.dname like '%"+name+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex());
		pt.setData(list);
		return pt;
	}
	/**
	 * 添加餐桌
	 * @param deskName 餐桌名
	 * @throws Exception
	 */
	public void saveDesk(String deskName) throws Exception{
		String sql = "insert into desk values((select nvl(max(DESKID),0)+1 from desk),'"+deskName+"',0,'')";
		DbUtils.excute(sql);
	}
	/**
	 * 删除餐桌
	 * @param deskId 餐桌编号
	 * @throws Exception
	 */
	public void deleteDesk(String deskId) throws Exception{
		String sql = "delete from desk where deskid="+deskId;
		DbUtils.excute(sql);
	}
}
