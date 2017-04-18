/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j.demoJavaXml.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import nux.xom.xquery.XQueryUtil;

/**
 *
 * @author LongChimNgan
 */
public class ProcessingXML {
    public ProcessingXML() {

    }

    public ArrayList readXML(String url) throws ParsingException, ValidityException, IOException {
        nu.xom.Document document = new nu.xom.Builder().build(new File(url + "Products.xml"));
        nu.xom.Nodes nodes = XQueryUtil.xquery(document, "//Products");
        ArrayList arrayList = new ArrayList();
        System.out.println(nodes.size());
        for (int i = 0; i < nodes.size(); i++) {
            Product pro = new Product();
            nu.xom.Node node = nodes.get(i);
            pro.setProductID(Integer.parseInt(node.getChild(1).getValue()));
            pro.setProductName(node.getChild(3).getValue());
            pro.setSupplierID(Integer.parseInt(node.getChild(5).getValue()));
            pro.setCategoryID(Integer.parseInt(node.getChild(7).getValue()));
            pro.setQuantityPerUnit(node.getChild(9).getValue());
            pro.setUnitPrice(Double.parseDouble(node.getChild(11).getValue()));
            pro.setUnitsInStock(Integer.parseInt(node.getChild(13).getValue()));
            pro.setUnitsOnOrder(Integer.parseInt(node.getChild(15).getValue()));
            pro.setReorderLevel(Integer.parseInt(node.getChild(17).getValue()));
            pro.setDiscontinued(Boolean.parseBoolean(node.getChild(19).getValue()));
            arrayList.add(pro);
        }

        return arrayList;
    }

    public ArrayList filtedContent(String url, String filter) throws ParsingException, ValidityException, IOException {
        nu.xom.Document document = new nu.xom.Builder().build(new File(url + "Products.xml"));
        nu.xom.Nodes nodes = XQueryUtil.xquery(document, "//Product[UnitPrice>" + filter + "]");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < nodes.size(); i++) {
            Product pro = new Product();
            nu.xom.Node node = nodes.get(i);
            pro.setProductID(Integer.parseInt(node.getChild(1).getValue()));
            pro.setProductName(node.getChild(3).getValue());
            pro.setSupplierID(Integer.parseInt(node.getChild(5).getValue()));
            pro.setCategoryID(Integer.parseInt(node.getChild(7).getValue()));
            pro.setQuantityPerUnit(node.getChild(9).getValue());
            pro.setUnitPrice(Double.parseDouble(node.getChild(11).getValue()));
            pro.setUnitsInStock(Integer.parseInt(node.getChild(13).getValue()));
            pro.setUnitsOnOrder(Integer.parseInt(node.getChild(15).getValue()));
            pro.setReorderLevel(Integer.parseInt(node.getChild(17).getValue()));
            pro.setDiscontinued(Boolean.parseBoolean(node.getChild(19).getValue()));
            arrayList.add(pro);
        }
        return arrayList;
    }
}
