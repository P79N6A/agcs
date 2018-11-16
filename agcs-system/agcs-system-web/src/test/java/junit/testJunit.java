package junit;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.agcs.system.web.dao.system.SystemDao;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:spring-*.xml"})
//@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false) 
//@Transactional
public class testJunit {
	private static final Logger log = Logger.getLogger(testJunit.class);
	
//	@Resource
//	private IUserService userService;
//	
	@Resource
	private SystemDao systemDao;
	private Jedis jedis; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		
		//连接redis服务器，192.168.0.100:6379
		jedis = new Jedis("192.168.0.81", 6379);
		//权限认证
		//jedis.auth("admin"); 
	}
	
	@Test
	public void findUserByIdTest() throws Exception{
		//List<GlptUser> ss = this.userService.getUserList(null, "admin", "000000");
//		GlptUser user = new GlptUser();
//		user.setUser_code("dddd");
//		user.setUser_name("fdfd");
//		user.setPassword("dfdfd");
//		GlptUser user2 = this.userDao.save(user);
//		log.info(user2.getUser_id());
		
		//log.info("用户名："+ss.get(0).getUser_name());
//		String aliasName, name, type, alias = ""; 
//		PropertyDescriptor origDescriptors[] = PropertyUtils
//				.getPropertyDescriptors(new GlptUser());
//		for (int i = 0; i < origDescriptors.length; i++) {
//			aliasName = (alias.equals("") ? "" : alias + ".")
//					+ origDescriptors[i].getName();
//			name = origDescriptors[i].getName();
//			type = origDescriptors[i].getPropertyType().toString();
//			System.out.println("aliasName:"+aliasName+",name:"+name+",type:"+type);
//		}
		
		/*DataGrid dataGrid = new DataGrid();
		dataGrid.setPage(1);
		dataGrid.setRows(10);
		dataGrid.setSort("user_code");
		dataGrid.setOrder(SortDirection.desc);
		
		GlptUser user = new GlptUser();
		user.setUser_code("admin");
		
		
		Map<String, String[]> parameterMap = new HashMap<String, String[]>();
		parameterMap.put("page", new String[]{"1"});
		parameterMap.put("rows", new String[]{"10"});
		parameterMap.put("user_code", new String[]{"admin"});
		parameterMap.put("age_begin", new String[]{"20"});
		parameterMap.put("age_end", new String[]{"30"});
		
		parameterMap.put("lrsj_begin", new String[]{"2015-01-01"});
		parameterMap.put("lrsj_end", new String[]{"2016-01-01"});
		
		
		CriteriaQuery cq = new CriteriaQuery(GlptUser.class, dataGrid);
		
		CriteriaUtils.installHql(cq, user, parameterMap);
		userDao.getCriteriaQueryList(cq, true);
		System.out.println(dataGrid.toString());*/
		/*GlptUserEntity glptUser = new GlptUserEntity();
		glptUser.setUserCode("admins");
		int page = 1;
		int row = 10;
		List<Map> listMap = systemDao.getAllEntities(glptUser, page, row);
		System.out.println(listMap.size());*/
		
	}
	
	@Test
	public void testString() throws Exception{
		//-----添加数据----------  
		jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin  
		System.out.println(jedis.get("name"));//执行结果：xinxin  
		         
		jedis.append("name", " is my lover"); //拼接
		System.out.println(jedis.get("name")); 
		         
		jedis.del("name");  //删除某个键
		System.out.println(jedis.get("name"));
		//设置多个键值对
		jedis.mset("name","liuling","age","23","qq","476777XXX");
		jedis.incr("age"); //进行加1操作
		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}


}
