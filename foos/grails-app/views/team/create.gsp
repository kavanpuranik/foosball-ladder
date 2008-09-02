

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Team</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link controller="player" class="list" action="list">Player List</g:link></span>
            <span class="menuButton"><g:link controller="player" class="create" action="create">Add New Player</g:link></span>
        </div>
        <div class="body">
            <h1>Create Team</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${team}">
            <div class="errors">
                <g:renderErrors bean="${team}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                         	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Team Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:team,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:team,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="playerOne">Player One:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:team,field:'playerOne','errors')}">
                                    <g:select optionKey="id" from="${Player.list()}" name="playerOne.id" value="${team?.playerOne?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="playerTwo">Player Two:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:team,field:'playerTwo','errors')}">
                                    <g:select optionKey="id" from="${Player.list()}" name="playerTwo.id" value="${team?.playerTwo?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rank"></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:team,field:'rank','errors')}">
                                    <input type="hidden" id="rank" name="rank" value="9999" />
                                </td>
                            </tr>  
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
