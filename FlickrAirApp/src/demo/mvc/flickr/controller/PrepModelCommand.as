package demo.mvc.flickr.controller
{
    import demo.mvc.flickr.model.FlickrProxy;

    import org.puremvc.as3.interfaces.INotification;
    import org.puremvc.as3.patterns.command.SimpleCommand;

    public class PrepModelCommand extends SimpleCommand
    {
        override public function execute(note:INotification):void
        {
            var flickrProxy:FlickrProxy = new FlickrProxy();

            facade.registerProxy(flickrProxy);

            flickrProxy.getImages();

        }
    }
}
