<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>

        <h1>To-Do:</h1>
        <form>
            <div>
                <input type="text" id="newItem" class="form-control w-50 d-inline" name="newItem"
                       placeholder="Enter new todo"/>
                <button formaction="additem" formmethod="post" type="submit" class="btn btn-outline-success ml-20 align-top">Add</button>
            </div>
            <table class="table table-striped mt-4">
                <c:forEach var="item" items="${requestScope.itemlist}">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.created}</td>
                        <td>
                            <button type="submit" formaction="completetask" formmethod="post" value="${item.itemID}" name="itemid" class="btn btn-outline-success btn-sm">Done</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <h1>Done:</h1>
        <form>
            <table class="table table-striped mt-4">
                <c:forEach var="item" items="${requestScope.donelist}">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.created}</td>
                        <td>
                            <button type="submit" formaction="undotask" formmethod="post" value="${item.itemID}" name="itemid" class="btn btn-outline-danger btn-sm">Undo</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>

    </jsp:body>

</t:pagetemplate>