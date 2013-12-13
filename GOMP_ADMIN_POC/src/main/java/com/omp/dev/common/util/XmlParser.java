package com.omp.dev.common.util;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.omp.commons.util.StringUtil;

public class XmlParser {
    
    private static String prNodeNm          = "";
    private static Map<String, String> list = null;
    
    public static Map viewXML(String strXml)  {
        
        try {
            list = new HashMap<String, String>();
            DocumentBuilderFactory dbf  = DocumentBuilderFactory.newInstance();
            DocumentBuilder        db   = dbf.newDocumentBuilder();
            Document               doc  = db.parse(new ByteArrayInputStream(strXml.getBytes()));
            Element                root = doc.getDocumentElement();             
            parseXML(root);
            return list;
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }

        return null;
    }
    
    public static void parseXML(Node node)
    {        
        int          i          = 0;
        String       chNodeNm   = "";
        NamedNodeMap v_attrList = null;
        Attr         v_attr     = null;
        Node         chNode     = null;
        //=====================================================//
        // Root Node Attribute Define
        //=====================================================//
        
        chNodeNm = node.getNodeName();
        
        if (node.hasAttributes()) {
            v_attrList = node.getAttributes();
            
            for (i = 0; i < v_attrList.getLength(); i ++)
            {
                v_attr = (Attr)v_attrList.item(i);
                list.put(chNodeNm + "_" + v_attr.getName(), v_attr.getValue());
                //System.out.println("//////////====> Node Name : " + chNodeNm + "_" + v_attr.getName() + "    Value = " + v_attr.getValue());
            }            
        }

        //=====================================================//
        // Child Node Attribute Define
        //=====================================================//        
        for(chNode = node.getFirstChild(); chNode != null; chNode = chNode.getNextSibling())
        {
            // Node Type이  Element 일때
            if (chNode.getNodeType() == 1 )
            {
                chNodeNm  = StringUtil.setTrim(chNode.getNodeName());
                prNodeNm  = chNodeNm; // 재귀호출 된 Node 에서 부모 Node의 Name을 참조할 수 있게 하기 위해.
                
                // Attribute 가 있을때.
                if (chNode.hasAttributes())
                {
                    v_attrList = chNode.getAttributes();
                    for (i = 0; i < v_attrList.getLength(); i ++)
                    {
                        v_attr = (Attr)v_attrList.item(i);
                        list.put(chNodeNm + "_" + v_attr.getName(), v_attr.getValue());                        
                        // System.out.println("====> Node Name : " + chNodeNm + "_" + v_attr.getName() + " value = " + v_attr.getValue());
                    }
                }
                // Attribute 가 없을때.
                else
                {
                    // System.out.println("====> Node Name : " + chNodeNm + "    Attrubute Name  : None");
                }
            }
            // 재귀호출시 테그 사이의 값을 읽어 올때 사용 ex) <AA>ABC</AA> 에서 재귀호출을 통해 ABC 를 읽어 옴.
            // Text값일때 상단에 있는것이 Name
            else if(chNode.getNodeType()==3)
            {
                chNodeNm = StringUtil.setTrim(chNode.getNodeValue());
                
                if (!chNodeNm.equals(""))
                {
                    list.put(prNodeNm, chNodeNm);
                    // System.out.println("====> Node Name : |" + prNodeNm + "|   Value : " + chNodeNm);
                }
            }
            
            // 하위 태그 또는 Text 정보를 읽기위해 재귀호출함.
            if(chNode.getNodeType() == Node.ELEMENT_NODE)
            {
                parseXML(chNode);
            }            
        }
    }
    
}
