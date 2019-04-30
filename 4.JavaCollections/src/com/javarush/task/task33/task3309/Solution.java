package com.javarush.task.task33.task3309;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        String result=null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);


            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new InputSource(new StringReader(writer.toString())));
            document.normalize();

            Node root = document.getDocumentElement();
            if(root.hasChildNodes()) {
                Queue<NodeList> queue = new ArrayDeque<>();
                queue.offer(root.getChildNodes());
                while (!queue.isEmpty()) {
                    NodeList list = queue.poll();
                    for (int i = 0; i<list.getLength(); i++){
                        Node node = list.item(i);
                        if(node.hasChildNodes()){
                            queue.offer(node.getChildNodes());
                        }

                        if(Node.TEXT_NODE == node.getNodeType()){
                            String text = node.getTextContent();
                            if(text.contains("<") || text.contains(">") || text.contains("&")
                                    || text.contains("\'") || text.contains("\"")){
                                Node parent = node.getParentNode();
                                parent.appendChild(document.createCDATASection(node.getTextContent()));
                                parent.removeChild(node);
                            }

                        }
                    }
                }

            }

            document.normalize();

            NodeList list = document.getElementsByTagName(tagName);
            for(int i = 0; i<list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeName().equals(tagName)) {
                    node.getParentNode().insertBefore(document.createComment(comment), node);
                }
            }
            document.normalize();

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter stringWriter = new StringWriter();
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            result = stringWriter.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
