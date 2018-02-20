package pc.crs.auth.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pc.crs.auth.domain.UserDO
import pc.crs.auth.server.dao.UserDAO
import pc.crs.common.base.service.BaseService
import pc.crs.common.constant.BASE_DTO_READ_IGNORE_FIELD_LIST

@Service
class UserService(@Autowired override var dao: UserDAO)
    : BaseService<UserDO, UserDO, UserDAO>() {

    // 屏蔽DTO密码，避免直接修改
    override val dtoReadOnlyIgnoreFiledList = BASE_DTO_READ_IGNORE_FIELD_LIST.plus("password")
}