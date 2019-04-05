package com.vertu.vrts.data;

import com.vertu.vrts.data.InsContr;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-05T10:49:18")
@StaticMetamodel(PlcHldr.class)
public class PlcHldr_ { 

    public static volatile SingularAttribute<PlcHldr, String> secondname;
    public static volatile SingularAttribute<PlcHldr, Date> birthday;
    public static volatile SingularAttribute<PlcHldr, String> firstname;
    public static volatile SingularAttribute<PlcHldr, Integer> versnum;
    public static volatile SingularAttribute<PlcHldr, Long> plchldrId;
    public static volatile SingularAttribute<PlcHldr, Integer> psprtSrl;
    public static volatile SingularAttribute<PlcHldr, String> surename;
    public static volatile CollectionAttribute<PlcHldr, InsContr> insContrCollection;
    public static volatile SingularAttribute<PlcHldr, Integer> psprtNum;
    public static volatile SingularAttribute<PlcHldr, String> fio;

}