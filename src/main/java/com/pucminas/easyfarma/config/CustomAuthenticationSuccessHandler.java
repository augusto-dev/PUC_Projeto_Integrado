package com.pucminas.easyfarma.config;

import com.pucminas.easyfarma.domain.enums.Action;
import com.pucminas.easyfarma.domain.enums.Item;
import com.pucminas.easyfarma.service.AuditoriaService;
import com.pucminas.easyfarma.util.AuditUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AuditoriaService service;

    public CustomAuthenticationSuccessHandler(AuditoriaService service) {
        this.service = service;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        System.out.println("Logged user: " + authentication.getName());
        AuditUtils.saveAudit(service, Action.LOGIN, Item.SISTEMA, null, authentication.getName());
        response.sendRedirect("/");
    }


}
