package com.pucminas.easyfarma.config;

import com.pucminas.easyfarma.domain.enums.Action;
import com.pucminas.easyfarma.domain.enums.Item;
import com.pucminas.easyfarma.service.AuditoriaService;
import com.pucminas.easyfarma.util.AuditUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomLogoutHandler implements LogoutHandler {

    private final AuditoriaService service;

    public CustomLogoutHandler(AuditoriaService service) {
        this.service = service;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        System.out.println("Logout user: " + authentication.getName());
        AuditUtils.saveAudit(service, Action.LOGOUT, Item.SISTEMA, null, authentication.getName());
    }
}
