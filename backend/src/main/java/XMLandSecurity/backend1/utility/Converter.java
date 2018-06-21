package XMLandSecurity.backend1.utility;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Dejan Stojkic (Smek) on 6/21/2018.
 */
public class Converter {

    public Converter(){}

    public static XMLGregorianCalendar vracaXmlDate(Date daatiDatum) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(daatiDatum);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

        return date2;
    }

}
