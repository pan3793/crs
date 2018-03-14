package pc.crs.auth.server

import com.alibaba.fastjson.JSON
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pc.crs.auth.domain.UserDO
import pc.crs.auth.server.dao.UserDAO
import pc.crs.common.jpa.Criteria
import pc.crs.common.jpa.gt
import pc.crs.common.jpa.like


@RunWith(SpringRunner::class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
    lateinit var userDAO: UserDAO

	@Test
	fun contextLoads() {
        val criteria = Criteria<UserDO>()
        criteria.add(like("loginName", "d"))
        criteria.add(gt("id", 1))
        val all = userDAO.findAll(criteria)
        all.forEach { println(JSON.toJSONString(all)) }
	}

}
