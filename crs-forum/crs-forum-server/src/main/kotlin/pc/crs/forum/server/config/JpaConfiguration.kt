package pc.crs.forum.server.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan(basePackages = ["pc.crs.forum.domain"])
class JpaConfiguration