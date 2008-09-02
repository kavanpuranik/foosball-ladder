

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Change Ranking System</title>
    </head>
    <body>
        <div class="nav">
        	<span class="menuButton"><g:link class="list" action="list" controller="team">Home</g:link></span>
        </div>
        <div class="body">
            <h1>Change Ranking System</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <tbody>
                        <tr class="odd">
							<td>Current strategy:</td>
							<td>${currentStrategy.class.name.encodeAsHTML()}</td>
						</tr>
                    <g:each in="${strategies}" status="i" var="strategy">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        	<td>Alternate ${i+1}:</td>
                        	<td>
                        		${strategy.class.name.encodeAsHTML()}
                        		<g:form action="select" method="post" >
                					<input type="hidden" name="name" value="${strategy.class.name}" />
                					<div class="buttons">
					                    <span class="button"><g:actionSubmit class="save" value="Select" /></span>
					                </div>
                				</g:form>
                        	</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
