/*
 PureMVC AS3 Demo - Flex Employee Admin
 Copyright (c) 2007-10 Clifford Hall <clifford.hall@puremvc.org>
 Your reuse is governed by the Creative Commons Attribution 3.0 License
 */
package org.puremvc.as3.demos.flex.employeeadmin.controller
{
    import flash.utils.getQualifiedClassName;

    import mx.controls.Alert;

    import org.puremvc.as3.interfaces.INotification;
    import org.puremvc.as3.patterns.command.SimpleCommand;

    public class AddRoleResultCommand extends SimpleCommand
    {
        override public function execute(notification:INotification):void
        {
            trace(getQualifiedClassName(this) + ' :: execute');
            var result:Boolean = Boolean(notification.getBody());
            if (result == false)
            {
                Alert.show("Role already exists for this user!", "Add User Role");
            }
        }

    }
}
