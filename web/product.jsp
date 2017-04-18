<%-- 
    Document   : product
    Created on : Apr 15, 2017, 9:58:08 AM
    Author     : LongChimNgan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml-stylesheet type="text/xsl" href="product.xsl"?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
            <c:forEach var="product" items="${products}">
                <Product>
                        <ProductID>${product.productID}</ProductID>
                        <ProductName>${product.productName}</ProductName>
                        <SupplierID>${product.supplierID}</SupplierID>
                        <CategoryID>${product.categoryID}</CategoryID>
                        <QuantityPerUnit>${product.quantityPerUnit}</QuantityPerUnit>
                        <UnitPrice>${product.unitPrice}</UnitPrice>
                        <UnitsInStock>${product.unitsInStock}</UnitsInStock>
                        <UnitsOnOrder>${product.unitsOnOrder}</UnitsOnOrder>
                        <ReorderLevel>${product.reorderLevel}</ReorderLevel>
                        <Discontinued>${product.discontinued}</Discontinued>
                </Product>
            </c:forEach>
</body>
</html>
