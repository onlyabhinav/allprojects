/*
 PureMVC AS3 Demo - Flex Employee Admin
 Copyright (c) 2007-10 Clifford Hall <clifford.hall@puremvc.org>
 Your reuse is governed by the Creative Commons Attribution 3.0 License
 */
package org.puremvc.as3.demos.flex.employeeadmin.controller
{
    import org.puremvc.as3.demos.flex.employeeadmin.view.RolePanelMediator;
    import org.puremvc.as3.demos.flex.employeeadmin.view.UserFormMediator;
    import org.puremvc.as3.demos.flex.employeeadmin.view.UserListMediator;
    import org.puremvc.as3.interfaces.INotification;
    import org.puremvc.as3.patterns.command.SimpleCommand;

    public class PrepViewCommand extends SimpleCommand
    {
        /**
         * Prepare the View.
         *
         * Get the View Components for the Mediators from the app,
         * a reference to which was passed on the original startup
         * notification.
         */
        override public function execute(note:INotification):void
        {
            trace('PrepViewCommand::execute');
            var app:EmployeeAdmin = EmployeeAdmin(note.getBody());
            facade.registerMediator(new UserFormMediator(app.userForm));
            facade.registerMediator(new UserListMediator(app.userList));
            facade.registerMediator(new RolePanelMediator(app.rolePanel));
        }
    }
}
