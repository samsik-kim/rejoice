<%@page contentType="text/html;charset=euc-kr" %>
<html>
    <head><title>JSP Page</title></head>
    ��<body>
    <%
    request.setCharacterEncoding("euc-kr");

    String _resource = request.getParameter("resource");
    String _type = request.getParameter("type");
    %>
    <form action="resource.jsp" method="get">
        ���ҽ� Ǯ ���� :
            <input name="resource" type="text" size="40" value="<%= _resource != null ? _resource : "" %>"><br>
        <input type="radio" name="type" value="class" checked="true">Ŭ����
        <input type="radio" name="type" value="file">����<br>
        Ŭ������ "java.lang.String" �� ���� ����, ������ "/resources/ApplicationMessages.properties"�� ����
        �������� �����Ѵ�.<br>
        <input type="submit">
    </form>
    
    <%
        String path = null;
        
        if (_resource != null) {
            ResourceLocation rl = new ResourceLocation();

            if ("class".equals(_type)) {
                path = rl.getResourceURL(_resource, true);
            } else {
                path = rl.getResourceURL(_resource, false);
            }
    %>
    * ��û ���ҽ� : <%= _resource %><br>
    * ��û Ÿ�� : <%= _type %><br>
    * ���� ���ҽ� ��� : <%= path %>
    <%
        }
    %>
    </body>
</html>

<%!
/**
 * <p>
 * Java Class Ȥ�� ���ҽ�(Ŭ�����н� ���� �ִ� ����)�� ��ġ�� ��Ȯ�� �˷��ش�.
 * </p>
 * 
 * @author Son KwonNam(kwon37xi@yahoo.co.kr)
 */
private static class ResourceLocation {

    /**
     * ��Ű������ ������ Ŭ���� Ǯ ������ ���ҽ� ���(/package/classname.class)�� �ٲ��ش�.
     * 
     * @param classFullName
     *            Ŭ���� Ǯ ����(package.classname)
     * @return ���ҽ� ��� (/package/classname.class)
     */
    public String classFullNameToResourcePath(String classFullName) {
        String resourcePath = classFullName.replace('.', '/').trim();
        resourcePath = "/" + resourcePath + ".class";
        return resourcePath;
    }

    /**
     * Ŭ���� Ǯ ������ �޾Ƽ� ���� ������ ��ġ�� URL�� �����Ѵ�.
     * 
     * @param classFullName
     *            Ŭ������ Ǯ ����
     * @return Ŭ���� ������ URL
     */
    public String getResourceURL(String classFullName) {
        return getResourceURL(classFullName, true);
    }

    /**
     * Ŭ���� Ȥ�� ���ҽ��� �̸��� �޾Ƽ� ���� ������ ��ġ�� URL�� �����Ѵ�.
     * 
     * @param resource
     *            ���ҽ� �̸�
     * @param isClass
     *            Ŭ�������� ����
     * @return ���ҽ��� URL
     */
    public String getResourceURL(String resource, boolean isClass) {
        String refinedResource = null;

        // "/dir1/dir2/resource.ext" ���·� �ٲ۴�.
        if (isClass) {
            refinedResource = classFullNameToResourcePath(resource);
        } else if (!resource.startsWith("/")) {
            refinedResource = "/" + resource.trim();
        } else {
            refinedResource = resource.trim();
        }

        System.out.println("�˻��� ���ҽ� : " + refinedResource);

        java.net.URL resourceUrl = ResourceLocation.class
                .getResource(refinedResource);

        if (resourceUrl == null) {
            return null;
        }

        return resourceUrl.getFile();
    }
}
%>
