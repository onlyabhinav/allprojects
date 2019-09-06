package demo.mvc.flickr.controller
{
    import demo.mvc.flickr.model.FlickrProxy;
    import demo.mvc.flickr.view.ImagePanelMediator;

    import org.puremvc.as3.interfaces.ICommand;
    import org.puremvc.as3.interfaces.INotification;
    import org.puremvc.as3.patterns.command.MacroCommand;

    public class StartupCommand extends MacroCommand implements ICommand
    {
        override protected function initializeMacroCommand():void
        {
            this.addSubCommand(PrepModelCommand);
            this.addSubCommand(PrepViewCommand);
        }
    }
}
