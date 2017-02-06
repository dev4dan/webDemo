package testCase;

import static com.dev4dan.utils.MethodCase.SHOWINFO;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.dev4dan.model.Employee;
import com.dev4dan.model.SQLProviderHelper;
import com.dev4dan.model.sql.SqlProvider;
import com.dev4dan.utils.Constants;

public class TestClz {

	public static int code = 100;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		testSQLProvider();
		
		System.out.println(Constants.CONDITION.AND.name());
	}
	
	public static void providerUpdateSQLTest() {
		SQLProviderHelper helper = new SQLProviderHelper();

		helper.setClz(Employee.class);

		Map<String, Object> conditions = new HashMap<String, Object>();
		List<String> andCond = new ArrayList<String>();
		andCond.add("name='pio1'");
		andCond.add("idpk='1'");
		String and = Constants.CONDITION.AND.toString();
		conditions.put(and, andCond);

		helper.setConditions(conditions);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "pio20002");
		params.put("salary", "20002");
		helper.setParams(params);

		List<String> filters = new ArrayList<String>();
		filters.add("idpk");
		helper.setFilters(filters);

		helper.setTableName("t_employee");

		System.out.println(SqlProvider.getUpdateSQL(helper));
	}

	public static void test() {
		SqlProvider provider = new SqlProvider();

		System.out.println(provider.getBatchInsertValues(Employee.class,
				"employee", "--").getValues());

		SHOWINFO();

	}
	
	public static void testSQLProvider(){
		List<Employee> emps = new ArrayList<Employee>();
		for(int i = 0 ; i<10 ; i++){			
			Employee emp = new Employee();
			emp.setName("_emp" + i);
			emp.setSalary(10000);
			emps.add(emp);
		}
		SQLProviderHelper providerHelper = new SQLProviderHelper();
		providerHelper.setClz(Employee.class);
		providerHelper.setTableName("t_employee");
		providerHelper.setInstance(emps);
		providerHelper.addFilter("ID");
		
		try {
			System.out.println(SqlProvider.getBatchSQL4Insert(providerHelper));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public static void testBeanUtil(){
		Employee emp = new Employee();
		emp.setName("emp1");
		try {
//			System.out.println(BeanUtils.getProperty(emp, "name"));
			Map<String, String> describe = BeanUtils.describe(emp);
			Iterator<String> it = describe.keySet().iterator();
			while( it.hasNext() ){
				String prop = it.next();
				if(prop.equals("class")){
					continue;
				}
				System.out.println("prop : " + prop + ", value : " + describe.get(prop));
				
			}
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
	}

}
