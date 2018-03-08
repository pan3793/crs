package pc.crs.server.manager

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import pc.crs.common.bean.IdNameDTO

@FeignClient(name = "crs-auth-server", path = "/api/user")
interface UserManager {
    @GetMapping("idNameList")
    fun fetchIdNameList(): List<IdNameDTO>

    @GetMapping("teacherIdNameList")
    fun fetchTeacherIdNameList(): List<IdNameDTO>

    @GetMapping("studentIdNameList")
    fun fetchStudentIdNameList(): List<IdNameDTO>
}