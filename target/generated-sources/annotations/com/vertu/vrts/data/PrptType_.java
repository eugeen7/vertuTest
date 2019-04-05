package com.vertu.vrts.data;

import com.vertu.vrts.data.InsContr;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-05T10:49:18")
@StaticMetamodel(PrptType.class)
public class PrptType_ { 

    public static volatile SingularAttribute<PrptType, Integer> versnum;
    public static volatile SingularAttribute<PrptType, Long> prptpId;
    public static volatile SingularAttribute<PrptType, BigDecimal> prpKf;
    public static volatile SingularAttribute<PrptType, String> prpName;
    public static volatile CollectionAttribute<PrptType, InsContr> insContrCollection;

}