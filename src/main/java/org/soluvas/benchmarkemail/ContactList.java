package org.soluvas.benchmarkemail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;

/**
 * Created by ceefour on 12/17/15.
 */
public class ContactList implements Serializable {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("MMM d, yyyy");

    @JsonProperty
    private Long id; // Long, 4985205
    @JsonProperty("listname")
    private String name;
    @JsonProperty("contactcount")
    private Integer contactCount; // int
    @JsonProperty("list_description")
    private String description;
    @JsonProperty
    private Integer sequence; // int
    @JsonProperty("is_importing") // int or bool
    private Boolean importing;
    @JsonProperty("list_import_v3") // int or bool
    private Boolean listImportV3;
    @JsonProperty("is_master_unsubscribe") // int or bool
    private Boolean masterUnsubscribe;
    @JsonProperty("permissionPassList")
    private Integer permissionPassList; // int
    @JsonProperty
    private LocalDate createdDate; // Apr 09, 2015
    @JsonProperty("allow_delete")
    private Boolean allowDelete; // int or bool
    @JsonProperty
    private LocalDate modifiedDate; // Apr 09, 2015
    @JsonProperty
    private String encToken;
    @JsonProperty("list_audited")
    private Boolean listAudited; // int or bool

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactCount) {
        this.contactCount = contactCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getImporting() {
        return importing;
    }

    public void setImporting(Boolean importing) {
        this.importing = importing;
    }

    @JsonSetter
    public void setImporting(Byte importing) {
        this.importing = 1 == importing;
    }

    public Boolean getListImportV3() {
        return listImportV3;
    }

    public void setListImportV3(Boolean listImportV3) {
        this.listImportV3 = listImportV3;
    }

    @JsonSetter
    public void setListImportV3(Byte listImportV3) {
        this.listImportV3 = 1 == listImportV3;
    }

    public Boolean getMasterUnsubscribe() {
        return masterUnsubscribe;
    }

    public void setMasterUnsubscribe(Boolean masterUnsubscribe) {
        this.masterUnsubscribe = masterUnsubscribe;
    }

    @JsonSetter
    public void setMasterUnsubscribe(Byte masterUnsubscribe) {
        this.masterUnsubscribe = 1 == masterUnsubscribe;
    }

    public void setAllowDelete(Boolean allowDelete) {
        this.allowDelete = allowDelete;
    }

    @JsonSetter
    public void setAllowDelete(Byte allowDelete) {
        this.allowDelete = 1 == allowDelete;
    }

    public void setListAudited(Boolean listAudited) {
        this.listAudited = listAudited;
    }

    @JsonSetter
    public void setListAudited(Byte listAudited) {
        this.listAudited = 1 == listAudited;
    }

    public Integer getPermissionPassList() {
        return permissionPassList;
    }

    public void setPermissionPassList(Integer permissionPassList) {
        this.permissionPassList = permissionPassList;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @JsonSetter
    public void setCreatedDate(String createdDate) {
        this.createdDate = DATE_FORMAT.parseLocalDate(createdDate);
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @JsonSetter
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = DATE_FORMAT.parseLocalDate(modifiedDate);
    }

    public String getEncToken() {
        return encToken;
    }

    public void setEncToken(String encToken) {
        this.encToken = encToken;
    }

    @Override
    public String toString() {
        return "ContactList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactCount=" + contactCount +
                ", description='" + description + '\'' +
                ", sequence=" + sequence +
                ", importing=" + importing +
                ", listImportV3=" + listImportV3 +
                ", masterUnsubscribe=" + masterUnsubscribe +
                ", permissionPassList=" + permissionPassList +
                ", createdDate=" + createdDate +
                ", allowDelete=" + allowDelete +
                ", modifiedDate=" + modifiedDate +
                ", encToken='" + encToken + '\'' +
                ", listAudited=" + listAudited +
                '}';
    }
}
