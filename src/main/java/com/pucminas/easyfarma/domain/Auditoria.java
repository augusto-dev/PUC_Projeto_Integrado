package com.pucminas.easyfarma.domain;

import com.pucminas.easyfarma.domain.enums.Action;
import com.pucminas.easyfarma.domain.enums.Item;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String realizedBy;
    @Enumerated(EnumType.STRING)
    private Action action;
    @Enumerated(EnumType.STRING)
    private Item affectedItem;
    private Integer affectedItemId;
    private String itemName;
    private String itemDetailUrl;

    public Auditoria() {

    }

    public Auditoria(Integer id, Date date, String realizedBy,
                     Action action, Item affectedItem, Integer affectedItemId,
                     String itemName) {
        this.id = id;
        this.date = date;
        this.realizedBy = realizedBy;
        this.action = action;
        this.affectedItem = affectedItem;
        this.affectedItemId = affectedItemId;
        this.itemName = itemName;
    }

    public String setDetailUrl(Item item) {
        System.out.println("affected item: " + affectedItem);
        String url = "/error";

        switch (item) {
            case MEDICAMENTO:
                url = "/medicamentos/" + this.affectedItemId;
                break;
            case PESSOA:
                url = "/pessoas/" + this.affectedItemId;
                break;
            case PROCEDIMENTO:
                url = "/procedimentos/" + this.affectedItemId;
                break;
        }
        System.out.println("retornando: " + url);
        return url;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Auditoria other = (Auditoria) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDetailUrl() {
        return itemDetailUrl;
    }

    public void setItemDetailUrl(String itemDetailUrl) {
        this.itemDetailUrl = itemDetailUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRealizedBy() {
        return realizedBy;
    }

    public void setRealizedBy(String realizedBy) {
        this.realizedBy = realizedBy;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Item getAffectedItem() {
        return affectedItem;
    }

    public void setAffectedItem(Item affectedItem) {
        this.affectedItem = affectedItem;
    }

    public Integer getAffectedItemId() {
        return affectedItemId;
    }

    public void setAffectedItemId(Integer affectedItemId) {
        this.affectedItemId = affectedItemId;
    }

    @Override
    public String toString() {
        return "Auditoria{" +
                "id=" + id +
                ", date=" + date +
                ", realizedBy='" + realizedBy + '\'' +
                ", action=" + action +
                ", affectedItem=" + affectedItem +
                ", affectedItemId=" + affectedItemId +
                ", itemName='" + itemName + '\'' +
                ", itemDetailUrl='" + itemDetailUrl + '\'' +
                '}';
    }
}
