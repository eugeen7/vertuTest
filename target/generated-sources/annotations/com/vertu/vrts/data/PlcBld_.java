package com.vertu.vrts.data;

import com.vertu.vrts.data.InsContr;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-05T10:49:18")
@StaticMetamodel(PlcBld.class)
public class PlcBld_ { 

    public static volatile SingularAttribute<PlcBld, Integer> versnum;
    public static volatile SingularAttribute<PlcBld, BigDecimal> maxVal;
    public static volatile SingularAttribute<PlcBld, String> plcVal;
    public static volatile SingularAttribute<PlcBld, BigDecimal> minVal;
    public static volatile SingularAttribute<PlcBld, Long> plcbldId;
    public static volatile CollectionAttribute<PlcBld, InsContr> insContrCollection;
    public static volatile SingularAttribute<PlcBld, BigDecimal> plcKf;

}