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
@Table(name = "year_const", catalog = "tbhw_db", schema = "vertu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "YearConst.findAll", query = "SELECT y FROM YearConst y")
    , @NamedQuery(name = "YearConst.findByYcnstId", query = "SELECT y FROM YearConst y WHERE y.ycnstId = :ycnstId")
    , @NamedQuery(name = "YearConst.findByVersnum", query = "SELECT y FROM YearConst y WHERE y.versnum = :versnum")
    , @NamedQuery(name = "YearConst.findByConstPrd", query = "SELECT y FROM YearConst y WHERE y.constPrd = :constPrd")
    , @NamedQuery(name = "YearConst.findByYcntKf", query = "SELECT y FROM YearConst y WHERE y.ycntKf = :ycntKf")
    , @NamedQuery(name = "YearConst.findByMaxVal", query = "SELECT y FROM YearConst y WHERE y.maxVal = :maxVal")
    , @NamedQuery(name = "YearConst.findByMinVal", query = "SELECT y FROM YearConst y WHERE y.minVal = :minVal")})
public class YearConst implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "yearConstGen", table = "GEN_ID", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VAL", pkColumnValue = "yearcnst_pk", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "yearConstGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ycnst_id", nullable = false)
    private Long ycnstId;
    private Integer versnum;
    @Size(max = 100)
    @Column(name = "const_prd", length = 100)
    private String constPrd;
    @Min(value=1)
    @Column(name = "ycnt_kf", precision = 2, scale = 1)
    private BigDecimal ycntKf;
    @Column(name = "max_val")
    private Integer maxVal;
    @Column(name = "min_val")
    private Integer minVal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ycnstFk")
    private Collection<InsContr> insContrCollection;

    public YearConst() {
    }

    public YearConst(Long ycnstId) {
        this.ycnstId = ycnstId;
    }

    public Long getYcnstId() {
        return ycnstId;
    }

    public void setYcnstId(Long ycnstId) {
        this.ycnstId = ycnstId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getConstPrd() {
        return constPrd;
    }

    public void setConstPrd(String constPrd) {
        this.constPrd = constPrd;
    }

    public BigDecimal getYcntKf() {
        return ycntKf;
    }

    public void setYcntKf(BigDecimal ycntKf) {
        this.ycntKf = ycntKf;
    }

    public Integer getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Integer maxVal) {
        this.maxVal = maxVal;
    }

    public Integer getMinVal() {
        return minVal;
    }

    public void setMinVal(Integer minVal) {
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
        hash += (ycnstId != null ? ycnstId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof YearConst)) {
            return false;
        }
        YearConst other = (YearConst) object;
        if ((this.ycnstId == null && other.ycnstId != null) || (this.ycnstId != null && !this.ycnstId.equals(other.ycnstId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertu.vrts.data.YearConst[ ycnstId=" + ycnstId + " ]";
    }
    
}
