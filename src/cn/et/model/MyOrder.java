package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyOrder {

	public Integer getTableListCount(String deskId){
		
		String sql = "select count(rowid) as cr from FOODORDER where DESKID like '%"+deskId+"%'";
		List<Map> list = DbUtils.query(sql);
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	public PageTools getTableListPage(String deskId,Integer curPage){
		
		Integer totalCount = getTableListCount(deskId);
		PageTools pt = new PageTools(curPage, totalCount, null);
		List<Map> list = DbUtils.query("select * from (select t.*,rownum rn from FOODORDER t where t.DESKID like '%"+deskId+"%')"
				+ " where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex());
		pt.setData(list);
		return pt;
	}
	
	 
}
