package demo.mvc.flickr.controller
{
    import demo.mvc.flickr.view.ImagePanelMediator;
    import demo.mvc.flickr.view.SingleImageMediator;

    import org.puremvc.as3.interfaces.INotification;
    import org.puremvc.as3.patterns.command.SimpleCommand;

    public class PrepViewCommand extends SimpleCommand
    {
        override public function execute(note:INotification):void
        {
            trace('PrepViewCommand::execute');
            var app:FlickrAirApp = FlickrAirApp(note.getBody());
            facade.registerMediator(new ImagePanelMediator(app.imagePanel));
            facade.registerMediator(new SingleImageMediator(app.singleImage));
        }
    }
}
