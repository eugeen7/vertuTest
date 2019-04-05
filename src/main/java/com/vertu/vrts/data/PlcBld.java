/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.data;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SidorovEI
 */
@Entity
@Table(name = "plc_bld", catalog = "tbhw_db", schema = "vertu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlcBld.findAll", query = "SELECT p FROM PlcBld p")
    , @NamedQuery(name = "PlcBld.findByPlcbldId", query = "SELECT p FROM PlcBld p WHERE p.plcbldId = :plcbldId")
    , @NamedQuery(name = "PlcBld.findByVersnum", query = "SELECT p FROM PlcBld p WHERE p.versnum = :versnum")
    , @NamedQuery(name = "PlcBld.findByPlcVal", query = "SELECT p FROM PlcBld p WHERE p.plcVal = :plcVal")
    , @NamedQuery(name = "PlcBld.findByPlcKf", query = "SELECT p FROM PlcBld p WHERE p.plcKf = :plcKf")
    , @NamedQuery(name = "PlcBld.findByMaxVal", query = "SELECT p FROM PlcBld p WHERE p.maxVal = :maxVal")
    , @NamedQuery(name = "PlcBld.findByMinVal", query = "SELECT p FROM PlcBld p WHERE p.minVal = :minVal")})
public class PlcBld implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "plcBldGen", table = "GEN_ID", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VAL", pkColumnValue = "plcbld_pk", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "plcBldGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "plcbld_id", nullable = false)
    private Long plcbldId;
    @Version
    private Integer versnum;
    @Size(max = 100)
    @Column(name = "plc_val", length = 100)
    private String plcVal;
    @Min(value=1)
    @Column(name = "plc_kf", precision = 2, scale = 1)
    private BigDecimal plcKf;
    @Min(value=0)
    @Column(name = "max_val", precision = 10, scale = 1)
    private BigDecimal maxVal;
    @Min(value=0)
    @Column(name = "min_val", precision = 10, scale = 1)
    private BigDecimal minVal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plcbldFk")
    private Collection<InsContr> insContrCollection;

    public PlcBld() {
    }

    public PlcBld(Long plcbldId) {
        this.plcbldId = plcbldId;
    }

    public Long getPlcbldId() {
        return plcbldId;
    }

    public void setPlcbldId(Long plcbldId) {
        this.plcbldId = plcbldId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getPlcVal() {
        return plcVal;
    }

    public void setPlcVal(String plcVal) {
        this.plcVal = plcVal;
    }

    public BigDecimal getPlcKf() {
        return plcKf;
    }

    public void setPlcKf(BigDecimal plcKf) {
        this.plcKf = plcKf;
    }

    public BigDecimal getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(BigDecimal maxVal) {
        this.maxVal = maxVal;
    }

    public BigDecimal getMinVal() {
        return minVal;
    }

    public void setMinVal(BigDecimal minVal) {
        this.minVal = minVal;
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
        hash += (plcbldId != null ? plcbldId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlcBld)) {
            return false;
        }
        PlcBld other = (PlcBld) object;
        if ((this.plcbldId == null && other.plcbldId != null) || (this.plcbldId != null && !this.plcbldId.equals(other.plcbldId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertu.vrts.data.PlcBld[ plcbldId=" + plcbldId + " ]";
    }
    
}
