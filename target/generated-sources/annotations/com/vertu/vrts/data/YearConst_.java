package com.vertu.vrts.data;

import com.vertu.vrts.data.InsContr;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-05T10:49:18")
@StaticMetamodel(YearConst.class)
public class YearConst_ { 

    public static volatile SingularAttribute<YearConst, Integer> versnum;
    public static volatile SingularAttribute<YearConst, Integer> maxVal;
    public static volatile SingularAttribute<YearConst, Integer> minVal;
    public static volatile SingularAttribute<YearConst, Long> ycnstId;
    public static volatile SingularAttribute<YearConst, String> constPrd;
    public static volatile SingularAttribute<YearConst, BigDecimal> ycntKf;
    public static volatile CollectionAttribute<YearConst, InsContr> insContrCollection;

}