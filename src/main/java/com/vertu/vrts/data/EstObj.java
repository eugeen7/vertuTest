/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.data;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SidorovEI
 */
@Entity
@Table(name = "est_obj", catalog = "tbhw_db", schema = "vertu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstObj.findAll", query = "SELECT e FROM EstObj e")
    , @NamedQuery(name = "EstObj.findByEstobjId", query = "SELECT e FROM EstObj e WHERE e.estobjId = :estobjId")
    , @NamedQuery(name = "EstObj.findByVersnum", query = "SELECT e FROM EstObj e WHERE e.versnum = :versnum")
    , @NamedQuery(name = "EstObj.findByCountry", query = "SELECT e FROM EstObj e WHERE e.country = :country")
    , @NamedQuery(name = "EstObj.findByZipCode", query = "SELECT e FROM EstObj e WHERE e.zipCode = :zipCode")
    , @NamedQuery(name = "EstObj.findByOblast", query = "SELECT e FROM EstObj e WHERE e.oblast = :oblast")
    , @NamedQuery(name = "EstObj.findByDistrict", query = "SELECT e FROM EstObj e WHERE e.district = :district")
    , @NamedQuery(name = "EstObj.findByLocPoint", query = "SELECT e FROM EstObj e WHERE e.locPoint = :locPoint")
    , @NamedQuery(name = "EstObj.findByStreet", query = "SELECT e FROM EstObj e WHERE e.street = :street")
    , @NamedQuery(name = "EstObj.findByBuildNum", query = "SELECT e FROM EstObj e WHERE e.buildNum = :buildNum")
    , @NamedQuery(name = "EstObj.findByCorpus", query = "SELECT e FROM EstObj e WHERE e.corpus = :corpus")
    , @NamedQuery(name = "EstObj.findByBuilding", query = "SELECT e FROM EstObj e WHERE e.building = :building")
    , @NamedQuery(name = "EstObj.findByAprtNum", query = "SELECT e FROM EstObj e WHERE e.aprtNum = :aprtNum")})
public class EstObj implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableGenerator(name = "estObjGen", table = "GEN_ID", pkColumnName = "ID_NAME", 
            valueColumnName = "ID_VAL", pkColumnValue = "estobj_pk", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "estObjGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "estobj_id", nullable = false)
    private Long estobjId;
    @Version
    private Integer versnum;
    @Size(max = 512)
    @Column(length = 512)
    private String country;
    @Size(max = 20)
    @Column(name = "zip_code", length = 20)
    private String zipCode;
    @Size(max = 512)
    @Column(length = 512)
    private String oblast;
    @Size(max = 512)
    @Column(length = 512)
    private String district;
    @Size(max = 512)
    @Column(name = "loc_point", length = 512)
    private String locPoint;
    @Size(max = 512)
    @Column(length = 512)
    private String street;
    @Column(name = "build_num")
    private Integer buildNum;
    @Size(max = 20)
    @Column(length = 20)
    private String corpus;
    @Size(max = 20)
    @Column(length = 20)
    private String building;
    @Size(max = 20)
    @Column(name = "aprt_num", length = 20)
    private String aprtNum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estobjFk")
    private Collection<InsContr> insContrCollection;

    public EstObj() {
    }

    public EstObj(Long estobjId) {
        this.estobjId = estobjId;
    }

    public Long getEstobjId() {
        return estobjId;
    }

    public void setEstobjId(Long estobjId) {
        this.estobjId = estobjId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocPoint() {
        return locPoint;
    }

    public void setLocPoint(String locPoint) {
        this.locPoint = locPoint;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(Integer buildNum) {
        this.buildNum = buildNum;
    }

    public String getCorpus() {
        return corpus;
    }

    public void setCorpus(String corpus) {
        this.corpus = corpus;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getAprtNum() {
        return aprtNum;
    }

    public void setAprtNum(String aprtNum) {
        this.aprtNum = aprtNum;
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
        hash += (estobjId != null ? estobjId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstObj)) {
            return false;
        }
        EstObj other = (EstObj) object;
        if ((this.estobjId == null && other.estobjId != null) || (this.estobjId != null && !this.estobjId.equals(other.estobjId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertu.vrts.data.EstObj[ estobjId=" + estobjId + " ]";
    }
    
}
