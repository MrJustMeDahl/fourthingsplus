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
                <button formaction="additem" formmethod="post" type="submit"
                        class="btn btn-outline-success ml-20 align-top">Add
                </button>
            </div>
            <table class="table table-striped mt-4">
                <c:forEach var="item" items="${requestScope.itemlist}">
                    <c:choose>
                        <c:when test="${item.itemID != requestScope.currentTask}">
                            <tr>
                                <td>${item.name}</td>
                                <td>${item.created}</td>
                                <td>
                                    <button type="submit" formaction="completetask" formmethod="post"
                                            value="${item.itemID}" name="itemid" class="btn btn-outline-success btn-sm">
                                        Done
                                    </button>
                                    <button type="submit" formaction="edittask" formmethod="get" value="${item.itemID}"
                                            name="itemid" class="btn btn-outline-warning btn-sm">Edit
                                    </button>
                                    <button type="submit" formaction="deletetask" formmethod="post" name="deletetask"
                                            value="${item.itemID}" class="btn btn-outline-danger btn-sm">Remove
                                    </button>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td><input type="text" id="editedTask" name="editedTask"
                                           class="form-control d-inline" placeholder="${item.name}"/></td>
                                <td>
                                    <button type="submit" formaction="edittask" formmethod="post" value="${item.itemID}"
                                            name="itemid" class="btn btn-outline-primary btn-sm">Save changes
                                    </button>
                                </td>
                                <td>
                                    <button type="submit" formaction="completetask" formmethod="post"
                                            value="${item.itemID}" name="itemid" class="btn btn-outline-success btn-sm">
                                        Done
                                    </button>
                                    <button type="submit" formaction="deletetask" formmethod="post" name="deletetask"
                                            value="${item.itemID}" class="btn btn-outline-danger btn-sm">Remove</button>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
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
                            <button type="submit" formaction="undotask" formmethod="post" value="${item.itemID}"
                                    name="itemid" class="btn btn-outline-danger btn-sm">Undo
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>

    </jsp:body>

</t:pagetemplate>