<%@page import="doan.model.test_Product"%>
<%@page import="doan.model.test_Products"%>
<%@page import="java.util.List"%>


<h1>Products</h1>
<table border="1">
	<tr>
		<td>ma sp</td>
		<td>ten sp</td>
		<td>gia</td>
	</tr>
	<%
	test_Products listsp = new test_Products();
	List<test_Product> list = listsp.showProduct("");
	for (test_Product sp : list) {
		out.print("<form action=\"/doan/ControllerCarBean\">");
		out.print("<tr>" + "<td>" + sp.getProductID() + "</td>" + "<td>" + sp.getProductName() + "</td>" + "<td>"
		+ sp.getPrice() + "</td>" + "<td>" + "<input type=\"submit\" value=\"Add to Cart\" name=\"action\" >"
		+ "</td>" + "<input type=\"hidden\" value='" + sp.getProductID() + "' name=\"txtid\" >"
		+ "<input type=\"hidden\" value='" + sp.getProductName() + "' name=\"txtname\" >"
		+ "<input type=\"hidden\" value='" + sp.getPrice() + "' name=\"txtPrice\" >" + "</tr>");
		out.print("</form>");
	}
	%>

</table>
<form action="ControllerCarBean">
	<input type="submit" value="View Cart" name="action" />
</form>
