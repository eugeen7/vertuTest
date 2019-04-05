/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SidorovEI
 */
@Entity
@Table(name = "ins_contr", catalog = "tbhw_db", schema = "vertu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InsContr.findAll", query = "SELECT i FROM InsContr i")
    , @NamedQuery(name = "InsContr.findByInscntrId", query = "SELECT i FROM InsContr i WHERE i.inscntrId = :inscntrId")
    , @NamedQuery(name = "InsContr.findByVersnum", query = "SELECT i FROM InsContr i WHERE i.versnum = :versnum")
    , @NamedQuery(name = "InsContr.findByInsNum", query = "SELECT i FROM InsContr i WHERE i.insNum = :insNum")
    , @NamedQuery(name = "InsContr.findByInsSrNum", query = "SELECT i FROM InsContr i WHERE i.insSrNum = :insSrNum")
    , @NamedQuery(name = "InsContr.findByCntrDate", query = "SELECT i FROM InsContr i WHERE i.cntrDate = :cntrDate")
    , @NamedQuery(name = "InsContr.findByCntrDateStrt", query = "SELECT i FROM InsContr i WHERE i.cntrDateStrt = :cntrDateStrt")
    , @NamedQuery(name = "InsContr.findByCntrDateEnd", query = "SELECT i FROM InsContr i WHERE i.cntrDateEnd = :cntrDateEnd")
    , @NamedQuery(name = "InsContr.findByCntrDateCalc", query = "SELECT i FROM InsContr i WHERE i.cntrDateCalc = :cntrDateCalc")
    , @NamedQuery(name = "InsContr.findByInsSum", query = "SELECT i FROM InsContr i WHERE i.insSum = :insSum")
    , @NamedQuery(name = "InsContr.findByInsBonus", query = "SELECT i FROM InsContr i WHERE i.insBonus = :insBonus")
    , @NamedQuery(name = "InsContr.findByCntrCmnt", query = "SELECT i FROM InsContr i WHERE i.cntrCmnt = :cntrCmnt")})
