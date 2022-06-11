package com.pucminas.easyfarma.util;

import com.pucminas.easyfarma.domain.Auditoria;
import com.pucminas.easyfarma.domain.enums.Action;
import com.pucminas.easyfarma.domain.enums.Item;
import com.pucminas.easyfarma.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;


public class AuditUtils {

    public static void saveAudit(AuditoriaService service,
                                 Action action, Item item,
                                 Integer itemId, String itemName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Auditoria auditoria = new Auditoria();
        auditoria.setAction(action);
        auditoria.setAffectedItem(item);
        auditoria.setDate(new Date());
        auditoria.setRealizedBy(currentPrincipalName);
        auditoria.setAffectedItemId(itemId);
        auditoria.setItemDetailUrl(auditoria.setDetailUrl(item));
        auditoria.setItemName(itemName);
        service.insert(auditoria);
    }
}
