package com.vertu.vrts.data;

import com.vertu.vrts.data.EstObj;
import com.vertu.vrts.data.PlcBld;
import com.vertu.vrts.data.PlcHldr;
import com.vertu.vrts.data.PrptType;
import com.vertu.vrts.data.YearConst;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-05T10:49:18")
@StaticMetamodel(InsContr.class)
public class InsContr_ { 

    public static volatile SingularAttribute<InsContr, Long> inscntrId;
    public static volatile SingularAttribute<InsContr, Integer> versnum;
    public static volatile SingularAttribute<InsContr, Date> cntrDateEnd;
    public static volatile SingularAttribute<InsContr, String> cntrCmnt;
    public static volatile SingularAttribute<InsContr, Integer> insNum;
    public static volatile SingularAttribute<InsContr, EstObj> estobjFk;
    public static volatile SingularAttribute<InsContr, Date> cntrDateCalc;
    public static volatile SingularAttribute<InsContr, Integer> ycnstVal;
    public static volatile SingularAttribute<InsContr, PrptType> prptpFk;
    public static volatile SingularAttribute<InsContr, Date> cntrDate;
    public static volatile SingularAttribute<InsContr, BigDecimal> insSum;
    public static volatile SingularAttribute<InsContr, BigDecimal> insBonus;
    public static volatile SingularAttribute<InsContr, PlcHldr> plchldrFk;
    public static volatile SingularAttribute<InsContr, YearConst> ycnstFk;
    public static volatile SingularAttribute<InsContr, PlcBld> plcbldFk;
    public static volatile SingularAttribute<InsContr, String> insSrNum;
    public static volatile SingularAttribute<InsContr, Date> cntrDateStrt;
    public static volatile SingularAttribute<InsContr, BigDecimal> plcbldVal;

}