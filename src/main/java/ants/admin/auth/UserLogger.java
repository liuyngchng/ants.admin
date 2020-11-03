package ants.admin.auth;

import java.lang.annotation.*;

/**
 * 认证拦截器.
 * @author Richard Liu (liuyongcheng@test.com)
 * @version $Id$
 * @since 2017.03.30
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLogger {
}