public class InsContr implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "insCntrGen", table = "GEN_ID", pkColumnName = "ID_NAME",
            valueColumnName = "ID_VAL", pkColumnValue = "inscntr_pk", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "insCntrGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "inscntr_id", nullable = false)
    private Long inscntrId;
    @Version
    private Integer versnum;
    @Column(name = "ins_num")
    private Integer insNum;
    @Size(max = 20)
    @Column(name = "ins_sr_num", length = 20)
    private String insSrNum;
    @Column(name = "cntr_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cntrDate;
    @Column(name = "cntr_date_strt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cntrDateStrt;
    @Column(name = "cntr_date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cntrDateEnd;
    @Column(name = "cntr_date_calc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cntrDateCalc;
    @Min(value = 0)
    @Column(name = "ins_sum", precision = 12, scale = 2)
    private BigDecimal insSum;
    @Min(value = 0)
    @Column(name = "ins_bonus", precision = 12, scale = 2)
    private BigDecimal insBonus;
    @Size(max = 2048)
    @Column(name = "cntr_cmnt", length = 2048)
    private String cntrCmnt;
    @Column(name = "ycnst_val")
    private Integer ycnstVal;
    @Column(name = "plcbld_val")
    private BigDecimal plcbldVal;
    @JoinColumn(name = "estobj_fk", referencedColumnName = "estobj_id", nullable = false)
    @ManyToOne(optional = false)
    private EstObj estobjFk;
    @JoinColumn(name = "plcbld_fk", referencedColumnName = "plcbld_id", nullable = false)
    @ManyToOne(optional = false)
    private PlcBld plcbldFk;
    @JoinColumn(name = "plchldr_fk", referencedColumnName = "plchldr_id", nullable = false)
    @ManyToOne(optional = false)
    private PlcHldr plchldrFk;
    @JoinColumn(name = "prptp_fk", referencedColumnName = "prptp_id", nullable = false)
    @ManyToOne(optional = false)
    private PrptType prptpFk;
    @JoinColumn(name = "ycnst_fk", referencedColumnName = "ycnst_id", nullable = false)
    @ManyToOne(optional = false)
    private YearConst ycnstFk;

    public InsContr() {
        insSum = new BigDecimal(BigInteger.ZERO);
        insBonus = new BigDecimal(BigInteger.ZERO);
    }

    public InsContr(Long inscntrId) {
        this.inscntrId = inscntrId;
    }

    public Long getInscntrId() {
        return inscntrId;
    }

    public void setInscntrId(Long inscntrId) {
        this.inscntrId = inscntrId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public Integer getInsNum() {
        return insNum;
    }

    public void setInsNum(Integer insNum) {
        this.insNum = insNum;
    }

    public String getInsSrNum() {
        return insSrNum;
    }

    public void setInsSrNum(String insSrNum) {
        this.insSrNum = insSrNum;
    }

    public Date getCntrDate() {
        return cntrDate;
    }

    public void setCntrDate(Date cntrDate) {
        this.cntrDate = cntrDate;
    }

    public Date getCntrDateStrt() {
        return cntrDateStrt;
    }

    public void setCntrDateStrt(Date cntrDateStrt) {
        this.cntrDateStrt = cntrDateStrt;
    }

    public Date getCntrDateEnd() {
        return cntrDateEnd;
    }

    public void setCntrDateEnd(Date cntrDateEnd) {
        this.cntrDateEnd = cntrDateEnd;
    }

    public Date getCntrDateCalc() {
        return cntrDateCalc;
    }

    public void setCntrDateCalc(Date cntrDateCalc) {
        this.cntrDateCalc = cntrDateCalc;
    }

    public BigDecimal getInsSum() {
        return insSum;
    }

    public void setInsSum(BigDecimal insSum) {
        this.insSum = insSum;
    }

    public BigDecimal getInsBonus() {
        return insBonus;
    }

    public void setInsBonus(BigDecimal insBonus) {
        this.insBonus = insBonus;
    }

    public String getCntrCmnt() {
        return cntrCmnt;
    }

    public void setCntrCmnt(String cntrCmnt) {
        this.cntrCmnt = cntrCmnt;
    }

    public Integer getYcnstVal() {
        return ycnstVal;
    }

    public void setYcnstVal(Integer ycnstVal) {
        this.ycnstVal = ycnstVal;
    }

    public BigDecimal getPlcbldVal() {
        return plcbldVal;
    }

    public void setPlcbldVal(BigDecimal plcbldVal) {
        this.plcbldVal = plcbldVal;
    }

    public EstObj getEstobjFk() {
        return estobjFk;
    }

    public void setEstobjFk(EstObj estobjFk) {
        this.estobjFk = estobjFk;
    }

    public PlcBld getPlcbldFk() {
        return plcbldFk;
    }

    public void setPlcbldFk(PlcBld plcbldFk) {
        this.plcbldFk = plcbldFk;
    }

    public PlcHldr getPlchldrFk() {
        return plchldrFk;
    }

    public void setPlchldrFk(PlcHldr plchldrFk) {
        this.plchldrFk = plchldrFk;
    }

    public PrptType getPrptpFk() {
        return prptpFk;
    }

    public void setPrptpFk(PrptType prptpFk) {
        this.prptpFk = prptpFk;
    }

    public YearConst getYcnstFk() {
        return ycnstFk;
    }

    public void setYcnstFk(YearConst ycnstFk) {
        this.ycnstFk = ycnstFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inscntrId != null ? inscntrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsContr)) {
            return false;
        }
        InsContr other = (InsContr) object;
        if ((this.inscntrId == null && other.inscntrId != null) || (this.inscntrId != null && !this.inscntrId.equals(other.inscntrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vertu.vrts.data.InsContr[ inscntrId=" + inscntrId + " ]";
    }

}
