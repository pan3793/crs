package pc.crs.auth.server.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan(basePackages = ["pc.crs.auth.domain"])
class JpaConfiguration