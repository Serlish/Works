package TX;

import Model.Model;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class TransformXml {


    public TransformXml() {
    }

    public Model fromXmlToObject(String xmlStr) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Model.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlStr);
            Model model = (Model) unmarshaller.unmarshal(reader);
            return model;
        } catch (JAXBException e) {
        }
        return null;
    }


}
