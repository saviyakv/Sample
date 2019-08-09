package org.sample;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LDAPController {


	@RequestMapping(value="/")
    public Authentication hello(@ModelAttribute User user) {
        return LdapSecurity.getAuthentication();
    }

}
