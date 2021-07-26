package svg;

import cn.hutool.core.map.MapUtil;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * svg解析测试类
 */
public class SvgTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\F\\Work\\svg\\1.svg");
        if(file.exists()){
            System.out.println("yes");
        }
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        Document doc = f.createDocument(file.toURI().toString());
        NodeList text = doc.getElementsByTagName("text");
        int length = text.getLength();
        for(int i = 0 ; i <length;i++){
            Node item = text.item(i);
            NamedNodeMap attributes = item.getAttributes();
            Node id = attributes.getNamedItem("id");
            String nodeValue = id.getNodeValue();
            System.out.println(nodeValue);
        }
    }
}
