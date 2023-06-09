<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1>Register new account</h1>
        <div class="row">
            <div>
                <form action="createnewuser" method="post">
                    <div class="form-outline mb-4">
                        <input type="username" name="username" class="form-control w-25" placeholder="Username"/>
                    </div>
                    <div class="form-outline mb-4">
                        <input type="password" name="password" class="form-control w-25" placeholder="Password"/>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block mb-4">Register</button>
                </form>
            </div>
        </div>
    </jsp:body>

</t:pagetemplate>