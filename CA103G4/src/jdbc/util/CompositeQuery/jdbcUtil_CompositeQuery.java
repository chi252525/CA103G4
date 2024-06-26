/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("act_Cat".equals(columnName) || "coucat_No".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + "'"+value+"'";
		else if ("act_Name".equals(columnName)|| "act_No".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if("act_Start".equals(columnName))
			aCondition = columnName +" >= TO_Timestamp('"+value+"','mm/dd/yyyy')";
		else if("act_End".equals(columnName))
			aCondition = columnName +" <= TO_Timestamp('"+value+"','mm/dd/yyyy')";
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				System.out.println(key);
				System.out.println(value.trim());
				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("act_Cat", new String[] { "AC2" });
		map.put("coucat_No", new String[] { "20181009-000001" });
		map.put("act_Name", new String[] { "新品" });
		map.put("act_No", new String[] { "201810-0001" });
		map.put("act_Start", new String[] { "2018-09-17" });
		map.put("act_End", new String[] { "2018-09-20" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from activity "
				          + jdbcUtil_CompositeQuery.get_WhereCondition(map)
				          + "order by act_No";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
