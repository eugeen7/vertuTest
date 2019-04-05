/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SidorovEI
 */
@Entity
@Table(name = "plc_hldr", catalog = "tbhw_db", schema = "vertu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlcHldr.findAll", query = "SELECT p FROM PlcHldr p")
    , @NamedQuery(name = "PlcHldr.findByPlchldrId", query = "SELECT p FROM PlcHldr p WHERE p.plchldrId = :plchldrId")
    , @NamedQuery(name = "PlcHldr.findByVersnum", query = "SELECT p FROM PlcHldr p WHERE p.versnum = :versnum")
    , @NamedQuery(name = "PlcHldr.findBySurename", query = "SELECT p FROM PlcHldr p WHERE p.surename = :surename")
    , @NamedQuery(name = "PlcHldr.findByFirstname", query = "SELECT p FROM PlcHldr p WHERE p.firstname = :firstname")
    , @NamedQuery(name = "PlcHldr.findBySecondname", query = "SELECT p FROM PlcHldr p WHERE p.secondname = :secondname")
    , @NamedQuery(name = "PlcHldr.findByFio", query = "SELECT p FROM PlcHldr p WHERE p.fio = :fio")
    , @NamedQuery(name = "PlcHldr.findByBirthday", query = "SELECT p FROM PlcHldr p WHERE p.birthday = :birthday")
    , @NamedQuery(name = "PlcHldr.findByPsprtSrl", query = "SELECT p FROM PlcHldr p WHERE p.psprtSrl = :psprtSrl")
    , @NamedQuery(name = "PlcHldr.findByPsprtNum", query = "SELECT p FROM PlcHldr p WHERE p.psprtNum = :psprtNum")})
public class PlcHldr implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "plcHldrGen", table = "GEN_ID", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VAL", pkColumnValue = "plchldr_pk", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "plcHldrGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "plchldr_id", nullable = false)
    private Long plchldrId;
    @Version
    private Integer versnum;
    @Size(max = 100)
    @Column(length = 100)
    private String surename;
    @Size(max = 100)
    @Column(length = 100)
    private String firstname;
    @Size(max = 100)
    @Column(length = 100)
    private String secondname;
    @Size(max = 300)
    @Column(length = 300)
    private String fio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Max(value = 9999)
    @Column(name = "psprt_srl")
    private Integer psprtSrl;
    @Max(value = 999999)
    @Column(name = "psprt_num")
    private Integer psprtNum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plchldrFk")
    private Collection<InsContr> insContrCollection;

    public PlcHldr() {
    }

    public PlcHldr(Long plchldrId) {
        this.plchldrId = plchldrId;
    }

    public Long getPlchldrId() {
        return plchldrId;
    }

    public void setPlchldrId(Long plchldrId) {
        this.plchldrId = plchldrId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getPsprtSrl() {
        return psprtSrl;
    }

    public void setPsprtSrl(Integer psprtSrl) {
        this.psprtSrl = psprtSrl;
    }

    public Integer getPsprtNum() {
        return psprtNum;
    }

    public void setPsprtNum(Integer psprtNum) {
        this.psprtNum = psprtNum;
    }

    @XmlTransient
    public Collection<InsContr> getInsContrCollection() {
        return insContrCollection;
    }

    public void setInsContrCollection(Collection<InsContr> insContrCollection) {
        this.insContrCollection = insContrCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plchldrId != null ? plchldrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlcHldr)) {
            return false;
        }
        PlcHldr other = (PlcHldr) object;
        if ((this.plchldrId == null && other.plchldrId != null) || (this.plchldrId != null && !this.plchldrId.equals(other.plchldrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertu.vrts.data.PlcHldr[ plchldrId=" + plchldrId + " ]";
    }

}
